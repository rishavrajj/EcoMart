package com.shopping.ecomart.controller;

import com.shopping.ecomart.dtos.UserLoginDTO;
import com.shopping.ecomart.dtos.ResultResponseDTO;
import com.shopping.ecomart.entity.MyUser;
import com.shopping.ecomart.repository.MyUserRepository;
import com.shopping.ecomart.config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private MyUserRepository myUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/auth/login")
    public ResultResponseDTO login(@RequestBody UserLoginDTO user) {
        String userName = user.getUserName();
        List<MyUser> userList = myUserRepository.findByUserName(userName);
        MyUser dbUser = null;
        if (!userList.isEmpty()) {
            dbUser = userList.get(0);
            String dbPassword = dbUser.getPassword();
            String dbUserName = dbUser.getUserName();
            if (userName.equals(dbUserName) && passwordEncoder.matches(user.getPassword(), dbPassword)) {
                String jwt = jwtService.generateToken(dbUser);
                return ResultResponseDTO.builder().message("JWT Token Generated!").data(jwt).build();
            }
        }
        return ResultResponseDTO.builder().message("JWT Token Generation Failed!!!").data(null).build();
    }


}