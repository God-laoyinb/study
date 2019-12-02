package com.yrg.shirodemo.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class IniRealmTest {
	

	@Test
	public void testAuthentication() {

		//创建IniRealm对象
		IniRealm iniRealm = new IniRealm("classpath:user.ini");
		
		//1.构建SecurityManager环境
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		defaultSecurityManager.setRealm(iniRealm);
		//2.主体提交认证
		SecurityUtils.setSecurityManager(defaultSecurityManager);//设置Security Manager环境
		Subject subject = SecurityUtils.getSubject();//获得主体
		
		UsernamePasswordToken token = new UsernamePasswordToken("Mark","123456");//设置要进行认证的用户
		subject.login(token);//	登录，提交认证	
		
		System.out.println("isAuthenticated():"+subject.isAuthenticated());//判断是否认证成功
		
		subject.checkRole("admin");
		
		//subject.checkPermission("user:insert");
		//subject.checkPermission("user:update");
		subject.checkPermission("user:delete");
	
	}
}
