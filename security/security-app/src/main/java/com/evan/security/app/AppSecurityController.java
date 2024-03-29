/**
 * 
 */
package com.evan.security.app;

import javax.servlet.http.HttpServletRequest;

import com.evan.security.app.social.AppSingUpUtils;
import com.evan.security.core.properties.SecurityConstants;
import com.evan.security.core.social.SocialController;
import com.evan.security.core.social.support.SocialUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;


/**
 * @author Evan Huang
 *
 */
@RestController
public class AppSecurityController extends SocialController {
	
	@Autowired
	private ProviderSignInUtils providerSignInUtils;
	
	@Autowired
	private AppSingUpUtils appSingUpUtils;

	/**
	 * 需要注册时跳到这里，返回401和用户信息给前端
	 * @param request
	 * @return
	 */
	@GetMapping(SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
		SocialUserInfo userInfo = new SocialUserInfo();
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
		userInfo.setProviderId(connection.getKey().getProviderId());
		userInfo.setProviderUserId(connection.getKey().getProviderUserId());
		userInfo.setNickname(connection.getDisplayName());
		userInfo.setHeadimg(connection.getImageUrl());
		
		appSingUpUtils.saveConnectionData(new ServletWebRequest(request), connection.createData());

		return buildSocialUserInfo(connection);
	}

}
