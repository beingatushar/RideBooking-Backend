package com.beingatushar.ubercommons.dto;

import com.beingatushar.ubercommons.enums.BookingStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookingDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Driver ID is required")
    private Long driverId;

    @Valid
    @NotNull(message = "Pickup location is required")
    private LocationDTO pickupLocation;

    @Valid
    @NotNull(message = "Drop-off location is required")
    private LocationDTO dropOffLocation;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BookingStatus bookingStatus;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LiveLocationDTO liveLocation;
}