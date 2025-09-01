package com.beingatushar.ubercommons.mapper;

import com.beingatushar.ubercommons.dto.BookingDTO;
import com.beingatushar.ubercommons.entity.booking.Booking;

public class BookingMapper {

    public static BookingDTO toDTO(Booking booking) {
        if (booking == null) {
            return null;
        }
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setUserId(booking.getUser().getId());
        bookingDTO.setDriverId(booking.getDriver().getId());
        bookingDTO.setPickupLocation(LocationMapper.toDTO(booking.getPickupLocation()));
        bookingDTO.setDropOffLocation(LocationMapper.toDTO(booking.getDropOffLocation()));
        bookingDTO.setBookingStatus(booking.getBookingStatus());
        bookingDTO.setLiveLocation(LiveLocationMapper.toDTO(booking.getLiveLocation()));
        return bookingDTO;
    }

    // Note: We don't create a toEntity for BookingDTO directly because
    // creating a booking requires fetching User and Driver entities from the DB.
    // This logic is better handled in the BookingService.
}