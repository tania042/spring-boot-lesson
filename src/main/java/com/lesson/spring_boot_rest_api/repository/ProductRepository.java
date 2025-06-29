package com.lesson.spring_boot_rest_api.repository;


import com.lesson.spring_boot_rest_api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Find product by category
    List<Product> findProducstByCategory(String name);

    // Find products with price less or equal to maxprice;
    List<Product> findProductsByPriceLessThanEqual(Double maxprice);

    // Find products containing name (case insensitive)
    List<Product> findProductsByNameContainingIgnoreCase(String name);
}
