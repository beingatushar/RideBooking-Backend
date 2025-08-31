package com.beingatushar.ubercommons.entity.location;

import com.beingatushar.ubercommons.entity.booking.Booking;
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
    private Booking booking;
}
