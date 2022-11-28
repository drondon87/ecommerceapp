package com.ecommerce.app.response;

import java.io.Serializable;
import java.util.Date;

public class PriceResponse implements Serializable {

	private static final long serialVersionUID = -1744808285009692070L;

	private Long productId;

	private Long brandId;

	private String curr;

	private Double price;

	private Date applicationDate;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public String getCurr() {
		return curr;
	}

	public void setCurr(String curr) {
		this.curr = curr;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

}
