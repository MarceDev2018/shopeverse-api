package com.technova.shopeverse.model;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


@Entity
public class Product {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @NotEmpty(message = "El nombre no puede estar vacío.")
    private String name;
    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres.")

    private String description;

    private Double price;

    @ManyToOne

    @JoinColumn(name = "category_id") // Esta será la clave foránea en la base de datos

    private Category category;

    public Product() {}

    public Product(Long id, String name, String description, Double price) {

        this.id = id;

        this.name = name;

        this.description = description;

        this.price = price;

    }
    // Getters y Setters

    public Long getId() {

        return id;

    }

    public void setId(Long id) {

        this.id = id;

    }

    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;

    }

    public String getDescription() {

        return description;

    }

    public void setDescription(String description) {

        this.description = description;

    }

    public Double getPrice() {

        return price;

    }

    public void setPrice(Double price) {

        this.price = price;

    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}