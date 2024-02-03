package com.cwc.credit.model;

import org.springframework.data.annotation.Id;
import com.cwc.credit.model.enums.Bank;
import com.cwc.credit.model.enums.Company;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Builder
@ToString
//@Document(collection = "credit_card")
@Entity
public class CreditCard {

	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String cardholder;
	private String cardNumber;
//	@Field("company")
	@Enumerated(EnumType.STRING)
	private Company company;
//	@Field("bank")
	@Enumerated(EnumType.STRING)
	private Bank bank;
	private String expiryDate;
	private String cvv;
}
