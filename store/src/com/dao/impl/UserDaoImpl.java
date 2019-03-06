package com.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.dao.UserDao;
import com.domain.User;
import com.utils.DataSourceUtils;

public class UserDaoImpl implements UserDao{
	/**
	 * 将用户添加到数据库
	 */
	@Override
	public int save(User user) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into user values(?,?,?,?,?,?,?,?);";
		int a = qr.update(sql, user.getUid(),user.getUsername(),user.getPassword(),user.getName(),
				user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex()
				);
	  return a;
	}
	/**
	 * 用户登录
	 * @throws Exception 
	 */
	@Override
	public User getByUsernameAndPwd(String username, String password) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where username=? and password=? limit 1";
		return qr.query(sql,new BeanHandler<>(User.class),username,password);
		
	}

}
