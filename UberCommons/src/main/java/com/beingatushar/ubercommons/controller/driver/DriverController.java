package com.beingatushar.ubercommons.controller.driver;

import com.beingatushar.ubercommons.controller.BaseRestController;
import com.beingatushar.ubercommons.entity.driver.Driver;
import com.beingatushar.ubercommons.service.base.BaseService;
import com.beingatushar.ubercommons.service.driver.DriverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController extends BaseRestController<Driver, Long> {
    final DriverService driverService;

    DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @Override
    protected BaseService<Driver, Long> getService() {
        return driverService;
    }

    @GetMapping
    public ResponseEntity<List<Driver>> getAllDrivers() {
        return ResponseEntity.ok(getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Long id) {
        return ResponseEntity.ok(getById(id));
    }

    @PostMapping
    public ResponseEntity<Driver> createDriver(@RequestBody Driver driver) {
        return ResponseEntity.status(HttpStatus.CREATED).body(create(driver));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Driver> updateDriver(@PathVariable Long id, @RequestBody Driver driver) {
        return ResponseEntity.ok(update(id, driver));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDriver(@PathVariable Long id) {
        return ResponseEntity.ok(deleteById(id));
    }
}
