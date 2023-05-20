package com.test.warehouse.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.warehouse.dto.IotDeviceDto;
import com.test.warehouse.dto.PaginatedResponseDto;
import com.test.warehouse.dto.ResponseStatusMessages;
import com.test.warehouse.exception.DataValidationFailedException;
import com.test.warehouse.exception.DeviceNotFoundException;
import com.test.warehouse.mapper.IotDeviceMapper;
import com.test.warehouse.model.DeviceStatus;
import com.test.warehouse.model.IotDevice;
import com.test.warehouse.repository.IotDeviceRepository;
import com.test.warehouse.validation.IotDeviceDataValidator;

@Service
public class IotDevicesManagementService {

	@Autowired
	private IotDeviceRepository iotDeviceRepository;

	public IotDeviceDto getIotDeviceDetails(String serialNumber) throws DeviceNotFoundException {
		IotDevice iotDevice = getDeviceBySerialNumber(serialNumber);
		return IotDeviceMapper.getInstance().modelToDto(iotDevice);
	}

	public IotDeviceDto getIotDeviceStatus(String serialNumber) throws DeviceNotFoundException {
		IotDevice iotDevice = getDeviceBySerialNumber(serialNumber);
		if (iotDevice == null) {
			throw new DeviceNotFoundException();
		}
		IotDeviceDto iotDeviceDto = IotDeviceMapper.getInstance().modelToDto(iotDevice);

		// Omit confidential data
		iotDeviceDto.setPinCode(null);
		iotDeviceDto.setSold(null);
		return iotDeviceDto;
	}

	public void createIotDevice(IotDeviceDto dto) throws DataValidationFailedException {
		validateIotDeviceDto(dto, false);
		IotDevice iotDevice = IotDeviceMapper.getInstance().dtoToModel(dto);
		iotDeviceRepository.save(iotDevice);
	}

	public void updateIotDevice(IotDeviceDto dto) throws DataValidationFailedException, DeviceNotFoundException {
		validateIotDeviceDto(dto, false);
		IotDevice existingIotDevice = getDeviceBySerialNumber(dto.getSerialNumber());
		existingIotDevice.setStatus(dto.getStatus());
		existingIotDevice.setTemp(dto.getTemp());
		iotDeviceRepository.save(existingIotDevice);
	}

	public void patchIotDevice(IotDeviceDto dto) throws DataValidationFailedException, DeviceNotFoundException {
		validateIotDeviceDto(dto, true);
		IotDevice existingIotDevice = getDeviceBySerialNumber(dto.getSerialNumber());
		if (dto.getStatus() != null) {
			existingIotDevice.setStatus(dto.getStatus());
		}
		if (dto.getTemp() != null) {
			existingIotDevice.setTemp(dto.getTemp());
		}
		if (dto.getSold() != null) {
			existingIotDevice.setSold(dto.getSold());
		}
		validateIotDeviceModel(existingIotDevice);
		iotDeviceRepository.save(existingIotDevice);
	}

	public void deleteIotDevice(String serialNumber) throws DataValidationFailedException, DeviceNotFoundException {
		IotDevice existingIotDevice = getDeviceBySerialNumber(serialNumber);
		iotDeviceRepository.delete(existingIotDevice);
	}

	public PaginatedResponseDto getAllActiveIotDevices(int page, int size) throws DeviceNotFoundException {

		Pageable pageable = PageRequest.of(page, size, Sort.by("pinCode").ascending());
		Page<IotDevice> result = iotDeviceRepository.getByStatus(DeviceStatus.ACTIVE, pageable);
		List<IotDeviceDto> iotDeviceDtos = IotDeviceMapper.getInstance().modelToDto(result.getContent());
		return PaginatedResponseDto.builder().pageNo(page).pageSize(result.getSize())
				.totalElements(result.getTotalElements()).totalPages(result.getTotalPages()).last(result.isLast())
				.success(true).data(iotDeviceDtos).message(ResponseStatusMessages.DEVICES_RETRIEVED_SUCCESSFULLY)
				.build();
	}

	private IotDevice getDeviceBySerialNumber(String serialNumber) throws DeviceNotFoundException {
		IotDevice iotDevice = iotDeviceRepository.getBySerialNumber(serialNumber);
		if (iotDevice == null) {
			throw new DeviceNotFoundException();
		}
		return iotDevice;
	}

	private void validateIotDeviceDto(IotDeviceDto dto, boolean skipOptionalAttributes)
			throws DataValidationFailedException {
		List<String> validationErrors = IotDeviceDataValidator.getInstance().validateDeviceDto(dto,
				skipOptionalAttributes);
		if (validationErrors.size() > 0) {
			throw new DataValidationFailedException(validationErrors);
		}
	}

	private void validateIotDeviceModel(IotDevice subject) throws DataValidationFailedException {
		IotDeviceDto iotDeviceDto = IotDeviceMapper.getInstance().modelToDto(subject);
		validateIotDeviceDto(iotDeviceDto, false);
	}
}
