package com.domain;

import java.io.Serializable;

public class User implements Serializable{
  /**
   *  `uid` varchar(32) NOT NULL,
  `username` varchar(20) DEFAULT 'null',
  `password` varchar(20) DEFAULT 'null',
  
  `name` varchar(20) DEFAULT 'null',
  `email` varchar(30) DEFAULT 'null',
  `telephone` varchar(20) DEFAULT 'null',
  
  `birthday` date DEFAULT NULL,
  `sex` varchar(10) DEFAULT 'null',
  `state` int(11) DEFAULT NULL,
  
  `code` varchar(64) DEFAULT 'null',
   */
	private String uid;
	private String username;
	private String password;
	private String name;
	private String email;
	private String telephone;
	private String birthday;
	private String sex;

	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
