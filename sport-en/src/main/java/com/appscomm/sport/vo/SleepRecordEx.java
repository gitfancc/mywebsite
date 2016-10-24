package com.appscomm.sport.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SleepRecordEx implements Serializable {
	private static final long serialVersionUID = -4570898258026080304L;
	private Long id;
	private long personId;
	private String deviceType;// 设备类型
	private long startTime;// 睡眠开始时间
	private long endTime;// 睡眠结束时间
	private int quality;// 睡眠质量 0-100
	private int sleepDuration;// 睡眠持续时间 单位：分钟
	private int awakeDuration;// 清醒时长 单位：分钟
	private int lightDuration;// 浅睡时长 单位：分钟
	private int deepDuration;// 深睡时长 单位：分钟
	private int totalDuration;// 床上总时长 单位：分钟
	private int awakeCount;// 唤醒次数 单位：次
	private List<SleepRecordDetailEx> details;


	/**
	 * 取得id
	 * 
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * 设置id
	 * 
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	public List<SleepRecordDetailEx> getDetails() {
		return details;
	}

	public void setDetails(List<SleepRecordDetailEx> details) {
		this.details = details;
	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public int getSleepDuration() {
		return sleepDuration;
	}

	public void setSleepDuration(int sleepDuration) {
		this.sleepDuration = sleepDuration;
	}

	public int getAwakeDuration() {
		return awakeDuration;
	}

	public void setAwakeDuration(int awakeDuration) {
		this.awakeDuration = awakeDuration;
	}

	public int getLightDuration() {
		return lightDuration;
	}

	public void setLightDuration(int lightDuration) {
		this.lightDuration = lightDuration;
	}

	public int getDeepDuration() {
		return deepDuration;
	}

	public void setDeepDuration(int deepDuration) {
		this.deepDuration = deepDuration;
	}

	public int getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(int totalDuration) {
		this.totalDuration = totalDuration;
	}

	public int getAwakeCount() {
		return awakeCount;
	}

	public void setAwakeCount(int awakeCount) {
		this.awakeCount = awakeCount;
	}
}
