package com.bcm.bcmanager.service.review;

import com.bcm.bcmanager.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository repo;

    public Object getReview(String rid) {
        return repo.findById(Long.parseLong(rid));
    }
}
