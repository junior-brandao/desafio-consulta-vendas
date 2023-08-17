package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleSallerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;
@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleSallerDTO>> getReport(
					@PageableDefault(page = 0,size = 10,sort = "id", direction = Sort.Direction.ASC)
					@RequestParam(name = "name",defaultValue = "")String name,
					@RequestParam(name = "minDate",required = false)String minDate,
					@RequestParam(name = "maxDate",required = false)String maxDate,
					Pageable pageable
	     ) {

		Page<SaleSallerDTO> dto = service.findAll(minDate,maxDate,name,pageable);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<?> getSummary() {
		// TODO
		return null;
	}
}
