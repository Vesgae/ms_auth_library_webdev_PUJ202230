package com.web202230.ms_auth.service;

import com.web202230.ms_auth.entity.User;
import com.web202230.ms_auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public boolean existByEmail(String email){
        return userRepository.existsByEmail(email);
    }
    public void save(User user){
        userRepository.save(user);
    }
}
