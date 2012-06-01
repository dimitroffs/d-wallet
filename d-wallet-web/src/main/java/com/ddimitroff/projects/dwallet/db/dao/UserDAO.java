package com.ddimitroff.projects.dwallet.db.dao;

import com.ddimitroff.projects.dwallet.db.entities.User;

public interface UserDAO extends BaseDAO<User> {

	public User getUserByCredentials(String email, String password);

	public User getUserByEmail(String email);

}
