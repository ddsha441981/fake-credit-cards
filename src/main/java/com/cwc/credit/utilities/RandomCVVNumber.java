package com.cwc.credit.utilities;

import java.util.Random;

public class RandomCVVNumber {

	public static String generateRandomCVV() {
		Random rand = new Random();
		// Generate a 3-digit CVV
		int cvv = rand.nextInt(900) + 100;
		return String.valueOf(cvv);
	}

}
