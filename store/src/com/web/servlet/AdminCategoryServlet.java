package com.web.servlet;

import com.domain.Category;
import com.service.CategoryService;
import com.utils.BeanFactory;
import com.utils.UUIDUtils;
import com.web.servlet.base.BaseServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台分类模块
 */
public class AdminCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
   /**
    * 添加分类
    * @param request
    * @param response
    * @return
    * @throws ServletException
    * @throws IOException
    */
public String save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		// 封装Categroy
		Category c = new Category();
		c.setCid(UUIDUtils.getId());
		c.setCname(request.getParameter("cname"));
		
		//调用service将分类保存在数据库 
		CategoryService cd = (CategoryService) BeanFactory.getBean("CategoryService") ;
		cd.save(c);
		
		
		//重定向
		response.sendRedirect(request.getContextPath()+"/adminCategoryServlet?method=findAll");
	} catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException() ;
	}
	return null;
}
	
	/**
	 * 跳转到添加页面
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return "/admin/category/add.jsp";
	}
	/**
   * 展示后台分类
   * @param request
   * @param response
   * @return
   * @throws ServletException
   * @throws IOException
   */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 调用service 获取所有分类
			CategoryService   cs = (CategoryService) BeanFactory.getBean("CategoryService");
			List<Category> list = cs.findList();
			//2.将返回值放入request域中，请求转发
			request.setAttribute("list", list);
			return "/admin/category/list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}
}
