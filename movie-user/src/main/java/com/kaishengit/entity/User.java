package com.kaishengit.entity;

public class User {

	private Integer id;
	private String userName;
	private String password;
	private String tel;
	private String eamil;
	private String remark;

	private String newPassword;
	private String kaptche;
	private String kaptchaExpected;
	
	public String getKaptche() {
		return kaptche;
	}

	public void setKaptche(String kaptche) {
		this.kaptche = kaptche;
	}

	public String getKaptchaExpected() {
		return kaptchaExpected;
	}

	public void setKaptchaExpected(String kaptchaExpected) {
		this.kaptchaExpected = kaptchaExpected;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEamil() {
		return eamil;
	}

	public void setEamil(String eamil) {
		this.eamil = eamil;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
