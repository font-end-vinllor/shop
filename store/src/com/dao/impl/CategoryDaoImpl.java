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
	 * ��ѯ���з���
	 */
	public List<Category> findAll() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		return qr.query("select * from category;", new BeanListHandler<>(Category.class));
	}

	@Override
	/**
	 * �����ౣ�������ݿ�
	 */
	public void save(Category c) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource() ) ;
		String sql = "insert into category values(?,?);" ;
		qr.update(sql, c.getCid(),c.getCname()) ;
		
	}

}
