package com.cecile.cyriaque.EcommerceWebSite.service;

import com.cecile.cyriaque.EcommerceWebSite.dto.AddProduct;
import com.cecile.cyriaque.EcommerceWebSite.entity.CategoryEntity;
import com.cecile.cyriaque.EcommerceWebSite.entity.ProductEntity;
import com.cecile.cyriaque.EcommerceWebSite.entity.UserEntity;
import com.cecile.cyriaque.EcommerceWebSite.repositories.ProductRepository;
import com.cecile.cyriaque.EcommerceWebSite.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class DefaultProductService implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;

    public void addProduct(AddProduct addProduct, UserEntity userEntity){
        ProductEntity newProduct = new ProductEntity();
        BeanUtils.copyProperties(addProduct, newProduct);
        newProduct.setSeller(userEntity);
        newProduct.setActive(true);
        newProduct.setCategory(categoryService.getCategoryById(addProduct.getCategory()));
        productRepository.save(newProduct);
    }

    public List<ProductEntity> getAllProductsByUserId(UserEntity user){
        return productRepository.findAllBySellerAndActive(user,true);
    }
    public List<ProductEntity> getAllProductsByBuyerId(UserEntity user){
        return productRepository.findAllByBuyer(user);
    }
    public List<ProductEntity> getAllProductsActive(){
        return productRepository.findAllByActive(true);
    }
    public List<ProductEntity> getAllProducts(){
        return productRepository.findAll();
    }
    public List<ProductEntity> getAllProductsByCategory(CategoryEntity category){
        return productRepository.findAllByCategoryAndActive(category,true);
    }


    public void suppProduct(Long id){
        ProductEntity newProduct = new ProductEntity();
        ProductEntity addProduct = productRepository.findByIdAndActive(id, true).orElseThrow();
        BeanUtils.copyProperties(addProduct, newProduct);
        newProduct.setActive(false);
        productRepository.save(newProduct);
    }
    public void activeProduct(Long id){
        ProductEntity newProduct = new ProductEntity();
        ProductEntity addProduct = productRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(addProduct, newProduct);
        newProduct.setActive(true);
        productRepository.save(newProduct);
    }
    public void achat(Long id,UserEntity user){
        ProductEntity newProduct = new ProductEntity();
        ProductEntity addProduct = productRepository.findByIdAndActive(id,true).orElse(null);
        BeanUtils.copyProperties(addProduct, newProduct);
        newProduct.setActive(false);
        newProduct.setBuyer(user);
        productRepository.save(newProduct);
    }
}
