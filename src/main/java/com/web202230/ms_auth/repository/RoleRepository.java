package com.web202230.ms_auth.repository;

import com.web202230.ms_auth.entity.Role;
import com.web202230.ms_auth.enums.RoleNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleNames roleName);
}
