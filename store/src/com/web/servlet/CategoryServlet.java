package com.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.CategoryService;
import com.service.impl.CategoryServiceImpl;
import com.web.servlet.base.BaseServlet;


/**
 * ǰ̨����ģ��
 */

public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
  /**
   *   ��ѯ���з���
   * @param request
   * @param response
   * @return
   * @throws ServletException
   * @throws IOException
   */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // ������Ӧ����
		response.setContentType("text/html;charset=utf-8");
		
		// ����service����ѯ���з��࣬����ֵjson�ַ���
		CategoryService cs = new CategoryServiceImpl();
		String value;
		
		try {
			//��mysql��ȡ�б�
			value = cs.findAll();
			
			//��redis��ȡ�б�
			// value = cs.findAllFromRedis();
			//���ַ���д�ص������
		response.getWriter().write(value);
		} catch (Exception e) {
			
		}
		
		
		return null ;
	}

}
