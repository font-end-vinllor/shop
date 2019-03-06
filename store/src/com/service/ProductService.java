package com.service;

import java.util.List;

import com.domain.PageBean;
import com.domain.Product;

public interface ProductService {

	List<Product> findHot() throws Exception;

	List<Product> findNew() throws Exception;

	Product getById(String string) throws Exception;

	PageBean<Product> findByPage(int pageNumber, int pageSize, String cid) throws Exception;

	List<Product> findUp() throws Exception;

	void save(Product p) throws Exception;

}
