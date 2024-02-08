package com.buelna.springsecurity.controllers;

import com.buelna.springsecurity.dto.ProductDto;
import com.buelna.springsecurity.entities.Product;
import com.buelna.springsecurity.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<Product>> findAll(Pageable pageable) {
        Page<Product> productsPage = productService.findAll(pageable);

        if (productsPage.hasContent()) {
            return ResponseEntity.ok(productsPage);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> findOneById(@PathVariable Long productId) {
        Optional<Product> product = productService.findOneById(productId);

        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Product> createOne(@RequestBody @Valid ProductDto productDto) {

        Product product = productService.createOne(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateOneById(@PathVariable Long productId, @RequestBody @Valid ProductDto productDto) {

        Product product = productService.updateOneById(productId, productDto);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{productId}/disabled")
    public ResponseEntity<Product> disableOneById(@PathVariable Long productId) {

        Product product = productService.disableOneById(productId);
        return ResponseEntity.ok(product);
    }
}
