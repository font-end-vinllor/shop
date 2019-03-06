package com.domain;
/**
 * ������
 * @author ������
 *
 */
public class CartItem {
  // ��Ʒ
	private Product product ;
 // С��
	private Double subtotal ;
	// ����
	private Integer count ;
	
	public CartItem(Product product,Integer count) {
		this.product = product ; 
		this.count = count ; 
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Double getSubtotal() {
		return product.getShop_price()*count;
	}
	
//	public void setSubtotal(Double subtotal) {
//		this.subtotal = subtotal;
//	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	} 
	
	
}
