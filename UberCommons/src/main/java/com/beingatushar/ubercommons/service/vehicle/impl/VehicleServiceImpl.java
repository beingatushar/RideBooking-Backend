package com.beingatushar.ubercommons.service.vehicle.impl;

import com.beingatushar.ubercommons.dto.VehicleDTO;
import com.beingatushar.ubercommons.entity.vehicle.Vehicle;
import com.beingatushar.ubercommons.exception.ResourceNotFoundException;
import com.beingatushar.ubercommons.mapper.VehicleMapper;
import com.beingatushar.ubercommons.repository.VehicleRepository;
import com.beingatushar.ubercommons.service.vehicle.VehicleBrandService;
import com.beingatushar.ubercommons.service.vehicle.VehicleColorService;
import com.beingatushar.ubercommons.service.vehicle.VehicleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleBrandService vehicleBrandService;
    private final VehicleColorService vehicleColorService;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleBrandService vehicleBrandService, VehicleColorService vehicleColorService) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleBrandService = vehicleBrandService;
        this.vehicleColorService = vehicleColorService;
    }

    @Override
    @Transactional
    public VehicleDTO create(VehicleDTO vehicleDTO) {
        Vehicle vehicle = VehicleMapper.toEntity(vehicleDTO);
        // The createOrFind logic for brand and color is now handled within the main create method.
        vehicle.setBrand(vehicleBrandService.createOrFind(vehicle.getBrand()));
        vehicle.setColor(vehicleColorService.createOrFind(vehicle.getColor()));
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return VehicleMapper.toDTO(savedVehicle);
    }

    @Override
    @Transactional
    public VehicleDTO update(Long id, VehicleDTO vehicleDTO) {
        Vehicle vehicleToUpdate = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle with id " + id + " not found"));

        // Safe, manual mapping for updates
        vehicleToUpdate.setLicensePlate(vehicleDTO.getLicensePlate());
        vehicleToUpdate.setModel(vehicleDTO.getModel());
        vehicleToUpdate.setCapacity(vehicleDTO.getCapacity());
        vehicleToUpdate.setVehicleType(vehicleDTO.getVehicleType());

        // Handle nested entities
        if (vehicleDTO.getBrand() != null) {
            vehicleToUpdate.setBrand(vehicleBrandService.createOrFind(VehicleMapper.toEntity(vehicleDTO.getBrand())));
        }
        if (vehicleDTO.getColor() != null) {
            vehicleToUpdate.setColor(vehicleColorService.createOrFind(VehicleMapper.toEntity(vehicleDTO.getColor())));
        }

        Vehicle updatedVehicle = vehicleRepository.save(vehicleToUpdate);
        return VehicleMapper.toDTO(updatedVehicle);
    }

    @Override
    public VehicleDTO getById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle with id " + id + " not found"));
        return VehicleMapper.toDTO(vehicle);
    }

    @Override
    @Transactional
    public Boolean deleteById(Long id) {
        if (!existsById(id)) {
            return false;
        }
        vehicleRepository.deleteById(id);
        return true;
    }

    @Override
    public List<VehicleDTO> getAll() {
        return vehicleRepository.findAll()
                .stream()
                .map(VehicleMapper::toDTO)
                .toList();
    }

    @Override
    public boolean existsById(Long id) {
        return vehicleRepository.existsById(id);
    }

    @Override
    @Transactional
    public Vehicle createOrFind(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle entity cannot be null");
        }

        // If the vehicle entity has an ID, we can get a reference to it.
        if (vehicle.getId() != null) {
            return vehicleRepository.getReferenceById(vehicle.getId());
        }

        // A vehicle must have a license plate to be uniquely identified.
        if (vehicle.getLicensePlate() == null || vehicle.getLicensePlate().isBlank()) {
            throw new IllegalArgumentException("Vehicle license plate cannot be null or empty for createOrFind operation");
        }

        // Try to find an existing vehicle by its unique license plate.
        Vehicle existingVehicle = vehicleRepository.findByLicensePlate(vehicle.getLicensePlate());
        if (existingVehicle != null) {
            return existingVehicle;
        }

        // If not found, create a new one.
        // Ensure the associated brand and color are also persisted or found.
        if (vehicle.getBrand() != null) {
            vehicle.setBrand(vehicleBrandService.createOrFind(vehicle.getBrand()));
        } else {
            throw new IllegalArgumentException("Vehicle brand cannot be null for a new vehicle");
        }

        if (vehicle.getColor() != null) {
            vehicle.setColor(vehicleColorService.createOrFind(vehicle.getColor()));
        } else {
            throw new IllegalArgumentException("Vehicle color cannot be null for a new vehicle");
        }

        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle getByRef(Long aLong) {
        return vehicleRepository.getReferenceById(aLong);
    }
}