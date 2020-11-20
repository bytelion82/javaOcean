package com.lazylion.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author -> 太傅
 * @date -> 2020/11/20 : 16:37
 * #description    ->
 */

@RestController
@RequestMapping("/consume")
public class ConsumeController {
	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * http://localhost:9001/consume/hello?msg=nacos
	 * @param message
	 * @return
	 */
	@GetMapping("/hello")
	public String hello(@RequestParam("msg") String message){
		ServiceInstance serviceInstance = loadBalancerClient.choose("provider");
		String providerPath = String.format("http://%s:%s/provide/hello?msg=%s",
				serviceInstance.getHost(), serviceInstance.getPort(), message);
		//向服务提供者发起请求
		System.out.println("-----***------服务消费者发起请求访问-----***------");
		System.out.println(providerPath);
		String result = restTemplate.getForObject(providerPath, String.class);

		return String.format("%s from %s %s",result,serviceInstance.getHost(),serviceInstance.getPort());

	}
}
