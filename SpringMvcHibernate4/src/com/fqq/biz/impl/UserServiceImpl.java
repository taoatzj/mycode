package com.fqq.biz.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fqq.biz.IUserService;
import com.fqq.dao.IUserDao;
import com.fqq.vo.User;


/**
 * 登陆业务实现类
 * @author GJ
 * @date   2015年4月15日
 */
@Transactional(readOnly=false)
@Service("userService")
public class UserServiceImpl implements IUserService{

	@Autowired
	IUserDao userDao;
	
	@Override
	public User login(String userName, String password) {
		return userDao.login(userName, password);
	}

	
	

}
