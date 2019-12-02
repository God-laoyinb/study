package com.yrg.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yrg.shiro.vo.User;

@Controller
public class UserController {
	
	
	@RequestMapping(value = "/subLogin",method = RequestMethod.POST)
	@ResponseBody
	public String subLogin(User user) {
	//	SecurityUtils.setSecurityManager(securityManager);
		Subject subject  = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
		try {
			token.setRememberMe(user.isRemermberMe());
			subject.login(token);
		} catch (AuthenticationException e) {
			return e.getMessage();
		}
		if(subject.hasRole("admin")) {
			return "hava admin";
		}
		else {
			return "don't have admin";
		}
		//return "Login Success";
	}
	
	//@RequiresRoles("admin") //需要admin的权限才能访问testrole方法
	@RequestMapping(value="/testRole",method = RequestMethod.GET)
	@ResponseBody
	public String testRole() {
		return "testrole success";
	}
	
	//@RequiresPermissions("xxx")//可以添加数组形式
	//@RequiresRoles("admin1")//需要admin1的权限才能访问testrelo1方法
	@RequestMapping(value="/testRole1",method = RequestMethod.GET)
	@ResponseBody
	public String testRole1() {
		return "testrole1 success";
	}
	
	@RequestMapping(value="/testPerms",method = RequestMethod.GET)
	@ResponseBody
	public String testPerms() {
		return "testPerms success";
	}
	@RequestMapping(value="/testPerms1",method = RequestMethod.GET)
	@ResponseBody
	public String testPerms1() {
		return "testPerms1 success";
	}
}
