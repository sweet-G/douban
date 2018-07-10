package com.lv.entity;

import java.sql.Timestamp;
import java.util.List;

public class Movie {
	private Integer id;
	private String filmName;
	private String filmDirector;
	private String area;
	private String year;
	private String imgPath;
	private String content;
	private String simpoContent;
	private Timestamp createTime;
	private Integer scanNum;
	private Integer replyNum;
	private String lastReplyTime;
	private String remark;

	private List<Type> typeList;

	public String getLastReplyTime() {
		return lastReplyTime;
	}

	public void setLastReplyTime(String lastReplyTime) {
		this.lastReplyTime = lastReplyTime;
	}

	public List<Type> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<Type> typeList) {
		this.typeList = typeList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFilmName() {
		return filmName;
	}

	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}

	public String getFilmDirector() {
		return filmDirector;
	}

	public void setFilmDirector(String filmDirector) {
		this.filmDirector = filmDirector;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSimpoContent() {
		return simpoContent;
	}

	public void setSimpoContent(String simpoContent) {
		this.simpoContent = simpoContent;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getScanNum() {
		return scanNum;
	}

	public void setScanNum(Integer scanNum) {
		this.scanNum = scanNum;
	}

	public Integer getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(Integer replyNum) {
		this.replyNum = replyNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
