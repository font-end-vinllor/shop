package com.web.servlet;

import com.domain.Product;
import com.service.CategoryService;
import com.service.ProductService;
import com.utils.BeanFactory;
import com.web.servlet.base.BaseServlet;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *商品管理
 */

public class AdminProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
 /**
  * 后台添加商品
  */
public String addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  	try {
		CategoryService ps = (CategoryService) BeanFactory.getBean("CategoryService");
		request.setAttribute("list", ps.findList());
	} catch (Exception e) {
	}
	return "/admin/product/add.jsp";
}
     /**
      * 查询已上架商品在后台展示
      * @param request
      * @param response
      * @return
      * @throws ServletException
      * @throws IOException
      */
    	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		try {
				// 调用service查找已上架商品 ，返回List
				 ProductService ps = (ProductService) BeanFactory.getBean("ProductService") ;
				List<Product> list = ps.findUp();
				//将list保存在request中，请求转发到list.jsp
				request.setAttribute("list", list);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
    		
    		return "/admin/product/list.jsp";
    	}

}
