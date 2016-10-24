package com.appscomm.sport.vo;

import java.io.Serializable;
import java.util.Date;


public class SleepRecordDetail implements Serializable
{
	private static final long serialVersionUID = 2890351023151668105L;
	
	public static final int STATUS_AWAKE =1;//睡眠类型（状态） 1-清醒
	public static final int STATUS_LIGHT_SLEEP =2;//睡眠类型（状态） 2-浅度睡眠
	public static final int STATUS_DEEP_SLEEP =3;//睡眠类型（状态） 3-深度睡眠
	private Long id;
	private long sleepRecordId;
	private Date startTime;
	//private Date endTime;
	private int status;//睡眠类型（状态） 1-清醒 2-浅度睡眠 3-深度睡眠
	
	private Long starttime;//时间转换long型接受属性
	
	public Long getStarttime() {
		return starttime;
	}
	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}
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
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
