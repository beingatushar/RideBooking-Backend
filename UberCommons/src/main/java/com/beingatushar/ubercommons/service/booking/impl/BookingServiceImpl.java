package com.beingatushar.ubercommons.service.booking.impl;

import com.beingatushar.ubercommons.dto.BookingDTO;
import com.beingatushar.ubercommons.entity.booking.Booking;
import com.beingatushar.ubercommons.entity.location.Location;
import com.beingatushar.ubercommons.exception.ResourceNotFoundException;
import com.beingatushar.ubercommons.mapper.BookingMapper;
import com.beingatushar.ubercommons.mapper.LocationMapper;
import com.beingatushar.ubercommons.repository.BookingRepository;
import com.beingatushar.ubercommons.service.booking.BookingService;
import com.beingatushar.ubercommons.service.driver.DriverService;
import com.beingatushar.ubercommons.service.location.LiveLocationService;
import com.beingatushar.ubercommons.service.location.LocationService;
import com.beingatushar.ubercommons.service.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public BookingDTO create(BookingDTO bookingDTO) {
        Booking booking = new Booking();

        // Use getByRef for efficient association
        booking.setUser(userService.getByRef(bookingDTO.getUserId()));
        booking.setDriver(driverService.getByRef(bookingDTO.getDriverId()));

        // Create and associate locations
        Location pickupLocation = locationService.create(LocationMapper.toEntity(bookingDTO.getPickupLocation()));
        booking.setPickupLocation(pickupLocation);

        Location dropOffLocation = locationService.create(LocationMapper.toEntity(bookingDTO.getDropOffLocation()));
        booking.setDropOffLocation(dropOffLocation);

        // Save the booking first to get an ID
        Booking savedBooking = bookingRepository.save(booking);

        // Now, handle the live location if it exists, associating it with the saved booking
        if (bookingDTO.getLiveLocation() != null) {
            // LiveLocation creation logic would go here
            // For example:
            // LiveLocation liveLocation = new LiveLocation();
            // liveLocation.setLatitude(savedBooking.getPickupLocation().getLatitude());
            // liveLocation.setLongitude(savedBooking.getPickupLocation().getLongitude());
            // liveLocation.setBooking(savedBooking);
            // liveLocationService.create(liveLocation);
        }

        return BookingMapper.toDTO(savedBooking);
    }

    @Override
    @Transactional
    public BookingDTO update(Long id, BookingDTO bookingDTO) {
        Booking bookingToUpdate = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking with id " + id + " not found"));

        bookingToUpdate.setBookingStatus(bookingDTO.getBookingStatus());

        Booking updatedBooking = bookingRepository.save(bookingToUpdate);
        return BookingMapper.toDTO(updatedBooking);
    }

    @Override
    public BookingDTO getById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking with id " + id + " not found"));
        return BookingMapper.toDTO(booking);
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
    public List<BookingDTO> getAll() {
        return bookingRepository.findAll()
                .stream()
                .map(BookingMapper::toDTO)
                .collect(Collectors.toList());
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