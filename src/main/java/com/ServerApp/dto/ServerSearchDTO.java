package com.ServerApp.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ServerSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer serverId;
	
	private String hostname;
	
	private String serialNumber;
	
	private String manufacturer;
	
	private String model;
	
	private String status;
	
}
