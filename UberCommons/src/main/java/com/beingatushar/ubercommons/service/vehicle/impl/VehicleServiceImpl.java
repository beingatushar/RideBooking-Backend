package com.beingatushar.ubercommons.service.vehicle.impl;

import com.beingatushar.ubercommons.entity.vehicle.Vehicle;
import com.beingatushar.ubercommons.exception.ResourceNotFoundException;
import com.beingatushar.ubercommons.repository.VehicleRepository;
import com.beingatushar.ubercommons.service.vehicle.VehicleBrandService;
import com.beingatushar.ubercommons.service.vehicle.VehicleColorService;
import com.beingatushar.ubercommons.service.vehicle.VehicleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
    public Vehicle create(Vehicle vehicle) {
        vehicle.setBrand(vehicleBrandService.createOrFind(vehicle.getBrand()));
        vehicle.setColor(vehicleColorService.createOrFind(vehicle.getColor()));
        return vehicleRepository.save(vehicle);
    }

    @Override
    @Transactional
    public Vehicle update(Long id, Vehicle vehicle) {
        Vehicle vehicleToUpdate = getById(id);
        BeanUtils.copyProperties(vehicle, vehicleToUpdate);
        return vehicleRepository.save(vehicleToUpdate);
    }

    @Override
    public Vehicle getById(Long id) {
        return vehicleRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Vehicle with id " + id + " not found"));
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
    public List<Vehicle> getAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        return vehicleRepository.existsById(id);
    }

    @Override
    @Transactional
    public Vehicle createOrFind(Vehicle vehicle) {
        if (vehicle == null)
            throw new IllegalArgumentException("Vehicle cannot be null");
        if (vehicle.getId() != null) {
            return vehicleRepository.getReferenceById(vehicle.getId());
        }
        Vehicle vehicleByLicensePlate = vehicleRepository.findByLicensePlate(vehicle.getLicensePlate());
        if (vehicleByLicensePlate != null) {
            return vehicleByLicensePlate;
        }
        return create(vehicle);
    }
}