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
	 * 生成订单
	 */
	public void save(Order order) throws Exception {
	
		try {
			// 获取dao
			OrderDao od = (OrderDao) BeanFactory.getBean("OrderDao") ; 
			// 1. 开启事务
		   //	DataSourceUtils.startTransaction();
			// 2 .向order表中插入一条
			od.save(order);
			// 3 .向ordesrItem表中插入n条orderItem
			for (OrderItem oi: order.getOrderItem()) {
				od.saveItem(oi) ;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 4 .事务控制
		//	DataSourceUtils.rollbackAndClose();
	//		throw e ;
		}
		
		
	}

	@Override
	/**
	 * 我的订单
	 * 分页展示订单详情
	 */
	public PageBean<Order> findMyOrdersByPage(int pageNumber, int pageSize, String uid) throws Exception {
		
		// 获取dao
		OrderDao od = (OrderDao) BeanFactory.getBean("OrderDao") ;
		// 1 .创建pageBean
		PageBean<Order> pb = new PageBean<>(pageNumber, pageSize);
		
		//2. 查询总条数 设置当前数据
	   int totalRecord = od.getTotalRecord(uid);
		pb.setTotalRecord(totalRecord);
		
		//3. 查询当前页数据  设置当前页数据
		List<Order> data = od.findMyOrdersByPage(pb,uid);
		
		pb.setData(data);
		return pb;
	}

	@Override
	/**
	 * 通过oid获取order
	 */
	public Order getById(String oid) throws Exception {
		OrderDao od = (OrderDao)BeanFactory.getBean("OrderDao");
		Order order = od.getById(oid) ;
		return order;
	}

	@Override
	/**
	 * 更新订单
	 */
	public void update(Order order) throws Exception {
		OrderDao od = (OrderDao) BeanFactory.getBean("OrderDao") ;
		od.update(order) ;
	}
	/**
	 * 查询订单
	 */
	@Override
	public List<Order> findOrderByState(String state) throws Exception {
		OrderDao od = (OrderDao) BeanFactory.getBean("OrderDao");
		List<Order> list = od.findOrderByState(state);
		return list;
	}

}
