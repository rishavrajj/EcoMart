package com.shopping.ecomart.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class UserController {
    @GetMapping("/user")
    public String getUser(){
        return "This is a user";
    }

}
