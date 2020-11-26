package com.cecile.cyriaque.EcommerceWebSite.repositories;

import com.cecile.cyriaque.EcommerceWebSite.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long > {
    UserEntity findByEmail(String email);
}
