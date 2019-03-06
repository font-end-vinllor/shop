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
    *      ���涩�������ݿ�
    */
	public void save(Order o) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "insert into orders values(?,?,?,?,?,?,?,?);";
		 qr.update(DataSourceUtils.getConnection(), sql,o.getOid(),o.getOrdertime(),o.getTotal(),o.getState(),o.getAddress(),o.getName(),o.getTelephone(),o.getUid().getUid()) ;
	
	}

	@Override
	/**
	 * ��������浽���ݿ�
	 */
	public void saveItem(OrderItem oi) throws Exception {
		QueryRunner qr = new QueryRunner();
		String sql = "insert into orderitem values(?,?,?,?,?);" ;
		qr.update(DataSourceUtils.getConnection(), sql,oi.getItemid(),oi.getCount(),oi.getSubtotal(),oi.getProduct().getPid(),oi.getOrder().getOid());			
	}

	@Override
	/**
	 * ��ȡ������������
	 */
	public int getTotalRecord(String uid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from orders where userid = ?" ;
		return ((Long)qr.query(sql, new ScalarHandler(),uid)).intValue();
	}

	@Override
	/**
	 * ��ȡĳ�û��Ķ����б�
	 */
	public List<Order> findMyOrdersByPage(PageBean<Order> pb, String u) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		//��ѯ���ж�����������Ϣ��
		//String sql = "select * from orders where uid=? order by ordertime desc limit ?,?;";
		//List<Order> list = qr.query(sql, new BeanListHandler<>(Order.class), uid,pb.getStartIndex(),pb.getPageSize()) ;
       
		 String sql = "select * from orders where userid= ? order by ordertime desc limit ?,?;";
	      List<Order> list = qr.query(sql, new BeanListHandler<>(Order.class),u,pb.getStartIndex(),pb.getPageSize());
	       
		
		
		// �����������ϣ���ȡÿһ����������ѯÿ������������
		for (Order order : list) {
			sql = "select * from orderitem oi,product p where oi.pid = p.pid and oi.oid=?;" ;
			List<Map<String,Object>> maplist = qr.query(sql, new MapListHandler(),order.getOid()) ;
			
			//  ����maplist ,��ȡÿһ�����������飬��װ��orderitem,������뵱ǰ�����Ķ������б�
			for (Map<String,Object> map : maplist) {
				// ��װorderitem
				//a. ����orderitem
				OrderItem oi = new OrderItem();
				// b. ��װorderitem
				BeanUtils.populate(oi, map);
				//�ֶ���װproduct
				Product p = new Product() ;
				
				BeanUtils.populate(p, map);
				oi.setProduct(p);
				// ��orderitem����order�����б�
				order.getOrderItem().add(oi) ;
				
			}
		}
		
		
		return list;
	}

	@Override
	/**
	 * ��ѯ��������
	 */
	public Order getById(String oid) throws Exception {
		//��ȡ����
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource()) ;
		String sql = "select * from orders where oid =?" ;
		Order order = qr.query(sql, new BeanHandler<>(Order.class), oid) ;
		
		//��ѯ��������
		sql = "select * from orderitem oi,product p where oi.pid = p.pid and oi.oid=?;" ;
		List<Map<String,Object>> list = qr.query(sql, new MapListHandler() , oid) ;
		
		for (Map<String, Object> map : list) {
			// ��װorderitem
			OrderItem oi = new OrderItem() ;
			BeanUtils.populate(oi, map);
			//�ֶ���װproduct
			Product p = new Product() ;
			BeanUtils.populate(p, map);
			
			oi.setProduct(p);
			order.getOrderItem().add(oi) ;
		}
		return order ; 
	}

	@Override
	/**
	 * ���¶���
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
  * ��̨��ѯ����
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
