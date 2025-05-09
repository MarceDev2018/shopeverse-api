package com.technova.shopeverse.repository;

import com.technova.shopeverse.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;



@Repository

public interface CategoryRepository extends JpaRepository<Category, Long> {

}