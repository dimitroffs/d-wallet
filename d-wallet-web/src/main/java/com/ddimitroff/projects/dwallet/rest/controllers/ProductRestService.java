package com.ddimitroff.projects.dwallet.rest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ddimitroff.projects.dwallet.core.Product;

/**
 * This is an example REST style MVC controller. It serves as an endpoint for
 * retrieving Product Objects.
 */
@Controller
public class ProductRestService {

	/**
	 * This method returns a specific Product. The URI to request a Product is
	 * specified in the @RequestMapping.
	 * 
	 * @param productId
	 *          the identifier of the requested product
	 * @return a Product
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/products/{productId}")
	@ResponseBody
	public Product getProductById(@PathVariable String productId) {
		Product p = new Product();
		p.setName(productId);
		return p;
	}

}