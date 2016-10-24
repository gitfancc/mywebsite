package com.appscomm.sport.model;

import java.io.Serializable;
import java.util.Date;

public class ApiUserVO implements Serializable {
	private static final long serialVersionUID = -8006082200719050518L;
	private Long id;
	private Long userId;
	private String nickName;
	private String password;
	private String mail;
	private String telphone;
	private Date registerTime;
	private Integer isActive;
	private Integer isBind;
	
	public ApiUserVO(){
	}
	public ApiUserVO(UserVO user){
		this.id = user.getId();
		this.userId = user.getUserId();
		this.nickName = user.getNickName();
		this.password = "";
		this.mail = user.getMail();
		this.telphone = user.getTelphone();
		this.registerTime = user.getRegisterTime();
		this.isActive = user.getIsActive();
		if (user.getWatchId() == null){
			this.isBind = 0;
		} else {
			this.isBind = 1;
		}
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	public Integer getIsBind() {
		return isBind;
	}
	public void setIsBind(Integer isBind) {
		this.isBind = isBind;
	}
}
