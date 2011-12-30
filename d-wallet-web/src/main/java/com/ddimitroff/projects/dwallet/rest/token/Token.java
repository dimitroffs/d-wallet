package com.ddimitroff.projects.dwallet.rest.token;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Token {

	private static final int TOKEN_TIMEOUT = 30; // 30 minutes
	private static final SimpleDateFormat TOKEN_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd-hhmm");

	private String id;
	private String validTo;

	public Token(String username, String hashPassword) {
		// TODO check for valid user in DB
		// TODO check if user isn't already logged in
		// TODO create token
	}

	private static String getValidToDateAsString(Date startDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.add(Calendar.MINUTE, TOKEN_TIMEOUT);
		
		return TOKEN_DATE_FORMAT.format(cal.getTime());
	}

	public static void main(String[] args) {
		System.out.println(getValidToDateAsString(new Date()));
	}

}
