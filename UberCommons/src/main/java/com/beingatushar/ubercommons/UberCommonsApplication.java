package com.beingatushar.ubercommons;

import com.beingatushar.ubercommons.entity.booking.Booking;
import com.beingatushar.ubercommons.entity.driver.Driver;
import com.beingatushar.ubercommons.entity.location.Location;
import com.beingatushar.ubercommons.entity.user.User;
import com.beingatushar.ubercommons.entity.vehicle.Vehicle;
import com.beingatushar.ubercommons.repository.BookingRepository;
import com.beingatushar.ubercommons.repository.DriverRepository;
import com.beingatushar.ubercommons.repository.LocationRepository;
import com.beingatushar.ubercommons.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.Instant;

@SpringBootApplication
@EnableJpaAuditing
public class UberCommonsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UberCommonsApplication.class, args);
    }

    // This bean will be executed automatically when the application starts
    @Bean
    public CommandLineRunner demo(DriverRepository driverRepository, UserRepository userRepository, BookingRepository bookingRepository, LocationRepository locationRepository) {
        return args -> {
            // 1. Create and save a Driver
            Driver driver = new Driver();
            driver.setName("Tushar");
            driver.setEmail("tushar.driver@example.com"); // Email must be unique
            driver.setPassword("securepassword123");
            driver.setVehicle(Vehicle.builder().build());
            driver.setCreatedDate(Instant.now());
            driver.setUpdatedDate(Instant.now());

            System.out.println("Saving driver...");
            Driver savedDriver = driverRepository.save(driver);
            System.out.println("All drivers from repository: " + driverRepository.findAll());
            System.out.println("---------------------------------");


            // 2. Create and save a User
            User user = new User();
            user.setName("John Doe");
            user.setEmail("johndoe.user@example.com"); // Email must be unique
            user.setPassword("password456");

            System.out.println("Saving user...");
            // Save the user and store the returned instance, which has the generated ID
            User savedUser = userRepository.save(user);
            System.out.println("All users from repository: " + userRepository.findAll());
            System.out.println("---------------------------------");


            // 3. Create a Booking and associate it with the saved User
            Booking booking = new Booking();
            booking.setDriver(savedDriver);
            booking.setUser(savedUser); // Associate with the persisted user
            Location pickupLocation = Location.builder().latitude(1.25).longitude(1.55).build();
            locationRepository.save(pickupLocation);
            booking.setPickupLocation(pickupLocation);
            Location dropOffLocation = Location.builder().latitude(2.25).longitude(2.55).build();
            locationRepository.save(dropOffLocation);
            booking.setDropOffLocation(dropOffLocation);

//            Booking booking2 = Booking.builder()
//                    .user(savedUser)
//                    .pickupLocation("Indira Gandhi International Airport, New Delhi")
//                    .dropOffLocation("Connaught Place, New Delhi")
//                    .driver(driver)
//                    .build();
//            bookingRepository.save(booking2);

            System.out.println("Saving booking...");
            bookingRepository.save(booking);
            System.out.println("All bookings from repository: " + bookingRepository.findAll());
            System.out.println("---------------------------------");
        };
    }
}
