/**
 * 
 */
package org.example.accounts.controller;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.example.accounts.clients.CardsFeignClient;
import org.example.accounts.clients.LoansFeignClient;
import org.example.accounts.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.example.accounts.repository.AccountsRepository;

import java.util.List;



@RestController
@Slf4j
public class AccountsController {
	
	@Autowired
	private AccountsRepository accountsRepository;

	@Autowired
	CardsFeignClient cardsFeignClient;

	@Autowired
	LoansFeignClient loansFeignClient;

	@Value("${accounts.msg}")
	private String msg;

	@GetMapping("/accountMsg")
	public String getAccountMessage(){
		return msg;
	}

	@PostMapping("/myAccount")
	public Accounts getAccountDetails(@RequestBody Customer customer) {

		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
		if (accounts != null) {
			return accounts;
		} else {
			return null;
		}

	}



	@PostMapping("/myCustomerDetails")
	@CircuitBreaker(name = "myCustomerServiceCircuitBreaker", fallbackMethod = "myCustomerDetailsFallBack")
//	@Retry(name = "myCustomerServiceRetry", fallbackMethod = "myCustomerDetailsFallBack")
	public CustomerDetails getCustomerDetails(@RequestHeader("eazybank-correlation-id") String correlationId, @RequestBody Customer customer) {
		log.info("getCustomerDetails metho starte");
		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
		List<Loans> loans = loansFeignClient.getLoansDetails(correlationId, customer);
		List<Cards> cards = cardsFeignClient.getCardDetails(correlationId, customer);

		CustomerDetails customerDetails = new CustomerDetails();

		if (accounts != null) {
			customerDetails.setAccounts(accounts);
		}
		customerDetails.setLoans(loans);
		customerDetails.setCards(cards);
		log.info("getCustomerDetails metho ended");
		return customerDetails;
	}


	private CustomerDetails myCustomerDetailsFallBack(@RequestHeader String correlationId, Customer customer, Throwable t) {
		log.info("myCustomerDetailsFallBack metho started");
		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
		List<Loans> loans = loansFeignClient.getLoansDetails(correlationId, customer);
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setAccounts(accounts);
		customerDetails.setLoans(loans);
		log.info("myCustomerDetailsFallBack metho ended");
		return customerDetails;

	}

}
