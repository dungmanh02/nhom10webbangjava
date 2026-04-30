package com.lapzone.lapzoneweb.controller;

import com.lapzone.lapzoneweb.model.entity.User;
import com.lapzone.lapzoneweb.model.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // ==========================================
    // 1. HIỂN THỊ TRANG THÔNG TIN CÁ NHÂN
    // ==========================================
    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        // Lấy User từ Session
        User sessionUser = (User) session.getAttribute("currentUser");

        // Nếu chưa đăng nhập, đá về trang đăng nhập
        if (sessionUser == null) {
            return "redirect:/dangnhap";
        }

        // Lấy lại dữ liệu mới nhất từ Database thông qua Service
        User currentUser = userService.findById(sessionUser.getId());
        
        // Đưa dữ liệu sang HTML
        model.addAttribute("user", currentUser);
        
        return "profile"; 
    }

    // ==========================================
    // 2. XỬ LÝ CẬP NHẬT THÔNG TIN CÁ NHÂN
    // ==========================================
    @PostMapping("/profile/update")
    public String handleUpdateProfile(@ModelAttribute User userFromForm, 
                                      HttpSession session, 
                                      Model model) {
        
        // Gọi Service xử lý logic cập nhật
        User updatedUser = userService.updateProfile(userFromForm);

        if (updatedUser != null) {
            // Cập nhật lại Session để hiển thị tên mới trên Header ngay lập tức
            session.setAttribute("currentUser", updatedUser);
            model.addAttribute("success", "Cập nhật thông tin thành công!");
        } else {
            model.addAttribute("error", "Có lỗi xảy ra khi cập nhật!");
        }

        // Quay lại trang profile để xem kết quả
        model.addAttribute("user", updatedUser);
        return "profile";
    }
}