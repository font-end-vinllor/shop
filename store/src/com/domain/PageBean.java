package com.domain;

import java.util.List;

/**
 * 鍒嗛〉瀹炰綋
 * @author Administrator
 * @param <T>
 *
 */
public class PageBean<T> {
	private int pageNumber;//当前页
	private int pageSize;//每页显示数量
	private int totalRecord;//总条数
	private int totalPage;//总页数
	private List<T> data;//当前页的数据limit(pageNumber-1 )*pageSize
	private int startIndex;//
	public PageBean(int pageNumber, int pageSize) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}
	
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	
	
	public int getTotalPage() {
		return (int) Math.ceil(this.totalRecord*1.0/pageSize);
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
	
	public PageBean() { }
	
	
	public int getStartIndex() {
		return (this.pageNumber-1)*this.pageSize;
	}
	
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	
	
	
	
}
