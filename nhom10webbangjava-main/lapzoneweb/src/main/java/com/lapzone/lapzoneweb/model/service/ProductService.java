package com.lapzone.lapzoneweb.model.service;

import com.lapzone.lapzoneweb.model.entity.Product;
import com.lapzone.lapzoneweb.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getNewProducts() {
        return productRepository.findTop8ByOrderByIdDesc();
    }

    public List<Product> getReferenceProducts() {
        return productRepository.findTop4ByOrderByIdDesc();
    }

    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByCategory_Name(brand);
    }

    
    // Tìm 1 sản phẩm theo ID (trả về null nếu không tìm thấy)
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // Thêm hàm này vào dưới cùng của ProductService
    public List<Product> searchProducts(String keyword, Long categoryId, Double minPrice, Double maxPrice) {
        // Nếu keyword null thì gán bằng chuỗi rỗng để tìm tất cả
        String kw = (keyword != null) ? keyword : ""; 
        return productRepository.searchAndFilterProducts(kw, categoryId, minPrice, maxPrice);
    }
}