package com.lapzone.lapzoneweb.model.service;

import com.lapzone.lapzoneweb.model.entity.User;
import com.lapzone.lapzoneweb.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import
@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    public User authenticate(String emailOrPhone, String password) {
        User user = userRepository.findByEmailOrPhone(emailOrPhone, emailOrPhone);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    public boolean register(User newUser) {
        if (userRepository.existsByEmail(newUser.getEmail()) || 
            userRepository.existsByPhone(newUser.getPhone())) {
            return false;
        }
        newUser.setRole("USER"); 
        userRepository.save(newUser);
        return true;
    }
}