package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public @ResponseBody User hello() {
        return new User(1, "hello consul");
    }
}
