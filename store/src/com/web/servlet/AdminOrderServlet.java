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
 * ��̨������
 */

public class AdminOrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
  
	/**
	 * ���������״̬��Ϊ�Ѹ����
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String updateState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//��ȡoid
			String oid = request.getParameter("oid");
			//����service��ȡ����
			OrderService os = (OrderService) BeanFactory.getBean("OrderService");
			Order order = os.getById(oid);
			
			// ���Ķ���״̬
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
	 * ��̨չʾ��������
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String showDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {		
			//���ַ���д��ҳ��ʱע�����
			response.setContentType("text/html;charset=utf-8");
			//��ȡoid
			String oid = request.getParameter("oid");
			//����service��ȡ����
			OrderService os = (OrderService) BeanFactory.getBean("OrderService");
			Order order = os.getById(oid);
			//��ȡ�����������б�д�������
			if(order != null) {
				//��ȡ�������б�
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
   * ����״̬��ѯ����
   * @param request
   * @param response
   * @return
   * @throws ServletException
   * @throws IOException
   */
	public String findOrderByState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//��ȡ����״̬
			String state = request.getParameter("state");
			//����Service��ȡ�����б�
			OrderService os = (OrderService) BeanFactory.getBean("OrderService");
			List<Order> list = os.findOrderByState(state);
			//��list��ӵ�request���У�����ת��
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("��ѯ����ʧ�ܣ�");
		}
		return "/admin/order/list.jsp";
	}

}
