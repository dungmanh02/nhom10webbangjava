package com.lapzone.lapzoneweb.controller;

import com.lapzone.lapzoneweb.model.dto.UserUpdateDTO;
import com.lapzone.lapzoneweb.model.entity.User;
import com.lapzone.lapzoneweb.model.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // ==========================================
    // 1. HIỂN THỊ TRANG THÔNG TIN CÁ NHÂN
    // ==========================================
    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        User sessionUser = (User) session.getAttribute("currentUser");

        if (sessionUser == null) {
            return "redirect:/dangnhap";
        }

        User currentUser = userService.findById(sessionUser.getId());

        // Tạo DTO từ dữ liệu DB rồi đưa vào form (dùng tên "userForm" để khớp th:object)
        UserUpdateDTO dto = new UserUpdateDTO(
            currentUser.getId(),
            currentUser.getFullName(),
            currentUser.getPhone(),
            currentUser.getAddress()
        );
        model.addAttribute("userForm", dto);
        model.addAttribute("user", currentUser); // dùng để hiển thị email (readonly)

        return "profile";
    }

    // ==========================================
    // 2. XỬ LÝ CẬP NHẬT THÔNG TIN CÁ NHÂN
    // ==========================================
    @PostMapping("/profile/update")
    public String handleUpdateProfile(@Valid @ModelAttribute("userForm") UserUpdateDTO dto,
                                      BindingResult result,
                                      HttpSession session,
                                      Model model) {

        User sessionUser = (User) session.getAttribute("currentUser");
        if (sessionUser == null) {
            return "redirect:/dangnhap";
        }

        // Ép id luôn bằng user trong session, không tin id từ form (tránh IDOR)
        dto.setId(sessionUser.getId());

        if (result.hasErrors()) {
            User currentUser = userService.findById(sessionUser.getId());
            model.addAttribute("user", currentUser);
            return "profile";
        }

        User updatedUser = userService.updateProfileFromDTO(dto);

        if (updatedUser != null) {
            session.setAttribute("currentUser", updatedUser);
            model.addAttribute("success", "Cập nhật thông tin thành công!");
            model.addAttribute("user", updatedUser);

            UserUpdateDTO updatedDto = new UserUpdateDTO(
                updatedUser.getId(),
                updatedUser.getFullName(),
                updatedUser.getPhone(),
                updatedUser.getAddress()
            );
            model.addAttribute("userForm", updatedDto);
        } else {
            model.addAttribute("error", "Có lỗi xảy ra khi cập nhật!");
            User currentUser = userService.findById(sessionUser.getId());
            model.addAttribute("user", currentUser);
            model.addAttribute("userForm", dto);
        }

        return "profile";
    }
}