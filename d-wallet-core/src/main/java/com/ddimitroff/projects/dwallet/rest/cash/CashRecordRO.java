package com.ddimitroff.projects.dwallet.rest.cash;

import java.io.Serializable;
import java.util.ArrayList;

import com.ddimitroff.projects.dwallet.rest.token.TokenRO;

/**
 * A DTO class for object/JSON mapping cash record. It has the following
 * structure:<br>
 * {"token":{"tokenId":"<token-id>"}, "cashFlows":[{"cashFlowType":"1",
 * "cashFlowCurrency":"1", "cashFlowSum":"11.8"}, ...]}
 * 
 * @author Dimitar Dimitrov
 * 
 */
public class CashRecordRO implements Serializable {

  /** Serial version UID constant */
  private static final long serialVersionUID = 1L;

  /** {@link TokenRO} object field */
  private TokenRO token;

  /** List of {@link CashFlowRO} objects field */
  private ArrayList<CashFlowRO> cashFlows;

  /**
   * Default no argument constructor for creating new {@link CashRecordRO}
   * object
   */
  public CashRecordRO() {
  }

  /**
   * Constructor for creation of new {@link CashRecordRO} object
   * 
   * @param token
   *          - {@link TokenRO} object to set
   * @param cashFlows
   *          - List of {@link CashFlowRO} objects to set
   */
  public CashRecordRO(TokenRO token, ArrayList<CashFlowRO> cashFlows) {
    this.token = token;
    this.cashFlows = cashFlows;
  }

  /**
   * @return the token
   */
  public TokenRO getToken() {
    return token;
  }

  /**
   * @param token
   *          the token to set
   */
  public void setToken(TokenRO token) {
    this.token = token;
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
