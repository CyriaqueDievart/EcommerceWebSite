package com.cecile.cyriaque.EcommerceWebSite.controllers;

import com.cecile.cyriaque.EcommerceWebSite.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    @GetMapping
    public String catalog(Model model){
        return "catalog/index";
    }

}
