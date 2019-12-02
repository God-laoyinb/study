package com.yrg.shiro.dao;

import java.util.List;

import com.yrg.shiro.vo.User;

public interface IUserDao {

	User getUserByUserName(String username);

	List<String> queryRolesByUserName(String username);

	List<String> queryPermissionByUserName(String username);

}
