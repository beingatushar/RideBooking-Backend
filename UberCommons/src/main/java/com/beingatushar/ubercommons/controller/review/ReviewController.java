package com.beingatushar.ubercommons.controller.review;

import com.beingatushar.ubercommons.dto.ReviewDTO;
import com.beingatushar.ubercommons.service.review.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> createReview(@Valid @RequestBody ReviewDTO reviewDTO) {
        ReviewDTO createdReview = reviewService.create(reviewDTO);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long id) {
        ReviewDTO review = reviewService.getById(id);
        return ResponseEntity.ok(review);
    }

    @GetMapping
    public ResponseEntity<List<ReviewDTO>> getAllReviews() {
        List<ReviewDTO> reviews = reviewService.getAll();
        return ResponseEntity.ok(reviews);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> updateReview(@PathVariable Long id, @Valid @RequestBody ReviewDTO reviewDTO) {
        ReviewDTO updatedReview = reviewService.update(id, reviewDTO);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}