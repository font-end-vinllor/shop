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
 * 购物车
 */

public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
   
	
	public String clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取购物车清空
		getCart(request).clearCart();
		
		return "/jsp/cart.jsp";
	}
	/**
	 * 从购物车移除某件商品
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取pid
		 String pid = request.getParameter("pid");
		//获取购物车删除物品
		 getCart(request).removeFromCart(pid);
		 //重定向
	//	 response.sendRedirect("/jsp/cart.jsp");
		return "/jsp/cart.jsp";
	}
	/**
	 * 添加物品到购物车
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add2cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			// 获取pid count
			String pid = request.getParameter("pid");
			int count = Integer.parseInt(request.getParameter("count"));
			
			//封装cartitem
			//调用service获取product
			ProductService ps = (ProductService) BeanFactory.getBean("ProductService");
			Product product = ps.getById(pid);
			
  
			//创建cartitem
			CartItem item = new CartItem(product, count);
			
			//获取购物车
			Cart cart = getCart(request);
			cart.add2cart(item);
			//重定向
		//	response.sendRedirect("/jsp/cart.jsp");
		}  catch (Exception e) {	
			request.setAttribute("msg", "加入购物车失败");
			return "/jsp/msg.jsp";
		}
		return "/jsp/cart.jsp";
	}

 /**
  * 获取购物车
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
