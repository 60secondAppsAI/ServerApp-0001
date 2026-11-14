package com.ServerApp.service;

import java.util.List;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.ServerApp.domain.Server;
import com.ServerApp.dto.ServerDTO;
import com.ServerApp.dto.ServerSearchDTO;
import com.ServerApp.dto.ServerPageDTO;
import com.ServerApp.dto.ServerConvertCriteriaDTO;
import com.ServerApp.service.GenericService;
import com.ServerApp.dto.common.RequestDTO;
import com.ServerApp.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ServerService extends GenericService<Server, Integer> {

	List<Server> findAll();

	ResultDTO addServer(ServerDTO serverDTO, RequestDTO requestDTO);

	ResultDTO updateServer(ServerDTO serverDTO, RequestDTO requestDTO);

    Page<Server> getAllServers(Pageable pageable);

    Page<Server> getAllServers(Specification<Server> spec, Pageable pageable);

	ResponseEntity<ServerPageDTO> getServers(ServerSearchDTO serverSearchDTO);
	
	List<ServerDTO> convertServersToServerDTOs(List<Server> servers, ServerConvertCriteriaDTO convertCriteria);

	ServerDTO getServerDTOById(Integer serverId);



	
}
