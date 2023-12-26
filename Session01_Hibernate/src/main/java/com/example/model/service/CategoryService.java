package com.example.model.service;

import com.example.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Boolean save(Category category);
    Boolean update(Category category);
    void delete(Integer id);
    Category findById(Integer id);
    void changeStatus(Integer id);
}
