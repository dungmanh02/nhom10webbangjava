package com.lapzone.lapzoneweb.model.entity;

// BẮT BUỘC PHẢI CÓ CÁC IMPORT NÀY
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // Đánh dấu đây là một Entity (Thực thể) kết nối với Database
@Table(name = "users") // Chỉ định nó sẽ nối với bảng tên là "users" trong DB
public class User {
    
    @Id // Đánh dấu đây là Khóa chính (Primary Key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tăng (AUTO_INCREMENT)
    private Long id;

    @Column(name = "full_name") // Nối với cột full_name trong DB
    private String fullName;

    @Column(unique = true) // Cột email không được trùng nhau
    private String email;

    @Column
    private String phone;

    @Column
    private String password;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column
    private String role;

    // --- Giữ nguyên các hàm khởi tạo và Getter/Setter của bạn ---

    public User() {}

    public User(Long id, String fullName, String email, String phone, String password, String address, String role) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.address = address;
        this.role = role;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}