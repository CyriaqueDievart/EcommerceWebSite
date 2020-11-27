package com.cecile.cyriaque.EcommerceWebSite.repositories;

import com.cecile.cyriaque.EcommerceWebSite.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
    Optional<CategoryEntity> findById(Long ID);
    List<CategoryEntity> findAll();
    void removeById(Long ID);
}
