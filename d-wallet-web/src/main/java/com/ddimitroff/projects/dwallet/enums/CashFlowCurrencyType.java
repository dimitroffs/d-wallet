package com.ddimitroff.projects.dwallet.enums;

/**
 * Enumeration class for defining cash flow currency types
 * 
 * @author Dimitar Dimitrov
 * 
 */
public enum CashFlowCurrencyType {
  BGN(1), USD(2), EUR(3);

  /** Currency code as integer value */
  private final int currencyCode;

  /**
   * {@link CashFlowCurrencyType} private constructor
   * 
   * @param currencyCode
   *          - integer value for currency code to set
   */
  private CashFlowCurrencyType(int currencyCode) {
    this.currencyCode = currencyCode;
  }

  /**
   * A method for returning currency code of specified cash flow
   * 
   * @return integer value of cash flow currency code
   */
  public int getIntCurrencyCode() {
    return currencyCode;
  }

  /**
   * A method for returning cash flow currency type by specified currency code
   * 
   * @param currencyCode
   *          - specified currency code
   * 
   * @return {@link CashFlowCurrencyType} enumeration object
   */
  public static final CashFlowCurrencyType getCurrencyType(int currencyCode) {
    for (CashFlowCurrencyType current : CashFlowCurrencyType.values()) {
      if (current.getIntCurrencyCode() == currencyCode) {
        return current;
      }
    }

    return null;
  }

}
