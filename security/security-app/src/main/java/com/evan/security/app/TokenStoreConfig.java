/**
 * 
 */
package com.evan.security.app;

import com.evan.security.app.jwt.TokenJwtEnhancer;
import com.evan.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author Evan Huang
 *
 */
@Configuration
public class TokenStoreConfig {


	/**
	 * 使用redis存储token的配置，只有在evan.security.oauth2.tokenStore配置为redis时生效
	 * @author zhailiang
	 *
	 */
	@Configuration
	@ConditionalOnProperty(prefix = "evan.security.oauth2", name = "tokenStore", havingValue = "redis")
	public static class RedisConfig {

		@Autowired
		private RedisConnectionFactory redisConnectionFactory;

		/**
		 * @return
		 */
		@Bean
		public TokenStore redisTokenStore() {
			return new RedisTokenStore(redisConnectionFactory);
		}

	}

	/**
	 * 使用jwt时的配置，默认生效
	 *
	 * @author zhailiang
	 *
	 */
	@Configuration
	@ConditionalOnProperty(prefix = "evan.security.oauth2", name = "tokenStore", havingValue = "jwt", matchIfMissing = true)
	public static class JwtConfig {

		@Autowired
		private SecurityProperties securityProperties;

		@Bean
		public TokenStore jwtTokenStore() {
			return new JwtTokenStore(jwtAccessTokenConverter());
		}

		@Bean
		public JwtAccessTokenConverter jwtAccessTokenConverter(){
			JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
			converter.setSigningKey(securityProperties.getOauth2().getJwtSigningKey());
			return converter;
		}

		@Bean
		@ConditionalOnBean(TokenEnhancer.class)
		// 不能使用该条件注解，因为JwtAccessTokenConverter也是一个TokenEnhancer
//        @ConditionalOnMissingBean(TokenEnhancer.class)
		// 而 ConditionalOnBean 是必须存在一个TokenEnhancer的时候，才被创建
		// 先不纠结这个问题了。就这样吧。也就是封装程度的问题
		public TokenEnhancer jwtTokenEnhancer(){
			return new TokenJwtEnhancer();
		}

	}

}
