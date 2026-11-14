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
import com.ServerApp.dao.ProcessorDAO;
import com.ServerApp.domain.Processor;
import com.ServerApp.dto.ProcessorDTO;
import com.ServerApp.dto.ProcessorSearchDTO;
import com.ServerApp.dto.ProcessorPageDTO;
import com.ServerApp.dto.ProcessorConvertCriteriaDTO;
import com.ServerApp.dto.common.RequestDTO;
import com.ServerApp.dto.common.ResultDTO;
import com.ServerApp.service.ProcessorService;
import com.ServerApp.util.ControllerUtils;


@Service
public class ProcessorServiceImpl extends GenericServiceImpl<Processor, Integer> implements ProcessorService {

    private final static Logger logger = LoggerFactory.getLogger(ProcessorServiceImpl.class);

	@Autowired
	ProcessorDAO processorDao;

	

	@Override
	public GenericDAO<Processor, Integer> getDAO() {
		return (GenericDAO<Processor, Integer>) processorDao;
	}
	
	public List<Processor> findAll () {
		List<Processor> processors = processorDao.findAll();
		
		return processors;	
		
	}

	public ResultDTO addProcessor(ProcessorDTO processorDTO, RequestDTO requestDTO) {

		Processor processor = new Processor();

		processor.setProcessorId(processorDTO.getProcessorId());

		processor.setServerId(processorDTO.getServerId());

		processor.setModel(processorDTO.getModel());

		processor.setCoreCount(processorDTO.getCoreCount());

		processor.setClockSpeedGHz(processorDTO.getClockSpeedGHz());

		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		
		processor = processorDao.save(processor);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Processor> getAllProcessors(Pageable pageable) {
		return processorDao.findAll(pageable);
	}

	public Page<Processor> getAllProcessors(Specification<Processor> spec, Pageable pageable) {
		return processorDao.findAll(spec, pageable);
	}

	public ResponseEntity<ProcessorPageDTO> getProcessors(ProcessorSearchDTO processorSearchDTO) {
	
			Integer processorId = processorSearchDTO.getProcessorId(); 
 			Integer serverId = processorSearchDTO.getServerId(); 
 			String model = processorSearchDTO.getModel(); 
 			Integer coreCount = processorSearchDTO.getCoreCount(); 
  			String sortBy = processorSearchDTO.getSortBy();
			String sortOrder = processorSearchDTO.getSortOrder();
			String searchQuery = processorSearchDTO.getSearchQuery();
			Integer page = processorSearchDTO.getPage();
			Integer size = processorSearchDTO.getSize();

	        Specification<Processor> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, processorId, "processorId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, serverId, "serverId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, model, "model"); 
			
			spec = ControllerUtils.andIfNecessary(spec, coreCount, "coreCount"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("model")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Processor> processors = this.getAllProcessors(spec, pageable);
		
		//System.out.println(String.valueOf(processors.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(processors.getTotalPages()));
		
		List<Processor> processorsList = processors.getContent();
		
		ProcessorConvertCriteriaDTO convertCriteria = new ProcessorConvertCriteriaDTO();
		List<ProcessorDTO> processorDTOs = this.convertProcessorsToProcessorDTOs(processorsList,convertCriteria);
		
		ProcessorPageDTO processorPageDTO = new ProcessorPageDTO();
		processorPageDTO.setProcessors(processorDTOs);
		processorPageDTO.setTotalElements(processors.getTotalElements());
		return ResponseEntity.ok(processorPageDTO);
	}

	public List<ProcessorDTO> convertProcessorsToProcessorDTOs(List<Processor> processors, ProcessorConvertCriteriaDTO convertCriteria) {
		
		List<ProcessorDTO> processorDTOs = new ArrayList<ProcessorDTO>();
		
		for (Processor processor : processors) {
			processorDTOs.add(convertProcessorToProcessorDTO(processor,convertCriteria));
		}
		
		return processorDTOs;

	}
	
	public ProcessorDTO convertProcessorToProcessorDTO(Processor processor, ProcessorConvertCriteriaDTO convertCriteria) {
		
		ProcessorDTO processorDTO = new ProcessorDTO();

		processorDTO.setProcessorId(processor.getProcessorId());

		processorDTO.setServerId(processor.getServerId());

		processorDTO.setModel(processor.getModel());

		processorDTO.setCoreCount(processor.getCoreCount());

		processorDTO.setClockSpeedGHz(processor.getClockSpeedGHz());
		
		return processorDTO;
	}

	public ResultDTO updateProcessor(ProcessorDTO processorDTO, RequestDTO requestDTO) {
		
		Processor processor = processorDao.getById(processorDTO.getProcessorId());
		
		processor.setProcessorId(ControllerUtils.setValue(processor.getProcessorId(), processorDTO.getProcessorId()));
		
		processor.setServerId(ControllerUtils.setValue(processor.getServerId(), processorDTO.getServerId()));
		
		processor.setModel(ControllerUtils.setValue(processor.getModel(), processorDTO.getModel()));
		
		processor.setCoreCount(ControllerUtils.setValue(processor.getCoreCount(), processorDTO.getCoreCount()));
		
		processor.setClockSpeedGHz(ControllerUtils.setValue(processor.getClockSpeedGHz(), processorDTO.getClockSpeedGHz()));

        processor = processorDao.save(processor);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ProcessorDTO getProcessorDTOById(Integer processorId) {
	
		Processor processor = processorDao.getById(processorId);
		
		ProcessorConvertCriteriaDTO convertCriteria = new ProcessorConvertCriteriaDTO();
		return(this.convertProcessorToProcessorDTO(processor,convertCriteria));
	}

}
