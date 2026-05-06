package com.lapzone.lapzoneweb.model.dto;

import jakarta.validation.constraints.*;

public class UserUpdateDTO {
    // User ID (cần để biết update user nào)
    @NotNull(message = "ID không được null")
    private Long id;

    // Họ tên: bắt buộc, dài 2-100 ký tự
    @NotBlank(message = "Tên không được để trống")
    @Size(min = 2, max = 100, message = "Tên phải từ 2 đến 100 ký tự")
    private String fullName;

    // Số điện thoại: 10 chữ số
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^[0-9]{10}$", message = "Số điện thoại phải gồm 10 chữ số")
    private String phone;

    // Địa chỉ: không bắt buộc, tối đa 500 ký tự
    @Size(max = 500, message = "Địa chỉ không được quá 500 ký tự")
    private String address;

    // Constructor rỗng (bắt buộc cho Thymeleaf @ModelAttribute)
    public UserUpdateDTO() {}

    // Constructor đủ tham số
    public UserUpdateDTO(Long id, String fullName, String phone, String address) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
    }

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

}
