package com.lapzone.lapzoneweb.model.repository;

import com.lapzone.lapzoneweb.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Tìm kiếm để lấy ra thông tin
    User findByEmail(String email);
    User findByPhone(String phone);
    User findByEmailOrPhone(String email, String phone);

    // BỔ SUNG: Dùng để kiểm tra trùng lặp nhanh gọn (trả về true/false)
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}