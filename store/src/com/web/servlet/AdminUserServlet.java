package com.web.servlet;


import com.domain.User;
import com.service.UserService;
import com.utils.BeanFactory;
import com.web.servlet.base.BaseServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ��̨����Ա��¼
 */

public class AdminUserServlet extends BaseServlet {
	 
	public String admin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�û���   ����
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//����service��ѯ
		UserService us = (UserService) BeanFactory.getBean("UserService");
		try {
			User user = us.login(username, password);
			if(user == null || !user.getName().equals("vinllor") || !user.getPassword().equals("123456")) {
				return "/admin/adminfail.jsp";
			}
			
		} catch (Exception e) {			
		}
		return "/admin/home.jsp";
	}

}
