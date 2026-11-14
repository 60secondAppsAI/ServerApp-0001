package com.ServerApp.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.ServerApp.domain.MemoryModule;
import com.ServerApp.dto.MemoryModuleDTO;
import com.ServerApp.dto.MemoryModuleSearchDTO;
import com.ServerApp.dto.MemoryModulePageDTO;
import com.ServerApp.dto.MemoryModuleConvertCriteriaDTO;
import com.ServerApp.service.GenericService;
import com.ServerApp.dto.common.RequestDTO;
import com.ServerApp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MemoryModuleService extends GenericService<MemoryModule, Integer> {

	List<MemoryModule> findAll();

	ResultDTO addMemoryModule(MemoryModuleDTO memoryModuleDTO, RequestDTO requestDTO);

	ResultDTO updateMemoryModule(MemoryModuleDTO memoryModuleDTO, RequestDTO requestDTO);

    Page<MemoryModule> getAllMemoryModules(Pageable pageable);

    Page<MemoryModule> getAllMemoryModules(Specification<MemoryModule> spec, Pageable pageable);

	ResponseEntity<MemoryModulePageDTO> getMemoryModules(MemoryModuleSearchDTO memoryModuleSearchDTO);
	
	List<MemoryModuleDTO> convertMemoryModulesToMemoryModuleDTOs(List<MemoryModule> memoryModules, MemoryModuleConvertCriteriaDTO convertCriteria);

	MemoryModuleDTO getMemoryModuleDTOById(Integer memoryModuleId);



	
}
