package com.web.servlet;

import com.constant.Constant;
import com.domain.Cart;
import com.domain.CartItem;
import com.domain.Order;
import com.domain.OrderItem;
import com.domain.PageBean;
import com.domain.User;
import com.service.OrderService;
import com.utils.BeanFactory;
import com.utils.PaymentUtil;
import com.utils.UUIDUtils;
import com.web.servlet.base.BaseServlet;
import java.io.IOException;
import java.sql.Date;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ����ģ��
 */

public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	 
	
	
	public String getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// ��ȡoid
			String oid = request.getParameter("oid");
			//����service��ȡorder
			OrderService os = (OrderService) BeanFactory.getBean("OrderService") ; 
			Order order = os.getById(oid) ;
			//����ת��
			request.setAttribute("order", order);
			return "/jsp/order_info.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "��ѯ�ö�����Ϣʧ�ܣ�");
			return "/jsp/msg.jsp" ;
		}
	}
	 /**
	  * ��ҳչʾ��������
	  */
	public String findMyOrdersByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. ��ȡpageNumber  ����pageSize  ��ȡuserid
			int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			int pageSize = 3 ;			
			User user = (User)request.getSession().getAttribute("user");
			if(user == null) {
				request.setAttribute("msg", "���ȵ�¼!");
				return "/jsp/msg.jsp";
			}
						// 2. ����service��ȡ��ǰҳ���������� pageBean
			OrderService os = (OrderService) BeanFactory.getBean("OrderService");
			PageBean<Order>  bean = os.findMyOrdersByPage(pageNumber,pageSize,user.getUid());
			//3. ��pageBean����request���У�����ת����order_list.jsp
			request.setAttribute("pb", bean);
			return "/jsp/order_list.jsp";
		  
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "��ȡ�ҵĶ���ʧ�ܣ�");
			return "/jsp/msg.jsp";
		}
		
	}
	
	/**
		 * ���ɶ���
		 * @param request
		 * @param response
		 * @return
		 * @throws ServletException
		 * @throws IOException
		 */
    	public String save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		try {// ��ȡUser
    		User user = (User) request.getSession().getAttribute("user");
    		
    		if(user == null) {
    			request.setAttribute("msg", "���ȵ�¼����");
    			return "/jsp/msg.jsp";
    		}
    		//��ȡ���ﳵ
    		Cart cart = (Cart) request.getSession().getAttribute("cart");
    		
    		//��װ��������
    		Order order = new Order();
    		// ���ö���id
    		order.setOid(UUIDUtils.getId());
    		// ���ö���ʱ��
    		order.setOrdertime(new Date(System.currentTimeMillis()));
    		//���ö����ܶ�
    		order.setTotal(cart.getTotal());
    		//���ö���״̬
    		order.setState(Constant.ORDER_WEIFUKUAN);
    		//����User 
    		order.setUid(user);
    		//���ö������б�
    		for (CartItem ci : cart.getItemMap().values()) {
				//1. ��װ�������б�
    			OrderItem oi = new OrderItem();
    			//���ö������id
    			oi.setItemid(UUIDUtils.getId());
    			//������Ʒ
    			oi.setProduct(ci.getProduct());
    			//���ù��������
    			oi.setCount(ci.getCount());
    			//����С��
    			oi.setSubtotal(ci.getSubtotal());
    			//���������ĸ�����
    			oi.setOrder(order);
    			// 2. ���붩��
    			order.getOrderItem().add(oi) ;
    			request.setAttribute("order", order);
    		}
    		
				// ͨ��orderservice ��ɱ������
				OrderService os = (OrderService) BeanFactory.getBean("OrderService") ;
				os.save(order);
			} catch (Exception e) {
			}
    		//����ת����order_info 
    		return "/jsp/order_info.jsp";
    	}
    	/**
    	 * ֧��
    	 */
 
    	public String pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		try {
				//���ܲ���
				String address=request.getParameter("address");
				String name=request.getParameter("name");
				String telephone=request.getParameter("telephone");
				String oid=request.getParameter("oid");
				
				
				//ͨ��id��ȡorder
				OrderService s=(OrderService) BeanFactory.getBean("OrderService");
				Order order = s.getById(oid);
				
				order.setAddress(address);
				order.setName(name);
				order.setTelephone(telephone);
				
				//����order
				s.update(order);
				

				// ��֯����֧����˾��Ҫ��Щ����
				String pd_FrpId = request.getParameter("pd_FrpId");
				String p0_Cmd = "Buy";
				String p1_MerId = ResourceBundle.getBundle("merchantInfo").getString("p1_MerId");
				String p2_Order = oid;
				String p3_Amt = "0.01";
				String p4_Cur = "CNY";
				String p5_Pid = "";
				String p6_Pcat = "";
				String p7_Pdesc = "";
				// ֧���ɹ��ص���ַ ---- ������֧����˾����ʡ��û�����
				// ������֧�����Է�����ַ
				String p8_Url = ResourceBundle.getBundle("merchantInfo").getString("responseURL");
				String p9_SAF = "";
				String pa_MP = "";
				String pr_NeedResponse = "1";
				// ����hmac ��Ҫ��Կ
				String keyValue = ResourceBundle.getBundle("merchantInfo").getString("keyValue");
				String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
						p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
						pd_FrpId, pr_NeedResponse, keyValue);
  	
				
				//���͸�������
				StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
				sb.append("p0_Cmd=").append(p0_Cmd).append("&");
				sb.append("p1_MerId=").append(p1_MerId).append("&");
				sb.append("p2_Order=").append(p2_Order).append("&");
				sb.append("p3_Amt=").append(p3_Amt).append("&");
				sb.append("p4_Cur=").append(p4_Cur).append("&");
				sb.append("p5_Pid=").append(p5_Pid).append("&");
				sb.append("p6_Pcat=").append(p6_Pcat).append("&");
				sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
				sb.append("p8_Url=").append(p8_Url).append("&");
				sb.append("p9_SAF=").append(p9_SAF).append("&");
				sb.append("pa_MP=").append(pa_MP).append("&");
				sb.append("pd_FrpId=").append(pd_FrpId).append("&");
				sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
				sb.append("hmac=").append(hmac);
				
				response.sendRedirect(sb.toString());
			} catch (Exception e) {
			  e.printStackTrace();
			  request.setAttribute("msg", "֧��ʧ�ܣ�");
			  return "/jsp/msg.jsp";
			}
    		
    		return null;
    	}
    	/**
    	 *�ص�
    	 * @param request
    	 * @param response
    	 * @return
    	 * @throws Exception
    	 */
    	public String callback(HttpServletRequest request,HttpServletResponse response) throws Exception{
    		String p1_MerId = request.getParameter("p1_MerId");
    		String r0_Cmd = request.getParameter("r0_Cmd");
    		String r1_Code = request.getParameter("r1_Code");
    		String r2_TrxId = request.getParameter("r2_TrxId");
    		String r3_Amt = request.getParameter("r3_Amt");
    		String r4_Cur = request.getParameter("r4_Cur");
    		String r5_Pid = request.getParameter("r5_Pid");
    		String r6_Order = request.getParameter("r6_Order");
    		String r7_Uid = request.getParameter("r7_Uid");
    		String r8_MP = request.getParameter("r8_MP");
    		String r9_BType = request.getParameter("r9_BType");
    		String rb_BankId = request.getParameter("rb_BankId");
    		String ro_BankOrderId = request.getParameter("ro_BankOrderId");
    		String rp_PayDate = request.getParameter("rp_PayDate");
    		String rq_CardNo = request.getParameter("rq_CardNo");
    		String ru_Trxtime = request.getParameter("ru_Trxtime");
    		// ���У�� --- �ж��ǲ���֧����˾֪ͨ��
    		String hmac = request.getParameter("hmac");
    		String keyValue = ResourceBundle.getBundle("merchantInfo").getString(
    				"keyValue");

    		// �Լ����������ݽ��м��� --- �Ƚ�֧����˾������hamc
    		boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
    				r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
    				r8_MP, r9_BType, keyValue);
    		if (isValid) {
    			// ��Ӧ������Ч
    			if (r9_BType.equals("1")) {
    				// ������ض���
    				System.out.println("111");
    				request.setAttribute("msg", "���Ķ�����Ϊ:"+r6_Order+",���Ϊ:"+r3_Amt+"�Ѿ�֧���ɹ�,�ȴ�����~~");
    				
    			} else if (r9_BType.equals("2")) {
    				// ��������Ե� --- ֧����˾֪ͨ��
    				System.out.println("����ɹ���222");
    				// �޸Ķ���״̬ Ϊ�Ѹ���
    				// �ظ�֧����˾
    				response.getWriter().print("success");
    			}
    			
    			//�޸Ķ���״̬
    			OrderService s=(OrderService) BeanFactory.getBean("OrderService");
    			Order order = s.getById(r6_Order);
    			order.setState(1);
    			
    			s.update(order);
    			
    		} else {
    			// ������Ч
    			System.out.println("���ݱ��۸ģ�");
    		}
    		
    		
    		return "/jsp/msg.jsp";
    		
    	}
}
