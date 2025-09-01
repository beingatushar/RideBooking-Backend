package com.beingatushar.ubercommons.service.vehicle.impl;

import com.beingatushar.ubercommons.dto.VehicleBrandDTO;
import com.beingatushar.ubercommons.entity.vehicle.VehicleBrand;
import com.beingatushar.ubercommons.exception.ResourceNotFoundException;
import com.beingatushar.ubercommons.mapper.VehicleMapper;
import com.beingatushar.ubercommons.repository.VehicleBrandRepository;
import com.beingatushar.ubercommons.service.vehicle.VehicleBrandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleBrandServiceImpl implements VehicleBrandService {

    private final VehicleBrandRepository vehicleBrandRepository;

    public VehicleBrandServiceImpl(VehicleBrandRepository vehicleBrandRepository) {
        this.vehicleBrandRepository = vehicleBrandRepository;
    }

    @Override
    @Transactional
    public VehicleBrandDTO create(VehicleBrandDTO vehicleBrandDTO) {
        VehicleBrand vehicleBrand = VehicleMapper.toEntity(vehicleBrandDTO);
        VehicleBrand savedVehicleBrand = vehicleBrandRepository.save(vehicleBrand);
        return VehicleMapper.toDTO(savedVehicleBrand);
    }

    @Override
    @Transactional
    public VehicleBrandDTO update(Long id, VehicleBrandDTO vehicleBrandDTO) {
        VehicleBrand vehicleBrandToUpdate = vehicleBrandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VehicleBrand with id " + id + " not found"));

        vehicleBrandToUpdate.setName(vehicleBrandDTO.getName());

        VehicleBrand updatedVehicleBrand = vehicleBrandRepository.save(vehicleBrandToUpdate);
        return VehicleMapper.toDTO(updatedVehicleBrand);
    }

    @Override
    public VehicleBrandDTO getById(Long id) {
        VehicleBrand vehicleBrand = vehicleBrandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VehicleBrand with id " + id + " not found"));
        return VehicleMapper.toDTO(vehicleBrand);
    }

    @Override
    @Transactional
    public Boolean deleteById(Long id) {
        if (!existsById(id)) {
            return false;
        }
        vehicleBrandRepository.deleteById(id);
        return true;
    }

    @Override
    public List<VehicleBrandDTO> getAll() {
        return vehicleBrandRepository.findAll()
                .stream()
                .map(VehicleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(Long id) {
        return vehicleBrandRepository.existsById(id);
    }

    @Override
    public VehicleBrand getByRef(Long id) {
        return vehicleBrandRepository.getReferenceById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public VehicleBrand createOrFind(VehicleBrand vehicleBrand) {
        if (vehicleBrand == null || vehicleBrand.getName() == null || vehicleBrand.getName().isBlank()) {
            throw new IllegalArgumentException("VehicleBrand and its name cannot be null or blank");
        }

        if (vehicleBrand.getId() != null) {
            return getByRef(vehicleBrand.getId());
        }

        // Use Optional for safer handling of nulls
        return vehicleBrandRepository.findByName(vehicleBrand.getName())
                .orElseGet(() -> vehicleBrandRepository.save(vehicleBrand));
    }
}