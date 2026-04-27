package com.lapzone.lapzoneweb.model.entity;

import java.util.Date;

public class Order {
    private Long id;
    private Double totalAmount;
    private String status;
    private Date orderDate;
    private String customerName;
    private String customerPhone;
    private String address;
    private Long userId;

    public Order() {}

    public Order(Long id, Double totalAmount, String status, Date orderDate, String customerName, String customerPhone, String address, Long userId) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.status = status;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.address = address;
        this.userId = userId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}