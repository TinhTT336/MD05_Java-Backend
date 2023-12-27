package com.example.model.dao;

import com.example.model.entity.Category;
import com.example.model.entity.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> findAll();
    Boolean save(Product product);
    Boolean update(Product product);
    void delete(Integer id);
    Product findById(Integer id);
    void changeStatus(Integer id);
    List<Product> findByCategoryId(Integer categoryId);

}
