package com.ServerApp.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseProcessorDTO {

	private Integer ownerId;

	private Integer processorId;

	private Integer processorStatus;
	
	private Integer nextOrPrevious;
}

