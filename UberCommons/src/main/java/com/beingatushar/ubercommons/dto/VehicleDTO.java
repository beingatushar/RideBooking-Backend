package com.beingatushar.ubercommons.dto;

import com.beingatushar.ubercommons.enums.VehicleType;
import lombok.Data;

@Data
public class VehicleDTO {

    private Long id;
    private VehicleBrandDTO brand;
    private String model;
    private VehicleColorDTO color;
    private String licensePlate;
    private Integer capacity;
    private VehicleType vehicleType;
}