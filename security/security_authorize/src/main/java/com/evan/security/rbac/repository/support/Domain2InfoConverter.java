/**
 * 
 */
package com.evan.security.rbac.repository.support;

import org.springframework.core.convert.converter.Converter;

/**
 * @author Evan Huang
 *
 */
public interface Domain2InfoConverter<T, I> extends Converter<T, I> {
	
}
