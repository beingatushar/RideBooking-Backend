package com.beingatushar.ubercommons.repository;

import com.beingatushar.ubercommons.entity.vehicle.VehicleColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleColorRepository extends JpaRepository<VehicleColor, Long> {
    VehicleColor findByName(String name);
}