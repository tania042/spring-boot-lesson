package com.lesson.spring_boot_rest_api.controller;

import com.lesson.spring_boot_rest_api.model.Product;
import com.lesson.spring_boot_rest_api.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Get all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id)
                .orElseThrow(
                        () -> new RuntimeException("Product not found")
                );
        return ResponseEntity.ok(product);
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Product>> getProductsByCategory(
            @PathVariable String categoryName
    ) {
     return ResponseEntity.ok(productService.getProductByCategory(categoryName));
    }

    @GetMapping("/price/{maxPrice}")
    public ResponseEntity<List<Product>> getProductByMaxPrice(
            @PathVariable Double maxPrice) {
        return ResponseEntity.ok(
                productService.getProductsByPriceLessThanEqual(maxPrice)
        );
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String name) {
        return ResponseEntity.ok(
                productService.getProductsByProductName(name)
        );
    }

    // add new product
    @PostMapping
    public  ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product newProduct = productService.saveProduct(product);

        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestBody Product product
    ) {
        Product productToUpdate = productService.getProductById(id)
                .orElseThrow(
                        () -> new RuntimeException("Product not found")
                );

        // Update product field
        productToUpdate.setName(product.getName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setCategory(product.getCategory());

        Product updatedProduct = productService.saveProduct(productToUpdate);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        }

        // Delete product
        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
            Product productToDelete = productService.getProductById(id)
                    .orElseThrow(
                            () -> new RuntimeException("Product not found")
                    );

            productService.deleteProduct(productToDelete);

            return ResponseEntity.ok("Product Deleted");


    }



}
