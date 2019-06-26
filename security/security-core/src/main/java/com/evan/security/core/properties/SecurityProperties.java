/**
 * 
 */
package com.evan.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Evan Huang
 *
 */
@ConfigurationProperties(prefix = "evan.security")
public class SecurityProperties {
	
	private BrowserProperties browser = new BrowserProperties();

	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}

}
