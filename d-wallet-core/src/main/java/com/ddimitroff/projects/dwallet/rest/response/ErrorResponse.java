package com.ddimitroff.projects.dwallet.rest.response;

/**
 * A DTO class for object/JSON mapping error response. It has the following
 * structure:<br>
 * {"success":"false", "errorCode":"DWERROR001", "errorMessage":"Wrong 'd-wallet' API key"}
 * 
 * @author Dimitar Dimitrov
 * 
 */
public class ErrorResponse extends Response {

  /** Serial version UID constant */
  private static final long serialVersionUID = 1L;

  /** Error code {@link String} object as specified in D-Wallet API */
  private String errorCode;

  /** Error message {@link String} object as specified in D-Wallet API */
  private String errorMessage;

  /**
   * Default {@link ErrorResponse} constructor
   */
  public ErrorResponse() {
    setSuccess(false);
  }

  /**
   * Constructor for {@link ErrorResponse} objects with parameters
   * 
   * @param errorCode
   *          - error code to set
   * @param errorMessage
   *          - error message to set
   */
  public ErrorResponse(String errorCode, String errorMessage) {
    setSuccess(false);
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  /**
   * @return the errorCode
   */
  public String getErrorCode() {
    return errorCode;
  }

  /**
   * @param errorCodeArg
   *          the errorCode to set
   */
  public void setErrorCode(String errorCodeArg) {
    errorCode = errorCodeArg;
  }

  /**
   * @return the errorMessage
   */
  public String getErrorMessage() {
    return errorMessage;
  }

  /**
   * @param errorMessageArg
   *          the errorMessage to set
   */
  public void setErrorMessage(String errorMessageArg) {
    errorMessage = errorMessageArg;
  }

}
