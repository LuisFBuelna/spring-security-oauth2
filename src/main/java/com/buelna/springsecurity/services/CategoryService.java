package com.buelna.springsecurity.services;

import com.buelna.springsecurity.dto.CategoryDto;
import com.buelna.springsecurity.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryService {
    Page<Category> findAll(Pageable pageable);

    Optional<Category> findOneById(Long categoryId);

    Category createOne(CategoryDto categoryDto);

    Category updateOneById(Long categoryId, CategoryDto categoryDto);

    Category disableOneById(Long categoryId);
}
