package com.ServerApp.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StorageDeviceSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer storageDeviceId;
	
	private Integer serverId;
	
	private Integer capacityGB;
	
	private String storageType;
	
	private String status;
	
}
