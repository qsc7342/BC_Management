package com.bcm.bcmanager.rest.review;

import com.bcm.bcmanager.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("rest/review")
public class ReviewController {

    private final ReviewService service;

    @GetMapping(value = "/{rid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getReview(@PathVariable String rid) {
        return new ResponseEntity<>(service.getReview(rid), HttpStatus.OK);
    }

}
