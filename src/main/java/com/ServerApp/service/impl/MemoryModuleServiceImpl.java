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
import com.ServerApp.dao.MemoryModuleDAO;
import com.ServerApp.domain.MemoryModule;
import com.ServerApp.dto.MemoryModuleDTO;
import com.ServerApp.dto.MemoryModuleSearchDTO;
import com.ServerApp.dto.MemoryModulePageDTO;
import com.ServerApp.dto.MemoryModuleConvertCriteriaDTO;
import com.ServerApp.dto.common.RequestDTO;
import com.ServerApp.dto.common.ResultDTO;
import com.ServerApp.service.MemoryModuleService;
import com.ServerApp.util.ControllerUtils;


@Service
public class MemoryModuleServiceImpl extends GenericServiceImpl<MemoryModule, Integer> implements MemoryModuleService {

    private final static Logger logger = LoggerFactory.getLogger(MemoryModuleServiceImpl.class);

	@Autowired
	MemoryModuleDAO memoryModuleDao;

	

	@Override
	public GenericDAO<MemoryModule, Integer> getDAO() {
		return (GenericDAO<MemoryModule, Integer>) memoryModuleDao;
	}
	
	public List<MemoryModule> findAll () {
		List<MemoryModule> memoryModules = memoryModuleDao.findAll();
		
		return memoryModules;	
		
	}

	public ResultDTO addMemoryModule(MemoryModuleDTO memoryModuleDTO, RequestDTO requestDTO) {

		MemoryModule memoryModule = new MemoryModule();

		memoryModule.setMemoryModuleId(memoryModuleDTO.getMemoryModuleId());

		memoryModule.setServerId(memoryModuleDTO.getServerId());

		memoryModule.setCapacityGB(memoryModuleDTO.getCapacityGB());

		memoryModule.setMemoryType(memoryModuleDTO.getMemoryType());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		memoryModule = memoryModuleDao.save(memoryModule);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<MemoryModule> getAllMemoryModules(Pageable pageable) {
		return memoryModuleDao.findAll(pageable);
	}

	public Page<MemoryModule> getAllMemoryModules(Specification<MemoryModule> spec, Pageable pageable) {
		return memoryModuleDao.findAll(spec, pageable);
	}

	public ResponseEntity<MemoryModulePageDTO> getMemoryModules(MemoryModuleSearchDTO memoryModuleSearchDTO) {
	
			Integer memoryModuleId = memoryModuleSearchDTO.getMemoryModuleId(); 
 			Integer serverId = memoryModuleSearchDTO.getServerId(); 
 			Integer capacityGB = memoryModuleSearchDTO.getCapacityGB(); 
 			String memoryType = memoryModuleSearchDTO.getMemoryType(); 
 			String sortBy = memoryModuleSearchDTO.getSortBy();
			String sortOrder = memoryModuleSearchDTO.getSortOrder();
			String searchQuery = memoryModuleSearchDTO.getSearchQuery();
			Integer page = memoryModuleSearchDTO.getPage();
			Integer size = memoryModuleSearchDTO.getSize();

	        Specification<MemoryModule> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, memoryModuleId, "memoryModuleId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, serverId, "serverId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, capacityGB, "capacityGB"); 
			
			spec = ControllerUtils.andIfNecessary(spec, memoryType, "memoryType"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("memoryType")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<MemoryModule> memoryModules = this.getAllMemoryModules(spec, pageable);
		
		//System.out.println(String.valueOf(memoryModules.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(memoryModules.getTotalPages()));
		
		List<MemoryModule> memoryModulesList = memoryModules.getContent();
		
		MemoryModuleConvertCriteriaDTO convertCriteria = new MemoryModuleConvertCriteriaDTO();
		List<MemoryModuleDTO> memoryModuleDTOs = this.convertMemoryModulesToMemoryModuleDTOs(memoryModulesList,convertCriteria);
		
		MemoryModulePageDTO memoryModulePageDTO = new MemoryModulePageDTO();
		memoryModulePageDTO.setMemoryModules(memoryModuleDTOs);
		memoryModulePageDTO.setTotalElements(memoryModules.getTotalElements());
		return ResponseEntity.ok(memoryModulePageDTO);
	}

	public List<MemoryModuleDTO> convertMemoryModulesToMemoryModuleDTOs(List<MemoryModule> memoryModules, MemoryModuleConvertCriteriaDTO convertCriteria) {
		
		List<MemoryModuleDTO> memoryModuleDTOs = new ArrayList<MemoryModuleDTO>();
		
		for (MemoryModule memoryModule : memoryModules) {
			memoryModuleDTOs.add(convertMemoryModuleToMemoryModuleDTO(memoryModule,convertCriteria));
		}
		
		return memoryModuleDTOs;

	}
	
	public MemoryModuleDTO convertMemoryModuleToMemoryModuleDTO(MemoryModule memoryModule, MemoryModuleConvertCriteriaDTO convertCriteria) {
		
		MemoryModuleDTO memoryModuleDTO = new MemoryModuleDTO();

		memoryModuleDTO.setMemoryModuleId(memoryModule.getMemoryModuleId());

		memoryModuleDTO.setServerId(memoryModule.getServerId());

		memoryModuleDTO.setCapacityGB(memoryModule.getCapacityGB());

		memoryModuleDTO.setMemoryType(memoryModule.getMemoryType());
		
		return memoryModuleDTO;
	}

	public ResultDTO updateMemoryModule(MemoryModuleDTO memoryModuleDTO, RequestDTO requestDTO) {
		
		MemoryModule memoryModule = memoryModuleDao.getById(memoryModuleDTO.getMemoryModuleId());
		
		memoryModule.setMemoryModuleId(ControllerUtils.setValue(memoryModule.getMemoryModuleId(), memoryModuleDTO.getMemoryModuleId()));
		
		memoryModule.setServerId(ControllerUtils.setValue(memoryModule.getServerId(), memoryModuleDTO.getServerId()));
		
		memoryModule.setCapacityGB(ControllerUtils.setValue(memoryModule.getCapacityGB(), memoryModuleDTO.getCapacityGB()));
		
		memoryModule.setMemoryType(ControllerUtils.setValue(memoryModule.getMemoryType(), memoryModuleDTO.getMemoryType()));

        memoryModule = memoryModuleDao.save(memoryModule);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public MemoryModuleDTO getMemoryModuleDTOById(Integer memoryModuleId) {
	
		MemoryModule memoryModule = memoryModuleDao.getById(memoryModuleId);
		
		MemoryModuleConvertCriteriaDTO convertCriteria = new MemoryModuleConvertCriteriaDTO();
		return(this.convertMemoryModuleToMemoryModuleDTO(memoryModule,convertCriteria));
	}

}
