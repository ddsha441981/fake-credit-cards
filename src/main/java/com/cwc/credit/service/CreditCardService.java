package com.cwc.credit.service;

import java.util.List;

import com.cwc.credit.payload.CreditCardRequest;
import com.cwc.credit.payload.CreditCardResponse;

public interface CreditCardService {
	
	public List<CreditCardResponse> addCardDetails(CreditCardRequest creditCardRequest,int numbers);
	
	public List<CreditCardResponse> getLatestCards();
	public List<CreditCardResponse> getAllCards();
	

}
