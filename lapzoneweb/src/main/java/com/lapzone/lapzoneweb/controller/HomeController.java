package com.lapzone.lapzoneweb.controller;
import com.lapzone.lapzoneweb.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.lapzone.lapzoneweb.model.repository.CategoryRepository;



@Controller
public class HomeController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductService productService;
@GetMapping("/")
public String showHomePage(Model model) {
    model.addAttribute("referenceProducts", productService.getNewProducts());
    model.addAttribute("laptopProducts", productService.getProductsByBrand("Lenovo")); 
    model.addAttribute("appleProducts", productService.getProductsByBrand("Apple"));
    model.addAttribute("newProducts", productService.getNewProducts());
    return "index";
 }
}
 