package com.ddimitroff.projects.dwallet.db.cash;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ddimitroff.projects.dwallet.db.user.UserDAO;
import com.ddimitroff.projects.dwallet.rest.cash.CashBalanceRO;
import com.ddimitroff.projects.dwallet.rest.cash.CashFlowRO;

@Component
@Transactional
public class CashDAOManager implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CashDAOManager.class);

	@PersistenceContext(name = "dwallet", type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public CashBalanceDAO getCashBalanceByUser(UserDAO owner) {
		try {
			return (CashBalanceDAO) em.createNamedQuery(CashBalanceDAO.GET_CASH_BALANCE_BY_USER).setParameter("owner", owner).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public void saveCashBalance(CashBalanceDAO cashBalanceDAO) {
		if (null != em.find(CashBalanceDAO.class, cashBalanceDAO.getId())) {
			em.merge(cashBalanceDAO);
			logger.info(cashBalanceDAO + " updated successfully.");
		} else {
			em.persist(cashBalanceDAO);

			logger.info(cashBalanceDAO + " created successfully");
		}
		em.flush();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<CashFlowDAO> getCashFlowsByUser(UserDAO owner) {
		try {
			List<CashFlowDAO> cashFlows = em.createNamedQuery(CashFlowDAO.GET_CASH_FLOWS_BY_USER).setParameter("owner", owner).getResultList();
			return cashFlows;
		} catch (NoResultException e) {
			return null;
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveCashFlow(CashFlowDAO cashFlow) {
		if (null != em.find(CashFlowDAO.class, cashFlow.getId())) {
			em.merge(cashFlow);
			logger.info(cashFlow + " updated successfully.");
		} else {
			em.persist(cashFlow);

			logger.info(cashFlow + " created successfully");
		}
		em.flush();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteCashFlow(CashFlowDAO cashFlow) {
		CashFlowDAO toBeDeleted = em.find(CashFlowDAO.class, cashFlow.getId());
		if (toBeDeleted != null) {
			em.remove(toBeDeleted);
			em.flush();

			logger.info(cashFlow + " deleted successfully");
		}
	}

	/*
	 * Used for creating REST cash balance object from database one. !Please note
	 * that correct CashBalanceDAO object of specified user should be provided as
	 * parameter
	 */
	public CashBalanceRO convert(CashBalanceDAO dao) {
		CashBalanceRO ro = new CashBalanceRO(dao.getDebit(), dao.getCredit());
		logger.info("CashBalanceDAO successfully converted to CashBalanceRO!");

		return ro;
	}

	/*
	 * Used for creating REST cash flow object from database one
	 */
	public CashFlowRO convert(CashFlowDAO dao) {
		int cashFlowType = dao.getType().getIntType();
		int cashFlowCurrencyType = dao.getCurrencyType().getIntCurrencyCode();
		double cashFlowSum = dao.getSum();

		CashFlowRO ro = new CashFlowRO(cashFlowType, cashFlowCurrencyType, cashFlowSum);
		logger.info("CashFlowDAO successfully converted to CashFlowRO!");

		return ro;
	}

	/*
	 * Used for storing cash flow record in database
	 */
	public CashFlowDAO convert(CashFlowRO ro, UserDAO owner) {
		CashFlowDAOType cashFlowType = CashFlowDAOType.getType(ro.getCashFlowType());
		CashFlowDAOCurrencyType cashFlowCurrencyType = CashFlowDAOCurrencyType.getCurrencyType(ro.getCashFlowCurrency());
		double cashFlowSum = ro.getCashFlowSum();

		// TODO get correct date from CashFlowRO
		Date cashFlowDate = new Date();

		CashFlowDAO dao = new CashFlowDAO(owner, cashFlowType, cashFlowCurrencyType, cashFlowSum, cashFlowDate);
		logger.info("CashFlowRO successfully converted to CashFlowDAO!");

		return dao;
	}

}
