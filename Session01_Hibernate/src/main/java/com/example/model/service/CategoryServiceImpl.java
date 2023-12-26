package com.example.model.service;

import com.example.model.dao.CategoryDAO;
import com.example.model.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public Boolean save(Category category) {
        return categoryDAO.save(category);
    }

    @Override
    public Boolean update(Category category) {
        return categoryDAO.update(category);
    }


    @Override
    public void delete(Integer id) {
        categoryDAO.delete(id);
    }

    @Override
    public Category findById(Integer id) {
        return categoryDAO.findById(id);
    }

    @Override
    public void changeStatus(Integer id) {
        categoryDAO.changeStatus(id);
    }
}
