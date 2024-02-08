package com.buelna.springsecurity.controllers;

import com.buelna.springsecurity.dto.CategoryDto;
import com.buelna.springsecurity.dto.ProductDto;
import com.buelna.springsecurity.entities.Category;
import com.buelna.springsecurity.entities.Product;
import com.buelna.springsecurity.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<Category>> findAll(Pageable pageable) {
        Page<Category> categoryPage = categoryService.findAll(pageable);

        if (categoryPage.hasContent()) {
            return ResponseEntity.ok(categoryPage);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> findOneById(@PathVariable Long categoryId) {
        Optional<Category> category = categoryService.findOneById(categoryId);

        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Category> createOne(@RequestBody @Valid CategoryDto categoryDto) {

        Category category = categoryService.createOne(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateOneById(@PathVariable Long categoryId, @RequestBody @Valid CategoryDto categoryDto) {

        Category category = categoryService.updateOneById(categoryId, categoryDto);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{categoryId}/disabled")
    public ResponseEntity<Category> disableOneById(@PathVariable Long categoryId) {

        Category category = categoryService.disableOneById(categoryId);
        return ResponseEntity.ok(category);
    }
}
