package com.cecile.cyriaque.EcommerceWebSite.controllers;

import com.cecile.cyriaque.EcommerceWebSite.dto.AddCategory;
import com.cecile.cyriaque.EcommerceWebSite.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
public class CategoryController {

    private static final String REDIRECT_CATEGORY= "redirect:/admin/category";

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/admin/category")
    public String category(Model model){
        model.addAttribute("categorys", categoryService.getAllCategory());
        return "account/admin/category";
    }

    @GetMapping("admin/addCategory")
    public String addCategory(final Model model){
        model.addAttribute("addCategory", new AddCategory());
        return "account/admin/addCategory";
    }

    @PostMapping("admin/addCategory")
    public String categoryRegistration(final @Valid AddCategory addCategory, final BindingResult bindingResult, final Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categoryRegistration", addCategory);
            return "account/admin/addCategory";
        }
        categoryService.addCategory(addCategory);

        model.addAttribute("addCategoryMsg", messageSource.getMessage("add.category.msg", null, LocaleContextHolder.getLocale()));
        return "account/admin/addCategory";
    }

    @GetMapping("admin/suppCategory")
    @Transactional
    public String suppCategory(@RequestParam(required = true) Long id,final Model model){
        model.addAttribute("suppCategoryMsg", messageSource.getMessage("supp.category.msg", null, LocaleContextHolder.getLocale()));
        categoryService.suppCategory(id);
        return REDIRECT_CATEGORY;
    }
}
