package com.bijanghanei.firstjobapp.review.controller;

import com.bijanghanei.firstjobapp.review.entity.Review;
import com.bijanghanei.firstjobapp.review.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/reviews")
public class ReviewController {
    ReviewService reviewService;
    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<String> createReview(@PathVariable Long companyId,@RequestBody Review review){
        boolean created = reviewService.createReview(companyId,review);
        return created ? new ResponseEntity<>("successfully created",HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId,@PathVariable Long reviewId){
        Review review = reviewService.getReview(companyId,reviewId);
        if (review!=null){
            return new ResponseEntity<>(review, HttpStatus.OK);
        }else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,@PathVariable Long reviewId,@RequestBody Review review){
        boolean updated = reviewService.updateReview(companyId,reviewId,review);
        return updated ? new ResponseEntity<>("successfully updated",HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,@PathVariable Long reviewId){
        boolean deleted = reviewService.deleteReview(companyId,reviewId);
        return deleted ? new ResponseEntity<>("successfully deleted",HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
