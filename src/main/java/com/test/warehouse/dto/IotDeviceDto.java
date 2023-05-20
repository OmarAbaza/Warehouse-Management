package com.test.warehouse.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.test.warehouse.model.DeviceStatus;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)

public class IotDeviceDto {
	private String serialNumber;
	private String pinCode;
	private DeviceStatus status;
	private Integer temp;
	private Boolean sold;
}