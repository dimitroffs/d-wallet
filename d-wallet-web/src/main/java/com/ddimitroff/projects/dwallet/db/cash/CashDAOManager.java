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
import com.ddimitroff.projects.dwallet.rest.cash.CashFlowRO;

@Component
@Transactional
public class CashDAOManager implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CashDAOManager.class);

	@PersistenceContext(name = "dwallet", type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<CashFlowDAO> getCashFlowsByUser(UserDAO owner) {
		try {
			List<CashFlowDAO> user = em.createNamedQuery(CashFlowDAO.GET_CASHFLOWS_BY_USER).setParameter("owner", owner).getResultList();
			return user;
		} catch (NoResultException e) {
			return null;
		}
	}

	// @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	// public CashFlowDAO getUserByName(String email) {
	// try {
	// return (CashFlowDAO)
	// em.createNamedQuery(CashFlowDAO.GET_USER_BY_EMAIL).setParameter("email",
	// email).getSingleResult();
	// } catch (NoResultException ex) {
	// return null;
	// }
	// }

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveCashFlow(CashFlowDAO cashFlow) throws Exception {
		if (em.find(CashFlowDAO.class, cashFlow.getId()) != null) {
			em.merge(cashFlow);
			logger.info(cashFlow + " updated successfully.");
		} else {
			em.persist(cashFlow);

			logger.info(cashFlow + "] created successfully");
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

	// public UserRO convert(CashFlowDAO dao) {
	// UserRO ro = new UserRO(dao.getEmail(), dao.getHashPassword());
	// logger.info("UserDAO successfully converted to UserRO!");
	//
	// return ro;
	// }
	//

	// Every convert request for cash flow creates new CashFlowDAO object
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
