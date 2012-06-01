package com.ddimitroff.projects.dwallet.db.dao;

import com.ddimitroff.projects.dwallet.db.entities.CashBalance;
import com.ddimitroff.projects.dwallet.db.entities.User;

public interface CashBalanceDAO extends BaseDAO<CashBalance>{

	public CashBalance getCashBalanceByUser(User owner);
	
}
