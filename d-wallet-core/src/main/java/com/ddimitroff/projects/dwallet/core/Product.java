package com.ddimitroff.projects.dwallet.core;

public class Product {
	private String name;

	public Product() {
	}

	public Product(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
