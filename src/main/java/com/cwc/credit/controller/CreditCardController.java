package com.cwc.credit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwc.credit.payload.CreditCardRequest;
import com.cwc.credit.payload.CreditCardResponse;
import com.cwc.credit.service.CreditCardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/card")
@Slf4j
@RequiredArgsConstructor
public class CreditCardController {

	@Autowired
	private final CreditCardService creditCardService;

	@PostMapping
	public ResponseEntity<List<CreditCardResponse>> addCardDetails(@RequestBody CreditCardRequest creditCardRequest) {
		int numbers = creditCardRequest.getCardCount();

		
		List<CreditCardResponse> addCardDetails = this.creditCardService.addCardDetails(creditCardRequest, numbers);
		log.info("Credit card details saved {}", addCardDetails);
		return new ResponseEntity<List<CreditCardResponse>>(addCardDetails, HttpStatus.CREATED);
	}

	
	@GetMapping("/latest")
    public ResponseEntity<List<CreditCardResponse>> getLatestCreditCards() {
		List<CreditCardResponse> latestCards = this.creditCardService.getLatestCards();
        return new ResponseEntity<>(latestCards,HttpStatus.OK);
    }
	
	@GetMapping("/all")
    public ResponseEntity<List<CreditCardResponse>> allCards() {
		List<CreditCardResponse> allCards = this.creditCardService.getAllCards();
        return new ResponseEntity<>(allCards,HttpStatus.OK);
    }
}
