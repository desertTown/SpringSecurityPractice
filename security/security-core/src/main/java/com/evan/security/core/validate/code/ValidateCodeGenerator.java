/**
 * 
 */
package com.evan.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码生成器
 * @author Evan Huang
 *
 */
public interface ValidateCodeGenerator {

	/**
	 * 生成校验码
	 * @param request
	 * @return
	 */
	ValidateCode generate(ServletWebRequest request);
	
}
