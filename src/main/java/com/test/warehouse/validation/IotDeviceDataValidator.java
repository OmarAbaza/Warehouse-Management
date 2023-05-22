package com.test.warehouse.validation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Range;
import com.test.warehouse.dto.IotDeviceDto;

public class IotDeviceDataValidator {

	private static IotDeviceDataValidator instance = null;

	private IotDeviceDataValidator() {
	}

	public static synchronized IotDeviceDataValidator getInstance() {
		if (instance == null)
			instance = new IotDeviceDataValidator();
		return instance;
	}

	public List<String> validateDeviceDto(IotDeviceDto subject, boolean skipOptionalAttributes) {
		List<String> validationErrors = new ArrayList<>();
		if (subject.getSerialNumber() == null) {
			validationErrors.add("Device serial number is missing");
		}else if(!subject.getSerialNumber().matches("([0-9])*")) {
			validationErrors.add("Device serial number should contain digits only");
		}

		if (!skipOptionalAttributes && subject.getTemp() == null) {
			validationErrors.add("Device temprature value is missing");
		}

		if (subject.getStatus() != null) {
			switch (subject.getStatus()) {
			case ACTIVE:
				if (subject.getTemp() != null && !Range.between(0, 10).contains(subject.getTemp())) {
					validationErrors.add("Active device's temprature should be between 0C and 10C");
				}
				break;
			case READY:
				if (subject.getTemp() != null && subject.getTemp() != -1) {
					validationErrors.add("Ready device's temprature should be -1C");
				}
				if(subject.getSold()) {
					validationErrors.add("Ready devices needs to be configured before being sold");
				}
				break;
			default:
				validationErrors.add("Device status is required and should be one of [\"ACTIVE\", \"READY\"]");
				break;
			}
		} else if (!skipOptionalAttributes) {
			validationErrors.add("Device status is required");
		}
		if (!skipOptionalAttributes && subject.getPinCode() == null) {
			validationErrors.add("Device pincode is required");
		} else if (subject.getPinCode() != null && subject.getPinCode().length() != 7) {
			validationErrors.add("Device pincode length should be 7 digits");
		} else if (subject.getPinCode() != null && !subject.getPinCode().matches("([0-9])*")) {
			validationErrors.add("Device pincode should contain digits only");
		}

		return validationErrors;

	}
}
