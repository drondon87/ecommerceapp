package com.ecommerce.app.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ecommerce.app.exceptions.EcommerceException;
import com.ecommerce.app.models.data.EcommerceData;
import com.ecommerce.app.models.entity.Price;
import com.ecommerce.app.response.EcommerceResponse;
import com.ecommerce.app.services.IPriceService;

public class PriceControllerTestCases {

	private static final String SUCCESS_STATUS = "Success";
	private static final int SUCCESS_CODE = 200;
	private static final String OK = "OK";
	public static final List<Price> PRICES = new ArrayList<>();

	@Mock
	IPriceService service;

	@InjectMocks
	PriceController underTest;

	@Before
	public void init() throws EcommerceException, ParseException {

		MockitoAnnotations.initMocks(this);

		List<Price> dataPrices = EcommerceData.getListPrices();
		Long index = 1L;
		for (Price price : dataPrices) {
			price.setId(index++);
			PRICES.add(price);
		}

	}

	@Test
	public void listPrices() throws EcommerceException {
		final EcommerceResponse<?> response = underTest.priceList();
		assertEquals(response.getStatus(), SUCCESS_STATUS);
		assertEquals(response.getCode(), SUCCESS_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), PRICES);
	}

}
