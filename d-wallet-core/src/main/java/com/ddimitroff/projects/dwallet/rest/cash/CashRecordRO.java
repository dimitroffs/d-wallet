package com.ddimitroff.projects.dwallet.rest.cash;

import java.io.Serializable;
import java.util.ArrayList;

import com.ddimitroff.projects.dwallet.rest.token.TokenRO;

public class CashRecordRO implements Serializable {

  private static final long serialVersionUID = 1L;

  private TokenRO token;
  private ArrayList<CashFlowRO> cashFlows;

  public CashRecordRO() {
  }

  public CashRecordRO(TokenRO token, ArrayList<CashFlowRO> cashFlows) {
    this.token = token;
    this.cashFlows = cashFlows;
  }

  public TokenRO getToken() {
    return token;
  }

  public void setToken(TokenRO token) {
    this.token = token;
  }

  public ArrayList<CashFlowRO> getCashFlows() {
    return cashFlows;
  }

  public void setCashFlows(ArrayList<CashFlowRO> cashFlows) {
    this.cashFlows = cashFlows;
  }

}
