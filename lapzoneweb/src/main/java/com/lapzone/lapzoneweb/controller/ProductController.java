package com.lapzone.lapzoneweb.controller;

import com.lapzone.lapzoneweb.model.entity.Product;
import com.lapzone.lapzoneweb.model.repository.CategoryRepository;
import com.lapzone.lapzoneweb.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;

    // 1. Chức năng Xem chi tiết sản phẩm
    @GetMapping("/product_detail")
    public String showProductDetail(@RequestParam("id") Long id, Model model) {
        
        // Gọi Service lấy đúng 1 sản phẩm theo ID
        Product product = productService.getProductById(id);
        
        if (product != null) {
            // Ném sản phẩm đó qua cho HTML
            model.addAttribute("product", product);
            return "product_detail"; // Trả về trang product_detail.html
        } else {
            return "redirect:/"; // Nếu tìm không thấy ID thì đá về trang chủ
        }
    }

    // 2. Chức năng Tìm kiếm & Lọc siêu cấp
    @GetMapping("/search")
    public String searchPage(
            @RequestParam(value = "query", required = false, defaultValue = "") String query,
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice,
            @RequestParam(value = "cpu", required = false) String cpu,      // THÊM ĐÓN CPU
            @RequestParam(value = "gpu", required = false) String gpu,      // THÊM ĐÓN GPU
            Model model) {
        
        // 1. Gọi Service lấy sản phẩm (Truyền đủ 6 món vào đây)
        List<Product> searchResults = productService.searchProducts(query, categoryId, minPrice, maxPrice, cpu, gpu);
        
        // 2. Ném dữ liệu sang giao diện
        model.addAttribute("products", searchResults);
        model.addAttribute("keyword", query);
        
        // 3. Ném danh sách Category sang để vẽ cái Menu Lọc (Filter)
        model.addAttribute("categories", categoryRepository.findAll());
        
        // 4. Giữ lại các giá trị lọc cũ để hiển thị trên UI không bị mất
        model.addAttribute("selectedCategoryId", categoryId);
        model.addAttribute("selectedMinPrice", minPrice);
        model.addAttribute("selectedMaxPrice", maxPrice);
        model.addAttribute("selectedCpu", cpu); // NÉM CPU SANG HTML
        model.addAttribute("selectedGpu", gpu); // NÉM GPU SANG HTML

        return "search_results"; 
    }
}