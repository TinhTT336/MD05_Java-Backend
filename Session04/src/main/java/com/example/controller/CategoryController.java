package com.example.controller;

import com.example.model.entity.Category;
import com.example.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> categories() {
        List<Category> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> create(@RequestBody Category category) {
        Category categoryNew = categoryService.saveOrUpdate(category);
        return new ResponseEntity<>(categoryNew, HttpStatus.CREATED);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        if (category != null) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
        return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> edit(@PathVariable Long id, @RequestBody Category category) {
        Category categoryEdit = categoryService.findById(id);
        if (categoryEdit == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        category.setId(categoryEdit.getId());
        return new ResponseEntity<>(categoryService.saveOrUpdate(category), HttpStatus.OK);
    }

    //cach 2:
//    @PutMapping("/categories/{id}")
//    public ResponseEntity<Category> edit(@PathVariable Long id, @RequestBody Category category){
//        Category categoryUpdate=categoryService.findById(id);
//        categoryUpdate.setCategoryName(category.getCategoryName());
//        categoryUpdate.setStatus(category.getStatus());
//
//        Category categoryEdit=categoryService.saveOrUpdate(category);
//        return new ResponseEntity<>(categoryEdit,HttpStatus.OK);
//    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (categoryService.findById(id) != null) {
            categoryService.delete(id);
            return new ResponseEntity<>("Delete successfully!",HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }
}
