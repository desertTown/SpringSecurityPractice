/**
 * 
 */
package com.evan.security.rbac.repository;

import com.evan.security.rbac.domain.Admin;
import org.springframework.stereotype.Repository;

/**
 * @author Evan Huang
 *
 */
@Repository
public interface AdminRepository extends ImoocRepository<Admin> {

	Admin findByUsername(String username);

}
