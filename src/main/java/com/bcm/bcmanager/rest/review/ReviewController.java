package com.bcm.bcmanager.rest.review;

import com.bcm.bcmanager.domain.review.Review;
import com.bcm.bcmanager.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("rest/review")
public class ReviewController {

    private final ReviewService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getReviews() {
        return new ResponseEntity<>(service.getReviews(), HttpStatus.OK);
    }

    @GetMapping(value = "/{rid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getReview(@PathVariable String rid) {
        return new ResponseEntity<>(service.getReview(rid), HttpStatus.OK);
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveReview(@RequestBody Review review) {
        return new ResponseEntity<>(service.saveReview(review), HttpStatus.OK);
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateReview(@RequestBody Review review) {
        try {
            return new ResponseEntity<>(service.updateReview(review), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteReview(@RequestBody Review review) {
        try {
            service.deleteReview(review);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
