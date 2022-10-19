package com.web202230.ms_auth.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtDto {

    private String token;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;
    private String bearer = "Bearer";

    public JwtDto(String token, String email, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.email = email;
        this.authorities = authorities;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }
}
