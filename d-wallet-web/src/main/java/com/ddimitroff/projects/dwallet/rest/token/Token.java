package com.ddimitroff.projects.dwallet.rest.token;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Token {

	private static final int TOKEN_TIMEOUT = 30 * 60 * 1000; // 30 minutes
	private static final SimpleDateFormat TOKEN_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd-hhmm");

	private String id;
	private String validTo;

	public Token(String username, String hashPassword) {
		// TODO check for valid user in DB
		// TODO check if user isn't already logged in
		// TODO create token
	}

	public static void main(String[] args) {
		System.out.println(TOKEN_DATE_FORMAT.format(new Date()));
	}

}
