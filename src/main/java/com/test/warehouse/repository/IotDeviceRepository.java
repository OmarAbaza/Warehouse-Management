package com.test.warehouse.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.test.warehouse.model.DeviceStatus;
import com.test.warehouse.model.IotDevice;

public interface IotDeviceRepository extends JpaRepository<IotDevice, Long> {
	public IotDevice getBySerialNumber(String serialNumber);
	public List<IotDevice> getByStatusOrderByPinCodeAsc(DeviceStatus deviceStatus);
	public Page<IotDevice> getByStatus(DeviceStatus deviceStatus, Pageable pageable);
}
