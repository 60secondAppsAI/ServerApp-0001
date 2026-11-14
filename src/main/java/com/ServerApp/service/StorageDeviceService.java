package com.ServerApp.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.ServerApp.domain.StorageDevice;
import com.ServerApp.dto.StorageDeviceDTO;
import com.ServerApp.dto.StorageDeviceSearchDTO;
import com.ServerApp.dto.StorageDevicePageDTO;
import com.ServerApp.dto.StorageDeviceConvertCriteriaDTO;
import com.ServerApp.service.GenericService;
import com.ServerApp.dto.common.RequestDTO;
import com.ServerApp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface StorageDeviceService extends GenericService<StorageDevice, Integer> {

	List<StorageDevice> findAll();

	ResultDTO addStorageDevice(StorageDeviceDTO storageDeviceDTO, RequestDTO requestDTO);

	ResultDTO updateStorageDevice(StorageDeviceDTO storageDeviceDTO, RequestDTO requestDTO);

    Page<StorageDevice> getAllStorageDevices(Pageable pageable);

    Page<StorageDevice> getAllStorageDevices(Specification<StorageDevice> spec, Pageable pageable);

	ResponseEntity<StorageDevicePageDTO> getStorageDevices(StorageDeviceSearchDTO storageDeviceSearchDTO);
	
	List<StorageDeviceDTO> convertStorageDevicesToStorageDeviceDTOs(List<StorageDevice> storageDevices, StorageDeviceConvertCriteriaDTO convertCriteria);

	StorageDeviceDTO getStorageDeviceDTOById(Integer storageDeviceId);



	
}
