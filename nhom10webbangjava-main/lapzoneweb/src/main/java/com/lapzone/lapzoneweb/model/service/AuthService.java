package com.lapzone.lapzoneweb.model.service;

import com.lapzone.lapzoneweb.model.entity.User;
import com.lapzone.lapzoneweb.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    // Logic xác thực đăng nhập
    public User authenticate(String emailOrPhone, String password) {
        // Tìm kiếm trong database qua Repository
        User user = userRepository.findByEmailOrPhone(emailOrPhone, emailOrPhone);
        
        // Kiểm tra mật khẩu (Sử dụng equals cho đơn giản theo yêu cầu của nhóm)
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    // Logic đăng ký người dùng mới
    public boolean register(User newUser) {
        // Kiểm tra trùng lặp email hoặc số điện thoại
        if (userRepository.existsByEmail(newUser.getEmail()) || 
            userRepository.existsByPhone(newUser.getPhone())) {
            return false;
        }
        
        // Thiết lập các giá trị mặc định cho khách hàng mới
        newUser.setRole("USER"); 
        userRepository.save(newUser);
        return true;
    }
}