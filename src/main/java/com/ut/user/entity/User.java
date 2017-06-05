package com.ut.user.entity;

/**
 * 图书实体
 */
public class User {

	private long id;// 用户ID

	private String name;// 用户名称

	private String password;// 密码

	public User() {
	}

	public User(long id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



}
