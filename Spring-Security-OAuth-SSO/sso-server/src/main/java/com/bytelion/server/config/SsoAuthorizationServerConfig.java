package com.bytelion.server.config;

import com.bytelion.server.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author -> 太傅
 * @date -> 2020/11/19 : 17:58
 * #description    ->认证服务器配置。
 */

@Configuration
@EnableAuthorizationServer
public class SsoAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserDetailService userDetailService;

	@Bean
	public TokenStore jwtTokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}

	/**、
	 *Token使用JWT
	 * @return
	 */
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
		accessTokenConverter.setSigningKey("test_key");
		return accessTokenConverter;
	}

	/**
	 * 分配两个客户端配置，分别为app-a和app-b，使用默认的Spring Security登录页面来进行认证
	 * @param clients
	 * @throws Exception
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
				.withClient("app-a")
				.secret(passwordEncoder.encode("app-a-1234"))
				.authorizedGrantTypes("refresh_token","authorization_code")
				.accessTokenValiditySeconds(3600)
				.scopes("all")
				//自动认证授权，无需手动点击
				.autoApprove(true)
				//指定客户端A认账成功后注册到client的重定向URL
				.redirectUris("http://127.0.0.1:9090/app1/login")
				.and()
				.withClient("app-b")
				.secret(passwordEncoder.encode("app-b-1234"))
				.authorizedGrantTypes("refresh_token","authorization_code")
				.accessTokenValiditySeconds(7200)
				.scopes("all")
				.autoApprove(true)
				//指定客户端b认账成功后注册到client的重定向URL
				.redirectUris("http://127.0.0.1:9091/app2/login");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.tokenStore(jwtTokenStore())
				.accessTokenConverter(jwtAccessTokenConverter())
				.userDetailsService(userDetailService);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) {
		// 获取密钥需要身份认证
		security.tokenKeyAccess("isAuthenticated()");
	}


}
