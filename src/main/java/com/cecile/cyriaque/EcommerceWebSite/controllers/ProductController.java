package com.cecile.cyriaque.EcommerceWebSite.controllers;

import com.cecile.cyriaque.EcommerceWebSite.dto.AddProduct;
import com.cecile.cyriaque.EcommerceWebSite.repositories.CategoryRepository;
import com.cecile.cyriaque.EcommerceWebSite.repositories.UserRepository;
import com.cecile.cyriaque.EcommerceWebSite.service.CategoryService;
import com.cecile.cyriaque.EcommerceWebSite.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryService categoryService;
    @Autowired
    private MessageSource messageSource;

    private static final String REDIRECT_PRODUCT= "redirect:/admin/product";
    @GetMapping("/product")
    public String product(Model model, Authentication authentication){
        UserDetails user = (UserDetails) authentication.getPrincipal();

        model.addAttribute("products", productService.getAllProductsByUserId(userRepository.findByEmail(user.getUsername())));

        return "account/product";
    }
    @GetMapping("/addProduct")
    public String addCategory(final Model model){
        model.addAttribute("addProduct", new AddProduct());
        model.addAttribute("categorys", categoryService.getAllCategory());
        return "account/addProduct";
    }

    @PostMapping("/addProduct")
    public String productRegistration(final @Valid AddProduct addProduct, final BindingResult bindingResult, final Model model,Authentication authentication) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("productRegistration", addProduct);
            return "account/addProduct";
        }
        UserDetails user = (UserDetails) authentication.getPrincipal();
        productService.addProduct(addProduct,userRepository.findByEmail(user.getUsername()));

        model.addAttribute("addProductMsg", messageSource.getMessage("add.product.msg", null, LocaleContextHolder.getLocale()));
        return "account/addProduct";
    }
    @GetMapping("/admin/product")
    public String adminProduct(final Model model){
        model.addAttribute("products", productService.getAllProducts());
        return "account/admin/product";
    }
    @GetMapping("/admin/suppProduct")
    @Transactional
    public String suppAdminProduct(@RequestParam(required = true) Long id){
        productService.suppProduct(id);
        return REDIRECT_PRODUCT;
    }
    @GetMapping("/suppProduct")
    @Transactional
    public String suppProduct(@RequestParam(required = true) Long id){
        productService.suppProduct(id);
        return "redirect:/product";
    }
    @GetMapping("/admin/activeProduct")
    @Transactional
    public String activeProduct(@RequestParam(required = true) Long id){
        productService.activeProduct(id);
        return REDIRECT_PRODUCT;
    }
    @GetMapping("/achat")
    @Transactional
    public String achat(final Model model, Authentication authentication){
        UserDetails user = (UserDetails) authentication.getPrincipal();
        model.addAttribute("products", productService.getAllProductsByBuyerId(userRepository.findByEmail(user.getUsername())));

        return "account/achat";
    }
    @GetMapping("/getProduct")
    @Transactional
    public String getProduct(@RequestParam(required = true) Long id,Authentication authentication){
        UserDetails user = (UserDetails) authentication.getPrincipal();
        productService.achat(id,userRepository.findByEmail(user.getUsername()));
        return "redirect:/achat";
    }
}
