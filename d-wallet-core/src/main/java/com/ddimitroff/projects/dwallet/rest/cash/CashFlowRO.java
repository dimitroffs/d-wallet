package com.ddimitroff.projects.dwallet.rest.cash;

import java.io.Serializable;

/**
 * A DTO class for object/JSON mapping cash flows. It has the following
 * structure:<br>
 * {"cashFlowType":"1", "cashFlowCurrency":"1", "cashFlowSum":"11.8",
 * "cashFlowDescription":"*description-as-string*"
 * "cashFlowDate":"*date-as-long*"}
 * 
 * @author Dimitar Dimitrov
 * 
 */
public class CashFlowRO implements Serializable {

  /** Serial version UID constant */
  private static final long serialVersionUID = 1L;

  /** Cash flow type field */
  private int cashFlowType;

  /** Cash flow currency type field */
  private int cashFlowCurrency;

  /** Cash flow sum field */
  private double cashFlowSum;

  /** Cash flow description field */
  private String cashFlowDescription;

  /** Cash flow date */
  private long cashFlowDate;

  /**
   * Default no argument constructor for creating new {@link CashFlowRO} object
   */
  public CashFlowRO() {
  }

  /**
   * Constructor for creating new {@link CashFlowRO} object
   * 
   * @param cashFlowType
   *          - cashFlowType to set
   * @param cashFlowCurrency
   *          - cashFlowCurrency type to set
   * @param cashFlowSum
   *          - cashFlowSum to set
   */
  public CashFlowRO(int cashFlowType, int cashFlowCurrency, double cashFlowSum, String cashFlowDescription) {
    this.cashFlowType = cashFlowType;
    this.cashFlowCurrency = cashFlowCurrency;
    this.cashFlowSum = cashFlowSum;
    this.cashFlowDescription = cashFlowDescription;
  }

  /**
   * @return the cashFlowType
   */
  public int getCashFlowType() {
    return cashFlowType;
  }

  /**
   * @param cashFlowType
   *          the cashFlowType to set
   */
  public void setCashFlowType(int cashFlowType) {
    this.cashFlowType = cashFlowType;
  }

  /**
   * @return the cashFlowCurrency
   */
  public int getCashFlowCurrency() {
    return cashFlowCurrency;
  }

  /**
   * @param cashFlowCurrency
   *          the cashFlowCurrency to set
   */
  public void setCashFlowCurrency(int cashFlowCurrency) {
    this.cashFlowCurrency = cashFlowCurrency;
  }

  /**
   * @return the cashFlowSum
   */
  public double getCashFlowSum() {
    return cashFlowSum;
  }

  /**
   * @param cashFlowSum
   *          the cashFlowSum to set
   */
  public void setCashFlowSum(double cashFlowSum) {
    this.cashFlowSum = cashFlowSum;
  }

  /**
   * @return the cashFlowDescription
   */
  public String getCashFlowDescription() {
    return cashFlowDescription;
  }

  /**
   * @param cashFlowDescription
   *          the cashFlowDescription to set
   */
  public void setCashFlowDescription(String cashFlowDescription) {
    this.cashFlowDescription = cashFlowDescription;
  }

  /**
   * @return the cashFlowDate
   */
  public long getCashFlowDate() {
    return cashFlowDate;
  }

  /**
   * @param cashFlowDate
   *          the cashFlowDate to set
   */
  public void setCashFlowDate(long cashFlowDate) {
    this.cashFlowDate = cashFlowDate;
  }

}
