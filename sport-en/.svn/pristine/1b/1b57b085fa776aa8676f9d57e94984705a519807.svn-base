package com.appscomm.sport.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具
 * 
 * @author kuangzhenming
 * 
 */
public class DateUtils {
	static SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 今天
	 * 
	 * @return
	 */
	public static String[] Today() {
		// From 开始时间为 昨天23时59分59秒
		Calendar from = Calendar.getInstance();
		from.add(Calendar.DATE, -1);
		from.set(Calendar.HOUR_OF_DAY, 24);
		from.set(Calendar.MINUTE, 00);
		from.set(Calendar.SECOND, 00);
		
		Calendar to = Calendar.getInstance();
		to.set(Calendar.HOUR_OF_DAY, 23);
		to.set(Calendar.MINUTE, 59);
		to.set(Calendar.SECOND, 59);
		String[] time = { f.format(from.getTime()),
//				f.format(new Date().getTime()) };
				f.format(to.getTime()) };
		return time;
	}

	/**
	 * 最近一周, -7
	 * 
	 * @return
	 */
	public static String[] Lastweek() {
		Calendar from = Calendar.getInstance();
		from.add(Calendar.DATE, -7);
		from.set(Calendar.HOUR_OF_DAY, 24);
		from.set(Calendar.MINUTE, 00);
		from.set(Calendar.SECOND, 00);
		
		Calendar to = Calendar.getInstance();
		to.set(Calendar.HOUR_OF_DAY, 23);
		to.set(Calendar.MINUTE, 59);
		to.set(Calendar.SECOND, 59);
		String[] time = { f.format(from.getTime()),
				//f.format(new Date().getTime()) };
				f.format(to.getTime()) };
		return time;
	}

	/**
	 * 最近一月
	 * 
	 * @return
	 */
	public static String[] LastMonth() {
		Calendar from = Calendar.getInstance();
		from.add(Calendar.DATE, -30);
		from.set(Calendar.HOUR_OF_DAY, 24);
		from.set(Calendar.MINUTE, 00);
		from.set(Calendar.SECOND, 00);
		
		Calendar to = Calendar.getInstance();
		to.set(Calendar.HOUR_OF_DAY, 23);
		to.set(Calendar.MINUTE, 59);
		to.set(Calendar.SECOND, 59);
		
		String[] time = { f.format(from.getTime()),
				//f.format(new Date().getTime()) };
				f.format(to.getTime()) };
		return time;
	}

	/**
	 * 最近一年
	 * 
	 * @return
	 */
	public static String[] LastYear() {
		Calendar from = Calendar.getInstance();
		from.add(Calendar.DATE, -365);
		from.set(Calendar.HOUR_OF_DAY, 24);
		from.set(Calendar.MINUTE, 00);
		from.set(Calendar.SECOND, 00);
		
		Calendar to = Calendar.getInstance();
		to.set(Calendar.HOUR_OF_DAY, 23);
		to.set(Calendar.MINUTE, 59);
		to.set(Calendar.SECOND, 59);
		
		String[] time = { f.format(from.getTime()),
			//	f.format(new Date().getTime()) };
				f.format(to.getTime()) };
		return time;
	}

	/**
	 * 全部,默认显示3年的数据
	 * 
	 * @return
	 */
	public static String[] ALL() {
		Calendar from = Calendar.getInstance();
		from.add(Calendar.DATE, -1095);
		
		Calendar to = Calendar.getInstance();
		to.set(Calendar.HOUR_OF_DAY, 23);
		to.set(Calendar.MINUTE, 59);
		to.set(Calendar.SECOND, 59);
		String[] time = { f.format(from.getTime()),
				//f.format(new Date().getTime()) };
				f.format(to.getTime()) };
		return time;
	}

