package com.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.dao.OrderDao;
import com.domain.Order;
import com.domain.OrderItem;
import com.domain.PageBean;
import com.service.OrderService;
import com.utils.BeanFactory;
import com.utils.DataSourceUtils;

public class OrderServiceImpl implements OrderService {

	@Override
	/**
	 * ���ɶ���
	 */
	public void save(Order order) throws Exception {
	
		try {
			// ��ȡdao
			OrderDao od = (OrderDao) BeanFactory.getBean("OrderDao") ; 
			// 1. ��������
		   //	DataSourceUtils.startTransaction();
			// 2 .��order���в���һ��
			od.save(order);
			// 3 .��ordesrItem���в���n��orderItem
			for (OrderItem oi: order.getOrderItem()) {
				od.saveItem(oi) ;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 4 .�������
		//	DataSourceUtils.rollbackAndClose();
	//		throw e ;
		}
		
		
	}

	@Override
	/**
	 * �ҵĶ���
	 * ��ҳչʾ��������
	 */
	public PageBean<Order> findMyOrdersByPage(int pageNumber, int pageSize, String uid) throws Exception {
		
		// ��ȡdao
		OrderDao od = (OrderDao) BeanFactory.getBean("OrderDao") ;
		// 1 .����pageBean
		PageBean<Order> pb = new PageBean<>(pageNumber, pageSize);
		
		//2. ��ѯ������ ���õ�ǰ����
	   int totalRecord = od.getTotalRecord(uid);
		pb.setTotalRecord(totalRecord);
		
		//3. ��ѯ��ǰҳ����  ���õ�ǰҳ����
		List<Order> data = od.findMyOrdersByPage(pb,uid);
		
		pb.setData(data);
		return pb;
	}

	@Override
	/**
	 * ͨ��oid��ȡorder
	 */
	public Order getById(String oid) throws Exception {
		OrderDao od = (OrderDao)BeanFactory.getBean("OrderDao");
		Order order = od.getById(oid) ;
		return order;
	}

	@Override
	/**
	 * ���¶���
	 */
	public void update(Order order) throws Exception {
		OrderDao od = (OrderDao) BeanFactory.getBean("OrderDao") ;
		od.update(order) ;
	}
	/**
	 * ��ѯ����
	 */
	@Override
	public List<Order> findOrderByState(String state) throws Exception {
		OrderDao od = (OrderDao) BeanFactory.getBean("OrderDao");
		List<Order> list = od.findOrderByState(state);
		return list;
	}

}
