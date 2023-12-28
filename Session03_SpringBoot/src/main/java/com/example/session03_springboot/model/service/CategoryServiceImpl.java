package com.example.session03_springboot.model.service;

import com.example.session03_springboot.model.entity.Category;
import com.example.session03_springboot.repository.CategoryRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category saveOrUpdate(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        return categoryOptional.orElse(null);
//        return categoryRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
//        categoryRepository.delete(findById(id));
    }

    @Override
    public void changeStatus(Long id) {
        categoryRepository.changeStatus(id);
    }
}
