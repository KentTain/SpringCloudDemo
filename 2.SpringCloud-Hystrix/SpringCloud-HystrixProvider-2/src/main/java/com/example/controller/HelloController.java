package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public @ResponseBody User hello(@RequestParam(value = "id") int id) {
        return new User(id, "hello consul provider 2");
    }
}
