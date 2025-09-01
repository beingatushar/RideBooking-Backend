package com.beingatushar.ubercommons.controller.vehicle;

import com.beingatushar.ubercommons.controller.BaseRestController;
import com.beingatushar.ubercommons.entity.vehicle.Vehicle;
import com.beingatushar.ubercommons.service.base.BaseService;
import com.beingatushar.ubercommons.service.vehicle.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController extends BaseRestController<Vehicle, Long> {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @Override
    protected BaseService<Vehicle, Long> getService() {
        return vehicleService;
    }

    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        return ResponseEntity.status(HttpStatus.CREATED).body(create(vehicle));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        return ResponseEntity.ok(getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(update(id, vehicle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteVehicle(@PathVariable Long id) {
        return ResponseEntity.ok(deleteById(id));
    }
}