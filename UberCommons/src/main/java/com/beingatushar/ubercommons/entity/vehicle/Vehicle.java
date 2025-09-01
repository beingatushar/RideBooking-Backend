package com.beingatushar.ubercommons.entity.vehicle;

import com.beingatushar.ubercommons.dto.VehicleDTO;
import com.beingatushar.ubercommons.entity.BaseEntity;
import com.beingatushar.ubercommons.enums.VehicleType;
import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "vehicles")
public class Vehicle extends BaseEntity {
    @ManyToOne
    private VehicleBrand brand;
    private String model;
    @ManyToOne
    private VehicleColor color;
    @Column(unique = true, nullable = false)
    private String licensePlate;
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private VehicleType vehicleType = VehicleType.OTHER;


    public static VehicleDTO toDTO(Vehicle vehicle) {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(vehicle.getId());
        vehicleDTO.setVehicleType(vehicle.getVehicleType());
        vehicleDTO.setBrand(VehicleBrand.toDTO(vehicle.getBrand()));
        vehicleDTO.setColor(VehicleColor.toDTO(vehicle.getColor()));
        vehicleDTO.setCapacity(vehicle.getCapacity());
        vehicleDTO.setLicensePlate(vehicle.getLicensePlate());
        vehicleDTO.setModel(vehicle.getModel());
        return vehicleDTO;

    }
}
