package com.bytelion.server.service;

import com.bytelion.server.entity.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author -> 太傅
 * @date -> 2020/11/19 : 18:12
 * #description    ->自定义用户登录认证的服务
 */

@Configuration
public class UserDetailService implements UserDetailsService {


	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * 用户名随便写，密码为123456，并且拥有user:add权限
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUser user = new MyUser();
		user.setUserName(username);
		user.setPassword(this.passwordEncoder.encode("123456"));
		return new User(username, user.getPassword(), user.isEnabled(),
				user.isAccountNonExpired(), user.isCredentialsNonExpired(),
				user.isAccountNonLocked(), AuthorityUtils.commaSeparatedStringToAuthorityList("user:add"));
	}
}
