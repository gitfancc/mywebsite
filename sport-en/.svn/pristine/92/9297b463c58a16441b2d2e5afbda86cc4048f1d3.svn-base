package com.appscomm.sport.vo;

import java.io.Serializable;
import java.util.Date;


public class SleepRecordDetailEx implements Serializable
{
	private static final long serialVersionUID = 2890351023151668105L;
	
	public static final int STATUS_AWAKE =1;//睡眠类型（状态） 1-清醒
	public static final int STATUS_LIGHT_SLEEP =2;//睡眠类型（状态） 2-浅度睡眠
	public static final int STATUS_DEEP_SLEEP =3;//睡眠类型（状态） 3-深度睡眠
	private Long id;
	private long sleepRecordId;
	private long startTime;
	//private Date endTime;
	private int status;//睡眠类型（状态） 1-清醒 2-浅度睡眠 3-深度睡眠
	
	/**
     * 取得id
     * 
     * @return the id
     */
	public Long getId()
	{
		return this.id;
	}
	/**
     * 设置id
     * 
     */
	public void setId(final Long id)
	{
		this.id = id;
	}
	
	
	public long getSleepRecordId() {
		return sleepRecordId;
	}
	public void setSleepRecordId(long sleepRecordId) {
		this.sleepRecordId = sleepRecordId;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
