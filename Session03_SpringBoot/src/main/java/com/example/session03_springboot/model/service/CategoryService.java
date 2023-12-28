package com.example.session03_springboot.model.service;

import com.example.session03_springboot.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category saveOrUpdate(Category category);
    Category findById(Long id);
    void delete(Long id);
    void changeStatus(Long id);
}
