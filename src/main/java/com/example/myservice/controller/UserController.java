package com.example.myservice.controller;

import com.example.myservice.model.User;
import com.example.myservice.service.UserService;
import org.apache.coyote.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody User user){
        userService.newUser(user);
    }

    @GetMapping("/users")
    public List<User> printUser(){
        //TODO
        return null;
    }
}
