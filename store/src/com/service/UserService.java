package com.service;

import com.domain.User;

public interface UserService {

	int regist(User user) throws Exception;

	User login(String username, String password) throws Exception;

}
