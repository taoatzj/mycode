/**  
 * Project Name:boot-mybatis-mapper-demo  
 * File Name:UserController.java  
 * Package Name:com.test.demo.controller  
 * Date:2017年6月22日下午2:36:03  
 *  
*/  
  
package com.test.demo.controller;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.demo.entity.User;
import com.test.demo.service.UserService;


/**  
 * ClassName:UserController <br/>  
 * Date:     2017年6月22日 下午2:36:03 <br/>  
 * @author   ztd  
 * @version    
 * @see        
 */
@RestController
public class UserController {
	@Autowired
	UserService service;
	
	@RequestMapping("/user")
	@ResponseBody
	public ResponseEntity<User> getUserByName(@RequestParam("name") String name) {
		return new ResponseEntity<User>(service.findByName(name), HttpStatus.OK);
	}
	
}
  
