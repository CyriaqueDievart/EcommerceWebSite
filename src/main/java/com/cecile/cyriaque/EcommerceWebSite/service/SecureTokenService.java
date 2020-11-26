package com.cecile.cyriaque.EcommerceWebSite.service;

import com.cecile.cyriaque.EcommerceWebSite.entity.SecureTokenEntity;

public interface SecureTokenService {

    SecureTokenEntity createSecureToken();
    void saveSecureToken(final SecureTokenEntity token);
    SecureTokenEntity findByToken(final String token);
    void removeToken(final SecureTokenEntity token);
    void removeTokenByToken(final String token);
}