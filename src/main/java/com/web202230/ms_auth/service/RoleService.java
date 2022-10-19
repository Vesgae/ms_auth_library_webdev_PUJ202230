package com.web202230.ms_auth.service;

import com.web202230.ms_auth.entity.Role;
import com.web202230.ms_auth.enums.RoleNames;
import com.web202230.ms_auth.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Optional<Role> findByRoleName(RoleNames roleName){
        return roleRepository.findByRoleName(roleName);

    }
    public void save(Role role){
        roleRepository.save(role);
    }
}
