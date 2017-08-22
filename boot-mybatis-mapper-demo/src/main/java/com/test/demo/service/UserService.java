/**  
 * Project Name:boot-mybatis-mapper-demo  
 * File Name:UserService.java  
 * Package Name:com.test.demo.service  
 * Date:2017年6月22日下午2:31:46  
 *  
*/  
  
package com.test.demo.service;  

import com.test.demo.entity.User;

/**  
 * ClassName:UserService <br/>  
 * Date:     2017年6月22日 下午2:31:46 <br/>  
 * @author   ztd  
 * @version    
 * @see        
 */
public interface UserService {
	User findByName(String name);
}
  
