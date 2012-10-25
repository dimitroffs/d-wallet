package com.ddimitroff.projects.dwallet.managers;

import com.ddimitroff.projects.dwallet.db.entities.User;
import com.ddimitroff.projects.dwallet.rest.user.UserRO;

/**
 * Interface for {@link User} entity object manager. Used to specify methods
 * related only to {@link User} entity object.
 * 
 * @author Dimitar Dimitrov
 * 
 */
public interface UserManager extends BaseManager<User> {

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

  /**
   * A method for converting {@link User} entity object to {@link UserRO} object
   * 
   * @param entity
   *          - {@link User} entity object to convert
   * 
   * @return converted {@link UserRO} object
   */
  public UserRO convert(User entity);

  /**
   * A method for converting {@link UserRO} object to {@link User} entity
   * object. Used for registering new user.
   * 
   * @param ro
   *          - {@link UserRO} object to convert
   * 
   * @return converted {@link User} entity object
   */
  public User convert(UserRO ro);

  /**
   * A method for converting {@link UserRO} object to {@link User} entity
   * object. Used for registering new user.
   * 
   * @param ro
   *          - {@link UserRO} object to convert
   * 
   * @return converted {@link User} entity object
   */
  public User getConvertedUser(UserRO ro);

}
