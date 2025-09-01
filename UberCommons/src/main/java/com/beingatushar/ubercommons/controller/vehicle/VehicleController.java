package com.beingatushar.ubercommons.controller.vehicle;

import com.beingatushar.ubercommons.dto.VehicleDTO;
import com.beingatushar.ubercommons.service.vehicle.VehicleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<VehicleDTO> createVehicle(@RequestBody @Valid VehicleDTO vehicle) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleService.create(vehicle));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAll().stream().toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable Long id, @RequestBody @Valid VehicleDTO vehicle) {
        return ResponseEntity.ok(vehicleService.update(id, vehicle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteVehicle(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.deleteById(id));
    }
}