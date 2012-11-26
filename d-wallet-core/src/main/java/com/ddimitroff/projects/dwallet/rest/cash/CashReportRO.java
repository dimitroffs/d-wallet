package com.ddimitroff.projects.dwallet.rest.cash;

import java.io.Serializable;
import java.util.ArrayList;

import com.ddimitroff.projects.dwallet.enums.CashFlowsReportType;
import com.ddimitroff.projects.dwallet.rest.response.Responsable;

/**
 * A DTO class for object/JSON mapping cash report. It has the following
 * structure:<br>
 * {"cashFlowsCount":"11", "cashFlowsProfit":"1000", "cashFlowsCost":"200",
 * "cashFlowsReportType":"3", "cashFlows":[{"cashFlowType":"1",
 * "cashFlowCurrency":"1", "cashFlowSum":"11.8",
 * "cashFlowDescription":"*description-as-string*"
 * "cashFlowDate":"*date-as-long*"}, ...]}
 * 
 * @author Dimitar Dimitrov
 * 
 */
public class CashReportRO implements Serializable, Responsable {

  /** Serial version UID constant */
  private static final long serialVersionUID = 1L;

  /** Number of calculated cash flows for report */
  private int cashFlowsCount;

  /** Sum of calculated cash flows profit for report */
  private double cashFlowsProfit;

  /** Sum of calculated cash flows cost for report */
  private double cashFlowsCost;

  /** Cash flows report type value */
  private int cashFlowsReportType;

  /** List of {@link CashFlowRO} objects field */
  private ArrayList<CashFlowRO> cashFlows;

  /**
   * Default no argument constructor for creating new {@link CashReportRO}
   * object
   */
  public CashReportRO() {
  }

  /**
   * Constructor for creation of new {@link CashRecordRO} object
   * 
   * @param cashFlowsCount
   *          - cashFlowsCount to set
   * @param cashFlowsProfit
   *          - cashFlowsProfit to set
   * @param cashFlowsCost
   *          - cashFlowsCost to set
   * @param cashFlowsReportType
   *          - cashFlowsReportType to set
   * @param cashFlows
   *          - List of {@link CashFlowRO} objects to set
   */
  public CashReportRO(int cashFlowsCount, double cashFlowsProfit, double cashFlowsCost,
      CashFlowsReportType cashFlowsReportType, ArrayList<CashFlowRO> cashFlows) {
    this.cashFlowsCount = cashFlowsCount;
    this.cashFlowsProfit = cashFlowsProfit;
    this.cashFlowsCost = cashFlowsCost;
    this.cashFlowsReportType = cashFlowsReportType.getIntType();
    this.cashFlows = cashFlows;
  }

  /**
   * @return the cashFlowsCount
   */
  public int getCashFlowsCount() {
    return cashFlowsCount;
  }

  /**
   * @param cashFlowsCount
   *          the cashFlowsCount to set
   */
  public void setCashFlowsCount(int cashFlowsCount) {
    this.cashFlowsCount = cashFlowsCount;
  }

  /**
   * @return the cashFlowsProfit
   */
  public double getCashFlowsProfit() {
    return cashFlowsProfit;
  }

  /**
   * @param cashFlowsProfit
   *          the cashFlowsProfit to set
   */
  public void setCashFlowsProfit(double cashFlowsProfit) {
    this.cashFlowsProfit = cashFlowsProfit;
  }

  /**
   * @return the cashFlowsCost
   */
  public double getCashFlowsCost() {
    return cashFlowsCost;
  }

  /**
   * @param cashFlowsCost
   *          the cashFlowsCost to set
   */
  public void setCashFlowsCost(double cashFlowsCost) {
    this.cashFlowsCost = cashFlowsCost;
  }

  /**
   * @return the cashFlowsReportType
   */
  public int getCashFlowsReportType() {
    return cashFlowsReportType;
  }

  /**
   * @param cashFlowsReportType
   *          the cashFlowsReportType to set
   */
  public void setCashFlowsReportType(int cashFlowsReportType) {
    this.cashFlowsReportType = cashFlowsReportType;
  }

  /**
   * @return the cashFlows
   */
  public ArrayList<CashFlowRO> getCashFlows() {
    return cashFlows;
  }

  /**
   * @param cashFlows
   *          the cashFlows to set
   */
  public void setCashFlows(ArrayList<CashFlowRO> cashFlows) {
    this.cashFlows = cashFlows;
  }

}
