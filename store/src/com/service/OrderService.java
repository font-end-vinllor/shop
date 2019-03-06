package com.service;

import java.util.List;

import com.domain.Order;
import com.domain.PageBean;

public interface OrderService {

	void save(Order order) throws Exception;

	PageBean<Order> findMyOrdersByPage(int pageNumber, int pageSize, String uid) throws Exception;

	Order getById(String oid) throws Exception;

	void update(Order order) throws Exception;

	List<Order> findOrderByState(String state) throws Exception;

}
