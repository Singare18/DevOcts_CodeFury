package in.co.xyz.eassetmanagement.util;


import java.util.Calendar;
import java.util.Date;

public class Util {

	//To convert int into date
	public static Date intToDateConversion(int date) {
		
        // Extract year, month, and day from the int
        int year = date / 10000;
        int month = (date % 10000) / 100;
        int day = date % 100;

        // Create a Calendar instance and set the date components
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1); // Month is 0-based in Calendar
        calendar.set(Calendar.DAY_OF_MONTH, day);

        // Convert Calendar to Date
        Date newDate = calendar.getTime();
        return newDate;
	}
	
	
	
	
}
