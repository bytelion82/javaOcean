package com.lazylion.sso.client.controller;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author -> 太傅
 * @date -> 2020/11/19 : 17:54
 * #description    ->
 */
@RestController
public class UserController {

	@GetMapping("user")
	public Authentication user(Authentication authentication) {
		return authentication;
	}
}
