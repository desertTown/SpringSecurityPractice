/**
 * 
 */
package com.evan.security.core.authorize;

import com.evan.security.core.properties.SecurityConstants;
import com.evan.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * 核心模块的授权配置提供器，安全模块涉及的url的授权配置在这里。
 * @author Evan Huang
 *
 */
@Component
@Order(Integer.MIN_VALUE)
public class ImoocAuthorizeConfigProvider implements AuthorizeConfigProvider {

	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		config.antMatchers(
				SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
				SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE,
				SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_OPENID,
				securityProperties.getBrowser().getSignInPage(),
				SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
				securityProperties.getBrowser().getSignInPage(),
				securityProperties.getBrowser().getSignUpUrl(),
				securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
				securityProperties.getBrowser().getSignOutUrl())
		.permitAll();
	}

}
