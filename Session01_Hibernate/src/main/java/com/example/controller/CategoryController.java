package com.example.controller;

import com.example.model.entity.Category;
import com.example.model.service.CategoryService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("")
    public String index(Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList", categoryList);
        return "category/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "category/add";
    }

    @PostMapping("/add")
    public String addPost(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "category/edit";
    }

    @PostMapping("/edit")
    public String editPost(@ModelAttribute("category") Category category) {
        categoryService.update(category);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        categoryService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/change/{id}")
    public String change(@PathVariable("id") Integer id) {
        categoryService.changeStatus(id);
        return "redirect:/";
    }
}
