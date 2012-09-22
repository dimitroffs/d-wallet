package com.ddimitroff.projects.dwallet.managers;

import java.util.List;

import com.ddimitroff.projects.dwallet.db.entities.BaseEntity;

public interface BaseManager<E extends BaseEntity> {

  public E getById(Class<E> clazz, int id);

  public List<E> getAll(Class<E> clazz);

  public void save(E entity);

  public void delete(E entity);

}
