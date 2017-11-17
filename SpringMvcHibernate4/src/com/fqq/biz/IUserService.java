package com.fqq.biz;


import com.fqq.vo.User;


/**
 * 通用dao，包括基本的CRUD方法
 * @author Ljh
 *
 */

public interface IUserService {
 
	public User login(String userName,String passWord);
}
 
