package com.appscomm.sport.action;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.joda.time.DateTime;
import org.joda.time.DateTime.Property;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;

import com.appscomm.sport.model.UserVO;
import com.appscomm.sport.service.SleepInfoService;
import com.appscomm.sport.utils.ChartDataUtils;
import com.appscomm.sport.utils.ChartDataUtilsEx;
import com.appscomm.sport.utils.DateUtils;
import com.appscomm.sport.utils.StringUtil;
import com.appscomm.sport.vo.SleepRecord;
import com.appscomm.sport.vo.SleepRecordDetail;
import com.appscomm.sport.vo.SleepRecordStatistic;
import com.appscomm.sport.vo.SleepRecordVo;

import freemarker.template.utility.DateUtil;

@SuppressWarnings("all")
@ParentPackage(value="secure")
@Namespace("/sleepInfo")
@Action(value="sleepInfoAction")
public class SleepInfoAction extends BaseAction{
	
	@Autowired
	private SleepInfoService sleepInfoService;
		
	public void getSleepRecord() throws ParseException{
		UserVO user = super.getUsers();
		Map<String,Object> map = super.getMapByRequest(request);
		map.put("personId",user.getUserId());
		map.put("deviceType", super.getDefaultWatchType());
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		long smillionSeconds = StringUtil.dateToMillsecond(map.get("time").toString().replaceAll("/", "-"));
		long emillionSeconds =smillionSeconds+24*60*60*1000;
		map.put("startDate", smillionSeconds);
		map.put("endDate", emillionSeconds);
		SleepRecordVo sr = sleepInfoService.getSleepRecord(map);
		List<SleepRecord> list = sr.getData();
		if(list.size()!=0 && !list.isEmpty()){
			for(SleepRecord s : list){
				String end = convertStrToDate(s.getEndTime().toString(),"EEE MMM dd HH:mm:ss zzz yyyy","yyyy-MM-dd HH:mm:ss", Locale.US);
				String start = convertStrToDate(s.getStartTime().toString(),"EEE MMM dd HH:mm:ss zzz yyyy","yyyy-MM-dd HH:mm:ss", Locale.US);
				s.setStarttime(StringUtil.dateToMillSecond(start));
				s.setEndtime(StringUtil.dateToMillSecond(end));
				List<SleepRecordDetail> srd = s.getDetails();
				if(srd.size()!=0 && ! srd.isEmpty()){
					for(int i=0;i<srd.size();i++){
						String s2 = srd.get(i).getStartTime().toString();
						String start2 = convertStrToDate(s2,"EEE MMM dd HH:mm:ss zzz yyyy","yyyy-MM-dd HH:mm:ss", Locale.US);
						srd.get(i).setStarttime(StringUtil.dateToMillSecond(start2));
					}
				}
			}
		}
		super.writeJson(sr);
	}

	/**
	 * 获取睡眠统计信息
	 * @throws ParseException 
	 */
	public void getSleepTotal() throws ParseException {
		UserVO user = super.getUsers();
		Map<String,Object> map = super.getMapByRequest(request);
		String watchId = this.getUsers().getWatchId();
		Long personId = this.getUsers().getUserId();
		String watchType = super.getDefaultWatchType();
		
		String[] fromTo = DateUtils.Today();
		int doType=  Integer.valueOf(map.get("qryType").toString());
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
			fromTo = DateUtils.FromTo(map.get("startDate").toString(), map.get("endDate").toString());
		else
			fromTo = DateUtils.Today();
		
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
		super.writeJson(srsList);
	}
	
	public void getSleepList() throws ParseException{
		Map<String,Object> map = super.getMapByRequest(request);
		
		String watchId = this.getUsers().getWatchId();
		Long personId = this.getUsers().getUserId();
		String watchType = super.getDefaultWatchType();
		int currentPageIndex = Integer.valueOf(map.get("currentPageIndex").toString());
		int pageSize =  Integer.valueOf(map.get("pageSize").toString());
		
		String[] fromTo = DateUtils.Today();
		int doType=  Integer.valueOf(map.get("qryType").toString());
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
			fromTo = DateUtils.FromTo(map.get("startDate").toString(), map.get("endDate").toString());
		else
			fromTo = DateUtils.Today();
		
		List<SleepRecordStatistic> srsList =sleepInfoService.getSleepList(watchId, fromTo[0], fromTo[1], currentPageIndex, pageSize, personId, watchType);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		resultMap.put("data", srsList );
		int recordCount = sleepInfoService.getSleepCount(watchId, fromTo[0], fromTo[1], personId, watchType);
		resultMap.put("count",recordCount );
		super.writeJson(resultMap);
	}
	
	public void getLastSyncTime(){
		String watchId = this.getUsers().getWatchId();
		Long personId = this.getUsers().getUserId();
		String watchType = super.getDefaultWatchType();
		Date lastSyncTime = sleepInfoService.getLastSyncTime(personId, watchId);
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		if(lastSyncTime != null)
			super.writeJson(sdf.format(lastSyncTime));
		else
			super.writeJson("");
	}
    /** 
     * 根据传入的格式，日期化字符串 
     */  
    private String convertStrToDate(String dateStr, String source,String target,Locale locale) {  
        SimpleDateFormat format = new SimpleDateFormat(source, locale);  
        SimpleDateFormat formatNew = new SimpleDateFormat(target, locale);  
        Date date = null;  
        try {  
            date = format.parse(dateStr);  
        } catch (Exception e) {  
            e.printStackTrace();
        }  
        return formatNew.format(date);  
    }  
	
    /**
    * 计算时间差（分钟）
    * 2013-12-18
    * @param 
    * @return   
    * @throws
     */
    private Long getDiffMunites(String start,String end){
    	DateTimeFormatter format = DateTimeFormat .forPattern("yyyy-MM-dd HH:mm:ss");
		DateTime startDate = DateTime.parse(start, format); 
		DateTime endDate = DateTime.parse(end, format); 
		Duration duration = new Duration(startDate,endDate);//计算时间差
		long diffMunites = duration.getStandardMinutes();
    	return diffMunites;
    }
}
