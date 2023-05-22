package com.test.warehouse.dto;

import com.test.warehouse.model.DeviceStatus;

import lombok.Data;

@Data
public class IotDeviceStatusDto {
	private String serialNumber;
	private DeviceStatus status;
	private Integer temp;
	private Boolean sold;
}