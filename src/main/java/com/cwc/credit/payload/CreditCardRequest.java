package com.cwc.credit.payload;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Field;

import com.cwc.credit.model.CreditCard;
import com.cwc.credit.model.enums.Bank;
import com.cwc.credit.model.enums.Company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class CreditCardRequest {

	private String cardholder;
	private int cardCount;
	private String cardNumber;
	@Field("company")
	private Company company;
	@Field("bank")
	private Bank bank;
	private String expiryDate;
	private String cvv;
}
