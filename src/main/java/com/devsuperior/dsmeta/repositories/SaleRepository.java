package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.projections.SaleProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {

  //JPQL
  @Query(value =
       " SELECT obj FROM Sale obj JOIN obj.seller " +
       " WHERE obj.date >= :minDate " +
       " AND obj.date <= :maxDate " +
       " AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%')) ")
  Page<ReportDTO> search1(@Param("minDate")LocalDate minDate, @Param("maxDate")LocalDate maxDate, @Param("name") String name, Pageable pageable);

}