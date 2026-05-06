package com.lapzone.lapzoneweb.model.entity;

public class CartItem {
    private Long id;
    private Integer quantity;
    private Long userId;
    private Long productId;

    public CartItem() {}

    public CartItem(Long id, Integer quantity, Long userId, Long productId) {
        this.id = id;
        this.quantity = quantity;
        this.userId = userId;
        this.productId = productId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
}