package com.cwc.credit.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwc.credit.model.CreditCard;
import com.cwc.credit.model.enums.Company;
import com.cwc.credit.payload.CreditCardRequest;
import com.cwc.credit.payload.CreditCardResponse;
import com.cwc.credit.repository.CreditCardRepository;
import com.cwc.credit.service.CreditCardService;
import com.cwc.credit.utilities.RandomCVVNumber;
import com.cwc.credit.utilities.RandomCreditCardNumber;
import com.cwc.credit.utilities.RandomDate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {

	@Autowired
	private final CreditCardRepository creditCardRepository;

	@Override
	public List<CreditCardResponse> addCardDetails(CreditCardRequest creditCardRequest, int numbers) {
		List<CreditCardResponse> responseList = new ArrayList<>();

		for (int i = 0; i < numbers; i++) {
			// get company name here
			String companyName = creditCardRequest.getCompany().toString(); 
			// generate random card number
			String cardNumber = RandomCreditCardNumber.generateRandomCreditCardNumber(companyName);
			log.info(cardNumber);
			creditCardRequest.setCardNumber(cardNumber);

			// generate random CVV number
			String cvv = RandomCVVNumber.generateRandomCVV();
			creditCardRequest.setCvv(cvv);

			String expirationDate = RandomDate.generateDate();

			creditCardRequest.setExpiryDate(expirationDate);

			// here

			CreditCard creditCard = CreditCard.builder().cardholder(creditCardRequest.getCardholder())
					.cardholder(creditCardRequest.getCardholder() + (i + 1)) 
					.cardNumber(creditCardRequest.getCardNumber())
					.company(creditCardRequest.getCompany())
					.bank(creditCardRequest.getBank())
					.cvv(creditCardRequest.getCvv())//
					.expiryDate(creditCardRequest.getExpiryDate())//
					.build();
			 log.info("Saved data inside database: {}", creditCard);
			this.creditCardRepository.save(creditCard);
			 responseList.add(MapToResponse(creditCard));

		}
		return responseList;
	}
	
	
	@Override
	public List<CreditCardResponse> getAllCards() {
		List<CreditCard> list = this.creditCardRepository.findAll();
		List<CreditCardResponse> list2 = list.stream().map(cards -> MapToResponse(cards)).toList();
		return list2;
	}
	
	public List<CreditCardResponse> getLatestCards() {
        List<CreditCard> latestCreditCards = creditCardRepository.findTop5ByOrderByIdDesc();
        return convertToCreditCardResponses(latestCreditCards);
    }

    private List<CreditCardResponse> convertToCreditCardResponses(List<CreditCard> creditCards) {
        return creditCards.stream()
                .map(this::MapToResponse)
                .collect(Collectors.toList());
    }


	private CreditCardResponse MapToResponse(CreditCard creditCard) {
		CreditCardResponse cardResponse = CreditCardResponse.builder().cardholder(creditCard.getCardholder())
				.cardNumber(creditCard.getCardNumber()).company(creditCard.getCompany()).bank(creditCard.getBank())
				.cvv(creditCard.getCvv()).expiryDate(creditCard.getExpiryDate()).build();
		return cardResponse;
	}



	
}
