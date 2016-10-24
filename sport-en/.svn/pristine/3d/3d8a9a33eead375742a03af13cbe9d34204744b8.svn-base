/**
 * Copyright lenovo-cw.com 2013. All rights reserved.
 *
 * @createDate 2013-10-23
 */
package com.appscomm.sport.utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.appscomm.sport.model.ParamSportVO;
import com.appscomm.sport.model.StatisticsVO;

/**
 * TODO 图表数据控制处理类
 * 
 * @author xiechao
 * 
 */
@SuppressWarnings("all")
public class ChartDataUtils {

	/**
	 * 格式化日期  转换日期格式
	 * @param list
	 * @param doType
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static List<StatisticsVO> formartDate(List<StatisticsVO> list ,int doType,String startTime,
			String endTime)throws ParseException {
		// 默认今天的数据 doType 今天1，最近一周2，最近一月3，最近一年4，全部5，自定义6
		String dateFormart = "HH";
		int week = 0;
		for (int i = 0; i < list.size(); i++) {
			if (doType == 1)
				dateFormart = "HH";
			else if (doType == 2)
				week = 1;
			// dateFormart = "EEEE";
			else if (doType == 3)
				dateFormart = "M-d";
			else if (doType == 4)
				dateFormart = "MM";
			else if (doType == 5)
				dateFormart = "MM";
			else if (doType == 6) {
				long day = DateUtils.DateDiff(startTime, endTime);
				if (day == 0)
					dateFormart = "HH";
				if (day > 0 && day <= 7)
					week = 1;// dateFormart = "EEEE";
				if (day > 7 && day <= 40)
					dateFormart = "M-d";
				if (day > 40 && day < 90)
					dateFormart = "MM";
				if (day > 90)
					dateFormart = "MM";
			}

			if (week == 1) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(DateUtils.strToDate(list.get(i).getEndTime()));
				int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
				list.get(i).setEndTime(String.valueOf(w));
			} else {
				list.get(i).setEndTime(DateUtils.Formart(list.get(i).getEndTime(), dateFormart));
			}

		}
		return list;
	}
	
	/**
	 * 查询今天charts数据
	 * @param list
	 * @param doType
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static List<StatisticsVO> today(List<StatisticsVO> list,
			int doType, String startTime, String endTime) throws ParseException {

		List<StatisticsVO> dayList = new ArrayList<StatisticsVO>() ;
		//默认是0
		for (int i = 0; i <= 23; i++) {
			StatisticsVO daysv = new StatisticsVO();
			String hour = String.valueOf(i);
			if(i<10){
				hour= "0" + i;
			}
			daysv.setEndTime(DateUtils.Formart("2013-10-23 " + hour + ":00:00", "yyyy-MM-dd HH:mm:ss"));//拼数据
			dayList.add(daysv);
		}
		//赋值
		for (int i = 0; i < list.size(); i++) {
			String date = list.get(i).getEndTime();
			int time = DateUtils.getDatePart(date,11);
			dayList.set(time, list.get(i));
		}
		return 	formartDate(dayList, doType, startTime, endTime);
	}
	
	/**
	 * 查询一周charts数据
	 * @param list
	 * @param doType
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static List<StatisticsVO> week(List<StatisticsVO> list,int doType, String startTime, String endTime) throws ParseException {
		List<StatisticsVO> weekList = new ArrayList<StatisticsVO>() ;
		List<String> dayOfWeek = new ArrayList<String>();
		for (int i = 1; i <= 7; i++) {
			StatisticsVO sv = new StatisticsVO();
			sv.setEndTime(startTime);//拼数据
			weekList.add(sv);
			dayOfWeek.add(DateUtils.Formart(startTime, "yyyy-MM-dd"));
			startTime = DateUtils.datePartAdd(startTime, 5,1);
		}
		//赋值
		for (int i = 0; i < list.size(); i++) {
			String date = DateUtils.Formart(list.get(i).getEndTime(),"yyyy-MM-dd");
			int index = getIndex(dayOfWeek,date);
			if(index != -1)
				weekList.set(index, list.get(i));
				
		}
		return 	formartDate(weekList, doType, startTime, endTime);
	}
	
	/**
	 * 取list下标
	 * @param dayOfWeek
	 * @param date
	 * @return
	 */
	public static int getIndex(List<String> dayOfWeek,String date){
	    for(int i=0;i<dayOfWeek.size();i++){
	    	if(dayOfWeek.get(i).equals(date))return i;
	    }
		return -1;
	}
	
