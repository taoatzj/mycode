package com.ibeifeng.service;

import com.ibeifeng.po.User;

public interface UserService {
	//�û�ע��
	public boolean registerUser(User user);
	
	//�û���½
	public boolean loginUser(User user);
}
