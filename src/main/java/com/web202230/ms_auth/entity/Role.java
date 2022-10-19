package com.web202230.ms_auth.entity;

import com.sun.istack.NotNull;
import com.web202230.ms_auth.enums.RoleNames;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private RoleNames roleName;

    public Role() {
    }

    public Role(@NotNull RoleNames roleName) {
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoleNames getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleNames rolNombre) {
        this.roleName = rolNombre;
    }
}