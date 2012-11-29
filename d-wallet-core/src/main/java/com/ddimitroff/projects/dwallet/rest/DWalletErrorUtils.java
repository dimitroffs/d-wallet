package com.ddimitroff.projects.dwallet.rest;

/**
 * A class for static error utilities used in D-Wallet's REST API
 * 
 * @author Dimitar Dimitrov
 * 
 */
public class DWalletErrorUtils {

  /** Error codes and corresponding error messages */

  public static final String ERR001_CODE = "DWERROR001";
  public static final String ERR001_MSG = "Wrong 'd-wallet' API key";

  public static final String ERR002_CODE = "DWERROR002";
  public static final String ERR002_MSG = "Unable to find active token with provided token id";

  public static final String ERR010_CODE = "DWERROR010";
  public static final String ERR010_MSG = "Wrong request body - token structure is not correct";

  public static final String ERR011_CODE = "DWERROR011";
  public static final String ERR011_MSG = "Wrong request body - user structure is not correct";

  public static final String ERR012_CODE = "DWERROR012";
  public static final String ERR012_MSG = "Wrong request body - cash record structure is not correct";

  public static final String ERR020_CODE = "DWERROR020";
  public static final String ERR020_MSG = "Unable to generate new token for user";

  public static final String ERR030_CODE = "DWERROR030";
  public static final String ERR030_MSG = "Unable to find cash balance for user";
  
  public static final String ERR040_CODE = "DWERROR040";
  public static final String ERR040_MSG = "Unable to generate cash report for user";

  /** End of error codes and corresponding error messages */

}
