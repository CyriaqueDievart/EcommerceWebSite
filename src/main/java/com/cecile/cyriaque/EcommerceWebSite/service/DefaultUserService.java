package com.cecile.cyriaque.EcommerceWebSite.service;

import com.cecile.cyriaque.EcommerceWebSite.context.AccountVerificationEmailContext;
import com.cecile.cyriaque.EcommerceWebSite.entity.SecureTokenEntity;
import com.cecile.cyriaque.EcommerceWebSite.entity.UserEntity;
import com.cecile.cyriaque.EcommerceWebSite.dto.UserRegistration;
import com.cecile.cyriaque.EcommerceWebSite.repositories.SecureTokenRepository;
import com.cecile.cyriaque.EcommerceWebSite.repositories.UserRepository;
import com.cecile.cyriaque.EcommerceWebSite.exception.InvalidTokenException;
import com.cecile.cyriaque.EcommerceWebSite.exception.UnkownIdentifierException;
import com.cecile.cyriaque.EcommerceWebSite.exception.UserAlreadyExistException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.BooleanUtils;

import javax.mail.MessagingException;
import java.util.Objects;

@Service("userService")
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SecureTokenService secureTokenService;

    @Autowired
    SecureTokenRepository secureTokenRepository;

    @Value("${site.base.url.https}")
    private String baseURL;


    @Override
    public void register(UserRegistration user) throws UserAlreadyExistException {
        if(checkIfUserExist(user.getEmail())){
            throw new UserAlreadyExistException("Un utilisateur existe deja avec cette adresse mail");
        }
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        encodePassword(user, userEntity);
        if(userRepository.count() == 0){
            userEntity.setRole("ADMIN");
        }
        userRepository.save(userEntity);
        sendRegistrationConfirmationEmail(userEntity);

    }


    @Override
    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email) !=null ? true : false;
    }

    @Override
    public void sendRegistrationConfirmationEmail(UserEntity user) {
        SecureTokenEntity secureToken= secureTokenService.createSecureToken();
        secureToken.setUser(user);
        secureTokenRepository.save(secureToken);
        AccountVerificationEmailContext emailContext = new AccountVerificationEmailContext();
        emailContext.init(user);
        emailContext.setToken(secureToken.getToken());
        emailContext.buildVerificationUrl(baseURL, secureToken.getToken());
        try {
            emailService.sendMail(emailContext);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean verifyUser(String token) throws InvalidTokenException {

        SecureTokenEntity secureToken = secureTokenService.findByToken(token);
        if(Objects.isNull(secureToken) || !StringUtils.equals(token, secureToken.getToken()) || secureToken.isExpired()){
            throw new InvalidTokenException("Token is not valid");
        }
        UserEntity user = userRepository.getOne(secureToken.getUser().getId());
        if(Objects.isNull(user)){
            return false;
        }
        user.setAccountVerified(true);
        userRepository.save(user); // let's same user details

        // we don't need invalid password now
        secureTokenService.removeToken(secureToken);
        return true;
    }

    @Override
    public UserEntity getUserById(String id) throws UnkownIdentifierException {
        UserEntity user= userRepository.findByEmail(id);
        if(user == null || BooleanUtils.isFalse(user.isAccountVerified())){
            // we will ignore in case account is not verified or account does not exists
            throw new UnkownIdentifierException("Nous ne trouvons pas le compte, ou il n'est pas encore activé");
        }
        return user;
    }

    private void encodePassword(UserRegistration source, UserEntity target){
        target.setPassword(passwordEncoder.encode(source.getPassword()));
    }
}
