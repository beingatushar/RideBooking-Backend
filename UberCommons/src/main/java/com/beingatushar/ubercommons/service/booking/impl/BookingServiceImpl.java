package com.beingatushar.ubercommons.service.booking.impl;

import com.beingatushar.ubercommons.entity.booking.Booking;
import com.beingatushar.ubercommons.exception.ResourceNotFoundException;
import com.beingatushar.ubercommons.repository.BookingRepository;
import com.beingatushar.ubercommons.service.booking.BookingService;
import com.beingatushar.ubercommons.service.driver.DriverService;
import com.beingatushar.ubercommons.service.location.LiveLocationService;
import com.beingatushar.ubercommons.service.location.LocationService;
import com.beingatushar.ubercommons.service.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final UserService userService;
    private final DriverService driverService;
    private final LocationService locationService;
    private final LiveLocationService liveLocationService;

    BookingServiceImpl(BookingRepository bookingRepository, UserService userService, DriverService driverService, LocationService locationService, LiveLocationService liveLocationService) {
        this.bookingRepository = bookingRepository;
        this.userService = userService;
        this.driverService = driverService;
        this.locationService = locationService;
        this.liveLocationService = liveLocationService;
    }

    @Override
    @Transactional
    public Booking create(Booking booking) {
        booking.setUser(userService.getById(booking.getUser().getId()));
        booking.setDriver(driverService.getById(booking.getDriver().getId()));
        booking.setPickupLocation(locationService.create(booking.getPickupLocation()));
        booking.setDropOffLocation(locationService.create(booking.getDropOffLocation())); // Corrected this line

        // Save the booking first to get an ID
        Booking savedBooking = bookingRepository.save(booking);

        savedBooking.setLiveLocation(liveLocationService.create(booking.getLiveLocation()));
        return savedBooking;
    }

    @Override
    @Transactional
    public Booking update(Long id, Booking booking) {
        Booking bookingToUpdate = getById(id);
        BeanUtils.copyProperties(booking, bookingToUpdate);
        return bookingRepository.save(bookingToUpdate);
    }

    @Override
    public Booking getById(Long id) {
        return bookingRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Booking with id " + id + " not found"));
    }

    @Override
    @Transactional
    public Boolean deleteById(Long id) {
        if (!existsById(id)) {
            return false;
        }
        bookingRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        return bookingRepository.existsById(id);
    }

    @Override

    public Booking getByRef(Long id) {
        return bookingRepository.getReferenceById(id);
    }
}