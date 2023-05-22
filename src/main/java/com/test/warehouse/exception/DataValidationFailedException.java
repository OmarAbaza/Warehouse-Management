package com.test.warehouse.exception;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DataValidationFailedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4760774541485970176L;
	
	private List<String> validationErrors;
	public DataValidationFailedException(List<String> validationErrors) {
		this.validationErrors = validationErrors;
	}
}
