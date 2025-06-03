package com.technova.shopeverse.serviceImpl;

import com.technova.shopeverse.dto.ProductDTO;
import com.technova.shopeverse.model.Product;
import com.technova.shopeverse.repository.ProductRepository;
import com.technova.shopeverse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<ProductDTO> getAllProductDTOs() {
        return null;
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    @Override
    public ProductDTO toDTO(Product product) {
        String categoryName = product.getCategory() != null ? product.getCategory().getName() : null;
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                categoryName
        );
    }
    @Override
    public List<ProductDTO> getByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId).stream()
                .map(this::toDTO)
                .toList();

    }
}