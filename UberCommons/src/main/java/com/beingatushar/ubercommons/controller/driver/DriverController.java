package com.beingatushar.ubercommons.controller.driver;

import com.beingatushar.ubercommons.dto.DriverDTO;
import com.beingatushar.ubercommons.service.driver.DriverService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {
    final DriverService driverService;

    DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public ResponseEntity<List<DriverDTO>> getAllDrivers() {
        return ResponseEntity.ok(driverService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverDTO> getDriverById(@PathVariable Long id) {
        return ResponseEntity.ok(driverService.getById(id));
    }

    @PostMapping
    public ResponseEntity<DriverDTO> createDriver(@RequestBody @Valid DriverDTO driver) {
        return ResponseEntity.status(HttpStatus.CREATED).body(driverService.create(driver));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverDTO> updateDriver(@PathVariable Long id, @RequestBody @Valid DriverDTO driver) {
        return ResponseEntity.ok(driverService.update(id, driver));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDriver(@PathVariable Long id) {
        return ResponseEntity.ok(driverService.deleteById(id));
    }
}