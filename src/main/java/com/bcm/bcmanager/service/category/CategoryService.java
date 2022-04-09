package com.bcm.bcmanager.service.category;

import com.bcm.bcmanager.domain.category.Category;
import com.bcm.bcmanager.repository.category.CategoryReposittory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryReposittory repo;

    public List<Category> getCategories(){
        return repo.findAll();
    }

}
