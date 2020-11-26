package com.cecile.cyriaque.EcommerceWebSite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client-area")
public class ClientAreaController {

    @GetMapping
    public String clientArea(Model model){
        return "account/index";
    }

}
