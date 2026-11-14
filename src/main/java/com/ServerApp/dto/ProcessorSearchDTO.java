package com.ServerApp.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProcessorSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer processorId;
	
	private Integer serverId;
	
	private String model;
	
	private Integer coreCount;
	
	private Double clockSpeedGHz;
	
}
