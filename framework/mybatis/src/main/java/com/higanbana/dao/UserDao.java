package com.higanbana.dao;

import com.higanbana.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 陈明
 * @date 2020/3/25 17:17
 */
public interface UserDao
{
	// 1、 根据用户ID查询用户信息
	User findUserById(int id) throws Exception;
	
	// 2、 根据用户名称模糊查询用户列表
	List<User> findUsersByName( String name) throws Exception;
	
	// 3、 添加用户
	void insertUser(User user) throws Exception;
	
	
}
