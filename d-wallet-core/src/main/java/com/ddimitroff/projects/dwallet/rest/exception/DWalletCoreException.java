package com.ddimitroff.projects.dwallet.rest.exception;

/**
 * A class for custom exception. Thrown where exceptions occur in core
 * D-Wallet's back-end data manipulations
 * 
 * @author Dimitar Dimitrov
 * 
 */
public class DWalletCoreException extends Exception {

  /** Serial version UID constant */
  private static final long serialVersionUID = 1L;

  /**
   * Default constructor
   */
  public DWalletCoreException() {
    super();
  }

  /**
   * Parametrized constructor
   * 
   * @param message
   *          - message to set
   */
  public DWalletCoreException(String message) {
    super(message);
  }

  /**
   * Parametrized constructor
   * 
   * @param t
   *          - {@link Throwable} object to set
   */
  public DWalletCoreException(Throwable t) {
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
  public DWalletCoreException(String message, Throwable t) {
    super(message, t);
  }

}
