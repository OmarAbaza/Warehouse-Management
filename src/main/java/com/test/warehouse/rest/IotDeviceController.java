package com.test.warehouse.rest;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.dao.DataIntegrityViolationException;

import com.test.warehouse.dto.IotDeviceDto;
import com.test.warehouse.dto.PaginatedResponseDto;
import com.test.warehouse.dto.ResponseDto;
import com.test.warehouse.dto.ResponseStatusMessages;
import com.test.warehouse.exception.DataValidationFailedException;
import com.test.warehouse.exception.DeviceNotFoundException;
import com.test.warehouse.service.IotDevicesManagementService;

@RestController
@RequestMapping(path = "iotdevices")
public class IotDeviceController {

	@Autowired
	private IotDevicesManagementService iotDevicesService;

	@GetMapping
	public ResponseEntity<ResponseDto> getDeviceBySerialNumber(@RequestParam String serialNumber)
			throws DeviceNotFoundException {
		IotDeviceDto requestedIotDevice = iotDevicesService.getIotDeviceDetails(serialNumber);
		return new ResponseEntity<>(
				new ResponseDto(true, ResponseStatusMessages.DEVICE_RETRIEVED_SUCCESSFULLY, requestedIotDevice),
				HttpStatus.OK);
	}

	@GetMapping("status")
	public ResponseEntity<ResponseDto> getDeviceStatusBySerialNumber(@RequestParam String serialNumber)
			throws DeviceNotFoundException {
		IotDeviceDto requestedIotDevice = iotDevicesService.getIotDeviceStatus(serialNumber);
		// omit extra data
		return new ResponseEntity<>(
				new ResponseDto(true, ResponseStatusMessages.DEVICE_RETRIEVED_SUCCESSFULLY, requestedIotDevice),
				HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ResponseDto> createDevice(@RequestBody IotDeviceDto requestDTO)
			throws DataValidationFailedException {
		iotDevicesService.createIotDevice(requestDTO);
		return new ResponseEntity<>(
				new ResponseDto(true, ResponseStatusMessages.DEVICE_ADDED_SUCCESSFULLY, null), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<ResponseDto> updateDevice(@RequestBody IotDeviceDto requestDTO)
			throws DataValidationFailedException, DeviceNotFoundException {
		iotDevicesService.updateIotDevice(requestDTO);
		return new ResponseEntity<>(
				new ResponseDto(true, ResponseStatusMessages.DEVICE_UPDATED_SUCCESSFULLY, null), HttpStatus.OK);
	}

	@PatchMapping
	public ResponseEntity<ResponseDto> patchDevice(@RequestBody IotDeviceDto requestDTO)
			throws DataValidationFailedException, DeviceNotFoundException {
		iotDevicesService.patchIotDevice(requestDTO);
		return new ResponseEntity<>(
				new ResponseDto(true, ResponseStatusMessages.DEVICE_PATCHED_SUCCESSFULLY, null), HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<ResponseDto> deleteDevice(@RequestParam String pinCode)
			throws DataValidationFailedException, DeviceNotFoundException {
		iotDevicesService.deleteIotDevice(pinCode);
		return new ResponseEntity<>(
				new ResponseDto(true, ResponseStatusMessages.DEVICE_DELETED_SUCCESSFULLY, null), HttpStatus.OK);
	}

	@GetMapping("/availableactivedevices")
	public ResponseEntity<PaginatedResponseDto> getAllActiveDevices(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "20") int size) throws DeviceNotFoundException {
		PaginatedResponseDto allActiveIotDevices = iotDevicesService.getAllActiveIotDevices(page, size);
		return new ResponseEntity<>(allActiveIotDevices, HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(DeviceNotFoundException.class)
	public ResponseEntity<ResponseDto> handleDeviceNotFoundExceptions(DeviceNotFoundException ex) {
		return new ResponseEntity<>(new ResponseDto(false, ResponseStatusMessages.DEVICE_NOT_FOUND, null),
				HttpStatus.NOT_FOUND);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DataValidationFailedException.class)
	public ResponseEntity<ResponseDto> handleDataValidationExceptions(DataValidationFailedException ex) {
		return new ResponseEntity<>(
				new ResponseDto(false, ResponseStatusMessages.DATA_VALIDATION_FAILED, ex.getValidationErrors()),
				HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ResponseDto> handleInvalidFormatExceptions(HttpMessageNotReadableException ex) {
		return new ResponseEntity<>(
				new ResponseDto(false, ResponseStatusMessages.INVALID_DATA, ex.getRootCause()),
				HttpStatus.NOT_ACCEPTABLE);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ResponseDto> handleDuplicateKeyExceptions(DataIntegrityViolationException ex) {
		String conistraintName = ((ConstraintViolationException) ex.getCause()).getConstraintName().split("\\.")[1];
		return new ResponseEntity<>(new ResponseDto(false,
				ResponseStatusMessages.valueOf(conistraintName + "_CONISTRAINT_DUBLICATE"), null),
				HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseDto> handleTechnicalExceptions(Exception ex) {
		ex.printStackTrace();
		return new ResponseEntity<>(new ResponseDto(false, ResponseStatusMessages.TECHNICAL_FAILURE, null),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
