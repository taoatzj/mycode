package com.test.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pagehelper.PageHelper;
import com.test.demo.entity.User;
import com.test.demo.entity.UserQuery;
import com.test.demo.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	UserMapper userMapper;

	@Test
	public void insert() {
		User record = new User();
		record.setName("dada");
		record.setAge(30);
		userMapper.insert(record);

	}

	@Test
	public void update() {
		User record = new User();
		record.setId(1l);
		record.setName("hehe");
		record.setAge(6);
		userMapper.updateByPrimaryKey(record);		
	}

	@Test
	public void delete() {
		// 分页查询
		userMapper.deleteByPrimaryKey(2l);
	}

	@Test
	public void queryList() {
		// 分页查询
		UserQuery query = new UserQuery();
		List<User> users = userMapper.selectByExample(query);
		users.forEach(user -> System.out.println(user.getName()));
	}

	@Test
	public void queryPage() {
		// 分页查询
		PageHelper.startPage(1, 3);
		UserQuery query = new UserQuery();
		List<User> users = userMapper.selectByExample(query);
		users.forEach(user -> System.out.println(user.getName()));
	}

}
