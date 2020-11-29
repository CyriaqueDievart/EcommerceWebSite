package com.cecile.cyriaque.EcommerceWebSite.repositories;

import com.cecile.cyriaque.EcommerceWebSite.entity.CategoryEntity;
import com.cecile.cyriaque.EcommerceWebSite.entity.ProductEntity;
import com.cecile.cyriaque.EcommerceWebSite.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    Optional<ProductEntity> findByIdAndActive(Long id, boolean active);
    Optional<ProductEntity> findById(Long id);
    List<ProductEntity> findAllBySellerAndActive(UserEntity seller, boolean active);
    List<ProductEntity> findAllByBuyer(UserEntity seller);
    List<ProductEntity> findAllByActive(boolean active);
    List<ProductEntity> findAll();
    List<ProductEntity> findAllByCategoryAndActive(CategoryEntity category, boolean active);
    void removeById(Long ID);
}
