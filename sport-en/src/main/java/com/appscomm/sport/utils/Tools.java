/**
 * Copyright appscomm.cn 2013. All rights reserved.
 *
 * @createDate 2013-9-4
 */
package com.appscomm.sport.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 *  工具类 
 *	
 *  qindf create by 2013-9-4
 *
 */
public class Tools {
	public static String templateEmailForgotPwd = null;
	public static String templateEmailRegister = null;
	public static String templateEmailNewPwd = null;
	/**
	 * 获取当前周的周一日期
	 * @return
	 */
	public static String getFirtDayOfWeeks(){
		Calendar cal =Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return df.format(cal.getTime());
	}
	
	/**
	 * 获取当前周的周末日期
	 * @return
	 */
	public static String getLastDayOfWeeks(){
		Calendar cal =Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		return df.format(cal.getTime());
	}
	
	/**
	 * 获取当前月的第一天日期
	 * @param month
	 * @return
	 */
	public static String getMonthFirstDate(){
	 	return Tools.dateToString(Tools.getSwitchDate(Tools.getCurrentMonth(),"yyyy-MM"),"yyyy-MM-dd");
	}
	
	/**
	 * 获取当前月的最后一天日期
	 * @param month
	 * @return
	 */
	public static String getMonthLastDate(){
		Date nextMonth = getSwitchDate(getNextMonth(Tools.getCurrentMonth()),"yyyy-MM");
		long lastDayMillis = nextMonth.getTime() -1;
		return Tools.dateToString(changeMillToString(lastDayMillis),"yyyy-MM-dd");
	}
	
	public static Date getSwitchDate(String startDate,String format){   
	    Date dt=null;   
	    SimpleDateFormat sdf = new SimpleDateFormat(format);;   
	    try{   
	        dt = sdf.parse(startDate);;   
	    }catch(Exception e){   
	        e.printStackTrace();;   
	    }   
	    return dt;   
	}
	
	/**
	 * 取某个月份的下一月
	 * @param date
	 * @return
	 */
	public static String getNextMonth(String date){
	    int intMonth = Integer.valueOf(date.substring(date.indexOf("-")+1));
		int intYear = Integer.valueOf(date.substring(0, date.indexOf("-")));
		intMonth = intMonth + 1;
	    if(intMonth>12){
	    	intMonth = intMonth-12;
	    	intYear = intYear + 1;
		}
	    if(intMonth<10){
	    	 return (intYear+"-0"+intMonth);
	    }
	    return (intYear+"-"+intMonth);
	}
	
	/**
	 * 将毫秒转换为对应格式的日期数据字符串
	 * @param millis
	 * @return
	 */
	public static Date changeMillToString(long millis){
		Calendar cal=Calendar.getInstance();
	    cal.setTimeInMillis(millis);
	    Date date = cal.getTime();
	    return date;
	}
	
	/**
	 * 获取当前月
	 * @return
	 */
	public static String getCurrentMonth(){
		return format(new Date(),"yyyy-MM");
	}
	
	/**
	 * 获取当前日期时间
	 * @return
	 */
	public static String getCurrentDateTime(){
		return format(new Date(),"yyyy-MM-dd HH:mm:ss");
	}
	
	public static String getCurrentDate(){
		return format(new Date(), "yyyy-MM-dd");
	}
	
	/**
	 * 格式化日期时间
	 * @param date
	 * @param format
	 * @return
	 */
	public static String format(Date date, String format) {
		String result = "";
		try {
			if (date != null) {
				java.text.DateFormat df = new java.text.SimpleDateFormat(format);
				result = df.format(date);
			}
		} catch (Exception e) {
		}
		return result;
	}
	
	public static Date addDays(Date date, int amount)
	{
	   return add(date, 5, amount);
	}
	
	public static Date add(Date date, int calendarField, int amount)
	{
	    if (date == null) {
	      throw new IllegalArgumentException("The date must not be null");
	    }
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    c.add(calendarField, amount);
	    return c.getTime();
	}
	
	/**
	 * 日期对象转换为字符串
	 * 
	 * @param date Date对象
	 * @param pattern 转换格式
	 * @return String 日期字符串
	 */
	public static String dateToString(Date date, String pattern) {
		if (null == date) return null;
		if (StringUtils.isEmpty(pattern)) pattern = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	public static Date stringToDate(String dateStr, String pattern) {
		if (StringUtils.isBlank(dateStr) || StringUtils.isBlank(pattern))
			return null;
		
		try {
			return new SimpleDateFormat(pattern).parse(dateStr);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String readFile(String fileName) throws IOException {
		StringBuffer sb = new StringBuffer();
	//InputStream in = ClassLoader.getSystemResourceAsStream(fileName);
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
		//	reader = new BufferedReader(new InputStreamReader(in));
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				sb.append(tempString);
			}
			reader.close();
		} catch (IOException e) {
			throw e;
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return sb.toString();
	}
	
	public static String getSportTableName(String startTime, String endTime){
		/*try{
			// 开始时间为空， 从全量视图中查询
			if(startTime == null){
				return "v_param_sport";
			}
			
			String[] strStartDate = startTime.split("-");
			//开始时间和结束时间相差大于一个月，则返回视图查询
			if(startTime !=null && endTime != null){
				String[] strEndDate = endTime.split("-");
				Integer iStartDate = Integer.valueOf(strStartDate[0]+strStartDate[1]+"");
				Integer iEndDate = Integer.valueOf(strEndDate[0]+strEndDate[1]+"");
				if(iEndDate >iStartDate) {
					return "v_param_sport";
				}
			}
			
			return "t_param_sport_"+strStartDate[0]+strStartDate[1];
		}catch(Exception ex){
			return "v_param_sport";
		}*/
		return "t_param_sport";
	}
	
	public static boolean isValidWatchId(String watchId){
		if(StringUtils.isBlank(watchId) || "null".equalsIgnoreCase(watchId)){
			return false;
		}
		return  Pattern.matches("^[A-Za-z0-9]+$", watchId);
	}
	public static void main(String[] ares){
	//	System.out.println(dateToString(addDays(stringToDate("2013-09-22","yyyy-MM-dd"), 1),"yyyy-MM-dd"));
//		String retStr;
//		try {
//			retStr = readFile("template_forgot_pwd.html");
//			System.out.println(retStr);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		byte[] a={1,0,0,1};
		String watchId = new String(a);
		System.out.println(watchId);
		System.out.println(isValidWatchId(watchId));
	}

}
