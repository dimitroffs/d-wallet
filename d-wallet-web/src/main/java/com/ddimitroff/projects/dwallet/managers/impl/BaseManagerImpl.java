package com.ddimitroff.projects.dwallet.managers.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import com.ddimitroff.projects.dwallet.db.dao.BaseDAO;
import com.ddimitroff.projects.dwallet.db.entities.BaseEntity;
import com.ddimitroff.projects.dwallet.managers.BaseManager;

/**
 * Implementation class of {@link BaseManager} interface
 * 
 * @author Dimitar Dimitrov
 * 
 * @param <E>
 *          should extends {@link BaseEntity} object
 */
public abstract class BaseManagerImpl<E extends BaseEntity> implements BaseManager<E> {

  /** Data access object to use */
  protected BaseDAO<E> baseDao;

  public E getById(Class<E> clazz, int id) {
    return baseDao.getById(clazz, id);
  }

  public List<E> getAll(Class<E> clazz) {
    return baseDao.getAll(clazz);
  }

  public void save(E entity) {
    baseDao.save(entity);
  }

  public void delete(E entity) {
    baseDao.delete(entity);
  }

  /**
   * A method, executed after construction of {@link BaseManagerImpl} class
   */
  @PostConstruct
  public abstract void postConstruct();

}
