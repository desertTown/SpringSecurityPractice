/**
 * 
 */
package com.evan.security.core.validate.code.sms;

/**
 * @author Evan Huang
 *
 */
public interface SmsCodeSender {
	
	void send(String mobile, String code);

}
