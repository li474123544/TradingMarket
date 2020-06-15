package com.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.enums.ResponseCodeEnum;
import com.web.pojo.User;
import com.web.service.UserService;

@RestController
@CrossOrigin
@RequestMapping(value = "user")
public class LoginController extends BaseController{

	
	@Autowired
	private UserService userService;
	@RequestMapping(value = "login",method = RequestMethod.POST)
	public String login( @RequestParam String userName,@RequestParam String passWord){
		// System.out.println(request.getQueryString());
		System.out.println(11111);
//		String username=(String) map.get("username");
//		String password=(String) map.get("password");
//		String username="";
//		String password="";
//		System.out.println(userName);
//		System.out.println(passWord);
//		User user=userService.login(userName,passWord);
//		if(user==null){
//			return this.ResponseJson("S", ResponseCodeEnum.SUCCESS, null);
//			
//		}else{
//			System.out.println("2222");
//			return null;
//		}
		return "";
	}
	
	@RequestMapping("registered")
	public String registered(User user){
		
		int i=userService.registered(user);
		return null;
	}
}
