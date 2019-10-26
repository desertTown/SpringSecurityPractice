/**
 * 
 */
package com.evan.security.rbac.repository.spec;


import com.evan.security.rbac.domain.Admin;
import com.evan.security.rbac.dto.AdminCondition;
import com.evan.security.rbac.repository.support.ImoocSpecification;
import com.evan.security.rbac.repository.support.QueryWraper;

/**
 * @author Evan Huang
 *
 */
public class AdminSpec extends ImoocSpecification<Admin, AdminCondition> {

	public AdminSpec(AdminCondition condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Admin> queryWraper) {
		addLikeCondition(queryWraper, "username");
	}

}
