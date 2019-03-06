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
    * �û�ע��
 * @throws MessagingException 
 * @throws AddressException 
    */
	@Override
	public int regist(User user) throws Exception {
		// 1. ����DAO���ע��
		UserDao ud = new UserDaoImpl();
		return ud.save(user);
		// 2. ���ͼ����ʼ�
	//	String emailMsg = "��ϲ"+user.getName()+"����Ϊ�����̳ǵ�һԱ,<a href='http://localhost:8080/store/user?method=active&code="+user.getCode()+"'>��˼���</a>";
	//	MailUtils.sendMail(user.getEmail(), emailMsg);
	}

@Override
public User login(String username, String password) throws Exception {
	UserDao ud = new UserDaoImpl();
	return ud.getByUsernameAndPwd(username,password);
}

}
