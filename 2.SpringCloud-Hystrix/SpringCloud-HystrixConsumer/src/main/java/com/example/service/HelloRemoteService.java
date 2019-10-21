package com.example.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.User;

@Component
@FeignClient(name= "service-provider", fallback = HelloRemoteFallbackService.class)
public interface HelloRemoteService {
	@RequestMapping(value = "/hello")
    public User hello(@RequestParam(value = "id") int id);
	
	@RequestMapping(value = "/timeout", method = RequestMethod.GET)
    public String timeout();

    @RequestMapping(value = "/exception", method = RequestMethod.GET)
    public String exception();

}
