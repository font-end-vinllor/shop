package com.service.impl;

import java.util.List;
import com.dao.ProductDao;
import com.domain.PageBean;
import com.domain.Product;
import com.service.ProductService;
import com.utils.BeanFactory;

public class ProductServiceImpl implements ProductService {
	/**
	 * 查询最热商品
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Product> findHot() throws Exception {
		//ProductDao pd = new ProductDaoImpl();
		ProductDao pd = (ProductDao) BeanFactory.getBean("ProductDao");
		return pd.findHot();
	}
  /**
   * 查询最新商品
   * @return
   * @throws Exception
   */
	@Override
	public List<Product> findNew() throws Exception {
		//ProductDao pd = new ProductDaoImpl();
		ProductDao pd = (ProductDao) BeanFactory.getBean("ProductDao");
		return pd.findNew();
			}
	
	/**
	 * 查询商品信息
	 */
	@Override
	 public Product getById(String pid) throws Exception {
		//ProductDao pd = new ProductDaoImpl();
		ProductDao pd = (ProductDao) BeanFactory.getBean("ProductDao");
		return  pd.getById(pid);
		
}
	/**
	 * 分页查询商品
	 */
	@Override
	public PageBean<Product> findByPage(int pageNumber, int pageSize, String cid) throws Exception {
		//ProductDao pDao = new ProductDaoImpl();	
		ProductDao pDao = (ProductDao) BeanFactory.getBean("ProductDao");
		// 创建pageBean
		PageBean<Product> pb = new PageBean<>(pageNumber,pageSize);
		// 设置当前页数据
		List<Product> data = pDao.findByPage(pb,cid);
		pb.setData(data);
		//设置总记录数
		int totalRecord = pDao.getTotalRecord(cid);
		pb.setTotalRecord(totalRecord);
		return pb;
	}
	@Override
	/**
	 * 查询已上架的商品
	 */
	public List<Product> findUp() throws Exception {
		ProductDao pd = (ProductDao) BeanFactory.getBean("ProductDao") ;
		List<Product> list = pd.findUp();
		return list;
	}
	/**
	 * 将product添加到数据库product表中
	 */
	@Override
	public void save(Product p) throws Exception {
		ProductDao pd = (ProductDao) BeanFactory.getBean("ProductDao");
		pd.save(p);
	}

}
