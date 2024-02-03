package com.cwc.credit.utilities;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Random;

public class RandomDate {

	
	public static String generateDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate endDate = currentDate.plusYears(5);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedEndDate = endDate.format(formatter);

        return formattedEndDate;
    }
}
