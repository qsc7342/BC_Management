package com.bcm.bcmanager.repository.category;

import com.bcm.bcmanager.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryReposittory extends JpaRepository<Category, Long> {
}
