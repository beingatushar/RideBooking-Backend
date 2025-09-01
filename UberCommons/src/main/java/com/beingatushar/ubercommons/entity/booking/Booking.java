package com.beingatushar.ubercommons.entity.booking;

import com.beingatushar.ubercommons.entity.BaseEntity;
import com.beingatushar.ubercommons.entity.driver.Driver;
import com.beingatushar.ubercommons.entity.location.LiveLocation;
import com.beingatushar.ubercommons.entity.location.Location;
import com.beingatushar.ubercommons.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

//import com.beingatushar.ubercommons.entity.driver.Driver;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "bookings")
public class Booking extends BaseEntity {

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Driver driver;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Location pickupLocation;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Location dropOffLocation;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    private LiveLocation liveLocation;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private BookingStatus bookingStatus = BookingStatus.SCHEDULED;

    public enum BookingStatus {
        SCHEDULED,
        CANCELLED,
        CAB_ARRIVED,
        ASSIGNING_DRIVER,
        IN_RIDE,
        COMPLETED
    }
}
