/**
 * 
 */
package com.evan.sso.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author Evan Huang
 *
 */
@Configuration
@EnableAuthorizationServer
public class SsoAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
				.withClient("facebook")
				.secret("facebookSecret")
				.authorizedGrantTypes("authorization_code", "refresh_token")
				.scopes("all")
				.and()
				.withClient("test")
				.secret("test")
				.authorizedGrantTypes("authorization_code", "refresh_token")
				.scopes("all");
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(jwtTokenStore()).accessTokenConverter(jwtAccessTokenConverter());
	}
	
	@Override
	// 认证服务器的安全配置
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		// isAuthenticated 是SpringSecurity的一个授权表达式
		// 表示当需要访问认证服务器的tokenKey的时候需要身份认证。 tokenKey指的是下面签名的evan
		// 前端需要拿到这个tokenKey(签名秘钥来对JWT进行解析)
		security.tokenKeyAccess("isAuthenticated()");
	}
	
	@Bean
	public TokenStore jwtTokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}
	
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter(){
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("evan");
        return converter;
	}

}
