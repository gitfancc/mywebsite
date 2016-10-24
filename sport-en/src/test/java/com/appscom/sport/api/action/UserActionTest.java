package com.appscom.sport.api.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.appscomm.sport.utils.DateUtils;
import com.appscomm.sport.utils.HttpUtils;
import com.appscomm.sport.utils.ImageUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class UserActionTest {
	private boolean DEBUG = true;
	
	 private List<Header> getHeader(){
	    	List<Header> hl = new ArrayList<Header>();
			hl.add(new BasicHeader("oauthToken", "000000"));
			hl.add(new BasicHeader("clientType", "00"));
			hl.add(new BasicHeader("version", "1.0.0"));
			hl.add(new BasicHeader("weblogid", "1"));
			return hl;
	    }
	    
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Test
		public void testInfo(){
			String registerId = "1";
		/*	StringBuilder sb = new StringBuilder();
			sb.append("registerId").append("=").append(registerId);
			String jsonData = sb.toString();*/
			List list = new ArrayList();
			list.add(new BasicNameValuePair("registerId", registerId));
			
			try{
				String responseStr = null;
					responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/get_user_info", getHeader(),  list);
				System.out.println(" Response >>> " + responseStr);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Test
		public void testSet(){
			String id = "1656";
			StringBuilder sb = new StringBuilder();
			String userName = "qaz";
			String nickName = "hoho";
			String gender = "1";
			String birthDate = "2010-01-01";
			String telephone = "13536958214";
			String email = "2222222@qq.com";
			String 	qq = "123455";
			String weibo = "d2242";
			String imgUrl = "";
			String city = "440000|440100|440116";
			
			String height = "170.2";
			String weight = "76.1";
			String heightUnit = "0";
			String weightUnit = "0";
			
			sb.append("{");
			sb.append("\"").append("userId").append("\"").append(":").append("\"").append(id).append("\"").append(",");
			sb.append("\"").append("userName").append("\"").append(":").append("\"").append(userName).append("\"").append(",");
			sb.append("\"").append("nickName").append("\"").append(":").append("\"").append(nickName).append("\"").append(",");
			sb.append("\"").append("gender").append("\"").append(":").append("\"").append(gender).append("\"").append(",");
			sb.append("\"").append("birthDay").append("\"").append(":").append("\"").append(birthDate).append("\"").append(",");
			sb.append("\"").append("telephone").append("\"").append(":").append("\"").append(telephone).append("\"").append(",");
			sb.append("\"").append("email").append("\"").append(":").append("\"").append(email).append("\"").append(",");
		//	sb.append("\"").append("QQ").append("\"").append(":").append("\"").append(qq).append("\"").append(",");
		//	sb.append("\"").append("weibo").append("\"").append(":").append("\"").append(weibo).append("\"").append(",");
		//	sb.append("\"").append("imgUrl").append("\"").append(":").append("\"").append(imgUrl).append("\"").append(",");
		//	sb.append("\"").append("city").append("\"").append(":").append("\"").append(city).append("\",");
			
			sb.append("\"").append("height").append("\"").append(":").append("\"").append(height).append("\",");
			sb.append("\"").append("weight").append("\"").append(":").append("\"").append(weight).append("\",");
			sb.append("\"").append("heightUnit").append("\"").append(":").append("\"").append(heightUnit).append("\",");
			sb.append("\"").append("weightUnit").append("\"").append(":").append("\"").append(weightUnit).append("\"");
			sb.append("}");

			List list = new ArrayList();
			list.add(new BasicNameValuePair("userInfo", sb.toString()));
			try{
				String responseStr = null;
				if (DEBUG)
					responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/set_user_info", getHeader(),  list);
				else
					responseStr = HttpUtils.sendRequest("http://ledong.appscomm.cn/sport/api/set_user_info", getHeader(),  list);
				
				System.out.println(" Response >>> " + responseStr);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Test
		public void testDistrictInfo(){
			String districtId = "0";
				List list = new ArrayList();
				list.add(new BasicNameValuePair("districtId", districtId));
				
				try{
					String responseStr = null;
					if (DEBUG)
						responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/get_district_info", getHeader(),  list);
					else
						responseStr = HttpUtils.sendRequest("http://ledong.appscomm.cn/sport/api/get_district_info", getHeader(),  list);
					
					System.out.println(" Response >>> " + responseStr);
				}catch(Exception e){
					e.printStackTrace();
				}
		}
	
		@Test
		public void testSetAdvanceSetting(){
			List list = new ArrayList();
			list.add(new BasicNameValuePair("personId", "100"));
			list.add(new BasicNameValuePair("watchId", "1zxsssssa"));
			list.add(new BasicNameValuePair("timeMode", "1"));	
			list.add(new BasicNameValuePair("lengthUnit", "1"));
			list.add(new BasicNameValuePair("weightUnit", "1"));
			
			try{
				String responseStr = null;
				if (DEBUG)
					responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/set_advanced_settings", getHeader(),  list);
				
				System.out.println(" Response >>> " + responseStr);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		@Test
		public void testGetAdvanceSetting(){
			List list = new ArrayList();
			list.add(new BasicNameValuePair("personId", "100"));
			list.add(new BasicNameValuePair("watchId", "1zxsssssa"));
			
			try{
				String responseStr = null;
				if (DEBUG)
					responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/get_advanced_settings", getHeader(),  list);
				System.out.println(" Response >>> " + responseStr);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		@Test
		public void testResetDevice(){
			List list = new ArrayList();
			list.add(new BasicNameValuePair("account", "eagke0@163.com"));
			list.add(new BasicNameValuePair("watchId", "FCL28C13071288000014"));
			try{
				String responseStr = null;
				if (DEBUG)
					responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/reset_device", getHeader(),  list);
				System.out.println(" Response >>> " + responseStr);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
		@Test
		public void testSetUserCountry(){
			List list = new ArrayList();
			list.add(new BasicNameValuePair("userId", "2073"));
			list.add(new BasicNameValuePair("countryCode", "4"));
			list.add(new BasicNameValuePair("optType", "0"));
			try{
				String responseStr = null;
				if (DEBUG)
					responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/sync_user_country", getHeader(),  list);
				System.out.println(" Response >>> " + responseStr);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	@Test
	public void testDate() {
		String date = "2014-08-01 00:00:00";
		String dateFormart = "HH";
		String dateFormart3 = "M-d";
		String dateFormart4 = "MM";
		String dateFormart5 = "E";
		String dateFormart6 = "W";
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		Date d;
		try {
			d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
			System.out.println(new SimpleDateFormat(dateFormart).format(d));
			System.out.println(new SimpleDateFormat(dateFormart3).format(d));
			System.out.println(new SimpleDateFormat(dateFormart4).format(d));
			System.out.println(new SimpleDateFormat(dateFormart5).format(d));
			System.out.println(new SimpleDateFormat(dateFormart6).format(d));
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
			System.out.println(weekDays[w]);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testUploadImg(){
		String fileName = "E:\\test-img\\QQ截图20141213101928.png";
		File file = new File(fileName);
		try {
			String userId = "2073";
			//String resp = HttpUtils.sendRequest("http://app-zecircle.mykronoz.com/sport/api/set_upload_img", getHeader(),  userId, "photo", file);
			//String resp = HttpUtils.sendRequest("http://localhost:8080/sport/api/set_upload_img", getHeader(),  list);
			String resp = HttpUtils.sendRequest("http://app-zecircle.mykronoz.com/sport/api/set_upload_img", getHeader(),  userId, "photo", file);
			System.out.println(" Response >>> " + resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetUploadImg(){
		List list = new ArrayList();
		list.add(new BasicNameValuePair("userId", "2073"));
		try{
			String responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/get_upload_img", getHeader(),  list);
			System.out.println(" Response >>> " + responseStr);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUploadImgBase64(){
	      //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	        String imgFile = "E:\\test-img\\111.jpg";
	        InputStream in = null;
	        byte[] data = null;
	        try  {
	        	  //读取图片字节数组
	            in = new FileInputStream(imgFile);        
	            data = new byte[in.available()];
	            in.read(data);
	            in.close();
	        } 
	        catch (IOException e)  {
	            e.printStackTrace();
	        }
	        //对字节数组Base64编码
	        BASE64Encoder encoder = new BASE64Encoder();
	        //返回Base64编码过的字节数组字符串
	        String imgCodeStr =  encoder.encode(data);
	        
	        //发送请求
	        List list = new ArrayList();
			list.add(new BasicNameValuePair("userId", "0"));
			list.add(new BasicNameValuePair("image", imgCodeStr));
			list.add(new BasicNameValuePair("imageSuffix", "jpg"));
			String responseStr = "";
			try {
				responseStr = HttpUtils.sendRequest("http://app-zecircle.mykronoz.com/sport/api/set_upload_img_ex", getHeader(),  list);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(" Response >>> " + responseStr);
	        
	}
	
	@Test
	public void testBase64(){
		 String imgFile = "E:\\test-img\\111.jpg";
	        InputStream in = null;
	        byte[] data = null;
	        try  {
	        	  //读取图片字节数组
	            in = new FileInputStream(imgFile);        
	            data = new byte[in.available()];
	            in.read(data);
	            in.close();
	        } 
	        catch (IOException e)  {
	            e.printStackTrace();
	        }
	        //对字节数组Base64编码
	        BASE64Encoder encoder = new BASE64Encoder();
	        //返回Base64编码过的字节数组字符串
	      System.out.println("ImgBase64:" + encoder.encode(data));
	}
	
	@Test
	public void testImg(){
		String photo = "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";
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] imgBytes;
		try {
			imgBytes = decoder.decodeBuffer(photo);

			for (int i = 0; i < imgBytes.length; ++i) {
				if (imgBytes[i] < 0) {
					imgBytes[i] += 256; // 调整异常数据
				}
			}

			System.out.println(ImageUtil.saveFile(imgBytes, "jpg", "e:\\img"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMd5() throws ParseException{
	/*	System.out.println(DigestUtils.md5Hex("123456"));
		System.out.println(DigestUtils.md5Hex("123456789123456789"));
		System.out.println(DigestUtils.md5Hex("abcdefghijk"));*/
		
		String dd = "2015-04-23 13:03:24";
		System.out.println(DateUtils.timeDifference(dd));
		System.out.println(DateUtils.Formart(dd, "yyyy-MM-dd"));
	}
	
	
	public static void main(String[] args) {
		String t1 = "2015-09-07 08:30:00";
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Timestamp tp = new Timestamp(f.parse(t1).getTime());
			System.out.println(tp.getTimezoneOffset());
			System.out.println(tp.getTime());
			
			//180
			//1441625400000
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
