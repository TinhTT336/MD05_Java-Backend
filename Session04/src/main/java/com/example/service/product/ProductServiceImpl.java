package com.example.service.product;

import com.example.model.dto.ProductDTO;
import com.example.model.entity.Category;
import com.example.model.entity.Product;
import com.example.repository.CategoryRepository;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product saveOrUpdate(ProductDTO productDTO) {
        //cast doi tuong ProductDTO sang entity Product
        Product product=new Product();
        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        Category category=categoryRepository.findById(productDTO.getCategoryId()).orElse(null);
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<ProductDTO> searchByName(Pageable pageable, String name) {
        Page<Product> productPage=productRepository.findAllByProductNameContainingIgnoreCase(pageable,name);
        return productPage.map(product -> new ProductDTO(product));
    }
}
