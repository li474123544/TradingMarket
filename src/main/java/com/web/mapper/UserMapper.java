package com.web.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.pojo.User;

@Mapper
public interface UserMapper {

	User login(String username, String password);

}
