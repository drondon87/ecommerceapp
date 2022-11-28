package com.ecommerce.app.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ecommerce.app.exceptions.EcommerceException;
import com.ecommerce.app.models.data.EcommerceData;
import com.ecommerce.app.models.entity.Price;
import com.ecommerce.app.repositories.IPriceRepositoy;
import com.ecommerce.app.response.PriceResponse;
import com.ecommerce.app.services.impl.PriceServiceImpl;
import com.ecommerce.app.utils.constants.EcommerceConstants;

public class PriceServiceTestCases {

	public static final List<Price> PRICES = new ArrayList<>();
	public static final Long PRODUCT = 35455L;
	public static final Long BRAND = 1L;

	@Mock
	IPriceRepositoy repositoy;

	@InjectMocks
	PriceServiceImpl underTest;

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
		Mockito.when(repositoy.findAll()).thenReturn(PRICES);
		List<Price> response = underTest.findAllPrices();
		assertNotNull(response);
		assertEquals(response.get(0).getId(), PRICES.get(0).getId());
	}
	
	@Test
	public void listPricesTest1() throws EcommerceException, ParseException {
		
		//Date 2020-06-14 10:00:00
		Date dateTest1 = new SimpleDateFormat(EcommerceConstants.DATE_FORMAT).parse("2020-06-14 10:00:00");
		List<Price> pricesReturnTest1 = PRICES.stream().filter(p -> p.getId() != 1).collect(Collectors.toList());
		Mockito.when(repositoy.findAllBySeachParams(dateTest1, PRODUCT, BRAND)).thenReturn(pricesReturnTest1);
		
		List<PriceResponse> response = underTest.findAllPricesBySearchParams(dateTest1, PRODUCT, BRAND);
		
		assertNotNull(response);
		assertEquals(response.size(), pricesReturnTest1.size());
		assertEquals(response.get(0).getPrice(), pricesReturnTest1.get(0).getPrice()); //25.45
		assertEquals(response.get(1).getPrice(), pricesReturnTest1.get(1).getPrice()); //30.5
		assertEquals(response.get(2).getPrice(), pricesReturnTest1.get(2).getPrice()); //38.95
	}
	
	@Test
	public void listPricesTest2() throws EcommerceException, ParseException {
		
		//Date 2020-06-14 16:00:00
		Date dateTest2 = new SimpleDateFormat(EcommerceConstants.DATE_FORMAT).parse("2020-06-14 16:00:00");
		List<Price> pricesReturnTest2 = PRICES.stream().filter(p -> (p.getId() == 1 || p.getId() == 2)).collect(Collectors.toList());
		Mockito.when(repositoy.findAllBySeachParams(dateTest2, PRODUCT, BRAND)).thenReturn(pricesReturnTest2);
		
		List<PriceResponse> response = underTest.findAllPricesBySearchParams(dateTest2, PRODUCT, BRAND);
		
		assertNotNull(response);
		assertEquals(response.size(), pricesReturnTest2.size());
		assertEquals(response.get(0).getPrice(), pricesReturnTest2.get(0).getPrice()); //30.5
		assertEquals(response.get(1).getPrice(), pricesReturnTest2.get(1).getPrice()); //38.95
	}
	
	@Test
	public void listPricesTest3() throws EcommerceException, ParseException {
		
		//Date 2020-06-14 21:00:00
		Date dateTest3 = new SimpleDateFormat(EcommerceConstants.DATE_FORMAT).parse("2020-06-14 21:00:00");
		List<Price> pricesReturnTest3 = PRICES.stream().filter(p -> p.getId() != 1).collect(Collectors.toList());
		Mockito.when(repositoy.findAllBySeachParams(dateTest3, PRODUCT, BRAND)).thenReturn(pricesReturnTest3);
		
		List<PriceResponse> response = underTest.findAllPricesBySearchParams(dateTest3, PRODUCT, BRAND);
		
		assertNotNull(response);
		assertEquals(response.size(), pricesReturnTest3.size());
		assertEquals(response.get(0).getPrice(), pricesReturnTest3.get(0).getPrice());
		assertEquals(response.get(1).getPrice(), pricesReturnTest3.get(1).getPrice()); 
	}


}