	/**
	 * 查询转换一个月charts数据
	 * @param list
	 * @param doType
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static List<StatisticsVO> month(List<StatisticsVO> list,int doType, String startTime, String endTime) throws ParseException {
		List<StatisticsVO> monList = new ArrayList<StatisticsVO>() ;
		List<String> dayOfMonth = new ArrayList<String>();
		for (int i = 0; i < 31; i++) {
			StatisticsVO sv = new StatisticsVO();
			sv.setEndTime(startTime);//拼数据
			monList.add(sv);
			dayOfMonth.add(DateUtils.Formart(sv.getEndTime(), "yyyy-MM-dd"));
			startTime = DateUtils.datePartAdd(startTime, 5, 1);
		}
		
		//赋值
		for (int i = 0; i < list.size(); i++) {
			String date = DateUtils.Formart(list.get(i).getEndTime(),"yyyy-MM-dd");
			int index = getIndex(dayOfMonth,date);
			if(index != -1)
				monList.set(index, list.get(i));
		}
		return formartDate(monList, doType, startTime, endTime);
	}
	
	/**
	 * 查询转换一年charts数据
	 * @param list
	 * @param doType
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static List<StatisticsVO> year(List<StatisticsVO> list,int doType, String startTime, String endTime) throws ParseException {
		List<StatisticsVO> yearList = new ArrayList<StatisticsVO>() ;
		List<String> dayOfYear = new ArrayList<String>();
		for (int i = 0; i < 12; i++) {
			StatisticsVO sv = new StatisticsVO();
			sv.setEndTime(startTime);//拼数据
			yearList.add(sv);
			dayOfYear.add(DateUtils.Formart(sv.getEndTime(), "yyyyMM"));
			startTime = DateUtils.datePartAdd2(startTime);
		}
		
		//赋值
		for (int i = 0; i < list.size(); i++) {
			String date = DateUtils.Formart(list.get(i).getEndTime(),"yyyyMM"); 
			int index = getIndex(dayOfYear,date);
			if(index != -1)
				yearList.set(index, list.get(i));
		}
		return formartDate(yearList, doType, startTime, endTime);
	}
	
	/**
	 * 查询转换全部charts数据
	 * @param list
	 * @param doType
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static List<StatisticsVO> all(List<StatisticsVO> list,int doType, String startTime, String endTime) throws ParseException {
		List<StatisticsVO> allList = new ArrayList<StatisticsVO>() ;
		List<String> dayOfAll = new ArrayList<String>();
		for (int i = 0; i < 12; i++) {
			StatisticsVO sv = new StatisticsVO();
			sv.setEndTime(startTime);//拼数据
			allList.add(sv);
			dayOfAll.add(DateUtils.Formart(sv.getEndTime(), "MM"));
			startTime = DateUtils.datePartAdd2(startTime);
		}
		
		//赋值
		for (int i = 0; i < list.size(); i++) {
			String date = DateUtils.Formart(list.get(i).getEndTime(),"MM"); 
			int index = getIndex(dayOfAll,date);
			if(index != -1)
				allList.set(index, list.get(i));
		}
		return formartDate(allList, doType, startTime, endTime);
	}
	
	
	/**
	 * 自定义查询
	 * @param list
	 * @param doType
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static List<StatisticsVO> fromTo(List<StatisticsVO> list,int doType, String startTime, String endTime) throws ParseException {
		String dateFormart = "";
		long day = DateUtils.DateDiff(startTime, endTime);
		if(day==0){
			return today(list,1,startTime, endTime);
		} else if(day>0 && day<=7){
			return week2(list,2,startTime, endTime);
		} else if( day>7 && day<=30){
			return month(list,3,startTime, endTime);
		} else if( day>30 &&day<=365){
			return year(list,4,startTime, endTime);
		} else if( day>365){
			return all(list,5,startTime, endTime);
		} 
		return list;
	}
		
	
	
	/**
	 * 自定义查询一周算法
	 * @param list
	 * @param doType
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static List<StatisticsVO> week2(List<StatisticsVO> list,int doType, String startTime, String endTime) throws ParseException {
		List<StatisticsVO> weekList = new ArrayList<StatisticsVO>() ;
//		System.out.println(startTime);
//		startTime = DateUtils.datePartAdd(startTime, 5,-1);
//		System.out.println(startTime);
		List<String> dayOfWeek = new ArrayList<String>();
		
		for (int i = 1; i <= 7; i++) {
			StatisticsVO sv = new StatisticsVO();
			sv.setEndTime(startTime);//拼数据
			startTime = DateUtils.datePartAdd(startTime, 5,1);
			weekList.add(sv);
			dayOfWeek.add(DateUtils.Formart(sv.getEndTime(), "yyyy-MM-dd"));
		}
		//赋值
		for (int i = 0; i < list.size(); i++) {
			String date = DateUtils.Formart(list.get(i).getEndTime(),"yyyy-MM-dd");
			int index = getIndex(dayOfWeek,date);
			if(index != -1)
				weekList.set(index, list.get(i));
				
		}
		return 	formartDate(weekList, doType, startTime, endTime);
	}
	
	/**
	 * 查询今天charts数据
	 * @param list
	 * @param doType
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static List<ParamSportVO> today2(List<ParamSportVO> list,int doType, String endTime) throws ParseException {

		List<ParamSportVO> dayList = new ArrayList<ParamSportVO>() ;
		//默认是0
		for (int i = 0; i <= 23; i++) {
			ParamSportVO daysv = new ParamSportVO();
			String hour = String.valueOf(i);
			if(i<10){
				hour= "0" + i;
			}
			daysv.setEndTime(DateUtils.Formart("2013-10-23 " + hour + ":00:00", "yyyy-MM-dd HH:mm:ss"));//拼数据
			dayList.add(daysv);
		}
		//赋值
		for (int i = 0; i < list.size(); i++) {
			String date = list.get(i).getEndTime();
			int time = DateUtils.getDatePart(date,11);
			dayList.set(time, list.get(i));
		}
		return 	formartDate2(dayList, doType, endTime);
	}
	
	/**
	 * 
	 * @param list
	 * @param doType
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static List<ParamSportVO> formartDate2(List<ParamSportVO> list ,int doType,String endTime)throws ParseException {
		 String dateFormart="HH";
		  for(int i=0;i<list.size();i++){
			  if ( doType== 1)
				  dateFormart = "HH";
			   list.get(i).setEndTime( DateUtils.Formart( list.get(i).getEndTime(), dateFormart));
		  }
		  
		return list;
	}
	
	
	

}