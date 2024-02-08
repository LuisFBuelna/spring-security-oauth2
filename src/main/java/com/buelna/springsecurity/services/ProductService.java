package com.buelna.springsecurity.services;

import com.buelna.springsecurity.dto.ProductDto;
import com.buelna.springsecurity.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {
    Page<Product> findAll(Pageable pageable);

    Optional<Product> findOneById(Long productId);

    Product createOne(ProductDto productDto);

    Product updateOneById(Long productId, ProductDto productDto);

    Product disableOneById(Long productId);
}
