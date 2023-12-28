package com.example.session03_springboot.model.service;

import com.example.session03_springboot.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product saveOrUpdate(Product category);

    Product findById(Long id);

    void delete(Long id);
}
