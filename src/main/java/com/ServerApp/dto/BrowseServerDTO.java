package com.ServerApp.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseServerDTO {

	private Integer ownerId;

	private Integer serverId;

	private Integer serverStatus;
	
	private Integer nextOrPrevious;
}

