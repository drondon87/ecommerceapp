package com.ecommerce.app.jsons;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PriceSearchParamRest {

	@JsonProperty("applicationDate")
	private String applicationDate;
	
	@JsonProperty("product")
	private String product;
	
	@JsonProperty("brand")
	private String brand;

	public String getApplicationDate() {
		return applicationDate;
	}

	public PriceSearchParamRest() {
		super();
	}

	public PriceSearchParamRest(String applicationDate, String product, String brand) {
		super();
		this.applicationDate = applicationDate;
		this.product = product;
		this.brand = brand;
	}

	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

}
