package com.ServerApp.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class MemoryModuleDTO {

	private Integer memoryModuleId;

	private Integer serverId;

	private Integer capacityGB;

	private String memoryType;






}
