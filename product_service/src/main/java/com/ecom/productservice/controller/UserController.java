package com.ecom.productservice.controller;

import com.ecom.productservice.entity.User;
import com.ecom.productservice.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @PostMapping("/register")
    public User register(@RequestBody User user)
    {
        return myUserDetailsService.createUser(user);
    }
}
