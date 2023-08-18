package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleProjection;

import java.time.LocalDate;

public class ReportDTO {
  private Long id;
  private Double amount;
  private LocalDate date;
  private String name;


  public ReportDTO(String name) {

    this.name = name;
  }

  public ReportDTO(SaleProjection projection){
    id = projection.getId();
    amount = projection.getAmount();
    date = projection.getDate();
    name = projection.getName();
  }
  public ReportDTO(Sale entity) {
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
