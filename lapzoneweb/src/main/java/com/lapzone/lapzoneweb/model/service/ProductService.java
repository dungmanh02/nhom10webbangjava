package com.lapzone.lapzoneweb.model.service;

import com.lapzone.lapzoneweb.model.entity.Product;
import com.lapzone.lapzoneweb.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
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

    

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

   public List<Product> searchProducts(String keyword, Long categoryId, Double minPrice, Double maxPrice, String cpu, String gpu) {
        String kw = (keyword != null) ? keyword : ""; 
        String cpuFilter = (cpu != null && !cpu.trim().isEmpty()) ? cpu : null;
        String gpuFilter = (gpu != null && !gpu.trim().isEmpty()) ? gpu : null;
        
        return productRepository.searchAndFilterProducts(kw, categoryId, minPrice, maxPrice, cpuFilter, gpuFilter);
    }
    @Transactional
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

   
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    public List<Product> getProductsByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
}