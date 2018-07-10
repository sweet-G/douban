package com.lv.util;

import java.util.List;

public class Page<T> {

	// 当前页码
	private Integer pageNo;
	// 起始行
	private Integer start;
	// 每页数量
	private Integer pageSize = 5;
	// 总页数
	private Integer totalPage;
	// 当前页内容
	private List<T> tList;

	public Page(int pageNo, int count) {
		Integer totalPage = count / this.pageSize;
		
		totalPage = (count % this.pageSize) == 0 ? totalPage : ++totalPage;
		pageNo = pageNo < totalPage ? pageNo : totalPage;
		pageNo = pageNo < 1 ? 1 : pageNo;
		
		this.start = (pageNo - 1) * this.pageSize;
		this.pageNo = pageNo;
		this.totalPage = totalPage;
	}
	
	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public List<T> getTList() {
		return tList;
	}

	public void setTList(List<T> tList) {
		this.tList = tList;
	}

	
}
