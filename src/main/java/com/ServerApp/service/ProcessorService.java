package com.ServerApp.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.ServerApp.domain.Processor;
import com.ServerApp.dto.ProcessorDTO;
import com.ServerApp.dto.ProcessorSearchDTO;
import com.ServerApp.dto.ProcessorPageDTO;
import com.ServerApp.dto.ProcessorConvertCriteriaDTO;
import com.ServerApp.service.GenericService;
import com.ServerApp.dto.common.RequestDTO;
import com.ServerApp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ProcessorService extends GenericService<Processor, Integer> {

	List<Processor> findAll();

	ResultDTO addProcessor(ProcessorDTO processorDTO, RequestDTO requestDTO);

	ResultDTO updateProcessor(ProcessorDTO processorDTO, RequestDTO requestDTO);

    Page<Processor> getAllProcessors(Pageable pageable);

    Page<Processor> getAllProcessors(Specification<Processor> spec, Pageable pageable);

	ResponseEntity<ProcessorPageDTO> getProcessors(ProcessorSearchDTO processorSearchDTO);
	
	List<ProcessorDTO> convertProcessorsToProcessorDTOs(List<Processor> processors, ProcessorConvertCriteriaDTO convertCriteria);

	ProcessorDTO getProcessorDTOById(Integer processorId);



	
}
