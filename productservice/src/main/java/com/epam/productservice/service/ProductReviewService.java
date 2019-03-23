package com.epam.productservice.service;

import com.epam.productservice.entity.Review;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductReviewService {

    public List<Review> getReviews(int productId) ;
    public Review AddProductReview(Review review,int productId);
    public Review updateProductReview( Review review ,int productId ,int reviewId);
    public ResponseEntity<Object> deleteProductReview(int productId, int reviewId);

}
