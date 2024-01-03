package com.example.service.product;

import com.example.model.dto.ProductDTO;
import com.example.model.entity.Category;
import com.example.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Page<Product> findAll(Pageable pageable);
    Product saveOrUpdate(ProductDTO product);
    Product findById(Long id);
    void delete(Long id);
    Page<ProductDTO> searchByName(Pageable pageable, String name);
}
