package com.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.constant.Constant;
import com.domain.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;
import com.utils.UUIDUtils;
import com.web.servlet.base.BaseServlet;

/**
 * �û�ģ��
 */

public class UserServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * ע��
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. ��װ����
	  User user = new User();
	  
	
	  try {
		BeanUtils.populate(user, request.getParameterMap());
	  //1.1 �ֶ���װUID
		user.setUid(UUIDUtils.getId());
	

		// 2.����service���ע��
		   UserService us = new UserServiceImpl();
		   int row = us.regist(user);
		
		// 3. ҳ��ת�� ��ʾ��Ϣ
		   if(row != 0) {
		   request.setAttribute("msg", "�û�ע��ɹ������Ե�¼��");
		   }
	  } catch (Exception e) {
		e.printStackTrace();
		// ת����msg.jsp
		request.setAttribute("msg", "�û�ע��ʧ��");
		return "/jsp/msg.jsp";
	  }
		
		return "/jsp/msg.jsp";
	}
	
	/**
	 * ��¼
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// ����service��ɵ�¼����user
		UserService us = new UserServiceImpl();
		User user;
		try {
			user = us.login(username,password);
		
		
		// �ж�user
		if(user == null) {
			request.setAttribute("msg", "�û��������벻����");
			return "/jsp/login.jsp";
		}
		// ��¼�ɹ� �������û�״̬
		request.getSession().setAttribute("user", user);
		//��ס�û���
		
		if(Constant.SAVE_NAME.equals(request.getParameter("savename"))) {
			Cookie c = new Cookie("saveName",URLEncoder.encode(username, "utf-8"));
			c.setMaxAge(Integer.MAX_VALUE);
			c.setPath(request.getContextPath()+"/");
			response.addCookie(c);
		}
		//��ת��index.jsp
		response.sendRedirect(request.getContextPath());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "��¼ʧ��");
			return "/jsp/msg.jsp";
		}
		return null ;
	}
	/**
	 * ��ת����¼ҳ��
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/login.jsp";
	}
  /**
   * ��ת��ע��ҳ��
   * @param request
   * @param response
   * @return
   * @throws ServletException
   * @throws IOException
   */
	public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "jsp/register.jsp";
	}
	/**
	 * �˳�
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath());
		return null;
	}
}
