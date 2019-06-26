/**
 * 
 */
package com.evan.security.core.properties;

/**
 * @author Evan Huang
 *
 */
public class BrowserProperties {

	private String loginPage = "/evan-signIn.html";

	private LoginType loginType = LoginType.JSON;

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

}
