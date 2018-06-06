package com.poc.bookingSystem.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	public static Date convertStringToDate( String str_date ) throws ParseException{
		DateFormat formatter;
		Date date;
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = formatter.parse(str_date);
	     return date;
	}
	public static String convertDateToString( Date date ) throws ParseException{
		String str_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	     return str_date;
	}
	
	

	
	
	

}
