package com.ServerApp.dao;

import java.util.List;
import java.util.Date;

import com.ServerApp.dao.GenericDAO;
import com.ServerApp.domain.MemoryModule;




import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface MemoryModuleDAO extends GenericDAO<MemoryModule, Integer> {
  
	List<MemoryModule> findAll();
	


}

