package com.web.pojo;

public class User {
 
 private Long Id;
 private String username;
 private String password;
 private String name;
 private Integer phone;
public Long getId() {
	return Id;
}
public void setId(Long id) {
	Id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Integer getPhone() {
	return phone;
}
public void setPhone(Integer phone) {
	this.phone = phone;
}
 
}
