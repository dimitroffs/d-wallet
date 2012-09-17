package com.ddimitroff.projects.dwallet.db.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.ddimitroff.projects.dwallet.db.dao.BaseDAO;
import com.ddimitroff.projects.dwallet.db.entities.BaseEntity;

public class BaseDAOImpl<E extends BaseEntity> implements BaseDAO<E> {

  private static final Logger LOG = Logger.getLogger(BaseDAOImpl.class);

  @PersistenceContext(name = "dwallet")
  protected EntityManager em;

  public E getById(Class<E> clazz, int id) {
    E entity = (E) em.find(clazz, id);

    if (null != entity) {
      return entity;
    }

    return null;
  }

  @SuppressWarnings("unchecked")
  public List<E> getAll(Class<E> clazz) {
    List<E> objects = em.createQuery("from " + clazz.getName()).getResultList();

    if (null != objects && objects.size() > 0) {
      return objects;
    }

    return null;
  }

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
