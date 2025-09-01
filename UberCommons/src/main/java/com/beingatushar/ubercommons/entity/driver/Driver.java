package com.beingatushar.ubercommons.entity.driver;

import com.beingatushar.ubercommons.dto.DriverDTO;
import com.beingatushar.ubercommons.entity.user.User;
import com.beingatushar.ubercommons.entity.vehicle.Vehicle;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "drivers")
@PrimaryKeyJoinColumn(name = "id")
public class Driver extends User {
    @JoinColumn(nullable = false, unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    private Vehicle vehicle;

    public static DriverDTO toDTO(Driver driver) {
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setId(driver.getId());
        driverDTO.setName(driver.getName());
        driverDTO.setEmail(driver.getEmail());
        driverDTO.setVehicle(Vehicle.toDTO(driver.getVehicle()));
        return driverDTO;
    }

//    @OneToMany(mappedBy = "driver")
//    private List<Booking> bookings;

}