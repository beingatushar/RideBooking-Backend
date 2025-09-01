package com.beingatushar.ubercommons.repository;

import com.beingatushar.ubercommons.entity.location.LiveLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiveLocationRepository extends JpaRepository<LiveLocation, Long> {
}
