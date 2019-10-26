/**
 * 
 */
package com.evan.security.core.properties;

/**
 * @author Evan Huang
 * 浏览器环境配置项
 */
public class BrowserProperties {

	/**
	 * session管理配置项
	 */
	private SessionProperties session = new SessionProperties();

	/**
	 * 登录页面，当引发登录行为的url以html结尾时，会跳到这里配置的url上
	 */
	private String signInPage = SecurityConstants.DEFAULT_SIGN_IN_PAGE_URL;

	/**
	 * 社交登录，如果需要用户注册，跳转的页面
	 */
	private String signUpUrl = "/evan-signUp.html";

	/**
	 * 退出成功时跳转的url，如果配置了，则跳到指定的url，如果没配置，则返回json数据。
	 */
	private String signOutUrl;

	/**
	 * 登录响应的方式，默认是json
	 */
	private LoginResponseType signInResponseType = LoginResponseType.JSON;
	/**
	 * '记住我'功能的有效时间，默认1小时
	 */
	private int rememberMeSeconds = 3600;

	/**
	 * 登录成功后跳转的地址，如果设置了此属性，则登录成功后总是会跳到这个地址上。
	 */
	private String singInSuccessUrl;


	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}

	public String getSignUpUrl() {
		return signUpUrl;
	}

	public void setSignUpUrl(String signUpUrl) {
		this.signUpUrl = signUpUrl;
	}

	public SessionProperties getSession() {
		return session;
	}

	public void setSession(SessionProperties session) {
		this.session = session;
	}

	public String getSignOutUrl() {
		return signOutUrl;
	}

	public void setSignOutUrl(String signOutUrl) {
		this.signOutUrl = signOutUrl;
	}

	public String getSingInSuccessUrl() {
		return singInSuccessUrl;
	}

	public void setSingInSuccessUrl(String singInSuccessUrl) {
		this.singInSuccessUrl = singInSuccessUrl;
	}

	public LoginResponseType getSignInResponseType() {
		return signInResponseType;
	}

	public void setSignInResponseType(LoginResponseType signInResponseType) {
		this.signInResponseType = signInResponseType;
	}

	public String getSignInPage() {
		return signInPage;
	}

	public void setSignInPage(String signInPage) {
		this.signInPage = signInPage;
	}
}
