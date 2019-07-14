/**
 * 
 */
package com.evan.security.core.social.weixin.api;

/**
 * 微信API调用接口
 * 
 * @author Evan Huang
 *
 */
public interface Weixin {


	WeixinUserInfo getUserInfo(String openId);
	
}
