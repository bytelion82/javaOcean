package com.bytelion.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author -> 太傅
 * @date -> 2020/11/19 : 18:19
 * #description    ->认证服务配置类
 * 指定如何进行用户认证
 */

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * 密码加密方式
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()//Spring Security自带的登录页面认证
				.and()
				.authorizeRequests()
				.anyRequest()//拦截所有请求
				.authenticated();
	}
}
