package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SummaryDTO;
import com.devsuperior.dsmeta.projections.SellerProjection;
import com.devsuperior.dsmeta.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellerService {

  @Autowired
  private SellerRepository repository;

  @Transactional(readOnly = true)
  public List<SummaryDTO> findAll(String name, String minDate,String maxDate){

    LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
    LocalDate endDate;
    LocalDate startDate;

    if(maxDate==null){
      endDate= today;
    }else{
      endDate = LocalDate.parse(maxDate);
    }
    if(minDate==null) {
      startDate =  endDate.minusYears(1L);
    }else{
      startDate = LocalDate.parse(minDate);
    }
    List<SellerProjection> summary = repository.search1(name,startDate,endDate);
    return summary.stream().map(x-> new SummaryDTO(x)).collect(Collectors.toList());
  }

}
