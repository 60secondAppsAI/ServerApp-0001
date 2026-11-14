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

import com.ServerApp.domain.StorageDevice;
import com.ServerApp.dto.StorageDeviceDTO;
import com.ServerApp.dto.StorageDeviceSearchDTO;
import com.ServerApp.dto.StorageDevicePageDTO;
import com.ServerApp.service.StorageDeviceService;
import com.ServerApp.dto.common.RequestDTO;
import com.ServerApp.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/storageDevice")
@RestController
public class StorageDeviceController {

	private final static Logger logger = LoggerFactory.getLogger(StorageDeviceController.class);

	@Autowired
	StorageDeviceService storageDeviceService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<StorageDevice> getAll() {

		List<StorageDevice> storageDevices = storageDeviceService.findAll();
		
		return storageDevices;	
	}

	@GetMapping(value = "/{storageDeviceId}")
	@ResponseBody
	public StorageDeviceDTO getStorageDevice(@PathVariable Integer storageDeviceId) {
		
		return (storageDeviceService.getStorageDeviceDTOById(storageDeviceId));
	}

 	@RequestMapping(value = "/addStorageDevice", method = RequestMethod.POST)
	public ResponseEntity<?> addStorageDevice(@RequestBody StorageDeviceDTO storageDeviceDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = storageDeviceService.addStorageDevice(storageDeviceDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/storageDevices")
	public ResponseEntity<StorageDevicePageDTO> getStorageDevices(StorageDeviceSearchDTO storageDeviceSearchDTO) {
 
		return storageDeviceService.getStorageDevices(storageDeviceSearchDTO);
	}	

	@RequestMapping(value = "/updateStorageDevice", method = RequestMethod.POST)
	public ResponseEntity<?> updateStorageDevice(@RequestBody StorageDeviceDTO storageDeviceDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = storageDeviceService.updateStorageDevice(storageDeviceDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}





}
