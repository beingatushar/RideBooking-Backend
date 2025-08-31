package com.beingatushar.ubercommons.controller.review;

import com.beingatushar.ubercommons.controller.BaseRestController;
import com.beingatushar.ubercommons.entity.review.Review;
import com.beingatushar.ubercommons.service.review.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/reviews")
public class ReviewController extends BaseRestController<Review, Long> {
    private final ReviewService reviewService;

    ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    protected ReviewService getService() {
        return reviewService;
    }

    @PostMapping
    ResponseEntity<Review> createReview(@RequestBody Review review) {
        return ResponseEntity.status(HttpStatus.CREATED).body(create(review));
    }

    @GetMapping("/{id}")
    ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        return ResponseEntity.ok(getById(id));
    }

    @GetMapping
    ResponseEntity<Iterable<Review>> getAllReviews() {
        return ResponseEntity.ok(getAll());
    }

    @PutMapping("/{id}")
    ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review review) {
        return ResponseEntity.ok(update(id, review));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteReview(@PathVariable Long id) {
        return ResponseEntity.ok(deleteById(id));
    }
}
