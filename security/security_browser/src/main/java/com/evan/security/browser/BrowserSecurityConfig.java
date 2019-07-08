/**
 * 
 */
package com.evan.security.browser;

import com.evan.security.core.authentication.AbstractChannelSecurityConfig;
import com.evan.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.evan.security.core.properties.SecurityConstants;
import com.evan.security.core.properties.SecurityProperties;
import com.evan.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author Evan Huang
 *
 */
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;

	@Autowired
	private SpringSocialConfigurer imoocSocialSecurityConfig;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		applyPasswordAuthenticationConfig(http);

		http.apply(validateCodeSecurityConfig)
				.and()
				.apply(smsCodeAuthenticationSecurityConfig)
				.and()
				.apply(imoocSocialSecurityConfig)
				.and()
				.rememberMe()
				.tokenRepository(persistentTokenRepository())
				.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
				.userDetailsService(userDetailsService)
				.and()
				.authorizeRequests()
				.antMatchers(
						SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
						SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
						securityProperties.getBrowser().getLoginPage(),
						SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
						securityProperties.getBrowser().getSignUpUrl(),
						"/user/regist")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.csrf().disable();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		// 系统第一次启动的时候可以打开下面的注释让自动去创建表,之后就需要关闭了， 不然会报错 （因为表已经存在了）
		// tokenRepository.setCreateTableOnStartup(true);
		return tokenRepository;
	}
}
