package com.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.constant.Constant;
import com.domain.Order;
import com.domain.OrderItem;
import com.service.OrderService;
import com.utils.BeanFactory;
import com.utils.JsonUtil;
import com.web.servlet.base.BaseServlet;

/**
 * 后台管理订单
 */

public class AdminOrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
  
	/**
	 * 将待付款订单状态改为已付款订单
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String updateState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//获取oid
			String oid = request.getParameter("oid");
			//调用service获取订单
			OrderService os = (OrderService) BeanFactory.getBean("OrderService");
			Order order = os.getById(oid);
			
			// 更改订单状态
			if(order != null) {
				order.setState(Constant.ORDER_YIFUKUAN);
				os.update(order);
			}
		response.sendRedirect(request.getContextPath()+"/adminOrderServlet?method=findOrderByState&state=1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 后台展示订单详情
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String showDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {		
			//将字符串写回页面时注意编码
			response.setContentType("text/html;charset=utf-8");
			//获取oid
			String oid = request.getParameter("oid");
			//调用service获取订单
			OrderService os = (OrderService) BeanFactory.getBean("OrderService");
			Order order = os.getById(oid);
			//获取订单的详情列表，写回浏览器
			if(order != null) {
				//获取订单项列表
				List<OrderItem> list = order.getOrderItem();
				if(list != null) {
					request.setAttribute("orderitem", list);
					return "/admin/order/orderItem.jsp";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
  /**
   * 根据状态查询订单
   * @param request
   * @param response
   * @return
   * @throws ServletException
   * @throws IOException
   */
	public String findOrderByState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//获取订单状态
			String state = request.getParameter("state");
			//调用Service获取订单列表
			OrderService os = (OrderService) BeanFactory.getBean("OrderService");
			List<Order> list = os.findOrderByState(state);
			//将list添加到request域中，请求转发
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("查询订单失败！");
		}
		return "/admin/order/list.jsp";
	}

}
