package com.lapzone.lapzoneweb.model.service;

import com.lapzone.lapzoneweb.model.dto.UserUpdateDTO;
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
    public User updateProfileFromDTO(UserUpdateDTO dto) {
    User existingUser = userRepository.findById(dto.getId()).orElse(null);
    
    if (existingUser != null) {
        existingUser.setFullName(dto.getFullName());
        existingUser.setPhone(dto.getPhone());
        existingUser.setAddress(dto.getAddress());
        
        return userRepository.save(existingUser);
    }
    return null;
}
}