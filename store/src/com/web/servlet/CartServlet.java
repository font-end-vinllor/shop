package com.web.servlet;

import com.domain.Cart;
import com.domain.CartItem;
import com.domain.Product;
import com.service.ProductService;
import com.utils.BeanFactory;
import com.web.servlet.base.BaseServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ���ﳵ
 */

public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
   
	
	public String clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡ���ﳵ���
		getCart(request).clearCart();
		
		return "/jsp/cart.jsp";
	}
	/**
	 * �ӹ��ﳵ�Ƴ�ĳ����Ʒ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡpid
		 String pid = request.getParameter("pid");
		//��ȡ���ﳵɾ����Ʒ
		 getCart(request).removeFromCart(pid);
		 //�ض���
	//	 response.sendRedirect("/jsp/cart.jsp");
		return "/jsp/cart.jsp";
	}
	/**
	 * �����Ʒ�����ﳵ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add2cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			// ��ȡpid count
			String pid = request.getParameter("pid");
			int count = Integer.parseInt(request.getParameter("count"));
			
			//��װcartitem
			//����service��ȡproduct
			ProductService ps = (ProductService) BeanFactory.getBean("ProductService");
			Product product = ps.getById(pid);
			
  
			//����cartitem
			CartItem item = new CartItem(product, count);
			
			//��ȡ���ﳵ
			Cart cart = getCart(request);
			cart.add2cart(item);
			//�ض���
		//	response.sendRedirect("/jsp/cart.jsp");
		}  catch (Exception e) {	
			request.setAttribute("msg", "���빺�ﳵʧ��");
			return "/jsp/msg.jsp";
		}
		return "/jsp/cart.jsp";
	}

 /**
  * ��ȡ���ﳵ
  * @param request
  * @return
  */
	private Cart getCart(HttpServletRequest request) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		
		if(cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	
}
