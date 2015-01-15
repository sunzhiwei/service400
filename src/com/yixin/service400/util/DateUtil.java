package com.yixin.service400.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static String getTime() {
		return getTime("yyyy-MM-dd");
	}

	public static String getTime(String string) {
		SimpleDateFormat format = new SimpleDateFormat(string);
		return format.format(new Date());
	}

	public static String getDateTime(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String mDateTime = formatter.format(cal.getTime());
		return mDateTime;
	}
	
	public static String getDateTime_mins(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-ddHHmmss");
		String mDateTime = formatter.format(cal.getTime());
		return mDateTime;
	}
}
