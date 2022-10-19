package com.web202230.ms_auth.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MainUser implements UserDetails {
    private String email;
    private String password;
    private String name;
    private Date birthdate;
    private Collection<? extends GrantedAuthority> authorities;



    public MainUser(String email, String password, String name, Date birthdate, Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthdate = birthdate;
        this.authorities = authorities;
    }

    public MainUser(String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static MainUser build(User user){
        List<GrantedAuthority> authorities =
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(
                        role.getRoleName().name())).collect(Collectors.toList());
        return new MainUser(user.getEmail(), user.getPassword(), authorities);
    }

    public String getName() {
        return name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
