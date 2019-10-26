/**
 * 
 */
package com.evan.security.core.validate.code;

import com.evan.security.core.validate.code.image.ImageCodeGenerator;
import com.evan.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.evan.security.core.validate.code.sms.SmsCodeGenerator;
import com.evan.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.evan.security.core.properties.SecurityProperties;

/**
 * 验证码相关的扩展点配置。配置在这里的bean，业务系统都可以通过声明同类型或同名的bean来覆盖安全
 * 模块默认的配置。
 * @author Evan Huang
 *
 */
@Configuration
public class ValidateCodeBeanConfig {

	@Autowired
	private SecurityProperties securityProperties;

	/**
	 * 图片验证码图片生成器
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
	public ValidateCodeGenerator imageValidateCodeGenerator() {
		ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
		codeGenerator.setSecurityProperties(securityProperties);
		return codeGenerator;
	}
	// 这里由于产生了多个ValidateCodeGenerate的实现类
	// 所以需要使用name来区分
	// 在注入的时候也需要用其他手段与该name相同的id注入才可以
	// 当然还有其他的方式。可能可以使用：不同的子接口来分离短信和图形接口
	// 比如 @Qualifier("imageCodeGenerate") 或则什么的参数名和这个相同
	@Bean("smsValidateCodeGenerator")
	@ConditionalOnMissingBean(name = "smsValidateCodeGenerator")
	// 注意方法名称：如果没有指定bean则按方法名称作为beanName返回
	public ValidateCodeGenerator smsCodeGenerate() {
		SmsCodeGenerator smsCodeGenerator = new SmsCodeGenerator();
		smsCodeGenerator.setSecurityProperties(securityProperties);
		return smsCodeGenerator;
	}

	/**
	 * 短信验证码发送器
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender smsCodeSender() {
		return new DefaultSmsCodeSender();
	}

}
