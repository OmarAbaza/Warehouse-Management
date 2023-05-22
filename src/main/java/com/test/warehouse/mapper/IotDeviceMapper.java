package com.test.warehouse.mapper;

import java.util.ArrayList;
import java.util.List;

import com.test.warehouse.dto.IotDeviceDto;
import com.test.warehouse.model.IotDevice;

public class IotDeviceMapper {

	private static IotDeviceMapper instance = null;

	private IotDeviceMapper() {
	}

	public static synchronized IotDeviceMapper getInstance() {
		if (instance == null)
			instance = new IotDeviceMapper();
		return instance;
	}

	public IotDeviceDto modelToDto(IotDevice iotDeviceModel) {
		IotDeviceDto iotDeviceDto = new IotDeviceDto();
		iotDeviceDto.setPinCode(iotDeviceModel.getPinCode());
		iotDeviceDto.setSerialNumber(iotDeviceModel.getSerialNumber());
		iotDeviceDto.setStatus(iotDeviceModel.getStatus());
		iotDeviceDto.setTemp(iotDeviceModel.getTemp());
		iotDeviceDto.setSold(iotDeviceModel.getSold());
		return iotDeviceDto;
	}

	public IotDevice dtoToModel(IotDeviceDto iotDeviceDto) {
		IotDevice iotDevice = new IotDevice();
		iotDevice.setSerialNumber(iotDeviceDto.getSerialNumber());
		iotDevice.setPinCode(iotDeviceDto.getPinCode());
		iotDevice.setStatus(iotDeviceDto.getStatus());
		iotDevice.setTemp(iotDeviceDto.getTemp());
		iotDevice.setSold(iotDeviceDto.getSold());
		return iotDevice;
	}

	public List<IotDeviceDto> modelToDto(List<IotDevice> iotDeviceModelList) {
		List<IotDeviceDto> iotDeviceDtoList = new ArrayList<IotDeviceDto>();
		if (iotDeviceModelList != null) {
			for (IotDevice iotDeviceModel : iotDeviceModelList) {
				iotDeviceDtoList.add(modelToDto(iotDeviceModel));
			}
		}
		return iotDeviceDtoList;
	}

	public List<IotDevice> dtoToModel(List<IotDeviceDto> iotDeviceDtoList) {
		List<IotDevice> iotDeviceModelList = new ArrayList<IotDevice>();
		if (iotDeviceDtoList != null) {
			for (IotDeviceDto iotDeviceDto : iotDeviceDtoList) {
				iotDeviceModelList.add(dtoToModel(iotDeviceDto));
			}
		}
		return iotDeviceModelList;
	}
}
