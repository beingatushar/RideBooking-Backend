package com.beingatushar.ubercommons.entity.review;

import com.beingatushar.ubercommons.entity.BaseEntity;
import com.beingatushar.ubercommons.entity.booking.Booking;
import com.beingatushar.ubercommons.entity.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "reviews")
public class Review extends BaseEntity {
    private String comment;
    private Integer rating;

    @OneToOne
    private Booking booking;

    @ManyToOne
    private User user;


}
