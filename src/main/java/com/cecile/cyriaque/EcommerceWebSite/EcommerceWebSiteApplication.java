package com.cecile.cyriaque.EcommerceWebSite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class EcommerceWebSiteApplication {

	public static void main(String[] args) {

		SpringApplication.run(EcommerceWebSiteApplication.class, args);

	}



}
