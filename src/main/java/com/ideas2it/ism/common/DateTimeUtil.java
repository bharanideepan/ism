package com.ideas2it.ism.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Which handles the date and time functionalities 
 */
public class DateTimeUtil {

	/**
	 * Converts date and time into string format also splits date and time separately 
	 * 
	 * @param dateTime - Date with time in date format(yyyy-MM-dd HH:mm:ss)
	 * 
	 * @return dateAndTime - Map having date and time in string format
	 */
	public static Map<String, String> getDateAndTimeInStringFormat(Date dateTime) {
		DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat outputDateFormat = new SimpleDateFormat("dd MMM yyyy");
		Map<String, String> dateAndTime = new HashMap<String, String>();
		String[] splited = dateTime.toString().split("\\s+");
		Date date = null;
		try {
			date = inputDateFormat.parse(splited[0]);
		} catch (ParseException e) {}
		dateAndTime.put(Constant.DATE, outputDateFormat.format(date));
		dateAndTime.put(Constant.TIME, splited[1]);
		return dateAndTime;
	}
}
