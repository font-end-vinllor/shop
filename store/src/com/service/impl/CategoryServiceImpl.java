package com.service.impl;

import java.util.List;

import com.constant.Constant;
import com.dao.CategoryDao;
import com.dao.impl.CategoryDaoImpl;
import com.domain.Category;
import com.service.CategoryService;
import com.utils.BeanFactory;
import com.utils.JedisUtils;
import com.utils.JsonUtil;

import redis.clients.jedis.Jedis;

public class CategoryServiceImpl implements CategoryService{
	
	
	@Override
	/**
	 * ��ȡ���з�����б�
	 */
	public List<Category> findList() throws Exception {
		CategoryDao  cd = (CategoryDao) BeanFactory.getBean("CategoryDao") ;
		List<Category> list = cd.findAll();
		return list;
	}

 /**
  * ��ѯ���з���
 * @throws Exception 
  */
	@Override
	public String findAll() throws Exception {
		// ����dao����ѯ���з���
	//	CategoryDao cd = new CategoryDaoImpl();
//		CategoryDao cd = (CategoryDao) BeanFactory.getBean("CategoryDao");
//		List<Category> list;			
//		list = cd.findAll();
			
		List<Category> list = findList();
			// ��listתΪjson�ַ���
		if(list != null || list.size() > 0) {
		return  JsonUtil.list2json(list);
		}
	
		
		return null;
		
	}

@Override
/**
 *��redis ��ȡ���з���
 */
public String findAllFromRedis() throws Exception {
	// ��ȡjedis
	Jedis jedis = JedisUtils.getJedis();
	// ��redis�л�ȡ����
	String value = jedis.get(Constant.STORE_CATEGORY_LIST);

	// �ж������Ƿ�Ϊ��
	if(value == null) {
		// ���Ϊ�� ����findAll() ����ѯ�Ľ������redis return 
 	value = findAll();
	jedis.set(Constant.STORE_CATEGORY_LIST, value);
	System.out.println("��mysql��ȡ");
		return value ;
		
		}
	// ��buΪ��  return
	System.out.println("��redis��");
	return value;
}

@Override
/**
 * �����ౣ�������ݿ�
   */
public void save(Category c) throws Exception {
	CategoryDao cd = (CategoryDao) BeanFactory.getBean("CategoryDao") ;
	cd.save(c) ;
	
//	//����redis 
//	Jedis j = null ;
//	try {
//		j = JedisUtils.getJedis() ;
//		j.del(Constant.STORE_CATEGORY_LIST) ;
//	} finally {
//		JedisUtils.closeJedis(j);
//	}
}


}
