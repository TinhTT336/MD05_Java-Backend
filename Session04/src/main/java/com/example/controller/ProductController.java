package com.example.controller;

import com.example.model.dto.ProductDTO;
import com.example.model.entity.Category;
import com.example.model.entity.Product;
import com.example.service.category.CategoryService;
import com.example.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")

public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/products")
    public ResponseEntity<Page<ProductDTO>> findAll(@RequestParam(name = "limit", defaultValue = "3") Integer limit,
                                                    @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                    @RequestParam(name = "sort", defaultValue = "productId") String sort,
                                                    @RequestParam(name = "order", defaultValue = "asc") String order,
                                                    @RequestParam(name = "keyword", required = false) String keyword) {

        Sort.Direction direction = order.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(direction, sort));

        if (keyword != null) {
            Page<ProductDTO> list = productService.searchByName(pageable, keyword);
            return new ResponseEntity<>(list,HttpStatus.OK);
        } else {
            Page<Product>list = productService.findAll(pageable);
        return new ResponseEntity<>(list.map(ProductDTO::new), HttpStatus.OK);
        }

    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> create(@RequestBody ProductDTO productDTO) {

        Product pro = productService.saveOrUpdate(productDTO);
        return new ResponseEntity<>(pro, HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        Product productEdit = productService.findById(id);
        if (productEdit == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productDTO.setProductId(productEdit.getProductId());
        return new ResponseEntity<>(productService.saveOrUpdate(productDTO), HttpStatus.OK);
    }
}
