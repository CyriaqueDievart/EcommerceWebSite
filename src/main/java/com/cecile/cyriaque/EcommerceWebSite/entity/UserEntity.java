package com.cecile.cyriaque.EcommerceWebSite.entity;


import com.cecile.cyriaque.EcommerceWebSite.service.SecureTokenService;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private String role;
    private String token;
    private boolean accountVerified;

    @OneToMany(mappedBy = "user")
    private Set<SecureTokenEntity> tokens;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isAccountVerified() {
        return accountVerified;
    }

    public void setAccountVerified(boolean accountVerified) {
        this.accountVerified = accountVerified;
    }

    public Set<SecureTokenEntity> getTokens() {
        return tokens;
    }

    public void setTokens(Set<SecureTokenEntity> tokens) {
        this.tokens = tokens;
    }

    public void addToken(final SecureTokenEntity token){
        tokens.add(token);
    }
}
