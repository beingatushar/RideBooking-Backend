package com.beingatushar.ubercommons.controller.vehicle;

import com.beingatushar.ubercommons.controller.BaseRestController;
import com.beingatushar.ubercommons.entity.vehicle.VehicleColor;
import com.beingatushar.ubercommons.service.BaseService;
import com.beingatushar.ubercommons.service.vehicle.VehicleColorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle-colors")
public class VehicleColorController extends BaseRestController<VehicleColor, Long> {

    private final VehicleColorService vehicleColorService;

    public VehicleColorController(VehicleColorService vehicleColorService) {
        this.vehicleColorService = vehicleColorService;
    }

    @Override
    protected BaseService<VehicleColor, Long> getService() {
        return vehicleColorService;
    }

    @PostMapping
    public ResponseEntity<VehicleColor> createVehicleColor(@RequestBody VehicleColor vehicleColor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(create(vehicleColor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleColor> getVehicleColorById(@PathVariable Long id) {
        return ResponseEntity.ok(getById(id));
    }

    @GetMapping
    public ResponseEntity<List<VehicleColor>> getAllVehicleColors() {
        return ResponseEntity.ok(getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleColor> updateVehicleColor(@PathVariable Long id, @RequestBody VehicleColor vehicleColor) {
        return ResponseEntity.ok(update(id, vehicleColor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteVehicleColor(@PathVariable Long id) {
        return ResponseEntity.ok(deleteById(id));
    }
}