	/**
	 * 搜索，开始时间 到 结束时间
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static String[] FromTo(String startTime, String endTime)
			throws ParseException {
		Date start = f.parse(startTime);
		Date end = f.parse(endTime);
		String[] time = { f.format(start), f.format(end) };
		return time;
	}

	/**
	 * 计算日期间隔（天）
	 * 
	 * @param startTime
	 * @param endTime
	 * @return long
	 * @throws ParseException
	 */
	public static long DateDiff(String startTime, String endTime)
			throws ParseException {
		Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
		Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(endTime);

		long l = date1.getTime() - date2.getTime() > 0 ? date1.getTime()
				- date2.getTime() : date2.getTime() - date1.getTime();
		// 日期相减得到相差的日期
		long day = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000) > 0 ? (date1
				.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000)
				: (date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}
/**
 * 格式化日期
 * @param date
 * @param formart
 * @return
 * @throws ParseException
 */
	public static String Formart(String date, String formart)
			throws ParseException {
		Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
		return new SimpleDateFormat(formart).format(d);

	}
	
	/**
	 * 格式化日期
	 * @param date
	 * @param formart
	 * @return
	 * @throws ParseException
	 */
	 public static String Formart(Date date, String formart)
			throws ParseException {
		return new SimpleDateFormat(formart).format(date);
	 }
	
	/**
	 * 获得日期的小时
	 * @param date
	 * @param type
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("deprecation")
	public static int GetDate(String date,int type)
			throws ParseException {
		Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
		return d.getHours();

	}
	
	
	/**
	 * 获取时间的部分
	 * @param date
	 * @param calendar  1取年   2取月   5取 日    11取 时
	 * @return
	 * @throws ParseException
	 */
		public static int getDatePart(String date, int calendar) throws ParseException {
			Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			return c.get(calendar);
		}
		/**
		 * 日期部分添加
		 * @param date
		 * @param calendar
		 * @return String
		 * @throws ParseException
		 */
		public static String datePartAdd(String date, int calendar,int amount) throws ParseException {
			Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.add(calendar, amount);
			return f.format(c.getTime());
		}
		
		/**
		 * 月份+1
		 * @param dt
		 * @return
		 * @throws ParseException
		 */
		public static String datePartAdd2(String dt) throws ParseException {
			Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dt);
			Calendar cal = Calendar.getInstance();
	        cal.setTime(d);
	        cal.add(Calendar.MONTH, 1);
	        return  f.format(cal.getTime());
		}
		
		 /**
	     * 获取当前日期是星期几<br>
	     * 
	     * @param dt
	     * @return 当前日期是星期几
		 * @throws ParseException 
	     */
	    public static String getWeekOfDate(String dt) throws ParseException {
	    	Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dt);
	        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(d);

	        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
	        if (w < 0)
	            w = 0;
	        return weekDays[w];
	    }
	
	    
	    /**
	    * 函数功能说明:字符串毫秒数转日期类型
	    * xc 
	    * 2013-11-2
	    * @param 
	    * @return   
	    * @throws
	    */
	    public static String secendsToDate(String secends)throws ParseException{
	    	long time = Long.valueOf(secends);
	    	Date d = new Date(time);
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	String ds = sdf.format(d);
	    	return ds;
	    }
	    
	
	    public static Date strToDate(String str){
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	Date date = null;
	    	try {
	    		date = sdf.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	    	return date;
	    }
	    
	    
	    public static long mulitiHours(Long current) throws ParseException{
	    	return -((strToDate(secendsToDate(current + "")).getTime() - new Date().getTime())/1000/60);
	    }
	    
	    public static String getCurrentDate(){
	    	return f.format(new Date());
	    }
	    
	    public static String getWatchTime(){
	    	Calendar c = Calendar.getInstance();
			c.add(Calendar.HOUR, 8);
			return df.format(c.getTime());
	    }
	    
	    public static long timeDifference(String dateTime){
			try {
				Date srcDate = df.parse(dateTime);
				Date system = df.parse(df.format(new Date()));
		    	long diff = Math.abs(srcDate.getTime()-system.getTime());
		    	return diff/3600000; //和系统时间相差的小时
			} catch (ParseException e) {
			}
			return 0;
	    }

	/*    public static void main(String[] args) {
			System.out.println(DateUtils.timeDifference("2014-11-10"));
			System.out.println(DateUtils.timeDifference("2014-10-30"));
		}*/
}
