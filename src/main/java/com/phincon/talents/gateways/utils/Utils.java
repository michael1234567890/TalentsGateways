package com.phincon.talents.gateways.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

	public static String INPUT_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static String INPUT_DATE_FORMAT = "yyyy-MM-dd";
	public static String DB_TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	private static String CONFIG_FILE = "/config.properties";
	public static int TIMEBETWEENPROCESS = 100;
	public static String INPUT_DATE_FORMAT_ID = "yyyy-MM-dd";

	public static Date convertStringToDate(String strDate) {
		DateFormat df = new SimpleDateFormat(INPUT_DATE_FORMAT);
		Date oldDate = null;
		try {
			oldDate = df.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return oldDate;
	}
	
	public static Date converStringToDatetime(String strDate){
		DateFormat df = new SimpleDateFormat(DB_TIMESTAMP_FORMAT);
		Date oldDate = null;
		try {
			oldDate = df.parse(strDate);
		}catch (ParseException e){
			e.printStackTrace();
		}
		
		return oldDate;
	}
	
	public static Date addDay(Date dt, int amount) throws ParseException {
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, amount);
		dt = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String dateStr = sdf.format(dt);
		dt = sdf.parse(dateStr);
		return dt;
	}
	
	public static String convertDateToString(Date date) {
		DateFormat fmt = new SimpleDateFormat(INPUT_DATE_FORMAT_ID);
		String text = fmt.format(date);
		return text;
	}
	
}
