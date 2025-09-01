package com.beingatushar.ubercommons.service.vehicle.impl;

import com.beingatushar.ubercommons.entity.vehicle.VehicleColor;
import com.beingatushar.ubercommons.exception.ResourceNotFoundException;
import com.beingatushar.ubercommons.repository.VehicleColorRepository;
import com.beingatushar.ubercommons.service.vehicle.VehicleColorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleColorServiceImpl implements VehicleColorService {

    private final VehicleColorRepository vehicleColorRepository;

    public VehicleColorServiceImpl(VehicleColorRepository vehicleColorRepository) {
        this.vehicleColorRepository = vehicleColorRepository;
    }

    @Override
    @Transactional
    public VehicleColor create(VehicleColor vehicleColor) {
        return vehicleColorRepository.save(vehicleColor);
    }

    @Override
    @Transactional
    public VehicleColor update(Long id, VehicleColor vehicleColor) {
        VehicleColor vehicleColorToUpdate = getById(id);
        BeanUtils.copyProperties(vehicleColor, vehicleColorToUpdate);
        return vehicleColorRepository.save(vehicleColorToUpdate);
    }

    @Override
    public VehicleColor getById(Long id) {
        return vehicleColorRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("VehicleColor with id " + id + " not found"));
    }

    @Override
    @Transactional
    public Boolean deleteById(Long id) {
        if (!existsById(id)) {
            return false;
        }
        vehicleColorRepository.deleteById(id);
        return true;
    }

    @Override
    public List<VehicleColor> getAll() {
        return vehicleColorRepository.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        return vehicleColorRepository.existsById(id);
    }

    @Override
    @Transactional
    public VehicleColor createOrFind(VehicleColor vehicleColor) {
        if (vehicleColor == null)
            throw new IllegalArgumentException("VehicleColor cannot be null");
        if (vehicleColor.getId() != null) {
            return vehicleColorRepository.getReferenceById(vehicleColor.getId());
        }
        System.out.printf("Creating vehicleColor with name %s", vehicleColor.getName());
        VehicleColor vehicleColorByName = vehicleColorRepository.findByName(vehicleColor.getName());
        if (vehicleColorByName != null) {
            return vehicleColorByName;
        }
        return create(vehicleColor);
    }
}