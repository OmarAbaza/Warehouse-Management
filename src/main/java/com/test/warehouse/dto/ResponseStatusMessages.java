package com.test.warehouse.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ResponseStatusMessages {

	DEVICE_RETRIEVED_SUCCESSFULLY("Device data is retrieved successfully"),
	DEVICES_RETRIEVED_SUCCESSFULLY("Devices data are retrieved successfully"),
	DEVICE_ADDED_SUCCESSFULLY("Device data is add successfully"),
	DEVICE_ADDITION_FAILED("Error occured while saving the device data"),
	DEVICE_UPDATED_SUCCESSFULLY("Device data is updated successfully"),
	DEVICE_UPDATE_FAILED("Error occured while updating the device data"),
	DEVICE_PATCHED_SUCCESSFULLY("Device data is patched successfully"),
	DEVICE_PATCH_FAILED("Error occured while patching the device data"),
	DEVICE_DELETED_SUCCESSFULLY("Device data is deleted successfully"),
	UniqueDevicePinCode_CONISTRAINT_DUBLICATE("Pin code exists for another device"),
	UniqueDeviceSerialNumber_CONISTRAINT_DUBLICATE("Serial number exists for another device"),
	DEVICE_NOT_FOUND("Device not found"),
	DATA_VALIDATION_FAILED("Data validation failed"),
	INVALID_DATA("Request contains invalid data"),
	TECHNICAL_FAILURE("Technical failure occurred, Please contact support team"),
	ACCESS_DENIED("User doesn't have the required permission level to perform this action");

	private String message;

	private ResponseStatusMessages(String message) {
		this.message = message;
	}

	@JsonValue
	public String getMessage() {
		return this.message;
	}

}
