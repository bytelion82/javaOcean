package com.example.sso.client.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author -> 太傅
 * @date -> 2020/11/19 : 17:44
 * #description    ->在单点登录模式下如何进行权限校验。
 */

@Order(101)
//服务器已经配置了Spring Security配置，并且顺序为100，防止客户端的Spring Security配置冲突
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
}
