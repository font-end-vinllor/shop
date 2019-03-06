package com.web.servlet;

import com.domain.PageBean;
import com.domain.Product;
import com.service.ProductService;
import com.service.impl.ProductServiceImpl;
import com.web.servlet.base.BaseServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
 /**
  * 查询商品信息
  * @param request
  * @param response
  * @return
  * @throws ServletException
  * @throws IOException
  */
public String getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	 try {
		// 获取id
		String pid = request.getParameter("pid");
		
		// 获取product
		ProductService ps = new ProductServiceImpl();
		Product product = ps.getById(pid);
		//把product添加到request域中
		request.setAttribute("info",product);
		
	} catch (Exception e) {
   request.setAttribute("msg", "查询商品信息失败");
   return "/jsp/msg.jsp";
	}
	
    return "/jsp/product_info.jsp";
}

/**
 *       分页查询商品
 * @param request
 * @param response
 * @return
 * @throws ServletException
 * @throws IOException
 */
public String findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PageBean<Product> pb;
	try {
		// 获取pageNumber cid pageSize
		int pageNumber = 1 ;
		
		try {
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		} catch (Exception e) {
		}
		int pageSize = 12 ;
		String cid = request.getParameter("cid");
		//调用service  分页查询商品参数 3个 返回值 pageBean
		ProductService ps = new ProductServiceImpl();
		pb = ps.findByPage(pageNumber,pageSize,cid);
		request.setAttribute("cid", cid);
	} catch (Exception e) {
	  request.setAttribute("mag", "分页查询失败");
	  return "/jsp/msg.jsp";
	}
	
	//将pageBean 添加到request域中，请求转发到head.jsp中
	request.setAttribute("pb", pb);
	return "/jsp/product_list.jsp";
}
}
