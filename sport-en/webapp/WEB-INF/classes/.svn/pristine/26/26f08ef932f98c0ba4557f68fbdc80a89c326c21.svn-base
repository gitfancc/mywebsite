package com.appscomm.sport.model;

import java.io.Serializable;

public class LogInLinkInfo  implements Serializable {
	private static final long serialVersionUID = -3235583138315957849L;

	private Long regId;
	private Long userId;
	private String clientIp;
	private Long time;
	private String enCode;
	
	public LogInLinkInfo(){}
	public LogInLinkInfo(Long regId, Long userId, String clientIp, Long time, String enCode){
		this.regId = regId;
		this.userId = userId;
		this.time = time;
		this.enCode = enCode;
	}
	
	public Long getRegId() {
		return regId;
	}
	public void setRegId(Long regId) {
		this.regId = regId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public String getEnCode() {
		return enCode;
	}
	public void setEnCode(String enCode) {
		this.enCode = enCode;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	
	public boolean isValidTime(){
		if( (System.currentTimeMillis()/1000-time)>1800)
			return false;
		return true;
	}
}
