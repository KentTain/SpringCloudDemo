package com.example.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.User;

@Component
@FeignClient(name= "service-gateway", fallback = HelloRemoteFallbackService.class)
public interface HelloRemoteService {
	@RequestMapping(value = "/proxy/hello/index")
    public User hello(@RequestParam(value = "id") int id);
	
	@RequestMapping(value = "/proxy/hello/timeout", method = RequestMethod.GET)
    public String timeout();

    @RequestMapping(value = "/proxy/hello/exception", method = RequestMethod.GET)
    public String exception();

}
