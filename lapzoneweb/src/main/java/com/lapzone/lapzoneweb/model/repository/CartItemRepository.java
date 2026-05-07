package com.lapzone.lapzoneweb.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lapzone.lapzoneweb.model.entity.CartItem;
import com.lapzone.lapzoneweb.model.entity.Product;
import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    
    // 1. Lấy toàn bộ sản phẩm trong giỏ của 1 ông khách
    List<CartItem> findByUserId(Long userId);

    // 2. Tìm xem cái máy này khách đã cho vào giỏ từ trước chưa
    CartItem findByUserIdAndProduct(Long userId, Product product);
    
    // 3. Xoá sạch giỏ hàng (Sẽ dùng lúc thanh toán thành công)
    void deleteByUserId(Long userId);
}