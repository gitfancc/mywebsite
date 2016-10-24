/**
 * Copyright lenovo-cw.com 2013. All rights reserved.
 *
 * @createDate 2013-12-16
 */
package com.appscomm.sport.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.appscomm.sport.vo.SleepRecord;
import com.appscomm.sport.vo.SleepRecordEx;
import com.appscomm.sport.vo.SleepRecordStatistic;
import com.appscomm.sport.vo.SleepRecordVo;

/**
 * TODO 类/接口描述信息
 *
 * @author Administrator
 *
 */
public interface SleepInfoService {
	
	public SleepRecordVo getSleepRecord(Map<String,Object> map);
	public List<SleepRecordStatistic> getSleepTotal(String watchId, int doType, String startTime, String endTime, Long personId, String watchType) throws ParseException;
	public List<SleepRecordStatistic> getSleepList(String watchId, String startTime, String endTime, int currentPageIndex, int pageSize, Long personId, String watchType);
	public int getSleepCount(String watchId, String startTime, String endTime, Long personId, String watchType);
	public Date getLastSyncTime(Long personId, String watchId);
	
	
	public void saveSleepRecord(SleepRecord sleepRecord);
	public List<SleepRecordEx> getSleepRecords(long personId, String deviceType, Date startDate, Date endDate);
	public List<SleepRecordStatistic> getSleepRecordStatistcs(long personId, String deviceType, Date startDate, Date endDate, int qryType);
	
	public int deleteSleepRecord(Long personId, String watchId, String dateTime);
	public int deleteSleepRecordL11(Long personId, String watchId, String dateTime);
}
