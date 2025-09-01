package com.beingatushar.ubercommons.dto;

import com.beingatushar.ubercommons.enums.BookingStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookingStatusUpdateDTO {

    @NotNull(message = "Booking status cannot be null")
    private BookingStatus bookingStatus;
}