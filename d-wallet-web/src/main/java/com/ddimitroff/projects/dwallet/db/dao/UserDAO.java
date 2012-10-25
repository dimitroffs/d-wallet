package com.ddimitroff.projects.dwallet.db.dao;

import com.ddimitroff.projects.dwallet.db.entities.User;

/**
 * Interface for {@link User} entity object. Used to specify methods related
 * only to {@link User} entity object.
 * 
 * @author Dimitar Dimitrov
 * 
 */
public interface UserDAO extends BaseDAO<User> {

  /**
   * A method for getting {@link User} entity object by specifying its email and
   * password. Used for validating user after login operation.
   * 
   * @param email
   *          - email of user
   * @param password
   *          - password of user
   * 
   * @return {@link User} entity object if found in database, {@code null}
   *         otherwise
   */
  public User getUserByCredentials(String email, String password);

  /**
   * A method for getting {@link User} entity object by specifying its email.
   * Used for fast getting an already known user from database.
   * 
   * @param email
   *          - email of user
   * 
   * @return {@link User} entity object if found in database, {@code null}
   *         otherwise
   */
  public User getUserByEmail(String email);

}
