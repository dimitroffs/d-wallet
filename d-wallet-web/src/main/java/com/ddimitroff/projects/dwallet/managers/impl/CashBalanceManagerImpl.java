package com.ddimitroff.projects.dwallet.managers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddimitroff.projects.dwallet.db.dao.CashBalanceDAO;
import com.ddimitroff.projects.dwallet.db.entities.CashBalance;
import com.ddimitroff.projects.dwallet.db.entities.User;
import com.ddimitroff.projects.dwallet.managers.CashBalanceManager;
import com.ddimitroff.projects.dwallet.rest.cash.CashBalanceRO;

@Service("cashBalanceManager")
public class CashBalanceManagerImpl extends BaseManagerImpl<CashBalance> implements CashBalanceManager {

	// private static final Logger LOG =
	// Logger.getLogger(CashBalanceManagerImpl.class);

	@Autowired
	private CashBalanceDAO cashBalanceDao;

	@Override
	public void postConstruct() {
		baseDao = cashBalanceDao;
	}

	public CashBalance getCashBalanceByUser(User owner) {
		return cashBalanceDao.getCashBalanceByUser(owner);
	}

	/*
	 * Used for creating REST cash balance object from database one. !Please note
	 * that correct CashBalanceDAO object of specified user should be provided as
	 * parameter
	 */
	public CashBalanceRO convert(CashBalance entity) {
		CashBalanceRO ro = new CashBalanceRO(entity.getCurrency().getIntCurrencyCode(), entity.getDebit(),
				entity.getCredit());

		return ro;
	}
	
	public CashBalance getByUser(User user) {
	  return cashBalanceDao.getCashBalanceByUser(user);
	}

}
