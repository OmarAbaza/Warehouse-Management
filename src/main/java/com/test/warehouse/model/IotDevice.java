package com.test.warehouse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(indexes = { @Index(name = "UniqueDevicePinCode", columnList = "status, sold, pinCode ASC", unique = true),
		@Index(name = "UniqueDeviceSerialNumber", columnList = "serialNumber", unique = true) })
public class IotDevice {
	@Id
	@GeneratedValue
	@JsonIgnore
	private Long id;
	@Column(nullable = false)
	private String serialNumber;
	@Column(nullable = false)
	private String pinCode;
	@Enumerated(EnumType.STRING)
	private DeviceStatus status;
    @Column(columnDefinition = "integer default -1")
	private Integer temp;
    @Column(columnDefinition = "boolean default false")

	private Boolean sold;
}
