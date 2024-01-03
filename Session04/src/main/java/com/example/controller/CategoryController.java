package com.example.controller;

import com.example.model.entity.Category;
import com.example.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/api/v1/categories/search")
    public ResponseEntity<Page<Category>> search(@RequestParam(name = "keyword",required = false) String keyword,
                                                 @RequestParam(defaultValue = "5", name = "limit") int limit,
                                                 @RequestParam(defaultValue = "0", name = "page") int page,
                                                 @RequestParam(defaultValue = "id", name = "sort") String sort,
                                                 @RequestParam(defaultValue = "asc", name = "order") String order) {
        Pageable pageable;
        Page<Category> categoryPage;
        if (keyword != null) {
            if (order.equals("desc")) {
                pageable = PageRequest.of(page, limit, Sort.by(sort).descending());
            } else {
                pageable = PageRequest.of(page, limit, Sort.by(sort).ascending());
            }
            categoryPage = categoryService.searchByName(pageable, keyword);
            return new ResponseEntity<>(categoryPage, HttpStatus.OK);
        }
        pageable = PageRequest.of(page, limit);
        categoryPage = categoryService.getAllPagination(pageable);
        return new ResponseEntity<>(categoryPage, HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<Map<String, Object>> categories(@RequestParam(defaultValue = "5", name = "limit") int limit,
                                                          @RequestParam(defaultValue = "0", name = "page") int page) {

        Pageable pageable = PageRequest.of(page, limit);
        Page<Category> categoryPage = categoryService.getPagination(pageable);
        Map<String, Object> data = new HashMap<>();
        data.put("categories", categoryPage.getContent());
        data.put("totalPage", categoryPage.getTotalPages());
        data.put("totalElement", categoryPage.getTotalElements());
        data.put("total", categoryPage.getSize());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/api/v1/categories")
    public ResponseEntity<?> pagination(@RequestParam(defaultValue = "5", name = "limit") int limit,
                                        @RequestParam(defaultValue = "0", name = "page") int page,
                                        @RequestParam(defaultValue = "id", name = "sort") String sort,
                                        @RequestParam(defaultValue = "asc", name = "order") String order) {
        Pageable pageable;
        if (order.equals("desc")) {
            pageable = PageRequest.of(page, limit, Sort.by(sort).descending());
        } else {
            pageable = PageRequest.of(page, limit, Sort.by(sort).ascending());
        }
        Page<Category> categoryPage = categoryService.getAllPagination(pageable);
        return new ResponseEntity<>(categoryPage, HttpStatus.OK);
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
            return new ResponseEntity<>("Delete successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }
}
