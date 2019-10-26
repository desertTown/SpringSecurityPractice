/**
 * 
 */
package com.evan.security.server;

import com.evan.security.core.properties.OAuth2ClientProperties;
import com.evan.security.core.properties.SecurityProperties;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Evan Huang
 * 认证服务器配置
 *
 */
@Configuration
@EnableAuthorizationServer // 只要添加这个注解， 项目就可以成为认证服务器了
public class ImoocAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore tokenStore;


    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    // 这个Bean 在jwt的配置下才会生效,所以设置required为false
    @Autowired(required = false)
    private TokenEnhancer jwtTokenEnhancer;

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 认证及token配置
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);

        if(jwtAccessTokenConverter != null && jwtTokenEnhancer != null){
            TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
            List<TokenEnhancer> enhancers = new ArrayList<>();
            enhancers.add(jwtTokenEnhancer);
            enhancers.add(jwtAccessTokenConverter);
            enhancerChain.setTokenEnhancers(enhancers);
            endpoints.tokenEnhancer(enhancerChain)
                    .accessTokenConverter(jwtAccessTokenConverter);
        }
    }

    /**
     * tokenKey的访问权限表达式配置
     */
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()");
    }

    /**
     * 客户端配置
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                // 配置了这里的话就代表properties中的client和clientSecret不起作用了
//                .withClient("test")
//                .secret("test")
//                // 对于test client而言, 这里设置了只支持下面两种授权模式
//                .authorizedGrantTypes("refresh_token", "password")
//                // 这里如果客户端没传scope过来也会可以的, 但是如果传了scope则必须在下面这个集合内
//                .scopes("all","read","write")
//                .accessTokenValiditySeconds(600)
//                .and()
//                .withClient("facebook")
//                .secret("facebookSecret")
//                .accessTokenValiditySeconds(1200);
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        if (ArrayUtils.isNotEmpty(securityProperties.getOauth2().getClients())) {
            for (OAuth2ClientProperties client : securityProperties.getOauth2().getClients()) {
                builder.withClient(client.getClientId())
                        .secret(client.getClientSecret())
                        .authorizedGrantTypes("refresh_token", "authorization_code", "password")
                        .accessTokenValiditySeconds(client.getAccessTokenValidateSeconds())
                        .refreshTokenValiditySeconds(2592000)
                        .scopes("all");
            }
        }
    }

}
