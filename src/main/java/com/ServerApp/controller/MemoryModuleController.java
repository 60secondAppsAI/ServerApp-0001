package com.ServerApp.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.ZoneId;


import com.ServerApp.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.ServerApp.domain.MemoryModule;
import com.ServerApp.dto.MemoryModuleDTO;
import com.ServerApp.dto.MemoryModuleSearchDTO;
import com.ServerApp.dto.MemoryModulePageDTO;
import com.ServerApp.service.MemoryModuleService;
import com.ServerApp.dto.common.RequestDTO;
import com.ServerApp.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/memoryModule")
@RestController
public class MemoryModuleController {

	private final static Logger logger = LoggerFactory.getLogger(MemoryModuleController.class);

	@Autowired
	MemoryModuleService memoryModuleService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<MemoryModule> getAll() {

		List<MemoryModule> memoryModules = memoryModuleService.findAll();
		
		return memoryModules;	
	}

	@GetMapping(value = "/{memoryModuleId}")
	@ResponseBody
	public MemoryModuleDTO getMemoryModule(@PathVariable Integer memoryModuleId) {
		
		return (memoryModuleService.getMemoryModuleDTOById(memoryModuleId));
	}

 	@RequestMapping(value = "/addMemoryModule", method = RequestMethod.POST)
	public ResponseEntity<?> addMemoryModule(@RequestBody MemoryModuleDTO memoryModuleDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = memoryModuleService.addMemoryModule(memoryModuleDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/memoryModules")
	public ResponseEntity<MemoryModulePageDTO> getMemoryModules(MemoryModuleSearchDTO memoryModuleSearchDTO) {
 
		return memoryModuleService.getMemoryModules(memoryModuleSearchDTO);
	}	

	@RequestMapping(value = "/updateMemoryModule", method = RequestMethod.POST)
	public ResponseEntity<?> updateMemoryModule(@RequestBody MemoryModuleDTO memoryModuleDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = memoryModuleService.updateMemoryModule(memoryModuleDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
