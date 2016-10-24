/**
 * Copyright lenovo-cw.com 2013. All rights reserved.
 *
 * @createDate 2013-12-16
 */
package com.appscomm.sport.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.appscomm.sport.dao.ParamSportDao;
import com.appscomm.sport.dao.SleepRecordDAO;
import com.appscomm.sport.service.SleepInfoService;
import com.appscomm.sport.utils.StringUtil;
import com.appscomm.sport.vo.SleepRecord;
import com.appscomm.sport.vo.SleepRecordDetail;
import com.appscomm.sport.vo.SleepRecordDetailEx;
import com.appscomm.sport.vo.SleepRecordEx;
import com.appscomm.sport.vo.SleepRecordStatistic;
import com.appscomm.sport.vo.SleepRecordVo;

/**
 * TODO 类/接口描述信息
 *
 * @author Administrator
 *
 */
@Service("sleepInfoService")
public class SleepInfoServiceImpl implements SleepInfoService {

	private static  String  restfulURL = StringUtil.getProperty("restfulURL");
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ParamSportDao paramSportDao;
	@Autowired
	private SleepRecordDAO sleepRecordDAO;
	
	@Override
	public SleepRecordVo getSleepRecord(Map<String, Object> map) {
		SleepRecordVo sr = this.restTemplate.getForObject(restfulURL+"api/sleep/record?personId={personId}&deviceType={deviceType}&startDate={startDate}&endDate={endDate}", SleepRecordVo.class, map);
		return sr;
	}

	@Override
	public List<SleepRecordStatistic> getSleepTotal(String watchId, int doType, String startTime, String endTime, Long personId, String watchType) throws ParseException{
		return paramSportDao.getSleepStatistics(watchId, doType, startTime, endTime, personId, watchType);
	}
	
	@Override
	public List<SleepRecordStatistic> getSleepList(String watchId, String startTime, String endTime, int currentPageIndex, int pageSize, Long personId, String watchType)	{
		return paramSportDao.getSleepList(watchId, startTime, endTime, currentPageIndex, pageSize, personId, watchType);
	}
	
	@Override
	public int getSleepCount(String watchId, String startTime, String endTime, Long personId, String watchType){
		return paramSportDao.getSleepCount(watchId, startTime, endTime, personId, watchType);
	}
	
	@Override
	public Date getLastSyncTime(Long personId, String watchId){
		return paramSportDao.getLastSyncTime(personId, watchId);
	}

	@Override
	public void saveSleepRecord(SleepRecord sleepRecord) {
		//检查重复记录
		List<SleepRecordEx> srList = sleepRecordDAO.getSleepRecords(sleepRecord.getPersonId(), sleepRecord.getDeviceType(), sleepRecord.getStartTime(), sleepRecord.getEndTime());
		if(srList!=null && srList.size()>0){
			
		}else{
				//记录数据
				long sleepRecordId = sleepRecordDAO.saveSleepRecord(sleepRecord);
				List<SleepRecordDetail> srdList = sleepRecord.getDetails();
				for(SleepRecordDetail sleepRecordDetail : srdList){
					sleepRecordDetail.setSleepRecordId(sleepRecordId);
					sleepRecordDAO.saveSleepRecordDetail(sleepRecordDetail);
				}
		}
	}

	@Override
	public List<SleepRecordEx> getSleepRecords(long personId, String deviceType, Date startDate, Date endDate) {
		if(startDate.equals(endDate)){
			DateTime dt = new DateTime(endDate);
			dt = dt.withTimeAtStartOfDay().withTime(23, 59, 59, 999);
			endDate = dt.toDate();
		}
		
		 List<SleepRecordEx> srList = sleepRecordDAO.getSleepRecords(personId, deviceType, startDate, endDate);
		 for(SleepRecordEx sr : srList){
			 List<SleepRecordDetailEx> details = sleepRecordDAO.getSleepRecordDetails(personId, deviceType, startDate, endDate);
			 if(details != null){
				 sr.setDetails(details);
			 }
		 }
		 
		 return srList;
	}

	@Override
	public List<SleepRecordStatistic> getSleepRecordStatistcs(long personId, String deviceType, Date startDate, Date endDate, int qryType) {
		if (qryType == 1){
			// 按天统计
			return sleepRecordDAO.getSleepRecordStatisticsByDay(personId, deviceType, startDate, endDate);
		}else if (qryType == 2){
			// 按周统计
			return sleepRecordDAO.getSleepRecordStatisticsByWeek(personId, deviceType, startDate, endDate);
		}else if (qryType == 3){
			// 按月统计
			return sleepRecordDAO.getSleepRecordStatisticsByMonth(personId, deviceType, startDate, endDate);
		}else{
			return sleepRecordDAO.getSleepRecordStatisticsByMonth(personId, deviceType, startDate, endDate);
		}
	}

	@Override
	public int deleteSleepRecord(Long personId, String watchId, String dateTime) {
		List<SleepRecordEx> sleepRecordIdList = sleepRecordDAO.getSleepRecords(personId, dateTime);
		for(SleepRecordEx sleep : sleepRecordIdList){
			sleepRecordDAO.deleteSleepRecordDetail(sleep.getId());
		}
		
		if(sleepRecordIdList!=null && sleepRecordIdList.size()>0){
			return sleepRecordDAO.deleteSleepRecord(personId,  dateTime);
		}
		
		return 0;
	}
	
	@Override
	public int deleteSleepRecordL11(Long personId, String watchId, String dateTime) {
		List<SleepRecordEx> sleepRecordIdList = sleepRecordDAO.getSleepRecordsL11(personId, dateTime);
		for(SleepRecordEx sleep : sleepRecordIdList){
			sleepRecordDAO.deleteSleepRecordDetail(sleep.getId());
		}
		
		if(sleepRecordIdList!=null && sleepRecordIdList.size()>0){
			return sleepRecordDAO.deleteSleepRecordL11(personId,  dateTime);
		}
		
		return 0;
	}
}
