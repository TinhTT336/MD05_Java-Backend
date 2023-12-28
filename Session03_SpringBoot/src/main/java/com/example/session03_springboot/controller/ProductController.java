package com.example.session03_springboot.controller;

import com.example.session03_springboot.model.entity.Category;
import com.example.session03_springboot.model.entity.Product;
import com.example.session03_springboot.model.service.CategoryService;
import com.example.session03_springboot.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/product")
    public String product(Model model) {
        List<Product> productList = productService.findAll();
        model.addAttribute("productList", productList);
        return "product/index";
    }

    @GetMapping("/product-add")
    public String add(Model model) {
        Product product = new Product();
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("product", product);
        model.addAttribute("categoryList", categoryList);
        return "product/add";
    }

    @PostMapping("/product-add")
    public String addPost(@ModelAttribute("product") Product product) {
        if (productService.saveOrUpdate(product) != null) {
            return "redirect:/product";
        }
        return "product/add";
    }

    @GetMapping("/product-edit/{id}")
    public String edit(@PathVariable("id") Product product, Model model) {
        List<Category> categoryList = categoryService.findAll();

        model.addAttribute("product", product);
        model.addAttribute("categoryList", categoryList);
        return "product/edit";
    }
    @PostMapping("/product-edit")
    public String editPost(@ModelAttribute("product")Product product){
        if(productService.saveOrUpdate(product)!=null){
            return "redirect:/product";
        }
        return "product/edit/"+product.getId();
    }

}
