package com.bcm.bcmanager.service.review;

import com.bcm.bcmanager.domain.review.Review;
import com.bcm.bcmanager.repository.review.ReviewRepository;
import com.bcm.bcmanager.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Review updateReview(Review review) throws Exception {
        Review dbData = repo.getById(review.getId());
        if (dbData.getPwd().equals(review.getPwd())) {
            dbData.setContent(review.getContent());
            dbData.setUpddt(DateUtil.now());

            return dbData;
        } else {
            throw new Exception("패스워드가 일치하지 않습니다.");
        }
    }

    public void deleteReview(Review review) throws Exception {
        Review dbData = repo.getById(review.getId());
        if (dbData.getPwd().equals(review.getPwd())) {
            repo.deleteById(review.getId());
        } else {
            throw new Exception("패스워드가 일치하지 않습니다.");
        }

    }
}
