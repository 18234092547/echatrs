package com.ut.user.service;

import java.util.List;

import com.ut.user.entity.User;

/**
 * 业务接口：站在"使用者"角度设计接口 三个方面：方法定义粒度，参数，返回类型（return 类型/异常）
 */
public interface UserService {
	
	Long add(User entity);


	User getById(long bookId);

	long loginConfirm(String username, String password);
	
	List<User> getList();


	long registerUser(String name, String password);

	long updateUser(String name, String password,Long userId);

}
