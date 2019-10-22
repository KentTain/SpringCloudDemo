package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;


@RestController
@RequestMapping("/hello")
public class HelloController {
	private static Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/index")
    public @ResponseBody User index(@RequestParam(value = "id") int id) {
    	LOGGER.info("invoking index endpoint");
        return new User(id, "index consul from provider 2");
    }
    
    @RequestMapping("/timeout")
    public String timeout() throws InterruptedException {
        LOGGER.info("invoking timeout endpoint");
        Thread.sleep(10000L);
        return "exception from provider 2";
    }

    @RequestMapping("/exception")
    public String exception() {
        LOGGER.info("invoking exception endpoint");
        if (System.currentTimeMillis() % 2 == 0) {
            throw new RuntimeException("random exception from provider 2");
        }
        return "exception from provider 2";
    }
}