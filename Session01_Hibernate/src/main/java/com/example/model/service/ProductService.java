package com.example.model.service;

import com.example.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Boolean save(Product product);
    Boolean update(Product product);
    void delete(Integer id);
    Product findById(Integer id);
    void changeStatus(Integer id);
}
