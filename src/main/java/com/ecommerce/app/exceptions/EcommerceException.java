package com.ecommerce.app.exceptions;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.app.models.dto.ErrorDTO;

public class EcommerceException extends Exception{
	
	private static final long serialVersionUID = -6127737162547125177L;

	private final String code;

	private final int responseCode;

	private final List<ErrorDTO> errorList = new ArrayList<ErrorDTO>();

	public EcommerceException(String code, int responseCode, String message) {
		super(message);
		this.code = code;
		this.responseCode = responseCode;
	}

	public String getCode() {
		return code;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public List<ErrorDTO> getErrorList() {
		return errorList;
	}

}
