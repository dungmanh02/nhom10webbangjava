package com.lapzone.lapzoneweb.model.service;

import com.lapzone.lapzoneweb.model.entity.*;
import com.lapzone.lapzoneweb.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;

  
    public long countProducts() { return productRepository.count(); }
    public long countUsers() { return userRepository.count(); }
    public List<Category> getAllCategories() { return categoryRepository.findAll(); }
    public List<Product> getAllProducts() { return productRepository.findAll(); }
    public Product getProductById(Long id) { return productRepository.findById(id).orElse(null); }


    @Transactional
    public void saveProductWithImages(Product product, MultipartFile imageFile, MultipartFile[] subImages) {
      
        if (imageFile != null && !imageFile.isEmpty()) {
            String mainName = imageFile.getOriginalFilename();
            saveFileToFolder(imageFile, mainName);
            product.setImage(mainName);
        }
        if (subImages != null && subImages.length > 0) {
            List<ProductImage> listSubImages = new ArrayList<>();
            int limit = Math.min(subImages.length, 4); 
            for (int i = 0; i < limit; i++) {
                MultipartFile file = subImages[i];
                if (!file.isEmpty()) {
                    String subName = file.getOriginalFilename();
                    saveFileToFolder(file, subName);
                    
                    ProductImage pi = new ProductImage();
                    pi.setImageUrl(subName);
                    pi.setProduct(product);
                    listSubImages.add(pi);
                }
            }
            if (!listSubImages.isEmpty()) {
                product.setImages(listSubImages);
            }
        }
        productRepository.save(product);
    }

    private void saveFileToFolder(MultipartFile file, String fileName) {
        try {
            Path path = Paths.get("src/main/resources/static/assets/");
            if (!Files.exists(path)) Files.createDirectories(path);
            try (InputStream is = file.getInputStream()) {
                Files.copy(is, path.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Transactional
    public void deleteProduct(Long id) { productRepository.deleteById(id); }
}