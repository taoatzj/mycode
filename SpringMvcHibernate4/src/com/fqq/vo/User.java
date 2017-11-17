package com.fqq.vo;

import java.io.Serializable;

/**
 * 用户
 */
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//ID
	private Long id;
	// 用户名
	private String userName;
	// 密码
	private String password;
	// 等级
	private int level;
	// 密码
	private String nickName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


}
