package com.ecommerce.app.services;

import java.util.Date;
import java.util.List;

import com.ecommerce.app.exceptions.EcommerceException;
import com.ecommerce.app.models.entity.Price;
import com.ecommerce.app.response.PriceResponse;

public interface IPriceService {

	public List<Price> findAllPrices() throws EcommerceException;

	public List<PriceResponse> findAllPricesBySearchParams(Date applicationDate, Long product, Long brand) throws EcommerceException;

}
