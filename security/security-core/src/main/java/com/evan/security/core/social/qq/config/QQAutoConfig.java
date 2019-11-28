/**
 * 
 */
package com.evan.security.core.social.qq.config;

import com.evan.security.core.properties.QQProperties;
import com.evan.security.core.properties.SecurityProperties;
import com.evan.security.core.social.CurrentUserHolder;
import com.evan.security.core.social.qq.connet.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @author Evan Huang
 *
 */
@Configuration
@ConditionalOnProperty(prefix = "evan.security.social.qq", name = "app-id")
@Order(11)
public class QQAutoConfig extends SocialConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;


	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer configurer,
									   Environment environment) {
		configurer.addConnectionFactory(createConnectionFactory());
	}

	@Override
	public UserIdSource getUserIdSource() {
		return new CurrentUserHolder();
	}

	protected ConnectionFactory<?> createConnectionFactory() {
		QQProperties qqConfig = securityProperties.getSocial().getQq();
		return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
	}

}
