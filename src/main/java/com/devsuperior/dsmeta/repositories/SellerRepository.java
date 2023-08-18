package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.entities.Seller;
import com.devsuperior.dsmeta.projections.SellerProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SellerRepository extends JpaRepository<Seller, Long> {

  @Query(value =
          "SELECT tb_seller.name ,SUM(tb_sales.amount) as amount "+
          "FROM tb_seller "+
          "INNER JOIN tb_sales ON tb_sales.seller_id = tb_seller.id "+
          "WHERE tb_sales.date "+
          "BETWEEN :minDate AND :maxDate " +
          "AND UPPER(tb_seller.name) LIKE UPPER(CONCAT('%',:name)) "+
          "GROUP BY  tb_seller.name ",nativeQuery = true)
          List<SellerProjection> search1( @Param("name")String name, @Param("minDate") LocalDate minDate, @Param("maxDate")LocalDate maxDate);

}
