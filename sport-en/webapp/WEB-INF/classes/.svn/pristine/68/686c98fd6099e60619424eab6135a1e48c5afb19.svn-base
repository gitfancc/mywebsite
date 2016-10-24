package com.appscomm.sport.service;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.appscomm.sport.model.HeartRateVO;
import com.appscomm.sport.model.ParamSportVO;
import com.appscomm.sport.model.SportTotalVO;
import com.appscomm.sport.model.StatisticsVO;
import com.appscomm.sport.vo.PersonalSetting;

/**
 * 运动数据搜索
 * @author kuangzhenming
 *
 */
public interface ParamSportService{
	/**
	 * 根据开始时间，结束时间，进行分页搜素
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param currentPageIndex  当前页
	 * @param pageSize 一页显示条数
	 * @return List<ParamSportVO>
	 */
	public List<ParamSportVO> getList(String watchId,String startTime,String endTime,int currentPageIndex,int pageSize,Long personId,  String watchType);
   /**
    * 获取记录总数
    * @param sql
    * @return
    */
	public int getCount(String watchId,	String startTime, String endTime,Long personId,  String watchType) ;
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
	public List<StatisticsVO> getStatistics(String watchId,int doType,String startTime,String endTime,Long personId,  String watchType) throws ParseException;
	
	
	public Long sumStepsByWatchId(String watchId, Long personId, String watchType);
	
	/**
	 * 查询运动历史汇总记录，按小时汇总
	 * @param watchId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<SportTotalVO> SportGroupHour(String watchId, String startTime, String endTime);
	public List<SportTotalVO> SportGroupHour(String watchId, String startTime, String endTime,Long personId,  String watchType);
	
	/**
	 * 查询运动历史汇总记录，按天汇总
	 * @param watchId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<SportTotalVO> SportGroupDay(String watchId, String startTime, String endTime);	
	public List<SportTotalVO> SportGroupDay(String watchId, String startTime, String endTime,Long personId,  String watchType);	
	/**
	 * 查询运动历史汇总记录，按月汇总
	 * @param personId
	 * @param watchId
	 * @param watchType
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<SportTotalVO> SportgroupMonth(Long personId, String watchId, String watchType, String startTime, String endTime);
	/**
	 * 查询运动历史汇总记录，按周汇总
	 * @param personId
	 * @param watchId
	 * @param watchType
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<SportTotalVO> SportgroupWeek(Long personId, String watchId, String watchType, String startTime, String endTime);
	
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
	 * 获取最新的一条运动记录
	 * 
	 * @param params
	 * @return
	 */
	public ParamSportVO getLatelySport(Map<String, Object> params);
	
	public List<ParamSportVO> getSportData(String watchid, String starttime, String endtime, int curindex, int pagesize);
	public int getSportDataCount(String watchId, String startTime, String endTime);
	
	public List<ParamSportVO> getSportData(String watchid, String starttime, String endtime, int curindex, int pagesize, Long personId, String watchType);
	public int getSportDataCount(String watchId, String startTime, String endTime, Long personId, String watchType);
	
	/**
	* @description:删除用户当前日期的运动数据
	* @param personId
	* @param dateTime
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author lijl  2014-3-14
	 */
	public Integer deleteSportData(Long personId, String dateTime);
	
	/**
	 * 获取用户运动目标、睡眠目标设置
	* @description:
	* @param personId
	* @return(设定参数)
	* @return List<PersonalSetting>(返回值说明)
	* @author Administrator  2014-6-26
	 */
	public List<PersonalSetting> getPersonSettingTarget(Long personId);

	
}
