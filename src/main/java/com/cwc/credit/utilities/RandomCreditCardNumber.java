package com.cwc.credit.utilities;

import java.util.Random;

public class RandomCreditCardNumber {

	public static String generateRandomCreditCardNumber(String companyName) {
		Random rand = new Random();
		StringBuilder creditCardNumber = new StringBuilder();
		String lowerCase = companyName.toLowerCase();
		String issuer = getIssuerByCompanyName(lowerCase);

		creditCardNumber.append(getSeriesByIssuer(issuer));

		for (int i = 0; i < 12; i++) {
			creditCardNumber.append(rand.nextInt(10));
		}

		creditCardNumber.append(calculateChecksum(creditCardNumber.toString()));

		return creditCardNumber.toString();
	}

	private static String getIssuerByCompanyName(String companyName) {
		if (companyName.contains("visa")) {
			return "visa";
		} else if (companyName.contains("mastercard")) {
			return "mastercard";
		} else {
			return "americanexpress";
		}
	}

	private static String getSeriesByIssuer(String issuer) {
		switch (issuer.toLowerCase()) {
		case "visa":
			return "4477";
		case "mastercard":
			return "577";
		case "americanexpress":
			return "3777";
		case "rupay":
			return "2277";
		default:
			throw new IllegalArgumentException("Invalid credit card issuer");
		}
	}

	private static int calculateChecksum(String cardNumber) {
		int sum = 0;
		boolean doubleDigit = false;

		for (int i = cardNumber.length() - 1; i >= 0; i--) {
			int digit = Character.getNumericValue(cardNumber.charAt(i));

			if (doubleDigit) {
				digit *= 2;
				if (digit > 9) {
					digit -= 9;
				}
			}

			sum += digit;
			doubleDigit = !doubleDigit;
		}

		return (10 - (sum % 10)) % 10;
	}

}
