package com.lazylion.provider.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author -> 太傅
 * @date -> 2020/11/20 : 16:44
 * #description    ->
 */

@RestController
@RequestMapping("/provide")
public class HelloService {


	@GetMapping("/hello")
	public String hello(@RequestParam("msg") String message) {
		System.out.println("-----***------服务提供者收到请求访问-----***------");
		return String.format("hello %s", message);
	}
}
