package com.lapzone.lapzoneweb.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lapzone.lapzoneweb.model.entity.CartItem;
import com.lapzone.lapzoneweb.model.entity.Product;
import com.lapzone.lapzoneweb.model.repository.CartItemRepository;
import com.lapzone.lapzoneweb.model.repository.ProductRepository;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    // 1. Lấy giỏ hàng ra xem
    public List<CartItem> getCartItems(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }

    // 2. Logic Thêm vào giỏ (Rất quan trọng)
    public void addToCart(Long userId, Long productId, Integer quantity) {
        // Tìm xem sản phẩm có tồn tại không
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) return;

        // Kiểm tra trong giỏ khách đã có máy này chưa
        CartItem existingItem = cartItemRepository.findByUserIdAndProduct(userId, product);

        if (existingItem != null) {
            // Đã có -> Cộng dồn số lượng
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            cartItemRepository.save(existingItem);
        } else {
            // Chưa có -> Bỏ món mới vào giỏ
            CartItem newItem = new CartItem();
            newItem.setUserId(userId);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            cartItemRepository.save(newItem);
        }
    }

    // 3. Cập nhật số lượng (Lúc ấn nút + - trong trang Giỏ hàng)
    public void updateQuantity(Long cartItemId, Integer quantity) {
        CartItem item = cartItemRepository.findById(cartItemId).orElse(null);
        if (item != null) {
            if (quantity <= 0) {
                cartItemRepository.delete(item); // Nếu lùi về 0 thì xoá luôn
            } else {
                item.setQuantity(quantity);
                cartItemRepository.save(item);
            }
        }
    }

    // 4. Bỏ món ra khỏi giỏ
    public void removeFromCart(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    // 5. Máy tính tiền (Cộng dồn: Giá x Số lượng)
    public Double getCartTotal(Long userId) {
        List<CartItem> cartItems = cartItemRepository.findByUserId(userId);
        double total = 0.0;
        for (CartItem item : cartItems) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }
}