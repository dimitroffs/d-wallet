package com.ddimitroff.projects.dwallet.db.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ddimitroff.projects.dwallet.db.dao.CashFlowDAO;
import com.ddimitroff.projects.dwallet.db.entities.CashFlow;
import com.ddimitroff.projects.dwallet.db.entities.User;

@Repository("cashFlowDao")
public class CashFlowDAOImpl extends BaseDAOImpl<CashFlow> implements CashFlowDAO {

  private static final Logger LOG = Logger.getLogger(CashFlowDAOImpl.class);

  @SuppressWarnings("unchecked")
  @Override
  @Transactional(readOnly = true)
  public List<CashFlow> getCashFlowsByUser(User owner) {
    try {
      List<CashFlow> cashFlows = em.createNamedQuery(CashFlow.GET_CASH_FLOWS_BY_USER).setParameter("owner", owner)
          .getResultList();
      return cashFlows;
    } catch (NoResultException e) {
      LOG.error("Unable to find cash flows by user [e-mail='" + owner.getEmail() + "']");
      return null;
    }
  }

}
