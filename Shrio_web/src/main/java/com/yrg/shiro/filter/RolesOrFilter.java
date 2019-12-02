package com.yrg.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

/**
 * <p>ProjectName: Shrio_web</p>
 * <p>ClassName:RolesOrFilter</p>
 * @author yangrg
 * @time 2019年11月29日 上午10:29:47
 */
public class RolesOrFilter extends AuthorizationFilter{

	
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		
		Subject subject = getSubject(request, response);
		String[] roles = (String[]) mappedValue;
		if(roles == null||roles.length ==0) {
			return false;
		}
		for(String role:roles) {
			if(subject.hasRole(role)){
				return true;
			}
		}
		
		return false;
	}
	
	
	
}
