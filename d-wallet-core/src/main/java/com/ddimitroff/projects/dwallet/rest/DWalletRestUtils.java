package com.ddimitroff.projects.dwallet.rest;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

/**
 * A class for static utilities used in D-Wallet's REST API
 * 
 * @author Dimitar Dimitrov
 * 
 */
public class DWalletRestUtils {

  /** D-Wallet's request header */
  public static final String DWALLET_REQUEST_HEADER = "DWallet-API-Key";

  /** Decimal formatting of double values */
  private static final DecimalFormat TWO_DECIMALS_FORMAT = new DecimalFormat("#.##", new DecimalFormatSymbols(
      Locale.ENGLISH));

  /**
   * A method for checking if a provided API key is a valid one, contained in
   * provided API key set
   * 
   * @param apiKey
   *          - checked API key
   * @param apiKeys
   *          - checked API key set
   * 
   * @return {@code true} if API key is contained in provided key set,
   *         {@code false} otherwise
   */
  public static final boolean isValidAPIKey(String apiKey, ArrayList<String> apiKeys) {
    if (null == apiKey || null == apiKeys) {
      return false;
    }

    return apiKeys.contains(apiKey);
  }

  /**
   * A method for rounding a double value to two decimals format
   * 
   * @param d
   *          - double value to round
   * 
   * @return rounded double value
   */
  public static final double roundTwoDecimals(double d) {
    return Double.valueOf(TWO_DECIMALS_FORMAT.format(d));
  }

}
