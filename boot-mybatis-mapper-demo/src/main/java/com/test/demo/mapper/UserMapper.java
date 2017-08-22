package com.test.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.test.demo.entity.User;
import com.test.demo.entity.UserQuery;

public interface UserMapper {

	int countByExample(UserQuery example);

	int deleteByExample(UserQuery example);

	int deleteByPrimaryKey(Long id);

	int insert(User record);

	int insertSelective(User record);

	List<User> selectByExample(UserQuery example);

	User selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") User record,
			@Param("example") UserQuery example);

	int updateByExample(@Param("record") User record,
			@Param("example") UserQuery example);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);
}