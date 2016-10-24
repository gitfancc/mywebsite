/**
 * Copyright appscomm.cn 2013. All rights reserved.
 *
 * @createDate 2013-9-16
 */
package com.appscomm.sport.service.impl;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appscomm.sport.dao.ParamSportDao;
import com.appscomm.sport.model.HeartRateVO;
import com.appscomm.sport.model.ParamSportVO;
import com.appscomm.sport.model.SportTotalVO;
import com.appscomm.sport.model.StatisticsVO;
import com.appscomm.sport.service.ParamSportService;
import com.appscomm.sport.utils.DateUtils;
import com.appscomm.sport.vo.PersonalSetting;

;

/**
 * 运动数据
 * 
 * kuangzhenming create by 2013-9-16
 * 
 */
@Service("paramSportService")
@Transactional
public class ParamSportServiceImpl implements ParamSportService {

	@Autowired
	private ParamSportDao paramSportDao;

	@Override
	public List<ParamSportVO> getList(String watchId, String startTime,
			String endTime, int currentPageIndex, int pageSize,Long personId,  String watchType) {

		return paramSportDao.getList(watchId, startTime, endTime,
				currentPageIndex, pageSize, personId, watchType);
	}

	@Override
	public int getCount(String watchId, String startTime, String endTime, Long personId,  String watchType) {
		return paramSportDao.getCount(watchId, startTime, endTime, personId, watchType);
	}

	@Override
	public List<StatisticsVO> getStatistics(String watchId, int doType,String startTime, String endTime,Long personId,  String watchType)
			throws ParseException {
		  List<StatisticsVO> list=paramSportDao.getStatistics(watchId, doType, startTime, endTime, personId, watchType);
		return list;
	}

	/**
	* 函数功能说明:通过watchId统计总步数
	* Administrator 
	* 2013-11-6
	* @param 
	* @return   
	* @throws
	*/
	@Override
	public Long sumStepsByWatchId(String watchId,Long personId, String watchType) {
		return this.paramSportDao.sumStepsByWatchId(watchId, personId, watchType);
	}
	
	@Override
	public List<SportTotalVO> SportGroupHour(String watchId, String startTime, String endTime ){
		return paramSportDao.getSportGroupHour(watchId, startTime, endTime);
	}
	
	@Override
	public List<SportTotalVO> SportGroupHour(String watchId, String startTime, String endTime ,Long personId,  String watchType){
		return paramSportDao.getSportGroupHour(watchId, startTime, endTime, personId, watchType);
	}
	
	@Override
	public List<SportTotalVO> SportGroupDay(String watchId, String startTime, String endTime){
		return paramSportDao.getSportGroupDay(watchId, startTime, endTime);
	}
	
	@Override
	public List<SportTotalVO> SportGroupDay(String watchId, String startTime, String endTime,Long personId,  String watchType){
		return paramSportDao.getSportGroupDay(watchId, startTime, endTime, personId, watchType);
	}

	@Override
	public List<ParamSportVO> getParamSports(Map<String,Object> params){
		return paramSportDao.getParamSports(params);
	}
	
	@Override
	public List<HeartRateVO> getHeartRates(Map<String,Object> params){
		return paramSportDao.getHeartRates(params);
	}

	@Override
	public ParamSportVO getLatelySport(Map<String, Object> params) {
		return paramSportDao.getLatelySportTime(params);
	}
	
	@Override
	public List<ParamSportVO> getSportData(String watchid, String starttime, String endtime, int curindex, int pagesize)
	{
		return paramSportDao.getList(watchid, starttime, endtime, curindex, pagesize);
	}
	
	@Override
	public int getSportDataCount(String watchId, String startTime, String endTime)
	{
		return paramSportDao.getCount(watchId, startTime, endTime);
	}
	
	@Override
	public List<ParamSportVO> getSportData(String watchid, String starttime, String endtime, int curindex, int pagesize,  Long personId, String watchType)
	{
		return paramSportDao.getList(watchid, starttime, endtime, curindex, pagesize, personId, watchType);
	}
	
	@Override
	public int getSportDataCount(String watchId, String startTime, String endTime, Long personId, String watchType)
	{
		return paramSportDao.getCount(watchId, startTime, endTime, personId, watchType);
	}

	@Override
	public Integer deleteSportData(Long personId, String dateTime) {
		return paramSportDao.deleteSportData(personId, dateTime);
	}

	@Override
	public List<SportTotalVO> SportgroupMonth(Long personId, String watchId,
			String watchType, String startTime, String endTime) {
		return paramSportDao.getSportGroupMonth(personId, watchId, watchType, startTime, endTime);
	}

	@Override
	public List<SportTotalVO> SportgroupWeek(Long personId, String watchId,
			String watchType, String startTime, String endTime) {
		return paramSportDao.getSportGroupWeek(personId, watchId, watchType, startTime, endTime);
	}

	@Override
	public List<PersonalSetting> getPersonSettingTarget(Long personId) {
		// TODO Auto-generated method stub
		return paramSportDao.getPersonSettingTarget(personId);
	}
}
