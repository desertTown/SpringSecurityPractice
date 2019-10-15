/**
 * 
 */
package com.evan.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * @author Evan Huang
 *
 */
public interface SocialAuthenticationFilterPostProcessor {
	
	void process(SocialAuthenticationFilter socialAuthenticationFilter);

}
