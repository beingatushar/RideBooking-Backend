package com.beingatushar.ubercommons.entity.vehicle;

import com.beingatushar.ubercommons.dto.VehicleColorDTO;
import com.beingatushar.ubercommons.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "vehicle_colors")
public class VehicleColor extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String name;

    public static VehicleColorDTO toDTO(VehicleColor vehicleColor) {
        return new VehicleColorDTO(vehicleColor.getId(), vehicleColor.getName());
    }
}