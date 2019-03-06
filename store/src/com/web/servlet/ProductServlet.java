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
  * ��ѯ��Ʒ��Ϣ
  * @param request
  * @param response
  * @return
  * @throws ServletException
  * @throws IOException
  */
public String getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	 try {
		// ��ȡid
		String pid = request.getParameter("pid");
		
		// ��ȡproduct
		ProductService ps = new ProductServiceImpl();
		Product product = ps.getById(pid);
		//��product��ӵ�request����
		request.setAttribute("info",product);
		
	} catch (Exception e) {
   request.setAttribute("msg", "��ѯ��Ʒ��Ϣʧ��");
   return "/jsp/msg.jsp";
	}
	
    return "/jsp/product_info.jsp";
}

/**
 *       ��ҳ��ѯ��Ʒ
 * @param request
 * @param response
 * @return
 * @throws ServletException
 * @throws IOException
 */
public String findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PageBean<Product> pb;
	try {
		// ��ȡpageNumber cid pageSize
		int pageNumber = 1 ;
		
		try {
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		} catch (Exception e) {
		}
		int pageSize = 12 ;
		String cid = request.getParameter("cid");
		//����service  ��ҳ��ѯ��Ʒ���� 3�� ����ֵ pageBean
		ProductService ps = new ProductServiceImpl();
		pb = ps.findByPage(pageNumber,pageSize,cid);
		request.setAttribute("cid", cid);
	} catch (Exception e) {
	  request.setAttribute("mag", "��ҳ��ѯʧ��");
	  return "/jsp/msg.jsp";
	}
	
	//��pageBean ��ӵ�request���У�����ת����head.jsp��
	request.setAttribute("pb", pb);
	return "/jsp/product_list.jsp";
}
}
