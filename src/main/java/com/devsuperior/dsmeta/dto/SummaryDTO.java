package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Seller;
import com.devsuperior.dsmeta.projections.SellerProjection;

import java.util.List;

public class SummaryDTO {

  private String name;
  private Double amount;

  public SummaryDTO(){}

  public SummaryDTO(String name, Double amount) {
    this.name = name;
    this.amount = amount;
  }

  public SummaryDTO(SellerProjection projection) {
    this.name = projection.getName();
    this.amount = projection.getAmount();
  }

  public SummaryDTO(Seller entity){
    name = entity.getName();

  }

  public String getName() {
    return name;
  }

  public Double getAmount() {
    return amount;
  }
}
