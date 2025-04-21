package com.example.myservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyServiceRest {

    @GetMapping("/")
    public String sayHello(){
        return "Hello World 2 !";
    }

}
