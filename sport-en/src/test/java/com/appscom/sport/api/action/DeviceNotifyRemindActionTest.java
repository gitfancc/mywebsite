package com.appscom.sport.api.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import com.appscomm.sport.utils.HttpUtils;

public class DeviceNotifyRemindActionTest {
	 private List<Header> getHeader(){
	    	List<Header> hl = new ArrayList<Header>();
			hl.add(new BasicHeader("oauthToken", "000000"));
			hl.add(new BasicHeader("clientType", "00"));
			hl.add(new BasicHeader("version", "1.0.0"));
			hl.add(new BasicHeader("weblogid", "1"));
			return hl;
	    }
	 
	 @Test
	public void testSetDeviceVersion() {
		String watchId = "FCL111222335";
		String firmwareName = "aAc2244";
		String firmwareVersion = "1.0.1";

		try {
			List list = new ArrayList();
			list.add(new BasicNameValuePair("watchId", watchId));
			list.add(new BasicNameValuePair("firmwareName", firmwareName));
			list.add(new BasicNameValuePair("firmwareVersion", firmwareVersion));
			list.add(new BasicNameValuePair("UDID", "33333asdasdasdasdasda12332131312"));
			String responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/set_device_version", getHeader(), list);
			System.out.println(" Response >>> " + responseStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 @Test
	 public void testGetDeviceVersion(){
		String watchId = "FCL111222335";
		try {
			List list = new ArrayList();
			list.add(new BasicNameValuePair("watchId", watchId));
			String responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/get_device_version", getHeader(), list);
			System.out.println(" Response >>> " + responseStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
	 
	 @Test
	 public void setDeviceSyncInfo(){
		String watchId = "FCL111222333";
		String userId = "123456";
		String status = "0";

		try {
			List list = new ArrayList();
			list.add(new BasicNameValuePair("watchId", watchId));
			list.add(new BasicNameValuePair("userId", userId));
			list.add(new BasicNameValuePair("status", status));
			String responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/set_sync_switch", getHeader(), list);
			System.out.println(" Response >>> " + responseStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
	 
	 @Test
	 public void getDeviceSyncInfo(){
		String watchId = "FCL111222333";
		String userId = "123456";
		try {
			List list = new ArrayList();
			list.add(new BasicNameValuePair("watchId", watchId));
			list.add(new BasicNameValuePair("userId", userId));
			String responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/get_sync_switch", getHeader(), list);
			System.out.println(" Response >>> " + responseStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
	 
	 @Test
	 public void testSetNotifySwitch(){
			String watchId = "FCL111222333";
			String userId = "123456";
			String status = "1110100";
			try {
				List list = new ArrayList();
				list.add(new BasicNameValuePair("watchId", watchId));
				list.add(new BasicNameValuePair("userId", userId));
				list.add(new BasicNameValuePair("status", status));
				String responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/set_notify_info", getHeader(), list);
				System.out.println(" Response >>> " + responseStr);
			} catch (IOException e) {
				e.printStackTrace();
			}
	 }
	 @Test
	 public void testGetNotifySwitch(){
			String watchId = "FCL111222333";
			String userId = "123456";
			try {
				List list = new ArrayList();
				list.add(new BasicNameValuePair("watchId", watchId));
				list.add(new BasicNameValuePair("userId", userId));
				String responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/get_notify_info", getHeader(), list);
				System.out.println(" Response >>> " + responseStr);
			} catch (IOException e) {
				e.printStackTrace();
			}
	 }
	 
	 @Test
	 public void testSetSleepRemind(){
		String watchId = "FCL111222333";
		String userId = "123456";
		String startTime = "07:30";
		String endTime = "17:00";
		String status = "1";
		try {
			List list = new ArrayList();
			list.add(new BasicNameValuePair("watchId", watchId));
			list.add(new BasicNameValuePair("userId", userId));
			list.add(new BasicNameValuePair("startTime", startTime));
			list.add(new BasicNameValuePair("endTime", endTime));
			list.add(new BasicNameValuePair("status", status));
			String responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/set_sleep_remind", getHeader(), list);
			System.out.println(" Response >>> " + responseStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
	 @Test
	 public void testGetSleepRemind(){
			String watchId = "FCL111222333";
			String userId = "123456";
			try {
				List list = new ArrayList();
				list.add(new BasicNameValuePair("watchId", watchId));
				list.add(new BasicNameValuePair("userId", userId));
				String responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/get_sleep_remind", getHeader(), list);
				System.out.println(" Response >>> " + responseStr);
			} catch (IOException e) {
				e.printStackTrace();
			}
	 }
	 
	 @Test
	 public void testSetStayRemind(){
			String watchId = "FCL111222333";
			String userId = "123456";
			String startTime = "07:30";
			String endTime = "17:00";
			String interval = "40";
			String repeat = "1111100";
			String status = "1";
			try {
				List list = new ArrayList();
				list.add(new BasicNameValuePair("watchId", watchId));
				list.add(new BasicNameValuePair("userId", userId));
				list.add(new BasicNameValuePair("startTime", startTime));
				list.add(new BasicNameValuePair("endTime", endTime));
				list.add(new BasicNameValuePair("interval", interval));
				list.add(new BasicNameValuePair("repeat", repeat));
				list.add(new BasicNameValuePair("status", status));
				String responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/set_stay_remind", getHeader(), list);
				System.out.println(" Response >>> " + responseStr);
			} catch (IOException e) {
				e.printStackTrace();
			}
	 }
	 
	 @Test
	 public void testSetStayRemindPC(){
			String watchId = "FCL111222333";
			String userId = "123456";
			String startTime = "07:30";
			String endTime = "17:00";
			String interval = "40";
			String repeat = "1111100";
			String status = "1";
			String antiLostStatus = "1";
			try {
				List list = new ArrayList();
				list.add(new BasicNameValuePair("watchId", watchId));
				list.add(new BasicNameValuePair("userId", userId));
				list.add(new BasicNameValuePair("startTime", startTime));
				list.add(new BasicNameValuePair("endTime", endTime));
				list.add(new BasicNameValuePair("interval", interval));
				list.add(new BasicNameValuePair("repeat", repeat));
				list.add(new BasicNameValuePair("status", status));
				list.add(new BasicNameValuePair("antiLostStatus", antiLostStatus));
				String responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/set_stay_remind_pc", getHeader(), list);
				System.out.println(" Response >>> " + responseStr);
			} catch (IOException e) {
				e.printStackTrace();
			}
	 }
	 @Test
	 public void testGetStayRemind(){
		 String watchId = "FCL111222333";
			String userId = "123456";
			try {
				List list = new ArrayList();
				list.add(new BasicNameValuePair("watchId", watchId));
				list.add(new BasicNameValuePair("userId", userId));
				String responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/get_stay_remind", getHeader(), list);
				System.out.println(" Response >>> " + responseStr);
			} catch (IOException e) {
				e.printStackTrace();
			}
	 }
	 
	 @Test
	 public void testGetStayRemindPC(){
		 String watchId = "FCL111222333";
			String userId = "123456";
			try {
				List list = new ArrayList();
				list.add(new BasicNameValuePair("watchId", watchId));
				list.add(new BasicNameValuePair("userId", userId));
				String responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/get_stay_remind_pc", getHeader(), list);
				System.out.println(" Response >>> " + responseStr);
			} catch (IOException e) {
				e.printStackTrace();
			}
	 }
	 
	 @Test
	 public void testSetChar(){
			String tester = "réservés";//скачайте
			String tester2 = "测试";
			String tester3 = "скачайте";
			String tester4 = "алц";
			try {
				List list = new ArrayList();
				list.add(new BasicNameValuePair("tester", tester2));
				String responseStr = HttpUtils.sendRequest("http://192.168.1.143/sport/api/test_char", getHeader(), list);
				//String responseStr = HttpUtils.sendRequest("http://app-zecircle.mykronoz.com/sport/api/test_char", getHeader(), list);
				//String responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/sport/api/test_char", getHeader(), list);
				System.out.println(" Response >>> " + responseStr);
			} catch (IOException e) {
				e.printStackTrace();
			}
	 }

	 //%D1%81%D0%BA%D0%B0%D1%87%D0%B0%D0%B9%D1%82%D0%B5
	 //d181d0bad0b0d187d0b0d0b9d182d0b5
	 
	 //%E6%B5%8B%E8%AF%95
	 //e6b58be8af95
	 public static void main(String[] args) throws Exception {
		String aa = "d'Ã©tude";
		String bb = "скачайте";
		String cc = "abc123";
		String dd = "测试";
		System.out.println(new String(aa.getBytes("iso8859-1")));
		System.out.println(new String(bb.getBytes("utf-8"), "utf-8"));
		byte[] bbb = bb.getBytes();
		for(int i=0; i<bbb.length; i++){
			System.out.print(bbb[i]+",");
		}
		System.out.println(bytesToHexString(bbb));
		
		byte[] bbbb = dd.getBytes();
		for(int i=0; i<bbbb.length; i++){
			System.out.print(bbbb[i]+",");
		}
		System.out.println(bytesToHexString(bbbb));
	//	String ttt = bytesToHexString(bbb);
	}
	 
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}
}
