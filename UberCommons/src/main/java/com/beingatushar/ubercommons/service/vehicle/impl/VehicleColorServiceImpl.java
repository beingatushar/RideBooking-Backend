package com.beingatushar.ubercommons.service.vehicle.impl;

import com.beingatushar.ubercommons.dto.VehicleColorDTO;
import com.beingatushar.ubercommons.entity.vehicle.VehicleColor;
import com.beingatushar.ubercommons.exception.ResourceNotFoundException;
import com.beingatushar.ubercommons.mapper.VehicleMapper;
import com.beingatushar.ubercommons.repository.VehicleColorRepository;
import com.beingatushar.ubercommons.service.vehicle.VehicleColorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleColorServiceImpl implements VehicleColorService {

    private final VehicleColorRepository vehicleColorRepository;

    public VehicleColorServiceImpl(VehicleColorRepository vehicleColorRepository) {
        this.vehicleColorRepository = vehicleColorRepository;
    }

    @Override
    @Transactional
    public VehicleColorDTO create(VehicleColorDTO vehicleColorDTO) {
        VehicleColor vehicleColor = VehicleMapper.toEntity(vehicleColorDTO);
        VehicleColor savedVehicleColor = vehicleColorRepository.save(vehicleColor);
        return VehicleMapper.toDTO(savedVehicleColor);
    }

    @Override
    @Transactional
    public VehicleColorDTO update(Long id, VehicleColorDTO vehicleColorDTO) {
        VehicleColor vehicleColorToUpdate = vehicleColorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VehicleColor with id " + id + " not found"));

        vehicleColorToUpdate.setName(vehicleColorDTO.getName());

        VehicleColor updatedVehicleColor = vehicleColorRepository.save(vehicleColorToUpdate);
        return VehicleMapper.toDTO(updatedVehicleColor);
    }

    @Override
    public VehicleColorDTO getById(Long id) {
        VehicleColor vehicleColor = vehicleColorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VehicleColor with id " + id + " not found"));
        return VehicleMapper.toDTO(vehicleColor);
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
    public List<VehicleColorDTO> getAll() {
        return vehicleColorRepository.findAll()
                .stream()
                .map(VehicleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(Long id) {
        return vehicleColorRepository.existsById(id);
    }

    @Override
    public VehicleColor getByRef(Long id) {
        return vehicleColorRepository.getReferenceById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public VehicleColor createOrFind(VehicleColor vehicleColor) {
        if (vehicleColor == null || vehicleColor.getName() == null || vehicleColor.getName().isBlank()) {
            throw new IllegalArgumentException("VehicleColor and its name cannot be null or blank");
        }

        if (vehicleColor.getId() != null) {
            return getByRef(vehicleColor.getId());
        }

        return vehicleColorRepository.findByName(vehicleColor.getName())
                .orElseGet(() -> vehicleColorRepository.save(vehicleColor));
    }
}