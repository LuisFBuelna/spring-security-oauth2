package com.buelna.springsecurity.services.Impl;

import com.buelna.springsecurity.dto.ProductDto;
import com.buelna.springsecurity.entities.Category;
import com.buelna.springsecurity.entities.Product;
import com.buelna.springsecurity.exception.ObjectNotFoundException;
import com.buelna.springsecurity.repositories.ProductRepository;
import com.buelna.springsecurity.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> findOneById(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Product createOne(ProductDto productDto) {

        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStatus(Product.ProductStatus.ENABLED);

        Category category = new Category();
        category.setId(productDto.getCategoryId());
        product.setCategory(category);

        return productRepository.save(product);
    }

    @Override
    public Product updateOneById(Long productId, ProductDto productDto) {
        Product productFromDB = productRepository.findById(productId)
                .orElseThrow(() -> new ObjectNotFoundException("Product not found with id " + productId));

        productFromDB.setName(productDto.getName());
        productFromDB.setPrice(productDto.getPrice());

        Category category = new Category();
        category.setId(productDto.getCategoryId());

        return productRepository.save(productFromDB);
    }

    @Override
    public Product disableOneById(Long productId) {
        Product productFromDB = productRepository.findById(productId)
                .orElseThrow(() -> new ObjectNotFoundException("Product not found with id " + productId));
        productFromDB.setStatus(Product.ProductStatus.DISABLED);

        return productRepository.save(productFromDB);
    }
}
