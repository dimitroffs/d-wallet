package com.ddimitroff.projects.dwallet.db.dao;

import java.util.List;

import com.ddimitroff.projects.dwallet.db.entities.CashFlow;
import com.ddimitroff.projects.dwallet.db.entities.User;

public interface CashFlowDAO extends BaseDAO<CashFlow> {

	public List<CashFlow> getCashFlowsByUser(User owner);

}
