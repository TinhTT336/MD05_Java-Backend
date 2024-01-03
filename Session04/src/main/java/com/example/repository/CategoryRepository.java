package com.example.repository;

import com.example.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
Page<Category> findAllByCategoryNameContainingIgnoreCase(Pageable pageable,String name);
}