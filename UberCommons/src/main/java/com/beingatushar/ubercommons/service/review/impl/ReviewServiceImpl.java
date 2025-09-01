package com.beingatushar.ubercommons.service.review.impl;

import com.beingatushar.ubercommons.dto.ReviewDTO;
import com.beingatushar.ubercommons.entity.review.Review;
import com.beingatushar.ubercommons.exception.ResourceNotFoundException;
import com.beingatushar.ubercommons.mapper.ReviewMapper;
import com.beingatushar.ubercommons.repository.ReviewRepository;
import com.beingatushar.ubercommons.service.booking.BookingService;
import com.beingatushar.ubercommons.service.review.ReviewService;
import com.beingatushar.ubercommons.service.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookingService bookingService;
    private final UserService userService; // Assuming you have a UserService that provides getByRef

    ReviewServiceImpl(ReviewRepository reviewRepository, BookingService bookingService, UserService userService) {
        this.reviewRepository = reviewRepository;
        this.bookingService = bookingService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public ReviewDTO create(ReviewDTO reviewDTO) {
        Review review = new Review();
        // Use getByRef to create a proxy for the association, avoiding a full fetch.
        review.setBooking(bookingService.getByRef(reviewDTO.getBookingId()));
        review.setUser(userService.getByRef(reviewDTO.getUserId()));
        review.setComment(reviewDTO.getComment());
        review.setRating(reviewDTO.getRating());

        Review savedReview = reviewRepository.save(review);
        return ReviewMapper.toDTO(savedReview);
    }

    @Override
    @Transactional
    public ReviewDTO update(Long id, ReviewDTO reviewDTO) {
        Review reviewToUpdate = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review with id " + id + " not found"));

        // Safe, manual mapping of updatable fields
        reviewToUpdate.setComment(reviewDTO.getComment());
        reviewToUpdate.setRating(reviewDTO.getRating());

        Review updatedReview = reviewRepository.save(reviewToUpdate);
        return ReviewMapper.toDTO(updatedReview);
    }

    @Override
    public ReviewDTO getById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review with id " + id + " not found"));
        return ReviewMapper.toDTO(review);
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
    public List<ReviewDTO> getAll() {
        return reviewRepository.findAll()
                .stream()
                .map(ReviewMapper::toDTO)
                .toList();
    }

    @Override
    public boolean existsById(Long id) {
        return reviewRepository.existsById(id);
    }
}