package com.beingatushar.ubercommons.service.review;

import com.beingatushar.ubercommons.entity.review.Review;
import com.beingatushar.ubercommons.repository.ReviewRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review create(Review review) {
        return reviewRepository.save(review);
    }

    @Override
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
