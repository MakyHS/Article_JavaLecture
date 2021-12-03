package board.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MyUtil {
	
	public static String getCurrentDate(String dateFormat) {
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		String formatedNow = now.format(formatter);
		
		return formatedNow;
	}
	
}
