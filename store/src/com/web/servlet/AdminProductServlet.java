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
 *��Ʒ����
 */

public class AdminProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
 /**
  * ��̨�����Ʒ
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
      * ��ѯ���ϼ���Ʒ�ں�̨չʾ
      * @param request
      * @param response
      * @return
      * @throws ServletException
      * @throws IOException
      */
    	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		try {
				// ����service�������ϼ���Ʒ ������List
				 ProductService ps = (ProductService) BeanFactory.getBean("ProductService") ;
				List<Product> list = ps.findUp();
				//��list������request�У�����ת����list.jsp
				request.setAttribute("list", list);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
    		
    		return "/admin/product/list.jsp";
    	}

}
