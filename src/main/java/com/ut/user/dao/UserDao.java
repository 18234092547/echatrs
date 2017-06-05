package com.ut.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ut.user.entity.User;

public interface UserDao {

	/**
	 * 添加用户
	 * @param entity
	 * @return
	 */
	Long add(User entity);
	
	
	
	User getByName(@Param("user")String user);
	
	void insertUser(@Param("user")String user, @Param("password")String password);
	
	User getByNameAndPassword(@Param("user") String user, @Param("password")String password);
	
	/**
	 * 通过ID查询用户
	 * 
	 * @param id
	 * @return
	 */
	User queryById(long id);

	/**
	 * 分页查询所有用户
	 * 
	 * @param offset 查询起始位置
	 * @param limit 查询条数
	 * @return
	 */
	List<User> queryAll(@Param("offset") int offset, @Param("limit") int limit);



	void updateUser(@Param("user")String name, @Param("password")String password, @Param("userId")Long modId);

}
