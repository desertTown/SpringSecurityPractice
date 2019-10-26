package com.evan.security.core.properties;

/**
 * 使用jwt时为token签名的秘钥
 * @author EvanHuang
 * @date 10/17/2019 7:35 PM
 * @since
 */
public class OAuth2Properties {

    private String jwtSigningKey = "evan";

    /**
     * 客户端配置
     */
    private OAuth2ClientProperties[] clients = {};

    public OAuth2ClientProperties[] getClients() {
        return clients;
    }

    public void setClients(OAuth2ClientProperties[] clients) {
        this.clients = clients;
    }

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }

    public void setJwtSigningKey(String jwtSigningKey) {
        this.jwtSigningKey = jwtSigningKey;
    }
}
