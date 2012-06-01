package com.ddimitroff.projects.dwallet.db.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.ddimitroff.projects.dwallet.db.dao.BaseDAO;
import com.ddimitroff.projects.dwallet.db.entities.BaseEntity;

public class BaseDAOImpl<E extends BaseEntity> implements BaseDAO<E> {

	private static final Logger LOG = Logger.getLogger(BaseDAOImpl.class);

	@PersistenceContext(name = "dwallet")
	protected EntityManager em;

	// TODO getById()
	// TODO getAll()

	public void save(E entity) {
		if (null != em.find(entity.getClass(), entity.getId())) {
			em.merge(entity);
			LOG.debug("Successfully updated entity [class=" + entity.getClass() + "id=" + entity.getId() + "]");
		} else {
			em.persist(entity);
			LOG.debug("Successfully created entity [class=" + entity.getClass() + "id=" + entity.getId() + "]");
		}
		em.flush();
	}

	public void delete(E entity) {
		if (null != em.find(entity.getClass(), entity.getId())) {
			em.remove(entity);
			em.flush();
			LOG.debug("Successfully deleted entity [class=" + entity.getClass() + "id=" + entity.getId() + "]");
		}
	}

}
