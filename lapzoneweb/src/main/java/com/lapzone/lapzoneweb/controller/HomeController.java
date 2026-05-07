package com.lapzone.lapzoneweb.controller;
import com.lapzone.lapzoneweb.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lapzone.lapzoneweb.model.entity.Product;
import com.lapzone.lapzoneweb.model.repository.CategoryRepository;
import java.util.List;


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
    @GetMapping("/product_detail")
    public String showProductDetail(@RequestParam("id") Long id, Model model) {
        
        // Gọi Service lấy đúng 1 sản phẩm theo ID (Tí nữa mình hướng dẫn viết hàm này trong Service)
        Product product = productService.getProductById(id);
        
        if (product != null) {
            // Ném sản phẩm đó qua cho HTML
            model.addAttribute("product", product);
            return "product_detail"; // Trả về trang product_detail.html
        } else {
            return "redirect:/"; // Nếu tìm không thấy ID thì đá về trang chủ
        }
    }
@GetMapping("/search")

    public String searchPage(
            @RequestParam(value = "query", required = false, defaultValue = "") String query,
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice,
            @RequestParam(value = "cpu", required = false) String cpu,     // THÊM ĐÓN CPU
            @RequestParam(value = "gpu", required = false) String gpu,     // THÊM ĐÓN GPU
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