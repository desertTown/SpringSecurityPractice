/**
 * 
 */
package com.evan.security.rbac.web.controller.support;

/**
 * @author Evan Huang
 *
 */
public class SimpleResponse {
	
	public SimpleResponse(Object content){
		this.content = content;
	}
	
	private Object content;

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
	
}
