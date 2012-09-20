package com.ddimitroff.projects.dwallet.db.dao.impl;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ddimitroff.projects.dwallet.db.dao.CashBalanceDAO;
import com.ddimitroff.projects.dwallet.db.entities.CashBalance;
import com.ddimitroff.projects.dwallet.db.entities.User;

@Component("cashBalanceDao")
public class CashBalanceDAOImpl extends BaseDAOImpl<CashBalance> implements CashBalanceDAO {

  private static final Logger LOG = Logger.getLogger(CashBalanceDAOImpl.class);

  @Override
  public CashBalance getCashBalanceByUser(User owner) {
    try {
      return (CashBalance) em.createNamedQuery(CashBalance.GET_CASH_BALANCE_BY_USER).setParameter("owner", owner)
          .getSingleResult();
    } catch (NoResultException ex) {
      LOG.error("Unable to get cash balance by user [e-mail=" + owner.getEmail() + "]");
      return null;
    }
  }

}
