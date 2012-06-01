package com.ddimitroff.projects.dwallet.db.dao.impl;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.ddimitroff.projects.dwallet.db.dao.UserDAO;
import com.ddimitroff.projects.dwallet.db.entities.User;

@Repository("userDao")
public class UserDAOImpl extends BaseDAOImpl<User> implements UserDAO {

	private static final Logger LOG = Logger.getLogger(UserDAOImpl.class);

	public User getUserByCredentials(String email, String password) {
		try {
			User user = (User) em.createNamedQuery(User.GET_USER_BY_CREDENTIALS).setParameter("email", email)
					.setParameter("password", password).getSingleResult();
			return user;
		} catch (NoResultException e) {
			LOG.error("Unable to find user by credentials [e-mail=" + email + "]");
			return null;
		}
	}

	public User getUserByEmail(String email) {
		try {
			return (User) em.createNamedQuery(User.GET_USER_BY_EMAIL).setParameter("email", email).getSingleResult();
		} catch (NoResultException ex) {
			LOG.error("Unable to find user [e-mail=" + email + "]");
			return null;
		}
	}

}
