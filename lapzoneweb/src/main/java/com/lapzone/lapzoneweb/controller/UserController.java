package com.lapzone.lapzoneweb.controller;

import com.lapzone.lapzoneweb.model.entity.User;
import com.lapzone.lapzoneweb.model.dao.UserDAO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;

    // 1. TRANG THÔNG TIN CÁ NHÂN
    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        // Lấy đối tượng User từ Session (đã được lưu lúc đăng nhập ở AuthController)
        User currentUser = (User) session.getAttribute("currentUser");

        // Kiểm tra nếu chưa đăng nhập thì không cho xem profile, đá về trang đăng nhập
        if (currentUser == null) {
            return "redirect:/dangnhap";
        }

        // Gửi thông tin user sang file HTML để hiển thị
        model.addAttribute("user", currentUser);
        
        return "profile"; // Mở file templates/profile.html
    }

    // 2. SAU NÀY BẠN CÓ THỂ THÊM CÁC HÀM SỬA THÔNG TIN Ở ĐÂY
    // @PostMapping("/profile/update") ...
}