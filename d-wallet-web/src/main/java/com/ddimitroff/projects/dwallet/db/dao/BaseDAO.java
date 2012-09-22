package com.ddimitroff.projects.dwallet.db.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ddimitroff.projects.dwallet.db.entities.BaseEntity;

public interface BaseDAO<E extends BaseEntity> {

  public E getById(Class<E> clazz, int id);

  public List<E> getAll(Class<E> clazz);

  public void save(E entity);

  public void delete(E entity);

}
