package com.yrg.shirodemo.test;

import javax.sql.DataSource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;

public class JdbcRealmTest {
	
	DruidDataSource dataSource = new DruidDataSource();
	{
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/shirotest?serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
	}
	
	@Test
	public void testAuthentication() {

		//创建JdbcRealm对象
		JdbcRealm jdbcRealm = new JdbcRealm();	
		
		jdbcRealm.setDataSource(dataSource);
		jdbcRealm.setPermissionsLookupEnabled(true);//设置权限开关，只有设置成true才能查询权限
		
		//查询密码
		String sql = "select password from test_user where username = ?";
		jdbcRealm.setAuthenticationQuery(sql);
		
		//查询role
		String roleSql = "select role_name from test_user_role where username = ?";
		jdbcRealm.setUserRolesQuery(roleSql);
		
		//查询权限
		String permission  = "select permissions from test_role_permission where role_name = ?";
		jdbcRealm.setPermissionsQuery(permission);
		
		//1.构建SecurityManager环境
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		defaultSecurityManager.setRealm(jdbcRealm);
		//2.主体提交认证
		SecurityUtils.setSecurityManager(defaultSecurityManager);//设置Security Manager环境
		Subject subject = SecurityUtils.getSubject();//获得主体
		
		//UsernamePasswordToken token = new UsernamePasswordToken("Mark","123456");//设置要进行认证的用户
		UsernamePasswordToken token = new UsernamePasswordToken("xiaoming","654321");
		subject.login(token);//	登录，提交认证	
		
		System.out.println("isAuthenticated():"+subject.isAuthenticated());//判断是否认证成功
		
		//subject.checkRole("admin");
		subject.checkRole("user");

//		
//		subject.checkRoles("admin","user");
//		
		subject.checkPermission("user:delete");
	}	
		
}
