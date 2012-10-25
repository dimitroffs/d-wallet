package com.ddimitroff.projects.dwallet.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * A class for custom exception. Thrown where exceptions occur in D-Wallet's
 * back-end data manipulations
 * 
 * @author Dimitar Dimitrov
 * 
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class DWalletException extends Exception {

  /** Serial version UID constant */
  private static final long serialVersionUID = 1L;

  /**
   * Default constructor
   */
  public DWalletException() {
    super();
  }

  /**
   * Parametrized constructor
   * 
   * @param message
   *          - message to set
   */
  public DWalletException(String message) {
    super(message);
  }

  /**
   * Parametrized constructor
   * 
   * @param t
   *          - {@link Throwable} object to set
   */
  public DWalletException(Throwable t) {
    super(t);
  }

  /**
   * Parametrized constructor
   * 
   * @param message
   *          - message to set
   * @param t
   *          - {@link Throwable} object to set
   */
  public DWalletException(String message, Throwable t) {
    super(message, t);
  }

}
