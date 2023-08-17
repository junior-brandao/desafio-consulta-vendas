package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.projections.SaleProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;

public interface SaleRepository extends JpaRepository<Sale, Long> {

  @Query(value = " SELECT  tb_sales.id, tb_sales.date, tb_sales.amount,tb_seller.name " +
          "FROM tb_sales " +
          "INNER JOIN tb_seller ON tb_sales.seller_id = tb_seller.id " +
          "WHERE tb_sales.date BETWEEN :minDate AND :maxDate "+
          "AND UPPER(tb_seller.name) LIKE UPPER(CONCAT('%',:sallerName)) " ,nativeQuery = true)
  Page<SaleProjection> search1( LocalDate minDate, LocalDate maxDate, @Param("sallerName")String sallerName,Pageable pageable);

}
