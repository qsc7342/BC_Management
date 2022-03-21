package com.bcm.bcmanager.service.review;

import com.bcm.bcmanager.domain.review.Review;
import com.bcm.bcmanager.repository.review.ReviewRepository;
import com.bcm.bcmanager.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository repo;

    public Review getReview(String rid) {
        return repo.getById(Long.parseLong(rid));
    }

    public List<Review> getReviews() {
        List<Review> review = repo.findAll();

        return review;
    }

    public Review saveReview(Review review) {
        review.setCredt(DateUtil.now());
        return repo.save(review);
    }

    @Transactional
    public Review updateReview(Review review) {
        Review newReview = repo.getById(review.getId());

        newReview.setContent(review.getContent());
        newReview.setUpddt(DateUtil.now());

        return newReview;
    }

    public void deleteReview(Long rid) {
        repo.deleteById(rid);
    }
}
