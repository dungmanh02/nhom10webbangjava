package com.lapzone.lapzoneweb.controller;

import com.lapzone.lapzoneweb.model.entity.CartItem;
import com.lapzone.lapzoneweb.model.entity.User;
import com.lapzone.lapzoneweb.model.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class CartBadgeAdvice {

    @Autowired
    private CartService cartService;

    /**
     * Tự động cung cấp biến "cartItemCount" cho tất cả các file Thymeleaf (HTML)
     * Giúp icon giỏ hàng trên Header luôn hiển thị số lượng mới nhất
     */
    @ModelAttribute("cartItemCount")
    public int getCartItemCount(HttpSession session) {
        // Lấy thông tin user từ session (đã lưu lúc đăng nhập)
        User currentUser = (User) session.getAttribute("currentUser");
        
        if (currentUser != null) {
            // Nếu khách đã đăng nhập, gọi Service lấy danh sách đồ trong giỏ
            List<CartItem> items = cartService.getCartItems(currentUser.getId());
            
            // Tính tổng số lượng (Ví dụ: mua 2 chuột + 1 laptop = 3 món)
            int totalCount = 0;
            for (CartItem item : items) {
                totalCount += item.getQuantity();
            }
            return totalCount;
        }
        
        // Nếu chưa đăng nhập thì mặc định là 0
        return 0;
    }
}