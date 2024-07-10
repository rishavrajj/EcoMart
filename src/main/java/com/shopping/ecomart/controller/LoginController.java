package com.shopping.ecomart.controller;

import com.shopping.ecomart.dtos.MyUserDTO;
import com.shopping.ecomart.dtos.ResultResponseDTO;
import com.shopping.ecomart.entity.MyUser;
import com.shopping.ecomart.entity.Role;
import com.shopping.ecomart.repository.MyUserRepository;
import com.shopping.ecomart.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class LoginController {

    @Autowired
    private MyUserRepository myUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/auth/login")
    public ResultResponseDTO login(@RequestBody MyUserDTO user) {
        String userName = user.getUserName();
        List<MyUser> userList = myUserRepository.findByUserName(userName);
        MyUser dbUser=null;
        if(!userList.isEmpty()) {
            dbUser=userList.get(0);
            String dbPassword = dbUser.getPassword();
            String dbUserName = dbUser.getUserName();
            if (userName.equals(dbUserName) && passwordEncoder.matches(user.getPassword(),dbPassword)) {
                String jwt = jwtService.generateToken(getUserDetails(dbUser));
                return ResultResponseDTO.builder().message("JWT Token Generated!").response(jwt).build();
            }
        }
      return ResultResponseDTO.builder().message("JWT Token Generation Failed!!!").response(null).build();
    }

    private UserDetails getUserDetails(MyUser user) {
        Optional<Role> first = user.getRoles().stream().findFirst();
        List<GrantedAuthority> authority=new ArrayList<>();
        first.ifPresent(role -> {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
            authority.add(simpleGrantedAuthority);
        });
        return new User(user.getUserName(), user.getPassword(),authority);
    }
}