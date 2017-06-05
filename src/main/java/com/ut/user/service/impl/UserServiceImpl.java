package com.ut.user.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ut.user.dao.UserDao;
import com.ut.user.entity.User;
import com.ut.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserDao userDao;

	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public long loginConfirm(String name, String password) {
		User tmpUser;
		tmpUser = this.userDao.getByNameAndPassword(name ,password);
		if(tmpUser != null){
			if(tmpUser.getName().length() != 0){
				return tmpUser.getId();
			}
		}
		return -1;
		
	}
	
	public long registerUser(String name, String password){
		User tmpUser = null;
		tmpUser = this.userDao.getByName(name);
		if(tmpUser == null){
			this.userDao.insertUser(name, password);
			return 1l;
		}
		return 0l;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public long updateUser(String name, String password, Long modId){
		User tmpUser = null;
		tmpUser = this.userDao.getByName(name);
		if(tmpUser == null){
			this.userDao.updateUser(name, password, modId);
			return 1l;
		}
		return 0l;
	}
	
	
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public User getById(long bookId) {
		return this.userDao.queryById(bookId);
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<User> getList() {
		return this.userDao.queryAll(0, 1000);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Long add(User entity) {
		return this.userDao.add(entity);
	}


}
