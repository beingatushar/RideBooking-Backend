package com.beingatushar.ubercommons.mapper;

import com.beingatushar.ubercommons.dto.DriverDTO;
import com.beingatushar.ubercommons.entity.driver.Driver;

public class DriverMapper {

    public static DriverDTO toDTO(Driver driver) {
        if (driver == null) {
            return null;
        }
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setId(driver.getId());
        driverDTO.setName(driver.getName());
        driverDTO.setEmail(driver.getEmail());
        if (driver.getVehicle() != null) {
            driverDTO.setVehicle(VehicleMapper.toDTO(driver.getVehicle()));
        }
        return driverDTO;
    }

    public static Driver toEntity(DriverDTO driverDTO) {
        if (driverDTO == null) {
            return null;
        }
        Driver driver = new Driver();
        driver.setName(driverDTO.getName());
        driver.setEmail(driverDTO.getEmail());
        driver.setPassword(driverDTO.getPassword()); // Remember to hash this in the service layer
        if (driverDTO.getVehicle() != null) {
            driver.setVehicle(VehicleMapper.toEntity(driverDTO.getVehicle()));
        }
        return driver;
    }
}