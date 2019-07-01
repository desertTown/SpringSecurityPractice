/**
 * 
 */
package com.evan.security.core.social.qq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author Evan Huang
 *
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;

	/**
	 * connectionFactoryLocator：  查找connectionFactory，因为系统中可能有多个connectionFactory
	 * 他会根据条件去查找当前应该使用哪个connectionFactory
	 * Encryptors:  将查到数据库的数据加解密(AccessToken那些比较敏感的信息)
	 * 其中的方法.noOpText()： 表示不做任何加解密操作， 这里只是方便dev查看方便， prod环境不建议使用
	 * @param connectionFactoryLocator
	 * @return
	 */
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
		repository.setTablePrefix("imooc_");
		return repository;
	}
	
	@Bean
	public SpringSocialConfigurer imoocSocialSecurityConfig() {
		return new SpringSocialConfigurer();
	}
	
}
