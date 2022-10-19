package com.web202230.ms_auth.repository;

import com.web202230.ms_auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String mail);
    boolean existsByEmail(String email);
}
