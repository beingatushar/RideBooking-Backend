package com.beingatushar.ubercommons.service.location.impl;

import com.beingatushar.ubercommons.entity.location.Location;
import com.beingatushar.ubercommons.repository.LocationRepository;
import com.beingatushar.ubercommons.service.location.LocationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    @Transactional
    public Location create(Location liveLocation) {
        return locationRepository.save(liveLocation);
    }

    @Override
    @Transactional
    public Location update(Long id, Location liveLocation) {
        Location liveLocationToUpdate = getById(id);
        BeanUtils.copyProperties(liveLocation, liveLocationToUpdate);
        return locationRepository.save(liveLocationToUpdate);
    }

    @Override
    public Location getById(Long id) {
        return locationRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Location with id " + id + " not found"));
    }

    @Override
    @Transactional
    public Boolean deleteById(Long id) {
        if (!existsById(id)) {
            return false;
        }
        locationRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        return locationRepository.existsById(id);
    }
}
