package com.appscomm.sport.model;

import java.io.Serializable;

public class DeviceStayRemindInfoVO implements Serializable {
	private static final long serialVersionUID = 4581498149891959380L;

	private Long userId;
	private String watchId;
	private String startTime;
	private String endTime;
	private Integer interval;
	private String repeat;
	private String status;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getWatchId() {
		return watchId;
	}
	public void setWatchId(String watchId) {
		this.watchId = watchId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getInterval() {
		return interval;
	}
	public void setInterval(Integer interval) {
		this.interval = interval;
	}
	public String getRepeat() {
		return repeat;
	}
	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "DeviceStayRemindInfoVO [userId=" + userId + ", watchId=" + watchId + ", startTime=" + startTime + ", endTime=" + endTime + ", interval=" + interval + ", repeat=" + repeat + ", status=" + status + "]";
	}
	
}
