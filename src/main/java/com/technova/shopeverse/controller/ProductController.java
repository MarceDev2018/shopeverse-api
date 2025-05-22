package com.technova.shopeverse.controller;
import com.technova.shopeverse.dto.ProductDTO;
import com.technova.shopeverse.model.Product;
import com.technova.shopeverse.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")

public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.ok(products); // 200 OK
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product product, BindingResult result) {
        Product created = productService.createProduct(product);

        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@Valid @PathVariable Long id, @RequestBody Product product) {
        Product created = productService.createProduct(product);

        return ResponseEntity.status(201).body(created);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build(); // 204 No Content

        } catch (IllegalArgumentException e) {

            return ResponseEntity.notFound().build(); // 404 Not Found

        }

    }

    @GetMapping("/dto")

    public ResponseEntity<List<ProductDTO>> getAllWithCategory() {

        List<ProductDTO> dtoList = productService.getAllProductDTOs();

        if (dtoList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(dtoList);

    }

    @GetMapping("/by-category/{categoryId}")

    public ResponseEntity<List<ProductDTO>> getByCategory(@PathVariable Long categoryId) {

        List<ProductDTO> products = productService.getByCategoryId(categoryId);

        if (products.isEmpty()) {

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);

    }

}