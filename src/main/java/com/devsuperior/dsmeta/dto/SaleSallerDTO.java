package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleProjection;

import java.time.LocalDate;

public class SaleSallerDTO {
  private Long id;
  private Double amount;
  private LocalDate date;
  private String name;

  public SaleSallerDTO(String name) {

    this.name = name;
  }

  public  SaleSallerDTO(SaleProjection projection){
    id = projection.getId();
    amount = projection.getAmount();
    date = projection.getDate();
    name = projection.getName();
  }
  public SaleSallerDTO(Sale entity) {
    id = entity.getId();
    amount = entity.getAmount();
    date = entity.getDate();
    name = entity.getSeller().getName();
  }

  public Long getId() {
    return id;
  }

  public Double getAmount() {
    return amount;
  }

  public LocalDate getDate() {
    return date;
  }

  public String getName() {
    return name;
  }
}
