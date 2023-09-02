/**
 * 
 */
package org.example.loans.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import org.example.loans.model.Customer;
import org.example.loans.model.Loans;
import org.example.loans.repository.LoansRepository;


@RestController
@Slf4j
public class LoansController {

	@Autowired
	private LoansRepository loansRepository;

	@PostMapping("/myLoans")
	public List<Loans> getLoansDetails(@RequestHeader String correlationId, @RequestBody Customer customer) {
		log.info("getLoansDetails metho started");
		List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
		log.info("getLoansDetails metho end");
		if (loans != null) {
			return loans;
		} else {
			return null;
		}

	}

}
