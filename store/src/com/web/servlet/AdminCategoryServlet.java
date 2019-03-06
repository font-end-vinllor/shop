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
 * ��̨����ģ��
 */
public class AdminCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
   /**
    * ��ӷ���
    * @param request
    * @param response
    * @return
    * @throws ServletException
    * @throws IOException
    */
public String save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		// ��װCategroy
		Category c = new Category();
		c.setCid(UUIDUtils.getId());
		c.setCname(request.getParameter("cname"));
		
		//����service�����ౣ�������ݿ� 
		CategoryService cd = (CategoryService) BeanFactory.getBean("CategoryService") ;
		cd.save(c);
		
		
		//�ض���
		response.sendRedirect(request.getContextPath()+"/adminCategoryServlet?method=findAll");
	} catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException() ;
	}
	return null;
}
	
	/**
	 * ��ת�����ҳ��
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
   * չʾ��̨����
   * @param request
   * @param response
   * @return
   * @throws ServletException
   * @throws IOException
   */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. ����service ��ȡ���з���
			CategoryService   cs = (CategoryService) BeanFactory.getBean("CategoryService");
			List<Category> list = cs.findList();
			//2.������ֵ����request���У�����ת��
			request.setAttribute("list", list);
			return "/admin/category/list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}
}
