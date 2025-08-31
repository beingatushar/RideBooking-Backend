package com.beingatushar.ubercommons.service.vehicle.impl;

import com.beingatushar.ubercommons.entity.vehicle.VehicleBrand;
import com.beingatushar.ubercommons.exception.ResourceNotFoundException;
import com.beingatushar.ubercommons.repository.VehicleBrandRepository;
import com.beingatushar.ubercommons.service.vehicle.VehicleBrandService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleBrandServiceImpl implements VehicleBrandService {

    private final VehicleBrandRepository vehicleBrandRepository;

    public VehicleBrandServiceImpl(VehicleBrandRepository vehicleBrandRepository) {
        this.vehicleBrandRepository = vehicleBrandRepository;
    }

    @Override
    public VehicleBrand create(VehicleBrand vehicleBrand) {
        return vehicleBrandRepository.save(vehicleBrand);
    }

    @Override
    public VehicleBrand update(Long id, VehicleBrand vehicleBrand) {
        VehicleBrand vehicleBrandToUpdate = getById(id);
        BeanUtils.copyProperties(vehicleBrand, vehicleBrandToUpdate);
        return vehicleBrandRepository.save(vehicleBrandToUpdate);
    }

    @Override
    public VehicleBrand getById(Long id) {
        return vehicleBrandRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("VehicleBrand with id " + id + " not found"));
    }

    @Override
    public Boolean deleteById(Long id) {
        if (!existsById(id)) {
            return false;
        }
        vehicleBrandRepository.deleteById(id);
        return true;
    }

    @Override
    public List<VehicleBrand> getAll() {
        return vehicleBrandRepository.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        return vehicleBrandRepository.existsById(id);
    }
}