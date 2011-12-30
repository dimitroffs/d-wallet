package com.ddimitroff.projects.dwallet.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ddimitroff.projects.dwallet.db.UserDAO;
import com.ddimitroff.projects.dwallet.db.UserDAOManager;
import com.ddimitroff.projects.dwallet.rest.user.UserRO;

/**
 * This is an example REST style MVC controller. It serves as an endpoint for
 * retrieving Product Objects.
 */
@Controller
public class UsersRestService {

	@Autowired
	private UserDAOManager userManager;

	/**
	 * This method returns a specific Product. The URI to request a Product is
	 * specified in the @RequestMapping.
	 * 
	 * @param productId
	 *          the identifier of the requested product
	 * @return a Product
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/users/{productId}")
	@ResponseBody
	public UserRO getUserById(@PathVariable String productId) {
		UserDAO dao = userManager.getUserByName("mykob.11@gmail.com");
		UserRO ro = userManager.convert(dao);

		return ro;
	}

}