package com.lesson.spring_boot_rest_api.service;

import com.lesson.spring_boot_rest_api.model.Product;
import com.lesson.spring_boot_rest_api.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // get all the product
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    // get product by id
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Get products by category
    public  List<Product> getProductByCategory(String categoryName) {
        return productRepository.findProducstByCategory(categoryName);
    }

    public  List<Product> getProductsByPriceLessThanEqual(Double price) {
        return productRepository.findProductsByPriceLessThanEqual(price);
    }

    public List<Product> getProductsByProductName(String name) {
        return productRepository.findProductsByNameContainingIgnoreCase(name);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }
}
