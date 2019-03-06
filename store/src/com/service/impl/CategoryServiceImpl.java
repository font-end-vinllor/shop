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
	 * 获取所有分类的列表
	 */
	public List<Category> findList() throws Exception {
		CategoryDao  cd = (CategoryDao) BeanFactory.getBean("CategoryDao") ;
		List<Category> list = cd.findAll();
		return list;
	}

 /**
  * 查询所有分类
 * @throws Exception 
  */
	@Override
	public String findAll() throws Exception {
		// 调用dao，查询所有分类
	//	CategoryDao cd = new CategoryDaoImpl();
//		CategoryDao cd = (CategoryDao) BeanFactory.getBean("CategoryDao");
//		List<Category> list;			
//		list = cd.findAll();
			
		List<Category> list = findList();
			// 将list转为json字符串
		if(list != null || list.size() > 0) {
		return  JsonUtil.list2json(list);
		}
	
		
		return null;
		
	}

@Override
/**
 *从redis 获取所有分类
 */
public String findAllFromRedis() throws Exception {
	// 获取jedis
	Jedis jedis = JedisUtils.getJedis();
	// 从redis中获取数据
	String value = jedis.get(Constant.STORE_CATEGORY_LIST);

	// 判断数据是否为空
	if(value == null) {
		// 如果为空 调用findAll() 将查询的结果放入redis return 
 	value = findAll();
	jedis.set(Constant.STORE_CATEGORY_LIST, value);
	System.out.println("从mysql获取");
		return value ;
		
		}
	// 若bu为空  return
	System.out.println("从redis中");
	return value;
}

@Override
/**
 * 将分类保存在数据库
   */
public void save(Category c) throws Exception {
	CategoryDao cd = (CategoryDao) BeanFactory.getBean("CategoryDao") ;
	cd.save(c) ;
	
//	//更新redis 
//	Jedis j = null ;
//	try {
//		j = JedisUtils.getJedis() ;
//		j.del(Constant.STORE_CATEGORY_LIST) ;
//	} finally {
//		JedisUtils.closeJedis(j);
//	}
}


}
