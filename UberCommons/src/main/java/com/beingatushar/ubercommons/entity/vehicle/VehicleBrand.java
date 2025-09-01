package com.beingatushar.ubercommons.entity.vehicle;

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
@Table(name = "brands")
public class VehicleBrand extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String name;
}
