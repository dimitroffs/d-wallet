package com.ddimitroff.projects.dwallet.managers;

import java.util.List;

import com.ddimitroff.projects.dwallet.db.entities.CashFlow;
import com.ddimitroff.projects.dwallet.db.entities.User;
import com.ddimitroff.projects.dwallet.rest.cash.CashFlowRO;

public interface CashFlowManager extends BaseManager<CashFlow> {

  public List<CashFlow> getCashFlowsByUser(User owner);

  public CashFlowRO convert(CashFlow entity);

  public CashFlow convert(CashFlowRO ro, User owner);

}
