package com.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.CategoryService;
import com.service.impl.CategoryServiceImpl;
import com.web.servlet.base.BaseServlet;


/**
 * 前台分类模块
 */

public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
  /**
   *   查询所有分类
   * @param request
   * @param response
   * @return
   * @throws ServletException
   * @throws IOException
   */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // 设置响应编码
		response.setContentType("text/html;charset=utf-8");
		
		// 调用service，查询所有分类，返回值json字符串
		CategoryService cs = new CategoryServiceImpl();
		String value;
		
		try {
			//从mysql获取列表
			value = cs.findAll();
			
			//从redis获取列表
			// value = cs.findAllFromRedis();
			//将字符串写回到浏览器
		response.getWriter().write(value);
		} catch (Exception e) {
			
		}
		
		
		return null ;
	}

}
