package com.cecile.cyriaque.EcommerceWebSite.controllers;

import com.cecile.cyriaque.EcommerceWebSite.service.CategoryService;
import com.cecile.cyriaque.EcommerceWebSite.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/catalog")
public class CatalogController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @GetMapping
    public String catalog(@RequestParam(required = false) Long id,Model model){
        model.addAttribute("categorys", categoryService.getAllCategory());
        if(id != null){
            model.addAttribute("products", productService.getAllProductsByCategory(categoryService.getCategoryById(id)));
        }else{
            model.addAttribute("products", productService.getAllProductsActive());
        }

        return "catalog/index";
    }

}
