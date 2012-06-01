package com.ddimitroff.projects.dwallet.managers;

import com.ddimitroff.projects.dwallet.db.entities.User;
import com.ddimitroff.projects.dwallet.rest.user.UserRO;

public interface UserManager extends BaseManager<User> {

	public User getUserByCredentials(String email, String password);

	public User getUserByEmail(String email);

	public UserRO convert(User entity);

	public User convert(UserRO ro);

	public User getConvertedUser(UserRO ro);

}
