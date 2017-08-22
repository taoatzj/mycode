/**  
 * Project Name:boot-mybatis-mapper-demo  
 * File Name:UserServiceImpl.java  
 * Package Name:com.test.demo.service.impl  
 * Date:2017年6月22日下午2:32:29  
 *  
*/  
  
package com.test.demo.service.impl;  

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.test.demo.entity.User;
import com.test.demo.entity.UserQuery;
import com.test.demo.mapper.UserMapper;
import com.test.demo.service.UserService;

/**  
 * ClassName:UserServiceImpl <br/>  
 * Date:     2017年6月22日 下午2:32:29 <br/>  
 * @author   ztd  
 * @version    
 * @see        
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper mapper;
	
	@Override
	public User findByName(String name) {
		UserQuery query = new UserQuery();
		query.createCriteria().andNameLike("%" + name + "%");
		List<User> records = mapper.selectByExample(query);
		if(CollectionUtils.isEmpty(records)) {
			return null;
		}
		return records.get(0);
	}

}
  
