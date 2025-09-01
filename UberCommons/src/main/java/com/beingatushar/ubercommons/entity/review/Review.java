package com.beingatushar.ubercommons.entity.review;

import com.beingatushar.ubercommons.dto.ReviewDTO;
import com.beingatushar.ubercommons.entity.BaseEntity;
import com.beingatushar.ubercommons.entity.booking.Booking;
import com.beingatushar.ubercommons.entity.user.User;
import jakarta.persistence.*;
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

    @OneToOne(fetch = FetchType.LAZY)
    private Booking booking;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public static ReviewDTO toDTO(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setComment(review.getComment());
        reviewDTO.setRating(review.getRating());
        reviewDTO.setBookingId(review.getBooking().getId());
        reviewDTO.setUserId(review.getUser().getId());
        return reviewDTO;
    }
}
