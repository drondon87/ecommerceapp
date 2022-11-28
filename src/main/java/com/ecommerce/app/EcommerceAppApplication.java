package com.ecommerce.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ecommerce.app.models.data.EcommerceData;
import com.ecommerce.app.models.entity.Price;
import com.ecommerce.app.repositories.IPriceRepositoy;

@SpringBootApplication
public class EcommerceAppApplication implements CommandLineRunner {

	@Autowired
	private IPriceRepositoy priceRepositoy;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Price> prices = EcommerceData.getListPrices();
		prices.forEach(priceRepositoy::save);
	}

}
