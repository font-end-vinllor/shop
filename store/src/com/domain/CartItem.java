package com.domain;
/**
 * 购物项
 * @author 金文敏
 *
 */
public class CartItem {
  // 商品
	private Product product ;
 // 小计
	private Double subtotal ;
	// 数量
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
