package com.beingatushar.ubercommons.controller.vehicle;

import com.beingatushar.ubercommons.dto.VehicleColorDTO;
import com.beingatushar.ubercommons.service.vehicle.VehicleColorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle-colors")
public class VehicleColorController {

    private final VehicleColorService vehicleColorService;

    public VehicleColorController(VehicleColorService vehicleColorService) {
        this.vehicleColorService = vehicleColorService;
    }

    @PostMapping
    public ResponseEntity<VehicleColorDTO> createVehicleColor(@RequestBody @Valid VehicleColorDTO vehicleColor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleColorService.create(vehicleColor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleColorDTO> getVehicleColorById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleColorService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<VehicleColorDTO>> getAllVehicleColors() {
        return ResponseEntity.ok(vehicleColorService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleColorDTO> updateVehicleColor(@PathVariable Long id, @RequestBody @Valid VehicleColorDTO vehicleColor) {
        return ResponseEntity.ok(vehicleColorService.update(id, vehicleColor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteVehicleColor(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleColorService.deleteById(id));
    }
}