package com.appscom.sport.api.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import com.appscomm.sport.utils.HttpUtils;

public class RemaindActionTest {
	private boolean DEBUG = true;
	
	 private List<Header> getHeader(){
	    	List<Header> hl = new ArrayList<Header>();
			hl.add(new BasicHeader("oauthToken", "000000"));
			hl.add(new BasicHeader("clientType", "00"));
			hl.add(new BasicHeader("version", "1.0.0"));
			hl.add(new BasicHeader("weblogid", "1"));
			return hl;
	    }
	 
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Test
		public void testAddRemindInfo() {
			String userId = "27";
			String watchId = "w1000066";
			String repeat = "0101010";
			String time = "04:10";
			String type = "1111111"; //自定义
			String text = "运动提醒";  //介绍
			String detail = "hoho 开始跑步喽"; //详细描述
			String status = "1";  // 1.开启0关闭
			String doType = "1"; //0:删除1:添加2:更新

			try {
				List list = new ArrayList();
				list.add(new BasicNameValuePair("userId", userId));
				list.add(new BasicNameValuePair("watchId", watchId));
				list.add(new BasicNameValuePair("repeat", repeat));
				list.add(new BasicNameValuePair("time", time));
				list.add(new BasicNameValuePair("type", type));
				list.add(new BasicNameValuePair("text", text));
				list.add(new BasicNameValuePair("detail", detail));
				list.add(new BasicNameValuePair("status", status));
				list.add(new BasicNameValuePair("doType", doType));
				
				String responseStr= 
						HttpUtils.sendRequest("http://localhost:8080/sport/api/add_remind_info", getHeader(),  list);
				
				System.out.println(" Response >>> " + responseStr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Test
		public void testModifyRemindInfo() {
			String userId = "27";
			String watchId = "w10003";
			String repeat = "0101010";
			String time = "04:10";
			String type = "11111001"; //自定义
			String text = "运动提醒";  //介绍
			String detail = "hoho 修改下"; //详细描述
			String status = "1";  // 1.开启0关闭
			String doType = "0"; //0:删除1:添加2:更新

			try {
				List list = new ArrayList();
				list.add(new BasicNameValuePair("userId", userId));
				list.add(new BasicNameValuePair("watchId", watchId));
				list.add(new BasicNameValuePair("repeat", repeat));
				list.add(new BasicNameValuePair("time", time));
				list.add(new BasicNameValuePair("type", type));
				list.add(new BasicNameValuePair("text", text));
				list.add(new BasicNameValuePair("detail", detail));
				list.add(new BasicNameValuePair("status", status));
				list.add(new BasicNameValuePair("doType", doType));
				
				String responseStr = null;
				if (DEBUG)
					responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/add_remind_info", getHeader(),  list);
				else
					responseStr = HttpUtils.sendRequest("http://ledong.appscomm.cn/sport/api/add_remind_info", getHeader(),  list);
				
				System.out.println(" Response >>> " + responseStr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Test
		public void testGetRemindInfo(){
			String userId = "27";
			String watchId = "w10003";
			
			try {
				List list = new ArrayList();
				list.add(new BasicNameValuePair("userId", userId));
				list.add(new BasicNameValuePair("watchId", watchId));
				
				String responseStr = null;
				if (DEBUG)
					responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/get_remind_info", getHeader(),  list);
				else
					responseStr = HttpUtils.sendRequest("http://ledong.appscomm.cn/sport/api/get_remind_info", getHeader(),  list);
				
				System.out.println(" Response >>> " + responseStr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Test
		public void testDelRemindInfo(){
			String userId = "27";
			String watchId = "w10003";
			String repeat = "0101010";
			String time = "04:10";
			String type = "1111111"; //自定义
			String text = "运动提醒";  //介绍
			String detail = "hoho 开始跑步喽"; //详细描述
			String status = "1";  // 1.开启0关闭
			String doType = "0"; //0:删除1:添加2:更新

			try {
				List list = new ArrayList();
				list.add(new BasicNameValuePair("userId", userId));
				list.add(new BasicNameValuePair("watchId", watchId));
				list.add(new BasicNameValuePair("repeat", repeat));
				list.add(new BasicNameValuePair("time", time));
				list.add(new BasicNameValuePair("type", type));
				list.add(new BasicNameValuePair("text", text));
				list.add(new BasicNameValuePair("detail", detail));
				list.add(new BasicNameValuePair("status", status));
				list.add(new BasicNameValuePair("doType", doType));
				
				String responseStr = null;
				if (DEBUG)
					responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/add_remind_info", getHeader(),  list);
				else
					responseStr = HttpUtils.sendRequest("http://ledong.appscomm.cn/sport/api/add_remind_info", getHeader(),  list);
				
				System.out.println(" Response >>> " + responseStr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Test
		public void testModifyStatus(){
			String remindId = "1";
			String status = "1";  // 1.开启0关闭

			try {
				List list = new ArrayList();
				list.add(new BasicNameValuePair("id", remindId));
				list.add(new BasicNameValuePair("status", status));
				
				String responseStr = null;
				if (DEBUG)
					responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/modify_remind_status", getHeader(),  list);
				else
					responseStr = HttpUtils.sendRequest("http://ledong.appscomm.cn/sport/api/modify_remind_status", getHeader(),  list);
				
				System.out.println(" Response >>> " + responseStr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}
