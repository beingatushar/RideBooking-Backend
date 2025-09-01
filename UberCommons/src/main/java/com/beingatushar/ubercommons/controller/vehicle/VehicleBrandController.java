package com.beingatushar.ubercommons.controller.vehicle;

import com.beingatushar.ubercommons.controller.BaseRestController;
import com.beingatushar.ubercommons.entity.vehicle.VehicleBrand;
import com.beingatushar.ubercommons.service.base.BaseService;
import com.beingatushar.ubercommons.service.vehicle.VehicleBrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle-brands")
public class VehicleBrandController extends BaseRestController<VehicleBrand, Long> {

    private final VehicleBrandService vehicleBrandService;

    public VehicleBrandController(VehicleBrandService vehicleBrandService) {
        this.vehicleBrandService = vehicleBrandService;
    }

    @Override
    protected BaseService<VehicleBrand, Long> getService() {
        return vehicleBrandService;
    }

    @PostMapping
    public ResponseEntity<VehicleBrand> createVehicleBrand(@RequestBody VehicleBrand vehicleBrand) {
        return ResponseEntity.status(HttpStatus.CREATED).body(create(vehicleBrand));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleBrand> getVehicleBrandById(@PathVariable Long id) {
        return ResponseEntity.ok(getById(id));
    }

    @GetMapping
    public ResponseEntity<List<VehicleBrand>> getAllVehicleBrands() {
        return ResponseEntity.ok(getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleBrand> updateVehicleBrand(@PathVariable Long id, @RequestBody VehicleBrand vehicleBrand) {
        return ResponseEntity.ok(update(id, vehicleBrand));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteVehicleBrand(@PathVariable Long id) {
        return ResponseEntity.ok(deleteById(id));
    }
}