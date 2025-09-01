package com.beingatushar.ubercommons.service.location.impl;

import com.beingatushar.ubercommons.entity.location.LiveLocation;
import com.beingatushar.ubercommons.repository.LiveLocationRepository;
import com.beingatushar.ubercommons.service.location.LiveLocationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiveLocationServiceImpl implements LiveLocationService {
    private final LiveLocationRepository liveLocationRepository;

    public LiveLocationServiceImpl(LiveLocationRepository liveLocationRepository) {
        this.liveLocationRepository = liveLocationRepository;
    }

    @Override
    @Transactional
    public LiveLocation create(LiveLocation liveLocation) {
        return liveLocationRepository.save(liveLocation);
    }

    @Override
    @Transactional
    public LiveLocation update(Long id, LiveLocation liveLocation) {
        LiveLocation liveLocationToUpdate = getById(id);
        BeanUtils.copyProperties(liveLocation, liveLocationToUpdate);
        return liveLocationRepository.save(liveLocationToUpdate);
    }

    @Override
    public LiveLocation getById(Long id) {
        return liveLocationRepository.findById(id).orElseThrow(() ->
                new RuntimeException("LiveLocation with id " + id + " not found"));
    }

    @Override
    @Transactional
    public Boolean deleteById(Long id) {
        if (!existsById(id)) {
            return false;
        }
        liveLocationRepository.deleteById(id);
        return true;
    }

    @Override
    public List<LiveLocation> getAll() {
        return liveLocationRepository.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        return liveLocationRepository.existsById(id);
    }
}
