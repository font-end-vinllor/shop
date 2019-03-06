package com.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.dao.OrderDao;
import com.domain.Order;
import com.domain.OrderItem;
import com.domain.PageBean;
import com.domain.Product;
import com.utils.DataSourceUtils;

public class OrderDaoImpl implements OrderDao {
	@Override
   /**
    *      保存订单到数据库
    */
	public void save(Order o) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "insert into orders values(?,?,?,?,?,?,?,?);";
		 qr.update(DataSourceUtils.getConnection(), sql,o.getOid(),o.getOrdertime(),o.getTotal(),o.getState(),o.getAddress(),o.getName(),o.getTelephone(),o.getUid().getUid()) ;
	
	}

	@Override
	/**
	 * 将订单项保存到数据库
	 */
	public void saveItem(OrderItem oi) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "insert into orderitem values(?,?,?,?,?);" ;
		qr.update(DataSourceUtils.getConnection(), sql,oi.getItemid(),oi.getCount(),oi.getSubtotal(),oi.getProduct().getPid(),oi.getOrder().getOid());			
	}

	@Override
	/**
	 * 获取订单的总条数
	 */
	public int getTotalRecord(String uid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from orders where userid = ?" ;
		return ((Long)qr.query(sql, new ScalarHandler(),uid)).intValue();
	}

	@Override
	/**
	 * 获取某用户的订单列表
	 */
	public List<Order> findMyOrdersByPage(PageBean<Order> pb, String u) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		//查询所有订单（基本信息）
		//String sql = "select * from orders where uid=? order by ordertime desc limit ?,?;";
		//List<Order> list = qr.query(sql, new BeanListHandler<>(Order.class), uid,pb.getStartIndex(),pb.getPageSize()) ;
       
		 String sql = "select * from orders where userid= ? order by ordertime desc limit ?,?;";
	      List<Order> list = qr.query(sql, new BeanListHandler<>(Order.class),u,pb.getStartIndex(),pb.getPageSize());
	       
		
		
		// 遍历订单集合，获取每一个订单，查询每个订单订单项
		for (Order order : list) {
			sql = "select * from orderitem oi,product p where oi.pid = p.pid and oi.oid=?;" ;
			List<Map<String,Object>> maplist = qr.query(sql, new MapListHandler(),order.getOid()) ;
			
			//  遍历maplist ,获取每一个订单项详情，封装成orderitem,将其加入当前订单的订单项列表
			for (Map<String,Object> map : maplist) {
				// 封装orderitem
				//a. 创建orderitem
				OrderItem oi = new OrderItem();
				// b. 封装orderitem
				BeanUtils.populate(oi, map);
				//手动封装product
				Product p = new Product() ;
				
				BeanUtils.populate(p, map);
				oi.setProduct(p);
				// 将orderitem加入order订单列表
				order.getOrderItem().add(oi) ;
				
			}
		}
		
		
		return list;
	}

	@Override
	/**
	 * 查询订单详情
	 */
	public Order getById(String oid) throws Exception {
		//获取订单
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource()) ;
		String sql = "select * from orders where oid =?" ;
		Order order = qr.query(sql, new BeanHandler<>(Order.class), oid) ;
		
		//查询订单详情
		sql = "select * from orderitem oi,product p where oi.pid = p.pid and oi.oid=?;" ;
		List<Map<String,Object>> list = qr.query(sql, new MapListHandler() , oid) ;
		
		for (Map<String, Object> map : list) {
			// 封装orderitem
			OrderItem oi = new OrderItem() ;
			BeanUtils.populate(oi, map);
			//手动封装product
			Product p = new Product() ;
			BeanUtils.populate(p, map);
			
			oi.setProduct(p);
			order.getOrderItem().add(oi) ;
		}
		return order ; 
	}

	@Override
	/**
	 * 更新订单
	 */
	public void update(Order order) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource()) ;
//		`oid` varchar(32) NOT NULL,
//		  `ordertime` datetime DEFAULT NULL,
//		  `total` double DEFAULT NULL
		
//		  `state` int(11) DEFAULT NULL,
//		  `address` varchar(30) DEFAULT NULL,
//		  `name` varchar(20) DEFAULT NULL,
		
//		  `telephone` varchar(20) DEFAULT NULL,
//		  `uid` varchar(32) DEFAULT NULL,
		
	String sql = "update orders set state=? ,address=?,name=?,telephone=? where oid=?" ;	
	qr.update(sql, order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getOid()) ;
	}
 /**
  * 后台查询订单
  */
	@Override
	public List<Order> findOrderByState(String state) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders ";
		
		if(state == null || state.trim().length() == 0) {
			sql += "order by ordertime desc";
			return qr.query(sql, new BeanListHandler<>(Order.class));
		}
		
		sql += "where state=? order by ordertime desc";
		return qr.query(sql, new BeanListHandler<>(Order.class), state);
		
	}

}
