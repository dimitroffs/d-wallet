package com.ddimitroff.projects.dwallet.db.dao;

import java.util.List;

import com.ddimitroff.projects.dwallet.db.entities.BaseEntity;

/**
 * Interface for all data access objects
 * 
 * @author Dimitar Dimitrov
 * 
 * @param <E>
 *          should extends {@link BaseEntity} object
 */
public interface BaseDAO<E extends BaseEntity> {

  /**
   * Interface method for getting entity object by id from database
   * 
   * @param clazz
   *          - entity object's class
   * @param id
   *          - entity objects's id
   * 
   * @return entity object, specified by class and id parameters, {@code null}
   *         otherwise
   */
  public E getById(Class<E> clazz, int id);

  /**
   * Interface method for getting all entity objects of specified class
   * 
   * @param clazz
   *          - entity objects' class
   * 
   * @return {@link List} of all entity objects, specified by class parameter
   */
  public List<E> getAll(Class<E> clazz);

  /**
   * Interface method for save/update an entity of specified class
   * 
   * @param entity
   *          - entity to be saved
   */
  public void save(E entity);

  /**
   * Interface method for delete an entity of specified class
   * 
   * @param entity
   *          - entity to be deleted
   */
  public void delete(E entity);

}
