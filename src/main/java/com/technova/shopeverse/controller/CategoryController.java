package com.technova.shopeverse.controller;

import com.technova.shopeverse.model.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/categories")

public class CategoryController {

    @GetMapping
    public List<Category> getAllCategories() {
        return Arrays.asList(
                new Category(1L, "Mujer", "Lencería, maquillaje, de todo para la Mujer"),
                new Category(2L, "Electrónica", "Artículos Electrónicos"),
                new Category(3L, "Deportes", "Equipos y accesorios deportivos")
        );
    }
}
