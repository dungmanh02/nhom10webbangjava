package com.lapzone.lapzoneweb.controller;

import com.lapzone.lapzoneweb.model.dao.UserDAO;
import com.lapzone.lapzoneweb.model.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserDAO userDAO; // ĐÃ ĐỔI THÀNH UserDAO CHO KHỚP CẤU TRÚC CỦA BẠN

    // ==========================================
    // 1. HIỂN THỊ TRANG ĐĂNG KÝ VÀ ĐĂNG NHẬP
    // ==========================================
    
    @GetMapping("/dangki")
    public String showRegisterForm() {
        return "dangki";
    }

    @GetMapping("/dangnhap")
    public String showLoginForm() {
        return "dangnhap";
    }

    // ==========================================
    // 2. XỬ LÝ LƯU TÀI KHOẢN (ĐĂNG KÝ)
    // ==========================================
    
    @PostMapping("/dangki")
    public String processRegister(
            @RequestParam("fullName") String fullName,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("address") String address,
            @RequestParam("password") String password,
            Model model) {
        
        // Bước 1: Kiểm tra Email
        if (userDAO.findByEmail(email) != null) {
            model.addAttribute("error", "Email này đã được sử dụng!");
            return "dangki"; 
        }

        // Bước 2: Kiểm tra Số điện thoại
        if (userDAO.findByPhone(phone) != null) {
            model.addAttribute("error", "Số điện thoại này đã được sử dụng!");
            return "dangki";
        }

        // Bước 3: Tạo User mới
        User newUser = new User();
        newUser.setFullName(fullName);
        newUser.setEmail(email);
        newUser.setPhone(phone);
        newUser.setAddress(address);
        newUser.setPassword(password);
        newUser.setRole("USER"); 
        
        // Bước 4: Lưu xuống Database
        userDAO.save(newUser); 

        // Bước 5: Chuyển sang trang Đăng nhập
        model.addAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
        return "dangnhap";
    }

    // ==========================================
    // 3. XỬ LÝ ĐĂNG NHẬP
    // ==========================================
    
    @PostMapping("/dangnhap")
public String processLogin(
        @RequestParam("emailOrPhone") String emailOrPhone,
        @RequestParam("password") String password,
        HttpSession session, 
        Model model) {
    
    // 1. Tìm user bằng Email HOẶC Phone (Sử dụng hàm đã viết trong UserDAO)
    User user = userDAO.findByEmailOrPhone(emailOrPhone, emailOrPhone);

    // 2. Kiểm tra mật khẩu (So sánh trực tiếp vì chưa mã hóa)
    if (user != null && user.getPassword().equals(password)) {
        
        // Đăng nhập đúng -> Lưu vào Session để dùng ở Header (hiển thị tên, nút logout)
        session.setAttribute("currentUser", user);
        
        // 3. PHÂN LUỒNG TẠI ĐÂY
        if ("ADMIN".equals(user.getRole())) {
            System.out.println("--- ADMIN LOGGED IN ---");
            return "redirect:/admin/dashboard"; // Chuyển hướng đến trang quản trị
        } else {
            System.out.println("--- USER LOGGED IN ---");
            return "redirect:/"; // Chuyển hướng về trang chủ
        }
        
    } else {
        // Sai tài khoản hoặc mật khẩu
        model.addAttribute("error", "Tài khoản hoặc mật khẩu không chính xác!");
        return "dangnhap";
    }
}

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("currentUser"); 
        return "redirect:/"; 
    }
}