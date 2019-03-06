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
 * 用户模块
 */

public class UserServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 注册
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 封装对象
	  User user = new User();
	  
	
	  try {
		BeanUtils.populate(user, request.getParameterMap());
	  //1.1 手动封装UID
		user.setUid(UUIDUtils.getId());
	

		// 2.调用service完成注册
		   UserService us = new UserServiceImpl();
		   int row = us.regist(user);
		
		// 3. 页面转发 提示信息
		   if(row != 0) {
		   request.setAttribute("msg", "用户注册成功，可以登录了");
		   }
	  } catch (Exception e) {
		e.printStackTrace();
		// 转发到msg.jsp
		request.setAttribute("msg", "用户注册失败");
		return "/jsp/msg.jsp";
	  }
		
		return "/jsp/msg.jsp";
	}
	
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// 调用service完成登录返回user
		UserService us = new UserServiceImpl();
		User user;
		try {
			user = us.login(username,password);
		
		
		// 判断user
		if(user == null) {
			request.setAttribute("msg", "用户名和密码不般配");
			return "/jsp/login.jsp";
		}
		// 登录成功 ，保存用户状态
		request.getSession().setAttribute("user", user);
		//记住用户名
		
		if(Constant.SAVE_NAME.equals(request.getParameter("savename"))) {
			Cookie c = new Cookie("saveName",URLEncoder.encode(username, "utf-8"));
			c.setMaxAge(Integer.MAX_VALUE);
			c.setPath(request.getContextPath()+"/");
			response.addCookie(c);
		}
		//跳转到index.jsp
		response.sendRedirect(request.getContextPath());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "登录失败");
			return "/jsp/msg.jsp";
		}
		return null ;
	}
	/**
	 * 跳转到登录页面
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
   * 跳转到注册页面
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
	 * 退出
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
