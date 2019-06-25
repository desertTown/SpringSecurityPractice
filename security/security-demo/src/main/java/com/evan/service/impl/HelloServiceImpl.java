/**
 * 
 */
package com.evan.service.impl;

import com.evan.service.HelloService;
import org.springframework.stereotype.Service;


/**
 * @author Evan Huang
 *
 */
@Service
public class HelloServiceImpl implements HelloService {

	/* (non-Javadoc)
	 * @see com.evan.service.HelloService#greeting(java.lang.String)
	 */
	@Override
	public String greeting(String name) {
		System.out.println("greeting");
		return "hello "+name;
	}

}
