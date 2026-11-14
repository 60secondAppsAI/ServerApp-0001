package com.ServerApp.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class BrowseStorageDeviceDTO {

	private Integer ownerId;

	private Integer storageDeviceId;

	private Integer storageDeviceStatus;
	
	private Integer nextOrPrevious;
}

