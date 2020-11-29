package com.cecile.cyriaque.EcommerceWebSite.service;

import com.cecile.cyriaque.EcommerceWebSite.dto.AddCategory;
import com.cecile.cyriaque.EcommerceWebSite.entity.CategoryEntity;

import java.util.List;


public interface CategoryService {
    public void addCategory(AddCategory addCategory);
    public List<CategoryEntity> getAllCategory();
    public void suppCategory(Long id);
    public CategoryEntity getCategoryById(Long id);
}
