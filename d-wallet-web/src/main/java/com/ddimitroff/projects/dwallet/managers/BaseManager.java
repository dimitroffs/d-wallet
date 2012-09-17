package com.ddimitroff.projects.dwallet.managers;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ddimitroff.projects.dwallet.db.entities.BaseEntity;

public interface BaseManager<E extends BaseEntity> {

  @Transactional(readOnly = true)
  public E getById(Class<E> clazz, int id);

  @Transactional(readOnly = true)
  public List<E> getAll(Class<E> clazz);

  @Transactional
  public void save(E entity);

  @Transactional
  public void delete(E entity);

}
