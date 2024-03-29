/**
 * 
 */
package com.evan.security.rbac.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Evan Huang
 *
 */
public interface RbacService {
	
	boolean hasPermission(HttpServletRequest request, Authentication authentication);

}
