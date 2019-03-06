package com.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.domain.User;
import com.service.UserService;
import com.utils.MailUtils;

public class UserServiceImpl implements UserService{
   /**
    * 用户注册
 * @throws MessagingException 
 * @throws AddressException 
    */
	@Override
	public int regist(User user) throws Exception {
		// 1. 调用DAO完成注册
		UserDao ud = new UserDaoImpl();
		return ud.save(user);
		// 2. 发送激活邮件
	//	String emailMsg = "恭喜"+user.getName()+"：成为我们商城的一员,<a href='http://localhost:8080/store/user?method=active&code="+user.getCode()+"'>点此激活</a>";
	//	MailUtils.sendMail(user.getEmail(), emailMsg);
	}

@Override
public User login(String username, String password) throws Exception {
	UserDao ud = new UserDaoImpl();
	return ud.getByUsernameAndPwd(username,password);
}

}
