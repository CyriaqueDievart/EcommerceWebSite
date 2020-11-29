package com.cecile.cyriaque.EcommerceWebSite.service;

import com.cecile.cyriaque.EcommerceWebSite.dto.AddProduct;
import com.cecile.cyriaque.EcommerceWebSite.entity.CategoryEntity;
import com.cecile.cyriaque.EcommerceWebSite.entity.ProductEntity;
import com.cecile.cyriaque.EcommerceWebSite.entity.UserEntity;

import java.util.List;

public interface ProductService {
    public void addProduct(AddProduct addProduct, UserEntity userEntity);
    public List<ProductEntity> getAllProductsByUserId(UserEntity user);
    public List<ProductEntity> getAllProducts();
    public List<ProductEntity> getAllProductsActive();
    public List<ProductEntity> getAllProductsByCategory(CategoryEntity category);
    public void suppProduct(Long id);
    public void activeProduct(Long id);
    public List<ProductEntity> getAllProductsByBuyerId(UserEntity user);
    public void achat(Long id,UserEntity user);
}
