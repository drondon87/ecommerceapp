package com.ecommerce.app.models.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.app.models.entity.Price;
import com.ecommerce.app.utils.constants.EcommerceConstants;

public class EcommerceData {

	public static List<Price> getListPrices() throws ParseException {

		Price price = new Price();
		price.setBrandId(EcommerceConstants.BRAND_ID);
		price.setStartDate(new SimpleDateFormat(EcommerceConstants.DATE_FORMAT).parse("2020-06-14 00:00:00"));
		price.setEndDate(new SimpleDateFormat(EcommerceConstants.DATE_FORMAT).parse("2020-12-31 23:59:59"));
		price.setPriceList(1l);
		price.setProductId(EcommerceConstants.PRODUCT_ID);
		price.setPriority(0l);
		price.setPrice(35.50);
		price.setCurr(EcommerceConstants.CURR);

		Price price2 = new Price();
		price2.setBrandId(EcommerceConstants.BRAND_ID);
		price2.setStartDate(new SimpleDateFormat(EcommerceConstants.DATE_FORMAT).parse("2020-06-14 15:00:00"));
		price2.setEndDate(new SimpleDateFormat(EcommerceConstants.DATE_FORMAT).parse("2020-06-14 18:30:00"));
		price2.setPriceList(2l);
		price2.setProductId(EcommerceConstants.PRODUCT_ID);
		price2.setPriority(EcommerceConstants.PRIORITY);
		price2.setPrice(25.45);
		price2.setCurr(EcommerceConstants.CURR);

		Price price3 = new Price();
		price3.setBrandId(EcommerceConstants.BRAND_ID);
		price3.setStartDate(new SimpleDateFormat(EcommerceConstants.DATE_FORMAT).parse("2020-06-15 00:00:00"));
		price3.setEndDate(new SimpleDateFormat(EcommerceConstants.DATE_FORMAT).parse("2020-06-15 11:00:00"));
		price3.setPriceList(3l);
		price3.setProductId(EcommerceConstants.PRODUCT_ID);
		price3.setPriority(EcommerceConstants.PRIORITY);
		price3.setPrice(35.50);
		price3.setCurr(EcommerceConstants.CURR);

		Price price4 = new Price();
		price4.setBrandId(EcommerceConstants.BRAND_ID);
		price4.setStartDate(new SimpleDateFormat(EcommerceConstants.DATE_FORMAT).parse("2020-06-15 16:00:00"));
		price4.setEndDate(new SimpleDateFormat(EcommerceConstants.DATE_FORMAT).parse("2020-12-31 23:59:59"));
		price4.setPriceList(4l);
		price4.setProductId(EcommerceConstants.PRODUCT_ID);
		price4.setPriority(EcommerceConstants.PRIORITY);
		price4.setPrice(38.95);
		price4.setCurr(EcommerceConstants.CURR);

		List<Price> prices = new ArrayList<>();
		prices.add(price);
		prices.add(price2);
		prices.add(price3);
		prices.add(price4);

		return prices;
	}
}
