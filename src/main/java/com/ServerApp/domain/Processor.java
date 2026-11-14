package com.ServerApp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;

@Entity
@Table(name="processors")
@Getter @Setter @NoArgsConstructor
public class Processor {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="processor_id")
	private Integer processorId;
    
  	@Column(name="server_id")
	private Integer serverId;
    
  	@Column(name="model")
	private String model;
    
  	@Column(name="core_count")
	private Integer coreCount;
    
  	@Column(name="clock_speed_g_hz")
	private Double clockSpeedGHz;
    

}
