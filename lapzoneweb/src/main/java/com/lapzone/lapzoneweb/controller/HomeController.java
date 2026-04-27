package com.lapzone.lapzoneweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Khi người dùng gõ localhost:8080 hoặc địa chỉ trang web
    @GetMapping("/")
    public String showHomePage() {
    // Trả về tên file "index" (Spring sẽ tự tìm index.html trong thư mục templates)
        return "index"; 
    }
}