package com.ServerApp.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseMemoryModuleDTO {

	private Integer ownerId;

	private Integer memoryModuleId;

	private Integer memoryModuleStatus;
	
	private Integer nextOrPrevious;
}

