package com.cecile.cyriaque.EcommerceWebSite.repositories;

import com.cecile.cyriaque.EcommerceWebSite.entity.SecureTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecureTokenRepository extends JpaRepository<SecureTokenEntity, Long > {

    SecureTokenEntity findByToken(final String token);
    Long removeByToken(String token);
}
