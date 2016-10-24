/**
 *
 */
package com.appscomm.sport.model;

import java.io.Serializable;

/**
 *  心率信息实体对象  
 *	
 *  qindf create by 2013-8-28
 *
 */
public class HeartRateVO implements Serializable {

	private static final long serialVersionUID = -5969526953408441911L;
	
	private String watchId;
	private String setupTime;
	private String location;
	private int current;
	private int average;
	private int userStat;
	private int minRate;
	private int maxRate;
	private int deviceId;
	
	public String getWatchId() {
		return watchId;
	}
	public void setWatchId(String watchId) {
		this.watchId = watchId;
	}
	public String getSetupTime() {
		return setupTime;
	}
	public void setSetupTime(String setupTime) {
		this.setupTime = setupTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	public int getAverage() {
		return average;
	}
	public void setAverage(int average) {
		this.average = average;
	}
	public int getUserStat() {
		return userStat;
	}
	public void setUserStat(int userStat) {
		this.userStat = userStat;
	}
	public int getMinRate() {
		return minRate;
	}
	public void setMinRate(int minRate) {
		this.minRate = minRate;
	}
	public int getMaxRate() {
		return maxRate;
	}
	public void setMaxRate(int maxRate) {
		this.maxRate = maxRate;
	}
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	@Override
	public String toString() {
		return "HeartRateVO [watchId=" + watchId + ", setupTime=" + setupTime
				+ ", location=" + location + ", current=" + current
				+ ", average=" + average + ", userStat=" + userStat
				+ ", minRate=" + minRate + ", maxRate=" + maxRate
				+ ", deviceId=" + deviceId + "]";
	}

}
