package com.lapzone.lapzoneweb.model.service;

import com.lapzone.lapzoneweb.model.entity.User;
import com.lapzone.lapzoneweb.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 1. Lấy thông tin mới nhất từ Database (Đảm bảo dữ liệu luôn đúng)
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // 2. Logic cập nhật thông tin cá nhân
    public User updateProfile(User updatedUser) {
        // Tìm user cũ trong DB để so sánh
        User existingUser = userRepository.findById(updatedUser.getId()).orElse(null);
        
        if (existingUser != null) {
            // Chỉ cập nhật các trường được phép sửa
            existingUser.setFullName(updatedUser.getFullName());
            existingUser.setPhone(updatedUser.getPhone());
            existingUser.setAddress(updatedUser.getAddress());
            
            // Giữ nguyên Email và Role (Vì thường không cho phép khách tự đổi Email/Quyền)
            // existingUser.setEmail(existingUser.getEmail());
            // existingUser.setRole(existingUser.getRole());

            // Lưu lại thông tin đã thay đổi
            return userRepository.save(existingUser);
        }
        return null;
    }
}