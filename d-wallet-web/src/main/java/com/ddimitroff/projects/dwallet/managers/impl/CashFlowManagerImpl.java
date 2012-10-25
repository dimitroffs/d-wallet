package com.ddimitroff.projects.dwallet.managers.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddimitroff.projects.dwallet.db.dao.CashFlowDAO;
import com.ddimitroff.projects.dwallet.db.entities.CashFlow;
import com.ddimitroff.projects.dwallet.db.entities.User;
import com.ddimitroff.projects.dwallet.enums.CashFlowCurrencyType;
import com.ddimitroff.projects.dwallet.enums.CashFlowType;
import com.ddimitroff.projects.dwallet.managers.CashFlowManager;
import com.ddimitroff.projects.dwallet.rest.cash.CashFlowRO;

/**
 * Implementation class of {@link CashFlowManager} interface. It is used as
 * Spring component.
 * 
 * @author Dimitar Dimitrov
 * 
 */
@Service("cashFlowManager")
public class CashFlowManagerImpl extends BaseManagerImpl<CashFlow> implements CashFlowManager {

  /** Injected {@link CashFlowDAO} component by Spring */
  @Autowired
  private CashFlowDAO cashFlowDao;

  @Override
  public void postConstruct() {
    baseDao = cashFlowDao;
  }

  public List<CashFlow> getCashFlowsByUser(User owner) {
    return cashFlowDao.getCashFlowsByUser(owner);
  }

  public CashFlowRO convert(CashFlow entity) {
    int cashFlowType = entity.getType().getIntType();
    int cashFlowCurrencyType = entity.getCurrencyType().getIntCurrencyCode();
    double cashFlowSum = entity.getSum();

    CashFlowRO ro = new CashFlowRO(cashFlowType, cashFlowCurrencyType, cashFlowSum);

    return ro;
  }

  public CashFlow convert(CashFlowRO ro, User owner) {
    CashFlowType cashFlowType = CashFlowType.getType(ro.getCashFlowType());
    CashFlowCurrencyType cashFlowCurrencyType = CashFlowCurrencyType.getCurrencyType(ro.getCashFlowCurrency());
    double cashFlowSum = ro.getCashFlowSum();

    // TODO get correct date from CashFlowRO
    Date cashFlowDate = new Date();

    CashFlow dao = new CashFlow(owner, cashFlowType, cashFlowCurrencyType, cashFlowSum, cashFlowDate);

    return dao;
  }

}
