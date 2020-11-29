package com.cecile.cyriaque.EcommerceWebSite.service;

import com.cecile.cyriaque.EcommerceWebSite.dto.AddCategory;
import com.cecile.cyriaque.EcommerceWebSite.entity.CategoryEntity;
import com.cecile.cyriaque.EcommerceWebSite.repositories.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class DefaultCategoryService implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void addCategory(AddCategory addCategory){
        CategoryEntity newCategory = new CategoryEntity();
        BeanUtils.copyProperties(addCategory, newCategory);
        categoryRepository.save(newCategory);
    }

    public List<CategoryEntity> getAllCategory(){
        return categoryRepository.findAll();
    }

    public void suppCategory(Long id) {
        categoryRepository.removeById(id);
    }

    public CategoryEntity getCategoryById(Long id){
        return categoryRepository.findById(id).orElseThrow();
    }

}
