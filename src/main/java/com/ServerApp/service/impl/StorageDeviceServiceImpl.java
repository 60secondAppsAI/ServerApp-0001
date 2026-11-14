package com.ServerApp.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import com.ServerApp.dao.GenericDAO;
import com.ServerApp.service.GenericService;
import com.ServerApp.service.impl.GenericServiceImpl;
import com.ServerApp.dao.StorageDeviceDAO;
import com.ServerApp.domain.StorageDevice;
import com.ServerApp.dto.StorageDeviceDTO;
import com.ServerApp.dto.StorageDeviceSearchDTO;
import com.ServerApp.dto.StorageDevicePageDTO;
import com.ServerApp.dto.StorageDeviceConvertCriteriaDTO;
import com.ServerApp.dto.common.RequestDTO;
import com.ServerApp.dto.common.ResultDTO;
import com.ServerApp.service.StorageDeviceService;
import com.ServerApp.util.ControllerUtils;


@Service
public class StorageDeviceServiceImpl extends GenericServiceImpl<StorageDevice, Integer> implements StorageDeviceService {

    private final static Logger logger = LoggerFactory.getLogger(StorageDeviceServiceImpl.class);

	@Autowired
	StorageDeviceDAO storageDeviceDao;

	

	@Override
	public GenericDAO<StorageDevice, Integer> getDAO() {
		return (GenericDAO<StorageDevice, Integer>) storageDeviceDao;
	}
	
	public List<StorageDevice> findAll () {
		List<StorageDevice> storageDevices = storageDeviceDao.findAll();
		
		return storageDevices;	
		
	}

	public ResultDTO addStorageDevice(StorageDeviceDTO storageDeviceDTO, RequestDTO requestDTO) {

		StorageDevice storageDevice = new StorageDevice();

		storageDevice.setStorageDeviceId(storageDeviceDTO.getStorageDeviceId());

		storageDevice.setServerId(storageDeviceDTO.getServerId());

		storageDevice.setCapacityGB(storageDeviceDTO.getCapacityGB());

		storageDevice.setStorageType(storageDeviceDTO.getStorageType());

		storageDevice.setStatus(storageDeviceDTO.getStatus());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		storageDevice = storageDeviceDao.save(storageDevice);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<StorageDevice> getAllStorageDevices(Pageable pageable) {
		return storageDeviceDao.findAll(pageable);
	}

	public Page<StorageDevice> getAllStorageDevices(Specification<StorageDevice> spec, Pageable pageable) {
		return storageDeviceDao.findAll(spec, pageable);
	}

	public ResponseEntity<StorageDevicePageDTO> getStorageDevices(StorageDeviceSearchDTO storageDeviceSearchDTO) {
	
			Integer storageDeviceId = storageDeviceSearchDTO.getStorageDeviceId(); 
 			Integer serverId = storageDeviceSearchDTO.getServerId(); 
 			Integer capacityGB = storageDeviceSearchDTO.getCapacityGB(); 
 			String storageType = storageDeviceSearchDTO.getStorageType(); 
 			String status = storageDeviceSearchDTO.getStatus(); 
 			String sortBy = storageDeviceSearchDTO.getSortBy();
			String sortOrder = storageDeviceSearchDTO.getSortOrder();
			String searchQuery = storageDeviceSearchDTO.getSearchQuery();
			Integer page = storageDeviceSearchDTO.getPage();
			Integer size = storageDeviceSearchDTO.getSize();

	        Specification<StorageDevice> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, storageDeviceId, "storageDeviceId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, serverId, "serverId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, capacityGB, "capacityGB"); 
			
			spec = ControllerUtils.andIfNecessary(spec, storageType, "storageType"); 
			
			spec = ControllerUtils.andIfNecessary(spec, status, "status"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("storageType")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("status")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<StorageDevice> storageDevices = this.getAllStorageDevices(spec, pageable);
		
		//System.out.println(String.valueOf(storageDevices.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(storageDevices.getTotalPages()));
		
		List<StorageDevice> storageDevicesList = storageDevices.getContent();
		
		StorageDeviceConvertCriteriaDTO convertCriteria = new StorageDeviceConvertCriteriaDTO();
		List<StorageDeviceDTO> storageDeviceDTOs = this.convertStorageDevicesToStorageDeviceDTOs(storageDevicesList,convertCriteria);
		
		StorageDevicePageDTO storageDevicePageDTO = new StorageDevicePageDTO();
		storageDevicePageDTO.setStorageDevices(storageDeviceDTOs);
		storageDevicePageDTO.setTotalElements(storageDevices.getTotalElements());
		return ResponseEntity.ok(storageDevicePageDTO);
	}

	public List<StorageDeviceDTO> convertStorageDevicesToStorageDeviceDTOs(List<StorageDevice> storageDevices, StorageDeviceConvertCriteriaDTO convertCriteria) {
		
		List<StorageDeviceDTO> storageDeviceDTOs = new ArrayList<StorageDeviceDTO>();
		
		for (StorageDevice storageDevice : storageDevices) {
			storageDeviceDTOs.add(convertStorageDeviceToStorageDeviceDTO(storageDevice,convertCriteria));
		}
		
		return storageDeviceDTOs;

	}
	
	public StorageDeviceDTO convertStorageDeviceToStorageDeviceDTO(StorageDevice storageDevice, StorageDeviceConvertCriteriaDTO convertCriteria) {
		
		StorageDeviceDTO storageDeviceDTO = new StorageDeviceDTO();

		storageDeviceDTO.setStorageDeviceId(storageDevice.getStorageDeviceId());

		storageDeviceDTO.setServerId(storageDevice.getServerId());

		storageDeviceDTO.setCapacityGB(storageDevice.getCapacityGB());

		storageDeviceDTO.setStorageType(storageDevice.getStorageType());

		storageDeviceDTO.setStatus(storageDevice.getStatus());
		
		return storageDeviceDTO;
	}

	public ResultDTO updateStorageDevice(StorageDeviceDTO storageDeviceDTO, RequestDTO requestDTO) {
		
		StorageDevice storageDevice = storageDeviceDao.getById(storageDeviceDTO.getStorageDeviceId());
		
		storageDevice.setStorageDeviceId(ControllerUtils.setValue(storageDevice.getStorageDeviceId(), storageDeviceDTO.getStorageDeviceId()));
		
		storageDevice.setServerId(ControllerUtils.setValue(storageDevice.getServerId(), storageDeviceDTO.getServerId()));
		
		storageDevice.setCapacityGB(ControllerUtils.setValue(storageDevice.getCapacityGB(), storageDeviceDTO.getCapacityGB()));
		
		storageDevice.setStorageType(ControllerUtils.setValue(storageDevice.getStorageType(), storageDeviceDTO.getStorageType()));
		
		storageDevice.setStatus(ControllerUtils.setValue(storageDevice.getStatus(), storageDeviceDTO.getStatus()));

        storageDevice = storageDeviceDao.save(storageDevice);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public StorageDeviceDTO getStorageDeviceDTOById(Integer storageDeviceId) {
	
		StorageDevice storageDevice = storageDeviceDao.getById(storageDeviceId);
		
		StorageDeviceConvertCriteriaDTO convertCriteria = new StorageDeviceConvertCriteriaDTO();
		return(this.convertStorageDeviceToStorageDeviceDTO(storageDevice,convertCriteria));
	}

}
