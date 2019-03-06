package com.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
/**
 * 购物车
 * @author 金文敏
 *
 */
public class Cart{
  private Map<String,CartItem> itemMap = new HashMap<String,CartItem>();
  private Double total = 0.0 ;
 /**
  * 获取所有购物项
  * @return
  */
 	  
  
  public Collection<CartItem> getCartItems() {
	 return  itemMap.values();
  }
	public Map<String, CartItem> getItemMap() {
		return itemMap;
	}
	public void setItemMap(Map<String, CartItem> itemMap) {
		this.itemMap = itemMap;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	/**
	 * 添加到购物车
	 * @param item
	 */
	public void add2cart(CartItem item) {
		//获取商品的pid
		String pid = item.getProduct().getPid();
	  //判断购物车中是否有
		if(itemMap.containsKey(pid)) {
			// 有
			CartItem oItem = itemMap.get(pid) ;
			oItem.setCount(oItem.getCount()+item.getCount());
			
		}else {			
			itemMap.put(pid, item) ; 
			
		
		}
		//修改总金额
		total += item.getSubtotal() ; 
	}
	/**
	 * 从购物车删除商品项
	 * @param pid
	 */
	public void removeFromCart(String pid) {
		CartItem item = itemMap.remove(pid);
		
		total-=item.getSubtotal() ; 
	}
	/**
	 * 清空购物车
	 */
	public void clearCart() {
		itemMap.clear();
		
		total = 0.0 ;
	}
}