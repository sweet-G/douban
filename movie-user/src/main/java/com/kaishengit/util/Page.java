package com.kaishengit.util;

import java.util.List;

public class Page<T> {

	private Integer totalPage;
	private Integer pageSize = 3;
	private Integer start;
	private Integer pageNo;
	private List<T> items;

	public Page(int pageNo, int count) {
		// 计算做总页码
		int totalPage = count / pageSize;
		if (count % pageSize != 0) {
			totalPage++;
		}

		// 如果当前页码大于总页码，则取最后一页
		if (pageNo > totalPage) {
			pageNo = totalPage;
		}

		// 如果当前页码小于1 ，则返回首页
		if (pageNo < 1) {
			pageNo = 1;
		}

		this.start = (pageNo - 1) * this.pageSize;
		this.pageNo = pageNo;
		this.totalPage = totalPage;
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

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

}
