package com.shopping.ecomart.controller;

import com.shopping.ecomart.dtos.ResultResponseDTO;
import com.shopping.ecomart.dtos.UserRegsReqDTO;
import com.shopping.ecomart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register/user")
    public ResultResponseDTO createUser(@RequestBody UserRegsReqDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.save(user);
    }
}