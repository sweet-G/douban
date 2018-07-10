package com.lv.entity;

import java.util.List;

public class Page<T> {

	private Integer start;
	private Integer totalPage;
	private Integer pageSize = 3;
	private Integer pageNo;

	private List<T> tList;

	public Page(Integer pageNo, Integer count) {

		Integer totalPage = count / pageSize;

		if ((count % pageSize) != 0) {
			totalPage++;
		}
		pageNo = pageNo > totalPage ? totalPage : pageNo;
		pageNo = pageNo > 1 ? pageNo : 1;

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

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public List<T> gettList() {
		return tList;
	}

	public void settList(List<T> tList) {
		this.tList = tList;
	}

}
