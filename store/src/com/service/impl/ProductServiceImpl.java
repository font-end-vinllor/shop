package com.service.impl;

import java.util.List;
import com.dao.ProductDao;
import com.domain.PageBean;
import com.domain.Product;
import com.service.ProductService;
import com.utils.BeanFactory;

public class ProductServiceImpl implements ProductService {
	/**
	 * ��ѯ������Ʒ
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
   * ��ѯ������Ʒ
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
	 * ��ѯ��Ʒ��Ϣ
	 */
	@Override
	 public Product getById(String pid) throws Exception {
		//ProductDao pd = new ProductDaoImpl();
		ProductDao pd = (ProductDao) BeanFactory.getBean("ProductDao");
		return  pd.getById(pid);
		
}
	/**
	 * ��ҳ��ѯ��Ʒ
	 */
	@Override
	public PageBean<Product> findByPage(int pageNumber, int pageSize, String cid) throws Exception {
		//ProductDao pDao = new ProductDaoImpl();	
		ProductDao pDao = (ProductDao) BeanFactory.getBean("ProductDao");
		// ����pageBean
		PageBean<Product> pb = new PageBean<>(pageNumber,pageSize);
		// ���õ�ǰҳ����
		List<Product> data = pDao.findByPage(pb,cid);
		pb.setData(data);
		//�����ܼ�¼��
		int totalRecord = pDao.getTotalRecord(cid);
		pb.setTotalRecord(totalRecord);
		return pb;
	}
	@Override
	/**
	 * ��ѯ���ϼܵ���Ʒ
	 */
	public List<Product> findUp() throws Exception {
		ProductDao pd = (ProductDao) BeanFactory.getBean("ProductDao") ;
		List<Product> list = pd.findUp();
		return list;
	}
	/**
	 * ��product��ӵ����ݿ�product����
	 */
	@Override
	public void save(Product p) throws Exception {
		ProductDao pd = (ProductDao) BeanFactory.getBean("ProductDao");
		pd.save(p);
	}

}
