package com.example.myservice.controller;

import com.example.myservice.controller.dto.UserDTO;
import com.example.myservice.model.User;
import com.example.myservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> printUser() {
        return userService.listUsers();
    }

    @PutMapping
    public void changeUser(@RequestBody User user){
        userService.updateUser(user);
    }


    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username){
        userService.deleteUser(username);
    }


}
