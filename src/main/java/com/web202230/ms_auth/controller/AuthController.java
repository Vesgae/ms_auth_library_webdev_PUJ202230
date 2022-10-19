package com.web202230.ms_auth.controller;

import com.web202230.ms_auth.dto.JwtDto;
import com.web202230.ms_auth.dto.LoginUser;
import com.web202230.ms_auth.dto.NewUser;
import com.web202230.ms_auth.entity.Role;
import com.web202230.ms_auth.entity.User;
import com.web202230.ms_auth.enums.RoleNames;
import com.web202230.ms_auth.jwt.JwtProvider;
import com.web202230.ms_auth.service.RoleService;
import com.web202230.ms_auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    JwtProvider jwtProvider;
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> newUser(@Validated @RequestBody NewUser newUser, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity("Campos invalidos.", HttpStatus.BAD_REQUEST);
        if(userService.existByEmail(newUser.getEmail()))
            return new ResponseEntity("Usuario ya existe.", HttpStatus.BAD_REQUEST);
        User user = new User(newUser.getName(), newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()), newUser.getBirthday());
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(RoleNames.WORKERS));
        if(newUser.getRoles().contains("ADMIN"))
            roles.add(new Role(RoleNames.ADMIN));
        user.setRoles(roles);
        user.setBirthday(newUser.getBirthday());
        System.out.println(user.getEmail() + " - " + user.getBirthday().toString());
        userService.save(user);
        return new ResponseEntity("Usuario guardado correctamente.", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Validated @RequestBody LoginUser loginUser, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity("Campos invalidos.", HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }
}
