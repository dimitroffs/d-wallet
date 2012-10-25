package com.ddimitroff.projects.dwallet.db.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.ddimitroff.projects.dwallet.db.dao.BaseDAO;
import com.ddimitroff.projects.dwallet.db.entities.BaseEntity;

/**
 * Implementation class of {@link BaseDAO} interface
 * 
 * @author Dimitar Dimitrov
 * 
 * @param <E>
 *          should extends {@link BaseEntity} object
 */
public class BaseDAOImpl<E extends BaseEntity> implements BaseDAO<E> {

  /** Logger constant */
  private static final Logger LOG = Logger.getLogger(BaseDAOImpl.class);

  /** Entity manager field - injected by String */
  @PersistenceContext(name = "dwallet")
  protected EntityManager em;

  @Transactional(readOnly = true)
  public E getById(Class<E> clazz, int id) {
    E entity = (E) em.find(clazz, id);

    if (null != entity) {
      return entity;
    }

    return null;
  }

  @SuppressWarnings("unchecked")
  @Transactional(readOnly = true)
  public List<E> getAll(Class<E> clazz) {
    List<E> objects = em.createQuery("from " + clazz.getName()).getResultList();

    if (null != objects && objects.size() > 0) {
      return objects;
    }

    return null;
  }

  @Transactional
  public void save(E entity) {
    if (null != em.find(entity.getClass(), entity.getId())) {
      em.merge(entity);
      LOG.debug("Successfully updated entity [class=" + entity.getClass() + " id=" + entity.getId() + "]");
    } else {
      em.persist(entity);
      LOG.debug("Successfully created entity [class=" + entity.getClass() + " id=" + entity.getId() + "]");
    }
    em.flush();
  }

  @SuppressWarnings("unchecked")
  @Transactional
  public void delete(E entity) {
    E entityToDelete = (E) em.find(entity.getClass(), entity.getId());
    if (null != entityToDelete) {
      em.remove(entityToDelete);
      LOG.debug("Successfully deleted entity [class=" + entity.getClass() + " id=" + entity.getId() + "]");
      // em.flush();
    } else {
      LOG.error("Unable to find entity for delete [class=" + entity.getClass() + " id=" + entity.getId() + "]");
    }
  }

}
