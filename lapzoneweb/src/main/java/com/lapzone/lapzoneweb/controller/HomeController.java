package com.lapzone.lapzoneweb.controller;
import com.lapzone.lapzoneweb.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.lapzone.lapzoneweb.model.repository.CategoryRepository;



@Controller
public class HomeController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductService productService;
@GetMapping("/")
public String showHomePage(Model model) {
    
    // 1. Gửi danh sách 4 sản phẩm mới nhất làm Tham Khảo
    model.addAttribute("referenceProducts", productService.getNewProducts());
    
    // 2. Chỗ này KHÔNG dùng getAllProducts() nữa.
    // Lấy đại diện vài hãng phổ thông (Ví dụ: Lenovo, Dell, Asus) ném vào danh sách "Máy Tính Xách Tay"
    // Ghi chú: Sau này nếu muốn hiển thị tất cả, bạn có thể tạo 1 list tổng hợp từ các hãng.
    model.addAttribute("laptopProducts", productService.getProductsByBrand("Lenovo")); 
    
    // 3. Gửi danh sách Macbook
    model.addAttribute("appleProducts", productService.getProductsByBrand("Apple"));
    
    // 4. Gửi danh sách 8 Hàng mới cập nhật
    model.addAttribute("newProducts", productService.getNewProducts());

    return "index";
 }
}
 