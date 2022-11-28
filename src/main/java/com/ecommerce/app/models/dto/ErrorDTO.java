package com.ecommerce.app.models.dto;

import java.io.Serializable;

public class ErrorDTO implements Serializable{

	private static final long serialVersionUID = 7030167194881502485L;
	
	private String name;
	
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
