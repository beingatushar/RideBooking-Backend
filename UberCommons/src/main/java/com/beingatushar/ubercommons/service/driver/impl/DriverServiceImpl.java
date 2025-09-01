package com.beingatushar.ubercommons.service.driver.impl;

import com.beingatushar.ubercommons.dto.DriverDTO;
import com.beingatushar.ubercommons.entity.driver.Driver;
import com.beingatushar.ubercommons.entity.vehicle.Vehicle;
import com.beingatushar.ubercommons.exception.ResourceNotFoundException;
import com.beingatushar.ubercommons.mapper.DriverMapper;
import com.beingatushar.ubercommons.repository.DriverRepository;
import com.beingatushar.ubercommons.service.driver.DriverService;
import com.beingatushar.ubercommons.service.vehicle.VehicleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final VehicleService vehicleService;

    DriverServiceImpl(DriverRepository driverRepository, VehicleService vehicleService) {
        this.driverRepository = driverRepository;
        this.vehicleService = vehicleService;
    }

    @Override
    @Transactional
    public DriverDTO create(DriverDTO driverDTO) {
        Driver driver = DriverMapper.toEntity(driverDTO);
        // The vehicle from the DTO doesn't have an ID yet, so we need to create or find it.
        if (driver.getVehicle() != null) {
            Vehicle vehicle = vehicleService.createOrFind(driver.getVehicle());
            driver.setVehicle(vehicle);
        }
        // Remember to hash the password in a real application
        Driver savedDriver = driverRepository.save(driver);
        return DriverMapper.toDTO(savedDriver);
    }

    @Override
    @Transactional
    public DriverDTO update(Long id, DriverDTO driverDTO) {
        Driver driverToUpdate = driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Driver with id " + id + " not found"));

        // Update driver's personal info
        driverToUpdate.setName(driverDTO.getName());
        driverToUpdate.setEmail(driverDTO.getEmail());
        if (driverDTO.getPassword() != null && !driverDTO.getPassword().isBlank()) {
            // Remember to hash the password
            driverToUpdate.setPassword(driverDTO.getPassword());
        }

//        // Update vehicle info
//        if (driverDTO.getVehicle() != null) {
//            Vehicle vehicleToUpdate = driverToUpdate.getVehicle();
//            vehicleToUpdate.setLicensePlate(driverDTO.getVehicle().getLicensePlate());
//            vehicleToUpdate.setModel(driverDTO.getVehicle().getModel());
//            vehicleToUpdate.setCapacity(driverDTO.getVehicle().getCapacity());
//            vehicleToUpdate.setVehicleType(driverDTO.getVehicle().getVehicleType());
//            // You might want to handle brand and color updates as well, if applicable
//            // For example:
//            // VehicleBrand brand = vehicleBrandService.createOrFind(VehicleMapper.toEntity(driverDTO.getVehicle().getBrand()));
//            // vehicleToUpdate.setBrand(brand);
//        }

        Driver updatedDriver = driverRepository.save(driverToUpdate);
        return DriverMapper.toDTO(updatedDriver);
    }

    @Override
    public DriverDTO getById(Long id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Driver with id " + id + " not found"));
        return DriverMapper.toDTO(driver);
    }

    @Override
    @Transactional
    public Boolean deleteById(Long id) {
        if (!existsById(id)) {
            return false;
        }
        driverRepository.deleteById(id);
        return true;
    }

    @Override
    public List<DriverDTO> getAll() {
        return driverRepository.findAll()
                .stream()
                .map(DriverMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(Long id) {
        return driverRepository.existsById(id);
    }

    @Override
    public Driver getByRef(Long id) {
        return driverRepository.getReferenceById(id);
    }
}