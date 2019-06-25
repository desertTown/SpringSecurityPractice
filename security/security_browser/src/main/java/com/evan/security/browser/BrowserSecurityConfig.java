/**
 * 
 */
package com.evan.security.browser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Evan Huang
 *
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 这种表单认证方式会使UsernamePasswordAuthenticationFilter 生效
		http.formLogin()
		// 这种会使BasicAuthenticationFilter生效。 过滤器链会执行这个Filter
//		http.httpBasic()     // 这种会以弹窗的方式跳出登录框
			.and()
			.authorizeRequests()
			.anyRequest()
			.authenticated();
		
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
