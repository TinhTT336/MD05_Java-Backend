package com.example.session03_springboot.repository;

import com.example.session03_springboot.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
