/**
 * 
 */
package com.evan.security.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author Evan Huang
 *
 */
@Configuration
@EnableAuthorizationServer // 只要添加这个注解， 项目就可以成为认证服务器了
public class ImoocAuthorizationServerConfig {

}
