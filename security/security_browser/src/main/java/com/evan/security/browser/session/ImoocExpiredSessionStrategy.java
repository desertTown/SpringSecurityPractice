/**
 * 
 */
package com.evan.security.browser.session;

import com.evan.security.core.properties.SecurityProperties;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * 并发登录导致session失效时，默认的处理策略
 * @author Evan Huang
 *
 */
public class ImoocExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {

	public ImoocExpiredSessionStrategy(SecurityProperties securityPropertie) {
		super(securityPropertie);
	}


	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		onSessionInvalid(event.getRequest(), event.getResponse());
	}

	@Override
	protected boolean isConcurrency() {
		return true;
	}

}
