package com.constant;

public interface Constant {
	/**
	 * ��ѡ���û���
	 */
	String SAVE_NAME = "ok";
	/**
	 * redis�д洢�����б��key
	 */
	String STORE_CATEGORY_LIST = "STORE_CATEGORY_LIST";
  /**
   * redis�������ĵ�ַ
   */
	String REDIS_HOST = "192.168.17.136";;

	/**
	 * redis�������˿ں�
	 */
	int REDIS_PORT = 6379 ;
	/*
	 * ������Ʒ
	 */
	int PRODUCT_IS_HOT = 1 ;
	/**
	 * ��Ʒû�¼�
	 */
	int PRODUCT_IS_UP = 0;
	/**
	 * ��Ʒ�¼�
	 */
	int PRODUCT_IS_DOWN = 1 ;
	
	/**
	 * ����δ����
	 */
	int ORDER_WEIFUKUAN = 0 ;
	/**
	 * �����Ѹ���
	 */
	int ORDER_YIFUKUAN = 1 ;
	/**
	 * �ѷ���
	 */
	int ORDER_YIFAHUO = 2 ;
	/**
	 * �����
	 */
	int ORDER_YIWANCHENG = 3 ;
}
