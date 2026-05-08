package com.lapzone.lapzoneweb.controller;

import com.lapzone.lapzoneweb.model.entity.Product;
import com.lapzone.lapzoneweb.model.entity.ProductDetail;
import com.lapzone.lapzoneweb.model.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model, HttpSession session) {

        if (session.getAttribute("currentUser") == null) {
            return "redirect:/login";
        }

        model.addAttribute("countProducts", adminService.countProducts());
        model.addAttribute("countUsers", adminService.countUsers());
        model.addAttribute("countOrders", 0); 
        model.addAttribute("totalRevenue", 0.0);

        return "admin/dashboard";
    }

    
   @GetMapping("/products")
    public String listProducts(Model model, HttpSession session) {
       
        if (session.getAttribute("currentUser") == null) {
            return "redirect:/login";
        }

        model.addAttribute("products", adminService.getAllProducts());
        model.addAttribute("categories", adminService.getAllCategories());
        
        Product product = new Product();
        product.setProductDetail(new ProductDetail()); 
        model.addAttribute("product", product);
        
        return "admin/products";
    }


    @PostMapping("/products/save")
    public String saveProduct(@ModelAttribute("product") Product product,
                              @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
                              @RequestParam(value = "subImages", required = false) MultipartFile[] subImages) {
        
   
        if (product.getProductDetail() != null) {
            product.getProductDetail().setProduct(product);
        }
        if (product.getStock() == null) {
        product.setStock(1); 
    }

        adminService.saveProductWithImages(product, imageFile, subImages);

        return "redirect:/admin/products";
    }

  
    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        Product existingProduct = adminService.getProductById(id);
        

        if (existingProduct.getProductDetail() == null) {
            existingProduct.setProductDetail(new ProductDetail());
        }

        model.addAttribute("product", existingProduct);
        model.addAttribute("products", adminService.getAllProducts()); 
        model.addAttribute("categories", adminService.getAllCategories());
        
        return "admin/products";
    }


    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        adminService.deleteProduct(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
     
        session.removeAttribute("currentUser");
        session.invalidate(); 
        return "redirect:/login"; 
    }
}