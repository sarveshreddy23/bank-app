/**
 * 
 */
package org.example.cards.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import org.example.cards.model.Cards;
import org.example.cards.model.Customer;
import org.example.cards.repository.CardsRepository;


@RestController
@Slf4j
public class CardsController {

	@Autowired
	private CardsRepository cardsRepository;

	@PostMapping("/myCards")
	public List<Cards> getCardDetails(@RequestHeader String correlationId, @RequestBody Customer customer) {
		log.info("getCardDetails metho started");
		List<Cards> cards = cardsRepository.findByCustomerId(customer.getCustomerId());
		log.info("getCardDetails metho end");

		if (cards != null) {
			return cards;
		} else {
			return null;
		}

	}

}
