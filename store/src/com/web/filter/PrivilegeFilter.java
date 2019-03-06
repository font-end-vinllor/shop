package com.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.User;

public class PrivilegeFilter implements Filter {
   
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	//ǿת
		
		HttpServletRequest  req = (HttpServletRequest) request ;
		HttpServletResponse resp = (HttpServletResponse) response ;
		//�߼�
		User user = (User) req.getSession().getAttribute("user") ;
		if(user == null) {
			req.setAttribute("msg", "����ȥ��¼��");
			req.getRequestDispatcher("/jsp/msg.jsp").forward(req, resp);
			return ;
		}
		
		//����
		chain.doFilter(req, resp);
	}

}
