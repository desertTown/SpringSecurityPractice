/**
 * 
 */
package com.evan.security.rbac.repository;

import com.evan.security.rbac.domain.Resource;
import org.springframework.stereotype.Repository;

/**
 * @author Evan Huang
 *
 */
@Repository
public interface ResourceRepository extends ImoocRepository<Resource> {

	Resource findByName(String name);

}
