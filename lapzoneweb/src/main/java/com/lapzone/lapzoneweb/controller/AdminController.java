package com.lapzone.lapzoneweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin") // Mọi đường dẫn trong class này đều bắt đầu bằng /admin
public class AdminController {

    @GetMapping("/dashboard")
    public String showDashboard() {
        // Spring Boot sẽ tìm file: src/main/resources/templates/admin/dashboard.html
        return "admin/dashboard"; 
    }
    
    // Sau này bạn có thể thêm Quản lý sản phẩm, đơn hàng ở đây
    // @GetMapping("/products") ...
}