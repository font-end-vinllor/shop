package com.web.servlet.base;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ͨ�õ�servlet
 */

public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. ��ȡ��������
		String mName = request.getParameter("method");
		
		if(mName == null || mName.trim().length() == 0) {
			mName = "index"; 
		}
		// 2. ��ȡ��������
		
		try {
			Method method = this.getClass().getMethod(mName, HttpServletRequest.class,HttpServletResponse.class);
			
		// 3. �÷���ִ�У����շ���ֵ
			String path = (String) method.invoke(this,request, response);
		//4 . �жϷ���ֵ�Ƿ�Ϊ�գ���Ϊ�գ�ͳһ����ת��
			
			if(path != null) {
				request.getRequestDispatcher(path).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}
  public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	 response.setContentType("text/html;charset=utf-8");
	  response.getWriter().write("�ף�����");
	  return null;
  }
  
}
