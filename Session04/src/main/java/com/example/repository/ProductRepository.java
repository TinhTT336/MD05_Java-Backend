package com.example.repository;

import com.example.model.dto.ProductDTO;
import com.example.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findAllByProductNameContainingIgnoreCase(Pageable pageable, String name);
}
