package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.model.User;
import com.example.service.HelloRemoteService;

@RestController
public class ServiceController {

	@Autowired
	private LoadBalancerClient loadBalancer;
	@Autowired
	private DiscoveryClient discoveryClient;

	/**
	 * 获取所有服务
	 */
	@RequestMapping("/services")
	public Object services() {
		return discoveryClient.getInstances("service-provider");
	}

	/**
	 * 从所有服务中选择一个服务（轮询）
	 */
	@RequestMapping("/discover")
	public Object discover() {
		return loadBalancer.choose("service-provider").getUri().toString();
	}

	/**
	 * 使用RestTemplate，调用远程接口
	 * 
	 * @return
	 */
	@RequestMapping("/call")
	public User call() {
		ServiceInstance serviceInstance = loadBalancer.choose("service-provider");
		System.out.println("服务地址：" + serviceInstance.getUri());
		System.out.println("服务名称：" + serviceInstance.getServiceId());

		User callServiceResult = new RestTemplate().getForObject(serviceInstance.getUri().toString() + "/hello",
				User.class);
		System.out.println(callServiceResult);
		return callServiceResult;
	}
	
	@Autowired
    private HelloRemoteService userService;
	
	/**
	 * 使用Feign，调用远程接口
	 * 
	 * @return
	 */
	@RequestMapping("/FeignCall")
	public User FeignCall(@RequestParam(value = "id") int id) {
		User callServiceResult = userService.hello(id);
		System.out.println(callServiceResult);
		return callServiceResult;
	}

}
