package com.appscomm.sport.dao;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.appscomm.sport.model.HeartRateVO;
import com.appscomm.sport.model.ParamSportVO;
import com.appscomm.sport.model.SportTotalVO;
import com.appscomm.sport.model.StatisticsVO;
import com.appscomm.sport.vo.PersonalSetting;
import com.appscomm.sport.vo.SleepRecordStatistic;

/**
 * 运动数据搜索
 * @author kuangzhenming
 *
 */
public interface ParamSportDao {
	/**
	 * 根据开始时间，结束时间，进行分页搜素
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param currentPageIndex  当前页
	 * @param pageSize 一页显示条数
	 * @return List<ParamSportVO>
	 */
	public List<ParamSportVO> getList(String watchId,String startTime,String endTime,int currentPageIndex,int pageSize);
	public List<ParamSportVO> getList(String watchId,String startTime,String endTime,int currentPageIndex,int pageSize, Long personId,  String watchType);
   /**
    * 获取记录总数
    * @param sql
    * @return
    */
	public int getCount(String watchId, String startTime, String endTime) ;
	public int getCount(String watchId, String startTime, String endTime, Long personId,  String watchType) ;
   
	/**
	 * 统计数据，按天，按日，按周，按月，按年，自定义日期
	 * 根据开始时间，结束时间，进行分页搜素
	 * @param doType 操作类型
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param currentPageIndex  当前页
	 * @param pageSize 一页显示条数
	 * @return List<StatisticsVO>
	 * @throws ParseException 
	 */
	public List<StatisticsVO> getStatistics(String watchId,int doType,String startTime,String endTime, Long personId,  String watchType) throws ParseException;
	
	
	/**
	* 函数功能说明:通过watchId统计总步数
	* xc 
	* 2013-11-6
	* @param 
	* @return   
	* @throws
	*/
	public Long sumStepsByWatchId(String watchId, Long personId,  String watchType);
	
	public List<SportTotalVO> getSportGroupHour(String watchId, String startTime, String endTime);
	public List<SportTotalVO> getSportGroupHour(String watchId, String startTime, String endTime, Long personId,  String watchType);
	
	public List<SportTotalVO> getSportGroupDay(String watchId, String startTime, String endTime);
	public List<SportTotalVO> getSportGroupDay(String watchId, String startTime, String endTime, Long personId,  String watchType);
	
	public List<SportTotalVO> getSportGroupWeek(Long personId, String watchId, String watchType, String startTime, String endTime);
	public List<SportTotalVO> getSportGroupMonth(Long personId, String watchId, String watchType, String startTime, String endTime);

	/**
	 * 获取运动参数列表
	 * @param params
	 * @return
	 */
	public List<ParamSportVO> getParamSports(Map<String,Object> params);
	
	/**
	 * 获取运动心率列表
	 * @param params
	 * @return
	 */
	public List<HeartRateVO> getHeartRates(Map<String,Object> params);
	
	/**
	 * 获取当前最新的运动记录
	 * 
	 * @return
	 */
	public ParamSportVO getLatelySportTime(Map<String,Object> params);
	/**
	* @description:删除数据库中用户的数据
	* @param personId
	* @param dateTime
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2014-3-14
	 */
	public Integer deleteSportData(Long personId, String dateTime);
	
	public List<SleepRecordStatistic> getSleepStatistics(String watchId,int doType,String startTime,String endTime, Long personId,  String watchType) throws ParseException;
	public List<SleepRecordStatistic> getSleepList(String watchId,String startTime,String endTime,int currentPageIndex,int pageSize, Long personId,  String watchType);
	public int getSleepCount(String watchId, String startTime, String endTime, Long personId, String watchType);
	public Date getLastSyncTime(Long personId, String watchId);
	public List<PersonalSetting> getPersonSettingTarget(Long personId);
}
