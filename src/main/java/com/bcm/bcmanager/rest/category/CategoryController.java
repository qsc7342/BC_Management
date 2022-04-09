package com.bcm.bcmanager.rest.category;

import com.bcm.bcmanager.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/rest/category")
public class CategoryController {

    private final CategoryService service;

    @GetMapping(value = "/")
    public ResponseEntity<?> getCategories() {
        return new ResponseEntity<>(service.getCategories(), HttpStatus.OK);
    }

}
