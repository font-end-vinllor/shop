package com.web.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Product;
import com.service.ProductService;
import com.service.impl.ProductServiceImpl;
import com.web.servlet.base.BaseServlet;

/**
 * ������Ʒ ������Ʒ����
 */
public class IndexServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	@Override
		public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				
				// ����productService��ѯ������Ʒ ��������Ʒ
		
					 ProductService ps = new ProductServiceImpl();
					List<Product> hotList = ps.findHot();
					List<Product> newList = ps.findNew();
			
					  // ������list������request����
					request.setAttribute("hList", hotList);
					request.setAttribute("nList", newList);
		} catch (Exception e) {
			}
		
		 return "/jsp/index.jsp";
		}
}
