package com.lapzone.lapzoneweb.controller;

import com.lapzone.lapzoneweb.model.entity.User;
import com.lapzone.lapzoneweb.model.service.AuthService;
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
    private AuthService authService;


    @GetMapping("/dangnhap")
    public String loginPage() {
        return "dangnhap";
    }
    @GetMapping("/dangki")
    public String registerPage() {
        return "dangki";
    }
    @PostMapping("/dangnhap")
    public String handleLogin(@RequestParam String emailOrPhone, 
                              @RequestParam String password, 
                              HttpSession session, Model model) {
        
        User user = authService.authenticate(emailOrPhone, password);

        if (user != null) {
            session.setAttribute("currentUser", user);
            return "ADMIN".equals(user.getRole()) ? "redirect:/admin/dashboard" : "redirect:/";
        }
        
        model.addAttribute("error", "Sai tài khoản hoặc mật khẩu!");
        return "dangnhap";
    }
    @PostMapping("/dangki")
    public String handleRegister(@RequestParam String fullName, 
                                 @RequestParam String email,
                                 @RequestParam String phone,
                                 @RequestParam String address,
                                 @RequestParam String password,
                                 Model model) {
        
        User newUser = new User();
        newUser.setFullName(fullName);
        newUser.setEmail(email);
        newUser.setPhone(phone);
        newUser.setAddress(address);
        newUser.setPassword(password);

        if (authService.register(newUser)) {
            model.addAttribute("success", "Đăng ký thành công! Hãy đăng nhập.");
            return "dangnhap";
        }
        
        model.addAttribute("error", "Email hoặc Số điện thoại đã tồn tại!");
        return "dangki";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "redirect:/"; 
    }
}
