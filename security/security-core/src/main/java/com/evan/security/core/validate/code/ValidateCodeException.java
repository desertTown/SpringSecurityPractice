/**
 * 
 */
package com.evan.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Evan Huang
 *
 */
public class ValidateCodeException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7285211528095468156L;

	public ValidateCodeException(String msg) {
		super(msg);
	}

}
