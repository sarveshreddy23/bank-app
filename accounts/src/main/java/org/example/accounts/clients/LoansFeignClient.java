package org.example.accounts.clients;

import org.example.accounts.model.Cards;
import org.example.accounts.model.Customer;
import org.example.accounts.model.Loans;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("loans")
public interface LoansFeignClient {

    @RequestMapping(method = RequestMethod.POST, path="myLoans", consumes = "application/json")
    List<Loans> getLoansDetails(@RequestHeader String correlationId, @RequestBody Customer customer);
}
