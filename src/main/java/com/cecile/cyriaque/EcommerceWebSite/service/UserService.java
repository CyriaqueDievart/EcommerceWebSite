package com.cecile.cyriaque.EcommerceWebSite.service;


import com.cecile.cyriaque.EcommerceWebSite.entity.UserEntity;
import com.cecile.cyriaque.EcommerceWebSite.dto.UserRegistration;
import com.cecile.cyriaque.EcommerceWebSite.exception.InvalidTokenException;
import com.cecile.cyriaque.EcommerceWebSite.exception.UnkownIdentifierException;
import com.cecile.cyriaque.EcommerceWebSite.exception.UserAlreadyExistException;

public interface UserService {

    void register(final UserRegistration user) throws UserAlreadyExistException;
    boolean checkIfUserExist(final String email);
    void sendRegistrationConfirmationEmail(final UserEntity user);
    boolean verifyUser(final String token) throws InvalidTokenException;
    UserEntity getUserById(final String id) throws UnkownIdentifierException;
}