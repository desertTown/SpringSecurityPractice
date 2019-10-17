package com.evan.security.core.properties;

/**
 * @author EvanHuang
 * @date 10/17/2019 7:35 PM
 * @since
 */
public class OAuth2Properties {

    private String jwtSigningKey = "evan";

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
