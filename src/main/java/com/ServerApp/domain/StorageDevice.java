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
@Table(name="storage_devices")
@Getter @Setter @NoArgsConstructor
public class StorageDevice {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="storage_device_id")
	private Integer storageDeviceId;
    
  	@Column(name="server_id")
	private Integer serverId;
    
  	@Column(name="capacity_g_b")
	private Integer capacityGB;
    
  	@Column(name="storage_type")
	private String storageType;
    
  	@Column(name="status")
	private String status;
    

}
