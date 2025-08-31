package com.beingatushar.ubercommons.service.driver.impl;

import com.beingatushar.ubercommons.entity.driver.Driver;
import com.beingatushar.ubercommons.exception.ResourceNotFoundException;
import com.beingatushar.ubercommons.repository.DriverRepository;
import com.beingatushar.ubercommons.service.driver.DriverService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {
    //    @Autowired
    private final DriverRepository driverRepository;

    DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public Driver create(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public Driver update(Long id, Driver driver) {
        Driver driverToUpdate = getById(id);
        BeanUtils.copyProperties(driver, driverToUpdate);
        return driverRepository.save(driverToUpdate);
    }

    @Override
    public Driver getById(Long id) {
        return driverRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Driver with id " + id + " not found"));
    }

    @Override
    public Boolean deleteById(Long id) {
        if (!existsById(id)) {
            return false;
        }
        driverRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Driver> getAll() {
        return driverRepository.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        return driverRepository.existsById(id);
    }
}
