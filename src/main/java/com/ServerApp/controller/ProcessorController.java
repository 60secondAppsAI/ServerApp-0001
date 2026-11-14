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

import com.ServerApp.domain.Processor;
import com.ServerApp.dto.ProcessorDTO;
import com.ServerApp.dto.ProcessorSearchDTO;
import com.ServerApp.dto.ProcessorPageDTO;
import com.ServerApp.service.ProcessorService;
import com.ServerApp.dto.common.RequestDTO;
import com.ServerApp.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/processor")
@RestController
public class ProcessorController {

	private final static Logger logger = LoggerFactory.getLogger(ProcessorController.class);

	@Autowired
	ProcessorService processorService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Processor> getAll() {

		List<Processor> processors = processorService.findAll();
		
		return processors;	
	}

	@GetMapping(value = "/{processorId}")
	@ResponseBody
	public ProcessorDTO getProcessor(@PathVariable Integer processorId) {
		
		return (processorService.getProcessorDTOById(processorId));
	}

 	@RequestMapping(value = "/addProcessor", method = RequestMethod.POST)
	public ResponseEntity<?> addProcessor(@RequestBody ProcessorDTO processorDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = processorService.addProcessor(processorDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/processors")
	public ResponseEntity<ProcessorPageDTO> getProcessors(ProcessorSearchDTO processorSearchDTO) {
 
		return processorService.getProcessors(processorSearchDTO);
	}	

	@RequestMapping(value = "/updateProcessor", method = RequestMethod.POST)
	public ResponseEntity<?> updateProcessor(@RequestBody ProcessorDTO processorDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = processorService.updateProcessor(processorDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
