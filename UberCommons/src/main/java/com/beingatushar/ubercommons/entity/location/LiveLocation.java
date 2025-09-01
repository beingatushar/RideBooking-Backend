package com.beingatushar.ubercommons.entity.location;

import com.beingatushar.ubercommons.dto.LiveLocationDTO;
import com.beingatushar.ubercommons.entity.booking.Booking;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "live_locations")
@PrimaryKeyJoinColumn(name = "id")
public class LiveLocation extends Location {
    @OneToOne
    @JsonIgnoreProperties("liveLocation")
    private Booking booking;

    public static LiveLocationDTO toDTO(LiveLocation location) {
        LiveLocationDTO locationDTO = new LiveLocationDTO();
        locationDTO.setLatitude(location.getLatitude());
        locationDTO.setLongitude(location.getLongitude());
        locationDTO.setBookingId(location.getBooking().getId());
        location.setUpdatedDate(location.getUpdatedDate());
        return locationDTO;
    }
}
