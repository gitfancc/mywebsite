package com.appscomm.sport.action;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.appscomm.sport.model.ParamSportVO;
import com.appscomm.sport.model.StatisticsVO;
import com.appscomm.sport.service.ParamSportService;
import com.appscomm.sport.service.SleepInfoService;
import com.opensymphony.xwork2.Action;
import com.appscomm.sport.utils.ChartDataUtils;
import com.appscomm.sport.utils.ChartDataUtilsEx;
import com.appscomm.sport.utils.DateUtils;
import com.appscomm.sport.vo.PersonalSetting;
import com.appscomm.sport.vo.SleepRecordStatistic;

public class ParamSportAction extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Logger log = Logger.getLogger(ParamSportAction.class);

	private static final long serialVersionUID = 1L;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPageIndex() {
		return currentPageIndex<1?1:currentPageIndex;
	}

	public void setCurrentPageIndex(int currentPageIndex) {
		this.currentPageIndex = currentPageIndex;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private int pageSize;
	private int currentPageIndex;
	private String startTime;
	private String endTime;
	private String time;
	private int action;
	@Autowired
	private ParamSportService paramSportService;
	@Autowired
	private SleepInfoService sleepInfoService;

	private List<ParamSportVO> result;
	private Map<String,Object> resultMap = new HashMap<String,Object>();
	private List<StatisticsVO> resultChart;
	private Map<String, Object> retMap = new HashMap<String, Object>();
	private Integer paramValue;

	public Integer getParamValue() {
		return paramValue;
	}

	public void setParamValue(Integer paramValue) {
		this.paramValue = paramValue;
	}

	public List<ParamSportVO> getResult() {
		return result;
	}

	public void setResult(List<ParamSportVO> result) {
		this.result = result;
	}
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<StatisticsVO> getResultChart() {
		return resultChart;
	}

	public void setResultChart(List<StatisticsVO> resultChart) {
		this.resultChart = resultChart;
	}
/**
 * 获取图表数据
 * @return
 * @throws Exception
 */
	public String GetChartData() throws Exception {
		
		String watchId = this.getUsers().getWatchId();
		Long personId = this.getUsers().getUserId();
		String watchType = super.getDefaultWatchType();
		
		// 默认今天的数据 今天1，最近一周2，最近一月3，最近一年4，全部5，自定义6
		String[] fromTo = DateUtils.Today();
		int doType=this.getAction();
		if ( doType== 1)
			fromTo = DateUtils.Today();
		else if (doType == 2)
			fromTo = DateUtils.Lastweek();
		else if (doType== 3)
			fromTo = DateUtils.LastMonth();
		else if (doType== 4)
			fromTo = DateUtils.LastYear();
		else if (doType== 5)
			fromTo = DateUtils.ALL();
		else if (doType == 6)
			fromTo = DateUtils.FromTo(this.getStartTime(), this.getEndTime());
		else
			fromTo = DateUtils.Today();
		
		watchType = null;
		this.resultChart = paramSportService.getStatistics(watchId, doType,  fromTo[0], fromTo[1], personId, watchType);
		
		if(doType == 1){
			resultChart = ChartDataUtils.today(resultChart, doType, fromTo[0],fromTo[1]);
		}else if(doType == 2){
			resultChart = ChartDataUtils.week(resultChart, doType, fromTo[0],fromTo[1]);
		}else if(doType == 3){
			resultChart = ChartDataUtils.month(resultChart, doType, fromTo[0],fromTo[1]);
		}else if(doType == 4){
			resultChart = ChartDataUtils.year(resultChart, doType, fromTo[0],fromTo[1]);
		}else if(doType == 5){
			resultChart = ChartDataUtils.all(resultChart, doType, fromTo[0],fromTo[1]);
		}else if(doType == 6){
			resultChart = ChartDataUtils.fromTo(resultChart, doType, fromTo[0],fromTo[1]);
		}
		
		List<SleepRecordStatistic> sleepChart = getSleepStatistic(doType, fromTo, watchId, personId, watchType);
		retMap.put("sportChart", resultChart);
		retMap.put("sleepChart", sleepChart);
		return Action.SUCCESS;
	}
	
	public List<SleepRecordStatistic> getSleepStatistic(int doType, String[] fromTo, String watchId, Long personId, String watchType) throws ParseException{
		List<SleepRecordStatistic> srsList =sleepInfoService.getSleepTotal(watchId, doType, fromTo[0], fromTo[1], personId, watchType);
		if(doType == 1){
			srsList = ChartDataUtilsEx.today(srsList, doType, fromTo[0],fromTo[1]);
		}else if(doType == 2){
			srsList = ChartDataUtilsEx.week(srsList, doType, fromTo[0],fromTo[1]);
		}else if(doType == 3){
			srsList = ChartDataUtilsEx.month(srsList, doType, fromTo[0],fromTo[1]);
		}else if(doType == 4){
			srsList = ChartDataUtilsEx.year(srsList, doType, fromTo[0],fromTo[1]);
		}else if(doType == 5){
			srsList = ChartDataUtilsEx.all(srsList, doType, fromTo[0],fromTo[1]);
		}else if(doType == 6){
			srsList = ChartDataUtilsEx.fromTo(srsList, doType, fromTo[0],fromTo[1]);
		}
		return srsList;
	}
	
	public String getSleepList() throws ParseException{
		String watchId = this.getUsers().getWatchId();
		Long personId = this.getUsers().getUserId();
		String watchType = super.getDefaultWatchType();
		String[] fromTo = DateUtils.Today();
		int doType=this.getAction();
		if ( doType== 1)
			fromTo = DateUtils.Today();
		else if (doType == 2)
			fromTo = DateUtils.Lastweek();
		else if (doType== 3)
			fromTo = DateUtils.LastMonth();
		else if (doType== 4)
			fromTo = DateUtils.LastYear();
		else if (doType== 5)
			fromTo = DateUtils.ALL();
		else if (doType == 6)
			fromTo = DateUtils.FromTo(this.getStartTime(), this.getEndTime());
		else
			fromTo = DateUtils.Today();
		
		watchType = null;
		List<SleepRecordStatistic> srsList =sleepInfoService.getSleepList(watchId, fromTo[0], fromTo[1], this.getCurrentPageIndex(), this.getPageSize(), personId, watchType);
		int recordCount = sleepInfoService.getSleepCount(watchId, fromTo[0], fromTo[1], personId, watchType);
		this.resultMap.put("sleepData", srsList);
		this.resultMap.put("sleepCount", recordCount);
		return Action.SUCCESS;
	}
	
/**
 * 获取日志数据
 * @return
 * @throws Exception
 */
	public String GetSportData() throws Exception {
		String watchId = this.getUsers().getWatchId();
		Long personId = this.getUsers().getUserId();
		String watchType = super.getDefaultWatchType();
		// 默认今天的数据 今天1，最近一周2，最近一月3，最近一年4，全部5，自定义6
		String[] fromTo = DateUtils.Today();
		int doType=this.getAction();
		if ( doType== 1)
			fromTo = DateUtils.Today();
		else if (doType == 2)
			fromTo = DateUtils.Lastweek();
		else if (doType== 3)
			fromTo = DateUtils.LastMonth();
		else if (doType== 4)
			fromTo = DateUtils.LastYear();
		else if (doType== 5)
			fromTo = DateUtils.ALL();
		else if (doType == 6)
			fromTo = DateUtils.FromTo(this.getStartTime(), this.getEndTime());
		else
			fromTo = DateUtils.Today();
		
		watchType = null;
		this.result = paramSportService.getList(watchId, fromTo[0],	fromTo[1], this.getCurrentPageIndex(), this.getPageSize(), personId, watchType);
		this.resultMap.put("data", this.result );
		int recordCount=paramSportService.getCount(watchId,  fromTo[0], fromTo[1], personId, watchType);
		this.resultMap.put("count",recordCount );

		return Action.SUCCESS;
	}
	
	/**
	* 函数功能说明
	* Administrator 
	* 2013-11-6
	* @param 
	* @return   
	* @throws
	*/
	public void sumStepsByWatchId(){
		log.info("进入sumStepsByWatchId()方法...");
		String watchId = super.getUsers().getWatchId();
		Long personId = super.getUsers().getUserId();
		String watchType = super.getDefaultWatchType();
		//log.info(watchId);
		watchType = null;
		Long steps = this.paramSportService.sumStepsByWatchId(watchId, personId, watchType);
		super.writeJson(steps);
	}
	
	public void getSportTarget(){
		Long personId = super.getUsers().getUserId();
		List<PersonalSetting> pslist = this.paramSportService.getPersonSettingTarget(personId);
		super.writeJson(pslist);
	}
	
public Map<String,Object> getResultMap() {
	return resultMap;
}

public void setResultMap(Map<String,Object> resultMap) {
	this.resultMap = resultMap;
}

public Map<String, Object> getRetMap() {
	return retMap;
}

public void setRetMap(Map<String, Object> retMap) {
	this.retMap = retMap;
}
	
}
