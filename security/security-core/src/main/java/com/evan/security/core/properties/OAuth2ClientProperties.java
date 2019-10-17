package com.evan.security.core.properties;

/**
 * @author EvanHuang
 * @date 10/17/2019 7:35 PM
 * @since
 */
public class OAuth2ClientProperties {

    private String clientId;

    private String clientSecret;


    // 如果properties文件没配,并且这里设置为0, 代表这个令牌是不会过期的
    private int accessTokenValidateSeconds = 7200;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public int getAccessTokenValidateSeconds() {
        return accessTokenValidateSeconds;
    }

    public void setAccessTokenValidateSeconds(int accessTokenValidateSeconds) {
        this.accessTokenValidateSeconds = accessTokenValidateSeconds;
    }

}
