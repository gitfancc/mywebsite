package com.appscomm.sport.dao;

import java.util.Date;
import java.util.List;

import com.appscomm.sport.vo.SleepRecord;
import com.appscomm.sport.vo.SleepRecordDetail;
import com.appscomm.sport.vo.SleepRecordDetailEx;
import com.appscomm.sport.vo.SleepRecordEx;
import com.appscomm.sport.vo.SleepRecordStatistic;

public interface SleepRecordDAO {
	public long saveSleepRecord(SleepRecord sleepRecord);
	public long saveSleepRecordDetail(SleepRecordDetail sleepRecordDetail);
	public List<SleepRecordEx> getSleepRecords(long personId, String deviceType, Date startDate, Date endDate);
	public List<SleepRecordDetailEx> getSleepRecordDetails(long personId, String deviceType, Date startDate, Date endDate);
	public SleepRecordDetail getSleepRecordDetails(long sleepRecordId);
	public List<SleepRecordStatistic> getSleepRecordStatisticsByDay(long personId, String deviceType, Date startDate, Date endDate);
	public List<SleepRecordStatistic> getSleepRecordStatisticsByWeek(long personId, String deviceType, Date startDate, Date endDate);
	public List<SleepRecordStatistic> getSleepRecordStatisticsByMonth(long personId, String deviceType, Date startDate, Date endDate);
	
	public List<SleepRecordEx> getSleepRecords(long personId, String dateTime);
	public List<SleepRecordEx> getSleepRecordsL11(long personId, String dateTime);
	public int deleteSleepRecord(long personId,  String dateTime);
	public int deleteSleepRecordL11(long personId,  String dateTime);
	public int deleteSleepRecordDetail(long sleepId);
}