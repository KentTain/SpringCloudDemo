package com.example.service;

import org.springframework.stereotype.Component;

import com.example.model.User;

@Component
public class HelloRemoteFallbackService implements HelloRemoteService {
	@Override
    public User hello(int id)
    {
		System.out.println("调用服务失败--hello");
		return null;
    }
	
	@Override
    public String timeout() {
		System.out.println("调用服务失败--timeout");
        return "timeout 降级";
    }

    @Override
    public String exception() {
    	System.out.println("调用服务失败--exception");
        return "exception 降级";
    }

}
