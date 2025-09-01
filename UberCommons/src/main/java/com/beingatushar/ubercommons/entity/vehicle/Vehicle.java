package com.beingatushar.ubercommons.entity.vehicle;

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
}
