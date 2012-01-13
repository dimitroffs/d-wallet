package com.ddimitroff.projects.dwallet.rest;

import java.util.ArrayList;

public class DWalletRestUtils {

	public static final String DWALLET_REQUEST_HEADER = "DWallet-API-Key";

	public static final boolean isValidAPIKey(String apiKey, ArrayList<String> apiKeys) {
		if (null == apiKey || null == apiKeys) {
			return false;
		}

		return apiKeys.contains(apiKey);
	}
}
