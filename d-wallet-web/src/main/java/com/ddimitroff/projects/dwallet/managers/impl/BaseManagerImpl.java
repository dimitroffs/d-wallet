package com.ddimitroff.projects.dwallet.managers.impl;

import javax.annotation.PostConstruct;

import com.ddimitroff.projects.dwallet.db.dao.BaseDAO;
import com.ddimitroff.projects.dwallet.db.entities.BaseEntity;
import com.ddimitroff.projects.dwallet.managers.BaseManager;

public abstract class BaseManagerImpl<E extends BaseEntity> implements BaseManager<E> {

	protected BaseDAO<E> baseDao;

	public void save(E entity) {
		baseDao.save(entity);
	}

	public void delete(E entity) {
		baseDao.delete(entity);
	}

	@PostConstruct
	public abstract void postConstruct();

}
