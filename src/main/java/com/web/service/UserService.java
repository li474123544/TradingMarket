package com.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.enums.ResponseCodeEnum;
import com.web.mapper.UserMapper;
import com.web.pojo.User;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public User login(String username, String password) {
		
		User user=userMapper.login(username,password);
		return user;
	}

	public int registered(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

}
