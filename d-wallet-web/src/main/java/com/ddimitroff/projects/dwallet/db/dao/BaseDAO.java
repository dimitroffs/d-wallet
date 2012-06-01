package com.ddimitroff.projects.dwallet.db.dao;

import com.ddimitroff.projects.dwallet.db.entities.BaseEntity;

public interface BaseDAO<E extends BaseEntity> {

	public void save(E entity);

	public void delete(E entity);

}
