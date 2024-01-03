package com.example.model.dto;

import com.example.model.entity.Category;
import com.example.model.entity.Product;
import jakarta.persistence.*;
public class ProductDTO {
    private Long productId;



    private String productName;
    private float price;
    private Long categoryId;

    public ProductDTO() {
    }

    public ProductDTO(Long productId, String productName, float price, Long categoryId) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.categoryId = categoryId;
    }
    public ProductDTO(Product product) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.categoryId = product.getCategory().getId();
    }


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
