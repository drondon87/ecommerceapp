package com.ecommerce.app.controllers;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ecommerce.app.exceptions.EcommerceException;
import com.ecommerce.app.jsons.PriceSearchParamRest;
import com.ecommerce.app.models.data.EcommerceData;
import com.ecommerce.app.models.entity.Price;
import com.ecommerce.app.response.EcommerceResponse;
import com.ecommerce.app.response.PriceResponse;
import com.ecommerce.app.services.IPriceService;
import com.ecommerce.app.utils.constants.EcommerceConstants;

public class PriceControllerTestCases {

	private static final String SUCCESS_STATUS = "Success";
	private static final int SUCCESS_CODE = 200;
	private static final String OK = "OK";
	public static final List<Price> PRICES = new ArrayList<>();
	public static final List<PriceResponse> PRICES_RESPONSE = new ArrayList<>();
	public static final Long PRODUCT = 35455L;
	public static final Long BRAND = 1L;
	public static final String PRODUCT_REST = "35455";
	public static final String BRAND_REST = "1";
	public static final String DATE_TEST = "2020-06-14 10:00:00";
	private static final String DATA_TYPE_NOT_ALLOWED = "DATA_TYPE_NOT_ALLOWED";
	private static final int INTERNAL_SERVER_CODE = 500;
	private static final String INTERNAL_SERVER_MSJ = "500 INTERNAL_SERVER_ERROR";
	public static final String DATA_TYPE_NOT_ALLOWED_MSJ = "Data type not allowed";

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

		Mockito.when(service.findAllPrices()).thenReturn(PRICES);

		Date dateTest = new SimpleDateFormat(EcommerceConstants.DATE_FORMAT).parse(DATE_TEST);
		Mockito.when(service.findAllPricesBySearchParams(dateTest, PRODUCT, BRAND)).thenReturn(PRICES_RESPONSE);

	}

	@Test
	public void listPrices() throws EcommerceException {
		final EcommerceResponse<?> response = underTest.priceList();
		assertEquals(response.getStatus(), SUCCESS_STATUS);
		assertEquals(response.getCode(), SUCCESS_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), PRICES);
	}

	@Test
	public void listPricesByParamsTest1() throws EcommerceException {

		PriceSearchParamRest paramsSearchTest1 = new PriceSearchParamRest(DATE_TEST, PRODUCT_REST, BRAND_REST);

		final EcommerceResponse<?> response = underTest.priceListWithParams(paramsSearchTest1);
		assertEquals(response.getStatus(), SUCCESS_STATUS);
		assertEquals(response.getCode(), SUCCESS_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), PRICES);
	}

	@Test(expected = AssertionError.class)
	public void listPricesByParamsTestWrongDate() throws EcommerceException {

		PriceSearchParamRest paramsSearchWrongDate = new PriceSearchParamRest("2020-06-10l", PRODUCT_REST, BRAND_REST);
		final EcommerceResponse<?> response = underTest.priceListWithParams(paramsSearchWrongDate);
		assertEquals(response.getStatus(), DATA_TYPE_NOT_ALLOWED);
		assertEquals(response.getCode(), INTERNAL_SERVER_CODE);
		assertEquals(response.getMessage(), INTERNAL_SERVER_MSJ);
		assertEquals(response.getData(), DATA_TYPE_NOT_ALLOWED_MSJ);
		fail();
	}
	
	@Test(expected = AssertionError.class)
	public void listPricesByParamsTestWrongLong() throws EcommerceException {

		PriceSearchParamRest paramsSearchWrongLong = new PriceSearchParamRest(DATE_TEST, "2L", BRAND_REST);
		final EcommerceResponse<?> response = underTest.priceListWithParams(paramsSearchWrongLong);
		assertEquals(response.getStatus(), DATA_TYPE_NOT_ALLOWED);
		assertEquals(response.getCode(), INTERNAL_SERVER_CODE);
		assertEquals(response.getMessage(), INTERNAL_SERVER_MSJ);
		assertEquals(response.getData(), DATA_TYPE_NOT_ALLOWED_MSJ);
		fail();
	}

}
