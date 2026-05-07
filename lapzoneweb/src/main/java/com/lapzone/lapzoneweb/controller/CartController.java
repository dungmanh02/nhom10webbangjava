package com.lapzone.lapzoneweb.controller;

import com.lapzone.lapzoneweb.model.entity.CartItem;
import com.lapzone.lapzoneweb.model.entity.User;
import com.lapzone.lapzoneweb.model.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    // 1. Xem giỏ hàng
    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        // Lấy User từ session (giống như bạn đang làm ở Header)
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser == null) {
            return "redirect:/dangnhap"; // Chưa đăng nhập thì đá sang trang login
        }

        List<CartItem> cartItems = cartService.getCartItems(currentUser.getId());
        Double total = cartService.getCartTotal(currentUser.getId());

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);

        return "cart"; // Trả về file cart.html
    }

    // 2. Thêm sản phẩm vào giỏ
    @PostMapping("/cart/add")
    public String addToCart(@RequestParam("productId") Long productId,
                            @RequestParam(value = "quantity", defaultValue = "1") Integer quantity,
                            HttpSession session) {
        
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/dangnhap";
        }

        cartService.addToCart(currentUser.getId(), productId, quantity);
        return "redirect:/cart"; // Thêm xong thì chuyển thẳng đến trang giỏ hàng luôn
    }

    // 3. Cập nhật số lượng (Nhấn nút + hoặc -)
    @PostMapping("/cart/update")
    public String updateQuantity(@RequestParam("cartItemId") Long cartItemId,
                                 @RequestParam("quantity") Integer quantity) {
        cartService.updateQuantity(cartItemId, quantity);
        return "redirect:/cart";
    }

    // 4. Xóa sản phẩm khỏi giỏ
    @PostMapping("/cart/remove")
    public String removeItem(@RequestParam("cartItemId") Long cartItemId) {
        cartService.removeFromCart(cartItemId);
        return "redirect:/cart";
    }
}