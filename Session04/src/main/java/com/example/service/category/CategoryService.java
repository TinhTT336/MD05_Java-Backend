package com.example.service.category;

import com.example.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category saveOrUpdate(Category category);
    Category findById(Long id);
    void delete(Long id);
    Page<Category> getPagination(Pageable pageable);
    Page<Category> getAllPagination(Pageable pageable);
    Page<Category>searchByName(Pageable pageable,String name);
}
