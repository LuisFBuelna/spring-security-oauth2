package com.buelna.springsecurity.services.Impl;

import com.buelna.springsecurity.dto.CategoryDto;
import com.buelna.springsecurity.entities.Category;
import com.buelna.springsecurity.exception.ObjectNotFoundException;
import com.buelna.springsecurity.repositories.CategoryRepository;
import com.buelna.springsecurity.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Optional<Category> findOneById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public Category createOne(CategoryDto categoryDto) {

        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setStatus(Category.CategoryStatus.ENABLED);

        return categoryRepository.save(category);
    }

    @Override
    public Category updateOneById(Long categoryId, CategoryDto categoryDto) {
        Category categoryFromDB = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ObjectNotFoundException("Category not found with id %d"));

        categoryFromDB.setName(categoryDto.getName());

        return categoryRepository.save(categoryFromDB);
    }

    @Override
    public Category disableOneById(Long categoryId) {
        Category categoryFromDB = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ObjectNotFoundException("Category not found with id %d"));

        categoryFromDB.setStatus(Category.CategoryStatus.DISABLED);

        return categoryRepository.save(categoryFromDB);
    }
}
