package com.constant;

public interface Constant {
	/**
	 * 勾选了用户名
	 */
	String SAVE_NAME = "ok";
	/**
	 * redis中存储分类列表的key
	 */
	String STORE_CATEGORY_LIST = "STORE_CATEGORY_LIST";
  /**
   * redis服务器的地址
   */
	String REDIS_HOST = "192.168.17.136";;

	/**
	 * redis服务器端口号
	 */
	int REDIS_PORT = 6379 ;
	/*
	 * 热门商品
	 */
	int PRODUCT_IS_HOT = 1 ;
	/**
	 * 商品没下架
	 */
	int PRODUCT_IS_UP = 0;
	/**
	 * 商品下架
	 */
	int PRODUCT_IS_DOWN = 1 ;
	
	/**
	 * 订单未付款
	 */
	int ORDER_WEIFUKUAN = 0 ;
	/**
	 * 订单已付款
	 */
	int ORDER_YIFUKUAN = 1 ;
	/**
	 * 已发货
	 */
	int ORDER_YIFAHUO = 2 ;
	/**
	 * 已完成
	 */
	int ORDER_YIWANCHENG = 3 ;
}
