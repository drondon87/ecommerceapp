package com.ecommerce.app.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.app.exceptions.EcommerceException;
import com.ecommerce.app.jsons.PriceSearchParamRest;
import com.ecommerce.app.response.EcommerceResponse;
import com.ecommerce.app.services.IPriceService;
import com.ecommerce.app.utils.constants.EcommerceConstants;
import com.ecommerce.app.utils.constants.ResponseConstants;

@RestController
@RequestMapping("/api/v1")
public class PriceController {

	private static final Logger log = LoggerFactory.getLogger(PriceController.class);

	@Autowired
	private IPriceService priceService;

	@GetMapping("/price")
	public EcommerceResponse<?> priceList() throws EcommerceException {
		return new EcommerceResponse<>(ResponseConstants.SUCCESS, HttpStatus.OK.value(), HttpStatus.OK.toString(),
				priceService.findAllPrices());
	}

	@GetMapping("/price/search")
	public EcommerceResponse<?> priceListWithParams(@RequestBody PriceSearchParamRest searchParams)
			throws EcommerceException {

		DateFormat sourceFormat = new SimpleDateFormat(EcommerceConstants.DATE_FORMAT);
		Date applicationDateFormated = null;
		Long productFormatted = 0L;
		Long brandFormatted = 0L;

		try {
			applicationDateFormated = sourceFormat.parse(searchParams.getApplicationDate());
		} catch (ParseException e) {
			log.error("ERROR: " + e.getMessage());
			return new EcommerceResponse<>(ResponseConstants.DATA_TYPE_NOT_ALLOWED,
					HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					ResponseConstants.DATA_TYPE_NOT_ALLOWED_MSJ);
		}

		try {
			productFormatted = Long.parseLong(searchParams.getProduct());
			brandFormatted = Long.parseLong(searchParams.getBrand());
		} catch (NumberFormatException e) {
			log.error("ERROR: " + e.getMessage());
			return new EcommerceResponse<>(ResponseConstants.DATA_TYPE_NOT_ALLOWED,
					HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(),
					ResponseConstants.DATA_TYPE_NOT_ALLOWED_MSJ);
		}

		return new EcommerceResponse<>(ResponseConstants.SUCCESS, HttpStatus.OK.value(), HttpStatus.OK.toString(),
				priceService.findAllPricesBySearchParams(applicationDateFormated, productFormatted, brandFormatted));
	}

}
