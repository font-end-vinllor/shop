package com.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.constant.Constant;
import com.dao.ProductDao;
import com.domain.PageBean;
import com.domain.Product;
import com.utils.DataSourceUtils;

public class ProductDaoImpl implements ProductDao {
 /**
  * 查询热门商品
  */
	@Override
	public List<Product> findHot() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where is_hot=? and pflag=? order by pdate desc limit 9;" ;
		List<Product> list =  qr.query(sql, new BeanListHandler<>(Product.class),Constant.PRODUCT_IS_HOT,Constant.PRODUCT_IS_UP);
	 
		return list; 
	}
/**
 * 查询最新商品
 */
	@Override
	public List<Product> findNew() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pflag=? order by pdate desc limit 9;" ;
		return qr.query(sql, new BeanListHandler<>(Product.class),Constant.PRODUCT_IS_UP);
	}
/**
 * 通过id查询商品
 */
	@Override
	public Product getById(String pid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pid=? limit 1;";
		return qr.query(sql, new BeanHandler<>(Product.class), pid);
	}
	
	/**
	 * 分页查询商品
	 */
	@Override
	public List<Product> findByPage(PageBean<Product> pb, String cid) throws Exception {
	  QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
      String sql = "select * from product where cid =? and pflag=? order by pdate desc limit ?,?;";
      return qr.query(sql, new BeanListHandler<>(Product.class),cid,Constant.PRODUCT_IS_UP,pb.getStartIndex(),pb.getPageSize());
	}
	@Override
	/**
	 * 获取某一个分类下已上架的商品的总数量
	 */
	public int getTotalRecord(String cid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from product where cid =? and pflag=?";
		return ((Long)qr.query(sql, new ScalarHandler(),cid,Constant.PRODUCT_IS_UP)).intValue();
	}
	@Override
	/**
	 * 查询已上架的商品 
	 */
	public List<Product> findUp() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pflag=? order by pdate desc;" ;
		return qr.query(sql, new BeanListHandler<>(Product.class), Constant.PRODUCT_IS_UP);
	}
	/**
	 * 将product到数据库product表中
	 */
	@Override
	public void save(Product p) throws Exception {
	 QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
	 /*`pid` varchar(32) NOT NULL,
	  `pname` varchar(50) DEFAULT NULL,
	  `market_price` double DEFAULT NULL,
	  
	  `shop_price` double DEFAULT NULL,
	  `pimage` varchar(200) DEFAULT NULL,
	  `pdate` date DEFAULT NULL,
	  
	  `is_hot` int(11) DEFAULT NULL,
	  `pdesc` varchar(255) DEFAULT NULL,
	  `pflag` int(11) DEFAULT NULL,
	  
	  `cid` varchar(32) DEFAULT NULL,*/
	 String sql = "insert into product values(?,?,?,?,?,?,?,?,?,?);";
	 qr.update(sql, p.getPid(),p.getPname(),p.getMarket_price(),p.getShop_price(),p.getPimage(),
			 p.getPdate(),p.getIs_hot(),p.getPdesc(),p.getPflag(),p.getCategory().getCid());
	}

}
