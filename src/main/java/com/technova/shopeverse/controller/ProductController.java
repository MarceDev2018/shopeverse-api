package com.technova.shopeverse.controller;
import com.technova.shopeverse.model.Product;
import com.technova.shopeverse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController

@RequestMapping("/api/products")

public class ProductController {
    @Autowired

    private ProductRepository productRepository;

    @GetMapping

    public List<Product> getAll() {

        return productRepository.findAll();

    }

    @GetMapping("/{id}")

    public Product getById(@PathVariable Long id) {

        return productRepository.findById(id).orElse(null);

    }

    @PostMapping

    public Product create(@RequestBody Product product) {

        return productRepository.save(product);

    }

    @PutMapping("/{id}")

    public Product update(@PathVariable Long id, @RequestBody Product productDetails) {

        Product product = productRepository.findById(id).orElse(null);

        if (product != null) {

            product.setName(productDetails.getName());

            product.setDescription(productDetails.getDescription());

            product.setPrice(productDetails.getPrice());

            return productRepository.save(product);

        }
        return null;
    }



    @DeleteMapping("/{id}")

    public void delete(@PathVariable Long id) {

        productRepository.deleteById(id);

    }

}