package com.fqq.action;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fqq.biz.IUserService;
import com.fqq.util.JsonUtil;
import com.fqq.vo.User;

/**
 * 登陆入口
 * @author GJ
 * @date   2015年4月15日
 */
@Controller
@RequestMapping("/index")
public class UserController {
	 @Autowired
	 private IUserService userService;
	/**
	 * 方式一： 使用ModelAndView return new ModelAndView("redirect:/toList");
	 * 这样可以重定向到toList这个方法 方式二：返回String return "forward:index.jsp"; return
	 * "forward:user.do?method=reg5"; //转发 return
	 * "redirect:user.do?method=reg5"; //重定向 return
	 * "redirect:http://www.baidu.com"; //重定向
	 */

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void register(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> statuts = new HashMap<String, Object>();
		statuts.put("messageCode", 0);
		statuts.put("register", 0);
		statuts.put("register", 1);
		JsonUtil.toWriter(statuts, response);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request,
			HttpServletResponse response) {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		System.out.println(path);
		System.out.println(basePath);
		User user = userService.login(request.getParameter("userName"), request.getParameter("password"));
		if(user != null){
			return "index";
		}else{
			return "redirect:"+basePath+"login.jsp";
		}
	}
}
