package com.yrg.shiro.realm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class CustomRealm extends AuthorizingRealm{
	
	Map<String,String> userMap = new HashMap<String, String>(16);
	{
		Md5Hash md5Hash = new Md5Hash("123456","Mark");//"Mark"是盐
		userMap.put("Mark",md5Hash.toString());
		super.setName("customRealm");
	}
	/**
	 * 做授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		String username  = (String) principals.getPrimaryPrincipal();
		Set<String> roles = getRolesByUserName(username);
		
		Set<String> permissions = getPermissionByUserName(username);
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.setStringPermissions(permissions);
		simpleAuthorizationInfo.setRoles(roles);
		
		return simpleAuthorizationInfo;
	}
	
	private Set<String> getPermissionByUserName(String username) {
		Set<String> sets = new HashSet<String>();
 		sets.add("user:delete");
 		sets.add("user:add");
		return sets;
	}

	private Set<String> getRolesByUserName(String username) {
		Set<String> sets = new HashSet<String>();
 		sets.add("admin");
 		sets.add("user");
		return sets;
	}

	/**
	 * 做认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		//1.从主体传过来的认证信息中，获得用户名
		String username = (String) token.getPrincipal();
		
		//通过用户名到数据库中获取凭证
		String password =getPasswordByUserName(username);
		if(password==null) {
			return null;
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo("Mark",password,"customRealm");// 创建一个返回对象
		
		authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("Mark"));
		return authenticationInfo;
	}

	/**
	 * 模拟数据库
	 * @param username
	 * @return
	 */
	private String getPasswordByUserName(String username) {
		
		return userMap.get(username);
	}

}
