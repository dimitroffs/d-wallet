package com.ddimitroff.projects.dwallet.managers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddimitroff.projects.dwallet.db.dao.CashBalanceDAO;
import com.ddimitroff.projects.dwallet.db.entities.CashBalance;
import com.ddimitroff.projects.dwallet.db.entities.User;
import com.ddimitroff.projects.dwallet.managers.CashBalanceManager;
import com.ddimitroff.projects.dwallet.rest.cash.CashBalanceRO;

/**
 * Implementation class of {@link CashBalanceManager} interface. It is used as
 * Spring component.
 * 
 * @author Dimitar Dimitrov
 * 
 */
@Service("cashBalanceManager")
public class CashBalanceManagerImpl extends BaseManagerImpl<CashBalance> implements CashBalanceManager {

  /** Injected {@link CashBalanceDAO} component by Spring */
  @Autowired
  private CashBalanceDAO cashBalanceDao;

  @Override
  public void postConstruct() {
    baseDao = cashBalanceDao;
  }

  public CashBalance getCashBalanceByUser(User owner) {
    return cashBalanceDao.getCashBalanceByUser(owner);
  }

  public CashBalanceRO convert(CashBalance entity) {
    CashBalanceRO ro = new CashBalanceRO(entity.getCurrency().getIntCurrencyCode(), entity.getDebit(),
        entity.getCredit());

    return ro;
  }

}
