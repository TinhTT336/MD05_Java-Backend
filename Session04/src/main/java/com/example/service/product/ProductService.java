package com.example.service.product;

import com.example.model.dto.ProductDTO;
import com.example.model.entity.Category;
import com.example.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product saveOrUpdate(ProductDTO product);
    Product findById(Long id);
    void delete(Long id);
}
