package com.bijanghanei.firstjobapp.review.service;

import com.bijanghanei.firstjobapp.company.entity.Company;
import com.bijanghanei.firstjobapp.company.service.CompanyService;
import com.bijanghanei.firstjobapp.review.entity.Review;
import com.bijanghanei.firstjobapp.review.repository.ReviewRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;
    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService){
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findAllByCompanyId(companyId);
    }

    @Override
    public boolean createReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findAllByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review r) {
        Review review = getReview(companyId,reviewId);
        if (review!=null){
            r.setCompany(review.getCompany());
            r.setId(reviewId);
            reviewRepository.save(r);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        Review review = getReview(companyId,reviewId);
        if (review!=null){
            reviewRepository.delete(review);
            return true;
        }else{
            return false;
        }
    }


}
