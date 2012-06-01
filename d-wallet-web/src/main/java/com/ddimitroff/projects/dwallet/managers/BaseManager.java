package com.ddimitroff.projects.dwallet.managers;

import org.springframework.transaction.annotation.Transactional;

import com.ddimitroff.projects.dwallet.db.entities.BaseEntity;

public interface BaseManager<E extends BaseEntity> {

	@Transactional
	public void save(E entity);

	@Transactional
	public void delete(E entity);

}
