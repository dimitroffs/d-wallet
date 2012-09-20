package com.ddimitroff.projects.dwallet.managers;

import org.springframework.transaction.annotation.Transactional;

import com.ddimitroff.projects.dwallet.db.entities.CashBalance;
import com.ddimitroff.projects.dwallet.db.entities.User;
import com.ddimitroff.projects.dwallet.rest.cash.CashBalanceRO;

public interface CashBalanceManager extends BaseManager<CashBalance> {

	@Transactional(readOnly = true)
	public CashBalance getCashBalanceByUser(User owner);
	
	public CashBalanceRO convert(CashBalance entity);

  public CashBalance getByUser(User user);

}
