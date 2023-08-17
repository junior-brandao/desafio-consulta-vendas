package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSallerDTO;
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

  //JPQL
  @Query(value =

                  " SELECT obj FROM Sale obj JOIN obj.seller " +
                  " WHERE obj.date >= :min " +
                  " AND obj.date <= :max " +
                  " AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%')) ")
  Page<SaleSallerDTO> search1(@Param("min")LocalDate min, @Param("max")LocalDate max, @Param("name") String name, Pageable pageable);

  //SQL
  @Query(value = " SELECT  tb_sales.id, tb_sales.date, tb_sales.amount,tb_seller.name " +
          " FROM tb_sales " +
          " INNER JOIN tb_seller ON tb_sales.seller_id = tb_seller.id " +
          " WHERE tb_sales.date BETWEEN :min AND :max "+
          " AND UPPER(tb_seller.name) LIKE UPPER(CONCAT('%',:name)) "+
          "", countQuery = "" +
          " SELECT  tb_sales.id, tb_sales.date, tb_sales.amount,tb_seller.name " +
          " FROM tb_sales " +
          " INNER JOIN tb_seller ON tb_sales.seller_id = tb_seller.id " +
          " WHERE tb_sales.date BETWEEN :min AND :max "+
          " AND UPPER(tb_seller.name) LIKE UPPER(CONCAT('%',:name)) "

          ,nativeQuery = true)
  Page<SaleProjection> search2( LocalDate min, LocalDate max, @Param("name")String name,Pageable pageable);


}