package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.ReportDTO;
import com.devsuperior.dsmeta.projections.SaleProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.valueOf;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	@Transactional(readOnly = true)
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	@Transactional(readOnly = true)
	public Page<ReportDTO> findAll(
					 String minDate,String maxDate,
					 String name, Pageable pageable
	         ){
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
	  	  Page<ReportDTO> report = repository.search1(startDate, endDate, name,pageable);
		  	return report;
	  	}

}
