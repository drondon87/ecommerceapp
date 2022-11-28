package com.ecommerce.app.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.app.models.entity.Price;

public interface IPriceRepositoy extends JpaRepository<Price, Long> {

	@Query("select p from Price p where p.productId=:product and p.brandId = :brand and (p.startDate >= :aplicationDate or p.endDate <=:aplicationDate)")
	List<Price> findAllBySeachParams(@Param("aplicationDate") Date aplicationDate, @Param("product") Long product,
			@Param("brand") Long brand);

}
