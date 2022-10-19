package com.web202230.ms_auth.dto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class NewUser {
    private String name;
    private String email;
    private String password;
    @JsonFormat(pattern="dd/MM/yy")
    private Date birthday;
    private Set<String> roles = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
