package com.example.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.example.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Component
public class HelloRemoteFallbackService implements HelloRemoteService {
	@Override
    public User hello(int id)
    {
		System.out.println("调用服务失败--hello");
		return null;
    }
	
	@Autowired
	private LoadBalancerClient loadBalancer;
	
	@HystrixCollapser(batchMethod = "findAll",
            collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds", value = "100")})
    public Future<User> find(Long id) {
        throw new RuntimeException("This method body should not be executed");
    }

    @HystrixCommand
    public List<User> findAll(List<Long> ids) {
        System.out.println("Annotation---------" + ids + "Thread.currentThread().getName():" + Thread.currentThread().getName());
        ServiceInstance serviceInstance = loadBalancer.choose("service-provider");

		Map<String,String> map = new HashMap<String, String>();
        map.put("id", String.valueOf(ids));
		
		User[] users = new RestTemplate().getForObject(serviceInstance.getUri().toString() + "/hello/users?id=" + String.valueOf(ids),
				User[].class);

        return Arrays.asList(users);
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
