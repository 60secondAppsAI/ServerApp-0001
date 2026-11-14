package com.ServerApp.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class ProcessorDTO {

	private Integer processorId;

	private Integer serverId;

	private String model;

	private Integer coreCount;

	private Double clockSpeedGHz;






}
