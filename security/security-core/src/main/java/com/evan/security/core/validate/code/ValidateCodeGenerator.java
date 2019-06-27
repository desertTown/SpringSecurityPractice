/**
 * 
 */
package com.evan.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author Evan Huang
 *
 */
public interface ValidateCodeGenerator {

	ImageCode generate(ServletWebRequest request);
	
}
