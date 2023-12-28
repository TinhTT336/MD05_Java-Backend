package com.example.session03_springboot.controller;

import com.example.session03_springboot.model.entity.Category;
import com.example.session03_springboot.model.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public String category(Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        return "category/index";
    }

    @GetMapping("/category-add")
    public String add(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "category/add";
    }

    @PostMapping("/category-add")
    public String addPost(@ModelAttribute("category") Category category) {
        if (categoryService.saveOrUpdate(category) != null) {
            return "redirect:/category";
        }
        return "category/add";
    }

    @GetMapping("/category-edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "category/edit";
    }

    @PostMapping("/category-edit")
    public String editPost(@ModelAttribute("category") Category category) {
        if (categoryService.saveOrUpdate(category) != null) {
            return "redirect:/category";
        }
        return "category/edit/" + category.getId();
    }

    @GetMapping("/category-delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return "redirect:/category";
    }
    @GetMapping("/category-change/{id}")
    public String change(@PathVariable("id") Long id) {
        categoryService.changeStatus(id);
        return "redirect:/category";
    }
}
