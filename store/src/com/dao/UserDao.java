package com.dao;

import com.domain.User;

public interface UserDao {

	int save(User user) throws Exception;

	User getByUsernameAndPwd(String username, String password) throws Exception;
}
