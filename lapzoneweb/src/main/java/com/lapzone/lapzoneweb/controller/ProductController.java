package com.lapzone.lapzoneweb.controller;

import com.lapzone.lapzoneweb.model.entity.Product;
import com.lapzone.lapzoneweb.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product_detail")
    public String showProductDetail(@RequestParam("id") Long id, Model model) {
        Product product = productService.getProductById(id);

        if (product != null) {
            model.addAttribute("product", product);
            return "product_detail";
        }
        return "redirect:/";
    }
}
