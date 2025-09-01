package com.beingatushar.ubercommons.controller.vehicle;

import com.beingatushar.ubercommons.dto.VehicleBrandDTO;
import com.beingatushar.ubercommons.service.vehicle.VehicleBrandService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle-brands")
public class VehicleBrandController {

    private final VehicleBrandService vehicleBrandService;

    public VehicleBrandController(VehicleBrandService vehicleBrandService) {
        this.vehicleBrandService = vehicleBrandService;
    }

    @PostMapping
    public ResponseEntity<VehicleBrandDTO> createVehicleBrand(@Valid @RequestBody VehicleBrandDTO vehicleBrandDTO) {
        VehicleBrandDTO createdBrand = vehicleBrandService.create(vehicleBrandDTO);
        return new ResponseEntity<>(createdBrand, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleBrandDTO> getVehicleBrandById(@PathVariable Long id) {
        VehicleBrandDTO brand = vehicleBrandService.getById(id);
        return ResponseEntity.ok(brand);
    }

    @GetMapping
    public ResponseEntity<List<VehicleBrandDTO>> getAllVehicleBrands() {
        List<VehicleBrandDTO> brands = vehicleBrandService.getAll();
        return ResponseEntity.ok(brands);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleBrandDTO> updateVehicleBrand(@PathVariable Long id, @Valid @RequestBody VehicleBrandDTO vehicleBrandDTO) {
        VehicleBrandDTO updatedBrand = vehicleBrandService.update(id, vehicleBrandDTO);
        return ResponseEntity.ok(updatedBrand);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicleBrand(@PathVariable Long id) {
        vehicleBrandService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}