/**
 * 
 */
package com.evan.security.core.properties;

/**
 * 验证码配置
 * @author Evan Huang
 *
 */
public class ValidateCodeProperties {

	/**
	 * 图片验证码配置
	 */
	private ImageCodeProperties image = new ImageCodeProperties();

	/**
	 * 短信验证码配置
	 */
	private SmsCodeProperties sms = new SmsCodeProperties();

	public SmsCodeProperties getSms() {
		return sms;
	}

	public void setSms(SmsCodeProperties sms) {
		this.sms = sms;
	}

	public ImageCodeProperties getImage() {
		return image;
	}

	public void setImage(ImageCodeProperties image) {
		this.image = image;
	}
	
}
