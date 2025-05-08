package com.technova.shopeverse.controller;
import com.technova.shopeverse.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;



@RestController

@RequestMapping("/api/products")

public class ProductController {



    @GetMapping

    public List<Product> getAllProducts() {

        return Arrays.asList(

                new Product(1L, "Laptop Lenovo", "Notebook 15 pulgadas", 850.0),

                new Product(2L, "Mouse Logitech", "Mouse inalámbrico", 25.5),

                new Product(3L, "Monitor Samsung", "Monitor 24 pulgadas", 199.99)

        );

    }

}