package com.beingatushar.ubercommons.mapper;

import com.beingatushar.ubercommons.dto.ReviewDTO;
import com.beingatushar.ubercommons.entity.review.Review;

public class ReviewMapper {

    public static ReviewDTO toDTO(Review review) {
        if (review == null) {
            return null;
        }
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setComment(review.getComment());
        reviewDTO.setRating(review.getRating());
        reviewDTO.setBookingId(review.getBooking().getId());
        reviewDTO.setUserId(review.getUser().getId());
        return reviewDTO;
    }

    // As with Booking, creating a Review entity requires fetching other entities,
    // so this is best handled in the ReviewService.
}