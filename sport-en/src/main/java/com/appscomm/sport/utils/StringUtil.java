package com.appscomm.sport.utils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.joda.time.DateTime;

public class StringUtil {
	public static final int TYPE_REMIND_STAY = 1;
	public static final int TYPE_REMIND_NOTIFY = 2;
	public static final int TYPE_REMIND_SLEEP = 3;
	public static final int TYPE_DEVICE_SYNC = 4;
	// public static final String phpRedirect = "http://192.168.2.10:82";
	/**
	 * 日期转换成秒
	 * 
	 * @param date
	 * @return
	 */
	public static Integer dateToSecond(String date) {
		Integer millsecond = 0;
		if (date != null && !"".equals(date)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				millsecond = Integer.parseInt(String.valueOf(sdf.parse(date)
						.getTime() / 1000));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return millsecond;
	}
	
	public static long dateToMillsecond(String date){
		long millsecond = 0;
		if (date != null && !"".equals(date)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				millsecond = sdf.parse(date).getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return millsecond;
	}

	
	/**
	 * 日期转换成毫秒
	 * 
	 * @param date
	 * @return
	 */
	public static Long dateToMillSecond(String date) {
		Long millsecond = 0L;
		if (date != null && !"".equals(date)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				millsecond = Long.parseLong(String.valueOf(sdf.parse(date)
						.getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return millsecond;
	}
	
	// 过滤特殊字符
	public static String StringFilter(String str) throws PatternSyntaxException {
		// 只允许字母和数字
		// String regEx = "[^a-zA-Z0-9]";
		// 清除掉所有特殊字符
//		if(str!=null && !str.equals("")){
//			String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
////			String regEx = "[<>]";
//			Pattern p = Pattern.compile(regEx);
//			Matcher m = p.matcher(str);
//			return m.replaceAll("").trim();
//		}
		return str;
	}

	public static void main(String[] args) {
//		String str = "*adCVs*34_a _09_b5*[/435^*&城池<>()<script>^$$&*).{}+.|.)%%*(*.中国}34{45[]12.fd'*&999下面是中文的字符￥……{}【】。，；’“‘”？";
//		String str = "<script>alert(4444)</script>";
//		System.out.println(str);
//		System.out.println(StringFilter(str));
		
		System.out.println(dateToMillSecond("2013-12-28"));
		
//		DateTime startDate = new DateTime(2013, 12, 6, 0, 0);
//		System.out.println(startDate.getMillis());
	}

	/**
	 * 秒转换成日期
	 * 
	 * @param second
	 * @return
	 */
	public static String secondToDate(long second) {
		Date date = new Date(second * 1000);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		return time;
	}

	public static String secondToYYR(long second) {
		Date date = new Date(second * 1000);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String time = format.format(date);
		return time;
	}

	/**
	 * 判断文件夹是否存在
	 * 
	 * @param path
	 */
	public static void isExist(String path) {
		File file = new File(path);
		// 判断文件夹是否存在,如果不存在则创建文件夹
		if (!file.exists()) {
			file.mkdir();
		}
	}

	/**
	 * 判断字符串不为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotNull(String str) {
		boolean flag = false;
		if (str != null && !"".equals(str)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 判断字符串为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		boolean flag = false;
		if (str == null || "".equals(str)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 把标签转为特殊插入数据库
	 * 
	 * @param replaceHtml
	 * @return
	 */
	public static String replaceHtml(String replaceHtml) {
		String context = "";
		context = replaceHtml.replaceAll("&", "&amp;");
		context = context.replaceAll("<", "&lt;");
		context = context.replaceAll(">", "&gt;");
		context = context.replaceAll("\"", "&quot;");
		return context;
	}

	/**
	 * 特殊的转为标签显示在数据库
	 * 
	 * @param replaceHtml
	 * @return
	 */
	public static String replaceChange(String replaceHtml) {
		String context = "";
		context = replaceHtml.replaceAll("&amp;", "&");
		context = context.replaceAll("&lt;", "<");
		context = context.replaceAll("&gt;", ">");
		context = context.replaceAll("&quot;", "\"");
		return context;
	}

	public static boolean checkObj(Object obj) {
		boolean bool = true;
		if (obj == null || "".equals(obj.toString().trim()))
			bool = false;
		return bool;
	}

	public static String convertByte(String str) {
		String value = "";
		try {
			if (StringUtil.isNotNull(str)) {
				value = new String(str.getBytes("iso8859-1"), "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 数组转换成字符串
	 * 
	 * @param array
	 * @return
	 */
	public static String arrayToString(String[] array) {
		StringBuffer sbStr = new StringBuffer();
		String str = "";
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				sbStr.append(array[i]).append(",");
			}
			str = sbStr.toString();
			if (StringUtil.isNotNull(str)) {
				str = str.substring(0, str.length() - 1);
			}
		}
		return str;
	}

	/**
	 * 字符串转换成数组
	 * 
	 * @param str
	 * @return
	 */
	public static String[] stringToArray(String str) {
		String[] strArray = null;
		if (StringUtil.isNotNull(str)) {
			strArray = str.split(",");
		}
		return strArray;
	}

	/**
	 * ajax提交返回gson
	 */
	public static void writeStream(String str, String ecode) {
		HttpServletResponse response = ServletActionContext.getResponse();
		DataOutputStream out;
		try {
			out = new DataOutputStream(response.getOutputStream());
			out.write(str.getBytes(ecode));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 得到上传文件的大小
	 * 
	 * @param dirName
	 * @return
	 */
	static double dirlength;

	public static String getUploadDirSize(String dirName) {
		File dir = new File(dirName);
		if (dir.isDirectory())// 判断是否是目录,如果不是退出程序
		{
			sdl(dirName);// 计算目录大小
			DecimalFormat df = new DecimalFormat("0.00");
			return df.format(dirlength / (1024 * 1024 * 1.0));
		} else {
			return "0";
		}
	}

	private static void sdl(String dirname) {
		File dir = new File(dirname);
		System.out.println(dirname);
		String f[] = dir.list();
		File f1;
		for (int i = 0; i < f.length; i++) {
			f1 = new File(dirname + "/" + f[i]);
			if (!f1.isDirectory())
				dirlength += f1.length();
			else
				sdl(dirname + "/" + f[i]);// 如果是目录,递归调用
		}
	}

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */
	public static double doubleAdd(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 两个Double数相减
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double doubleSub(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 10;

	// 提供（相对）精确的除法运算。当发生除不尽的情况时，由DEF_DIV_SCALE参数指定精度，精确到 小数点以后10位，以后的数字四舍五入

	/**
	 * 两个Double数相除
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double doubleDiv(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
	}

	/**
	 * 得到现在的日期
	 * 
	 * @return
	 */
	public static String getNowDate() {
		Long second = System.currentTimeMillis() / 1000;
		String nowDate = StringUtil.secondToYYR(second);
		return nowDate;
	}

	public static Long getNowSecond() {
		Long second = System.currentTimeMillis() / 1000;
		return second;
	}

	/**
	 * 合并数组取差集
	 * 
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static String[] minus(String[] arr1, String[] arr2) {
		LinkedList<String> list = new LinkedList<String>();
		LinkedList<String> history = new LinkedList<String>();
		String[] longerArr = arr1;
		String[] shorterArr = arr2;
		// 找出较长的数组来减较短的数组
		if (arr1.length > arr2.length) {
			longerArr = arr2;
			shorterArr = arr1;
		}
		for (String str : longerArr) {
			if (!list.contains(str)) {
				list.add(str);
			}
		}
		for (String str : shorterArr) {
			if (list.contains(str)) {
				history.add(str);
				list.remove(str);
			} else {
				if (!history.contains(str)) {
					list.add(str);
				}
			}
		}

		String[] result = {};
		return list.toArray(result);
	}

	/*
	 * stringBuffer转化成string
	 */
	public static String sbToStr(Object sb) {
		String str = "";
		if (sb != null) {
			str = sb.toString();
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}

	/*
	 * 直接获取config.properties配置项，不用每次都取Config
	 */
	public static String getProperty(String key) {
		return Config.getProperty(key);
	}

	/*
	 * 重载,直接获取config.properties配置项，不用每次都取Config,如果取不到给一个默认值
	 */
	public static String getProperty(String key, String defaultValue) {
		return Config.getProperty(key, defaultValue);
	}

	public static Properties Config = PropertiesInit.getProperties();

	/*
	 * 静态内部类，初始化配置文件config.properties，不依赖web.xml加载，防止加载顺序问题导致取不到值
	 */
	private static class PropertiesInit {

		private static Properties p = null;

		public static synchronized Properties getProperties() {
			if (p == null) {
				p = new Properties();
				try {
					p.load(PropertiesInit.class
							.getResourceAsStream("/config.properties"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return p;
		}
	}

}
