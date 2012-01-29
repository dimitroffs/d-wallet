package com.ddimitroff.projects.dwallet.rest;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

public class DWalletRestUtils {

	public static final String DWALLET_REQUEST_HEADER = "DWallet-API-Key";
	private static final DecimalFormat TWO_DECIMALS_FORMAT = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.ENGLISH));

	public static final boolean isValidAPIKey(String apiKey, ArrayList<String> apiKeys) {
		if (null == apiKey || null == apiKeys) {
			return false;
		}

		return apiKeys.contains(apiKey);
	}

	public static final double roundTwoDecimals(double d) {
		return Double.valueOf(TWO_DECIMALS_FORMAT.format(d));
	}

}
