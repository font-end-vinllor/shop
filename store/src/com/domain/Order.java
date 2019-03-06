package com.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
	/**
	 * ����
	 */
//	`oid` varchar(32) NOT NULL,
//	  `ordertime` datetime DEFAULT NULL,
//	  `total` double DEFAULT NULL
	
//	  `state` int(11) DEFAULT NULL,
//	  `address` varchar(30) DEFAULT NULL,
//	  `name` varchar(20) DEFAULT NULL,
	
//	  `telephone` varchar(20) DEFAULT NULL,
//	  `uid` varchar(32) DEFAULT NULL,
	
	private String oid;   //����Id
	private Date ordertime;// ����ʱ��
	private Double total;  //�ܽ��
	
	private Integer state;  //����״̬  0��δ���� 1���Ѹ���  2  ���ѷ���  3 : �����
	private String address; 
	private String name; 
	
	private String telephone;  
	/**
	 * �ö��������ĸ��û�
	 */
	private User uid; 
	private String userid ; 
	
	
	/**
	 * �ö���������Щ������
	 */
	private List<OrderItem> orderItem = new ArrayList<OrderItem>();

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public User getUid() {
		return uid;
	}

	public void setUid(User uid) {
		this.uid = uid;
	}

	public List<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}

}
