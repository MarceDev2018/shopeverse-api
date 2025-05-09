package com.technova.shopeverse.repository;

import com.technova.shopeverse.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Aquí podemos agregar métodos personalizados si los necesitamos

}
