package com.ecommerce.app.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.app.exceptions.EcommerceException;
import com.ecommerce.app.models.entity.Price;
import com.ecommerce.app.repositories.IPriceRepositoy;
import com.ecommerce.app.response.PriceResponse;
import com.ecommerce.app.services.IPriceService;

@Service
public class PriceServiceImpl implements IPriceService {

	@Autowired
	private IPriceRepositoy priceRepository;

	@Override
	public List<Price> findAllPrices() throws EcommerceException {
		return priceRepository.findAll();
	}

	@Override
	public List<PriceResponse> findAllPricesBySearchParams(Date applicationDate, Long product, Long brand)
			throws EcommerceException {

		List<Price> prices = priceRepository.findAllBySeachParams(applicationDate, product, brand);

		List<PriceResponse> priceResponses = new ArrayList<>();

		prices.forEach(p -> {
			PriceResponse priceResponse = new PriceResponse();
			priceResponse.setProductId(p.getProductId());
			priceResponse.setBrandId(p.getBrandId());
			priceResponse.setApplicationDate(p.getStartDate());
			priceResponse.setCurr(p.getCurr());
			priceResponse.setPrice(p.getPrice());
			priceResponses.add(priceResponse);
		});

		return priceResponses;
	}

}
