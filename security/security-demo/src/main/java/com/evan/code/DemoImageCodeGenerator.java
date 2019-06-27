/**
 * 
 */
package com.evan.code;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.evan.security.core.validate.code.ImageCode;
import com.evan.security.core.validate.code.ValidateCodeGenerator;

/**
 * @author Evan Huang
 * 如果没注入这个Bean的话， 代表使用默认core模块的图形生成器
 */
//@Component("imageCodeGenerator")
//public class DemoImageCodeGenerator implements ValidateCodeGenerator {
//
//	@Override
//	public ImageCode generate(ServletWebRequest request) {
//		System.out.println("更高级的图形验证码生成代码");
//		return null;
//	}
//
//}
