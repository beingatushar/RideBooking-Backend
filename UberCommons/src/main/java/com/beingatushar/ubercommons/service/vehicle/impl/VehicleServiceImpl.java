package com.beingatushar.ubercommons.service.vehicle.impl;

import com.beingatushar.ubercommons.entity.vehicle.Vehicle;
import com.beingatushar.ubercommons.exception.ResourceNotFoundException;
import com.beingatushar.ubercommons.repository.VehicleRepository;
import com.beingatushar.ubercommons.service.vehicle.VehicleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle create(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
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
}