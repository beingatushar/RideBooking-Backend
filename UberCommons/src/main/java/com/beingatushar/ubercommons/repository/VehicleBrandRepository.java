package com.beingatushar.ubercommons.repository;

import com.beingatushar.ubercommons.entity.vehicle.VehicleBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleBrandRepository extends JpaRepository<VehicleBrand, Long> {
}