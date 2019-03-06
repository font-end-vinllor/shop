package com.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.dao.CategoryDao;
import com.domain.Category;
import com.utils.DataSourceUtils;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	/**
	 * 查询所有分类
	 */
	public List<Category> findAll() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		return qr.query("select * from category;", new BeanListHandler<>(Category.class));
	}

	@Override
	/**
	 * 将分类保存在数据库
	 */
	public void save(Category c) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource() ) ;
		String sql = "insert into category values(?,?);" ;
		qr.update(sql, c.getCid(),c.getCname()) ;
		
	}

}
