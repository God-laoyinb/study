package com.yrg.shirodemo.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author 74578
 *
 */
public class AuthenticationTest {
	
	SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
	
	@Before
	public void addUser() {
		//simpleAccountRealm.addAccount("Mark", "123456");//初始化一个数据
		simpleAccountRealm.addAccount("Mark", "123456","admin","user");//初始化一个数据，赋予一个或多个权限
	}
	
	@Test
	public void testAuthentication() {
		//1.构建SecurityManager环境
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		defaultSecurityManager.setRealm(simpleAccountRealm);//将simpleAccountRealm设置到环境中来
		//2.主体提交认证
		SecurityUtils.setSecurityManager(defaultSecurityManager);//设置Security Manager环境
		Subject subject = SecurityUtils.getSubject();//获得主体
		
		UsernamePasswordToken token = new UsernamePasswordToken("Mark","123456");//设置要进行认证的用户
		subject.login(token);//	登录，提交认证	
		
		System.out.println("isAuthenticated():"+subject.isAuthenticated());//判断是否认证成功
		
//		subject.logout();//退出
//		System.out.println("isAuthenticated():"+subject.isAuthenticated());//判断是否认证成功
		
		//subject.checkRole("admin");//检查一个权限
		subject.checkRoles("admin","user");//检查多个权限
	}
}
