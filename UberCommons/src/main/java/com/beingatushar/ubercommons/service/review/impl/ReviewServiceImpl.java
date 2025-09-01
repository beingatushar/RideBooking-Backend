package com.beingatushar.ubercommons.service.review.impl;

import com.beingatushar.ubercommons.entity.review.Review;
import com.beingatushar.ubercommons.repository.ReviewRepository;
import com.beingatushar.ubercommons.service.booking.BookingService;
import com.beingatushar.ubercommons.service.review.ReviewService;
import com.beingatushar.ubercommons.service.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookingService bookingService;
    private final UserService userService;

    ReviewServiceImpl(ReviewRepository reviewRepository, BookingService bookingService, UserService userService) {
        this.reviewRepository = reviewRepository;
        this.bookingService = bookingService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Review create(Review review) {
        review.setBooking(bookingService.getByRef(review.getBooking().getId()));
        review.setUser(userService.getByRef(review.getUser().getId()));
        return reviewRepository.save(review);
    }

    @Override
    @Transactional
    public Review update(Long id, Review review) {
        Review reviewToUpdate = getById(id);
        BeanUtils.copyProperties(review, reviewToUpdate);
        return reviewRepository.save(reviewToUpdate);
    }

    @Override
    public Review getById(Long id) {
        return reviewRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Review with id " + id + " not found"));
    }

    @Override
    @Transactional
    public Boolean deleteById(Long id) {
        if (!existsById(id)) {
            return false;
        }
        reviewRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        return reviewRepository.existsById(id);
    }
}
