package com.lapzone.lapzoneweb.model.dao;

import com.lapzone.lapzoneweb.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByPhone(String phone);
    User findByEmailOrPhone(String email, String phone);
}