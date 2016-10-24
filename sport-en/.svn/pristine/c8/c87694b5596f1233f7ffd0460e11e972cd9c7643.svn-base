package com.appscom.sport.api.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import com.appscomm.sport.utils.HttpUtils;

public class DeviceManagerActionTest {
	private boolean DEBUG = true;
	
    private List<Header> getHeader(){
    	List<Header> hl = new ArrayList<Header>();
		hl.add(new BasicHeader("oauthToken", "000000"));
		hl.add(new BasicHeader("clientType", "test"));
		hl.add(new BasicHeader("version", "1.0.0"));
		hl.add(new BasicHeader("weblogid", "1"));
		return hl;
    }
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testBindDeviceId() {
		String watchId = "1234567890";
		//String watchId = "null";
		//String userId = "254";
		String userId = "2064";
		//String watchTime = "2015-04-24";
		//String watchType = "L11";
		/*StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"").append("userId").append("\"").append(":").append("\"").append(userId).append("\"").append(",");
		sb.append("\"").append("watchId").append("\"").append(":").append("\"").append(watchId).append("\"").append(",");
		sb.append("\"").append("isDefault").append("\"").append(":").append("\"").append("0").append("\"");
		sb.append("}");*/
		List list = new ArrayList();
		list.add(new BasicNameValuePair("userId", userId));
		list.add(new BasicNameValuePair("watchId", watchId));
		//list.add(new BasicNameValuePair("watchType", watchType));
	//	list.add(new BasicNameValuePair("watchTime", watchTime));
		list.add(new BasicNameValuePair("isDefault", "0"));
		
		try{
			String responseStr = null;
			if (DEBUG)
				responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/device_bind", getHeader(),  list);
			else
				responseStr = HttpUtils.sendRequest("http://ledong.appscomm.cn/sport/api/device_bind", getHeader(),  list);
			
			System.out.println(" Response >>> " + responseStr);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testUnBindDeviceId() {
		String watchId = "11111111111111";
		String userId = "254";
		/*StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"").append("userId").append("\"").append(":").append("\"").append(userId).append("\"").append(",");
		sb.append("\"").append("watchId").append("\"").append(":").append("\"").append(watchId).append("\"");
		sb.append("}");*/

		List list = new ArrayList();
		list.add(new BasicNameValuePair("userId", userId));
		list.add(new BasicNameValuePair("watchId", watchId));
		try{
			String responseStr = null;
			if (DEBUG)
				responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/device_unbind", getHeader(), list);
			else
				responseStr = HttpUtils.sendRequest("http://ledong.appscomm.cn/sport/api/device_unbind", getHeader(), list);
			
			System.out.println(" Response >>> " + responseStr);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testBindDeviceSim() {
		String userId = "9";
		String watchId = "w1003";
		String watchSim = "13312345678";
		/*StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"").append("userId").append("\"").append(":").append("\"").append(userId).append("\"").append(",");
		sb.append("\"").append("watchSim").append("\"").append(":").append("\"").append(watchSim).append("\"").append(",");
		sb.append("\"").append("watchId").append("\"").append(":").append("\"").append(watchId).append("\"");
		sb.append("}");*/
		
		List list = new ArrayList();
		list.add(new BasicNameValuePair("userId", userId));
		list.add(new BasicNameValuePair("watchId", watchId));
		list.add(new BasicNameValuePair("watchSim", watchSim));
		try{
			String responseStr = null;
			if (DEBUG)
				responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/device_sim_bind", getHeader(), list);
			else
				responseStr = HttpUtils.sendRequest("http://ledong.appscomm.cn/sport/api/device_sim_bind", getHeader(), list);
			
			System.out.println(" Response >>> " + responseStr);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testUnBindDeviceSim() {
		String userId ="12";
		String watchId = "w1003";
		String watchSim = "13312345679";
		/*StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"").append("userId").append("\"").append(":").append("\"").append(userId).append("\"").append(",");
		sb.append("\"").append("watchSim").append("\"").append(":").append("\"").append(watchSim).append("\"").append(",");
		sb.append("\"").append("watchId").append("\"").append(":").append("\"").append(watchId).append("\"");
		sb.append("}");*/

		List list = new ArrayList();
		list.add(new BasicNameValuePair("userId", userId));
		list.add(new BasicNameValuePair("watchId", watchId));
		list.add(new BasicNameValuePair("watchSim", watchSim));
		try{
			String responseStr = null;
			if (DEBUG)
				responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/device_sim_unbind", getHeader(), list);
			else
				responseStr = HttpUtils.sendRequest("http://ledong.appscomm.cn/sport/api/device_sim_unbind", getHeader(), list);
			
			System.out.println(" Response >>> " + responseStr);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testListDevice() {
		String userId = "12";
		/*	StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"").append("userId").append("\"").append(":").append("\"").append(userId).append("\"");
		sb.append("}");*/
		List list = new ArrayList();
		list.add(new BasicNameValuePair("userId", userId));
		try{
			String responseStr = null;
			if (DEBUG)
				responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/get_devices", getHeader(), list);
			else
				responseStr = HttpUtils.sendRequest("http://ledong.appscomm.cn/sport/api/get_devices", getHeader(), list);
			
			System.out.println(" Response >>> " + responseStr);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testSaveDevice() {
		String userId = "12";
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"").append("watchId").append("\"").append(":").append("\"").append("w1003").append("\"").append(",");
		sb.append("\"").append("watchSim").append("\"").append(":").append("\"").append("13500005556").append("\"").append(",");
		sb.append("\"").append("heartStatus").append("\"").append(":").append("\"").append("1").append("\"").append(",");
		sb.append("\"").append("gpsStatus").append("\"").append(":").append("\"").append("1").append("\"").append(",");
		sb.append("\"").append("sportStatus").append("\"").append(":").append("\"").append("1").append("\"").append(",");
		sb.append("\"").append("heartInterval").append("\"").append(":").append("\"").append("10").append("\"").append(",");
		sb.append("\"").append("gpsInterval").append("\"").append(":").append("\"").append("10").append("\"").append(",");
		sb.append("\"").append("sportInterval").append("\"").append(":").append("\"").append("10").append("\"").append(",");
		sb.append("\"").append("gpsStartTime").append("\"").append(":").append("\"").append("05").append("\"").append(",");
		sb.append("\"").append("gpsEndTime").append("\"").append(":").append("\"").append("20").append("\"").append(",");
		sb.append("\"").append("lon").append("\"").append(":").append("\"").append("1111.2222").append("\"").append(",");
		sb.append("\"").append("ew").append("\"").append(":").append("\"").append("E").append("\"").append(",");
		sb.append("\"").append("lat").append("\"").append(":").append("\"").append("22.4444").append("\"").append(",");
		sb.append("\"").append("ns").append("\"").append(":").append("\"").append("N").append("\"").append(",");
		sb.append("\"").append("radius").append("\"").append(":").append("\"").append("12.33").append("\"").append(",");
		sb.append("\"").append("alarmMode").append("\"").append(":").append("\"").append("1").append("\"");
		sb.append("}");
		String device = sb.toString();
		
		List list = new ArrayList();
		list.add(new BasicNameValuePair("userId", userId));
		list.add(new BasicNameValuePair("device", device));
		try{
			String responseStr = null;
			if (DEBUG)
				responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/device_param_setting", getHeader(), list);
			else
				responseStr = HttpUtils.sendRequest("http://ledong.appscomm.cn/sport/api/device_param_setting", getHeader(), list);
			
			System.out.println(" Response >>> " + responseStr);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testGetDeviceParam() {
		String watchId = "w1003";
		/*StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"").append("watchId").append("\"").append(":").append("\"").append(watchId).append("\"");
		sb.append("}");*/
		List list = new ArrayList();
		list.add(new BasicNameValuePair("watchId", watchId));
		
		try{
			String responseStr = null;
			if (DEBUG)
				responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/get_device_info", getHeader(), list);
			else
				responseStr = HttpUtils.sendRequest("http://ledong.appscomm.cn/sport/api/get_device_info", getHeader(), list);
			
			System.out.println(" Response >>> " + responseStr);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testQryHisSportData(){
		String watchId = "355372020368356";
		String startTime = "2012-01-03 20:52:00";
		String endTime = "2012-01-08 20:00:01";
		String curPageIndex = "1";
		String pageSize = "5";
		
		List list = new ArrayList();
		list.add(new BasicNameValuePair("watchId", watchId));
		list.add(new BasicNameValuePair("startTime", startTime));
		list.add(new BasicNameValuePair("endTime", endTime));
		list.add(new BasicNameValuePair("pageSize", pageSize));
		list.add(new BasicNameValuePair("curPageIndex", curPageIndex));
		list.add(new BasicNameValuePair("userId", "35"));
		try{
			String responseStr = null;
			if (DEBUG)
				responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/get_sport_info", getHeader(), list);
			else
				responseStr = HttpUtils.sendRequest("http://ledong.appscomm.cn/sport/api/get_sport_info", getHeader(), list);
			
			System.out.println(" Response >>> " + responseStr);
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	@Test
	public void testSetSS() throws IOException{
		URL url = new URL("http://ledong.appscomm.cn:8000/comm/api/sportdataupload");  
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();  
        connection.setDoOutput(true);  
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());  
        String sportDatas =
       		 "{\"watchId\":\"w1112222\",\"version\":\"1.0.0\",\"type\":\"H8\",\"customer\":\"hometech\",\"values\":["
       		 +	 "{\"startTime\":\"2013-10-21 11:16:11\",\"endTime\":\"2013-10-22 11:15:11\",\"steps\":\"121\",\"dist\":\"260.15\",\"speed\":\"12.1\",\"cal\":\"12.2\",\"deviceId\":\"2\",\"avgRate\":\"80\",\"minRate\":\"75\",\"maxRate\":\"125\"}"
       		+	 ",{\"startTime\":\"2013-10-21 11:17:11\",\"endTime\":\"2013-10-22 11:18:11\",\"steps\":\"122\",\"dist\":\"261.15\",\"speed\":\"13.1\",\"cal\":\"13.2\",\"deviceId\":\"2\",\"avgRate\":\"81\",\"minRate\":\"76\",\"maxRate\":\"126\"}"
       		+	 ",{\"startTime\":\"2013-10-21 11:18:11\",\"endTime\":\"2013-10-22 11:19:11\",\"steps\":\"123\",\"dist\":\"262.15\",\"speed\":\"14.1\",\"cal\":\"14.2\",\"deviceId\":\"2\",\"avgRate\":\"82\",\"minRate\":\"77\",\"maxRate\":\"127\"}"
       		 + "]}";
       /* String sportDatas =
          		 "{\"watchId\":\"w1112222\",\"version\":\"1.0.0\",\"type\":\"H8\",\"customer\":\"hometech\",\"values\":["
          		 +	 "{\"startTime\":\"2013-10-21 11:16:11\",\"endTime\":\"2013-10-22 11:15:11\",\"steps\":\"121\",\"dist\":\"260.15\",\"speed\":\"12.1\",\"cal\":\"12.2\",\"deviceId\":\"2\",\"avgRate\":\"80\",\"minRate\":\"75\",\"maxRate\":\"125\"}"
          		 + "]}";*/
        out.write("sportDatas="+sportDatas); 
        // remember to clean up  
        out.flush();  
        out.close();  
        
        String sCurrentLine;  
        String sTotalString;  
        sCurrentLine = "";  
        sTotalString = "";  
        InputStream l_urlStream;  
        l_urlStream = connection.getInputStream();  
        BufferedReader l_reader = new BufferedReader(new InputStreamReader(l_urlStream));  
        while ((sCurrentLine = l_reader.readLine()) != null) {  
            sTotalString += sCurrentLine + "/r/n";  
  
        }  
        System.out.println(sTotalString);  
    }  
	
// [{"watchId":"355372020368356","startTime":"2012-01-06 19:52:11","endTime":"2012-01-06 19:52:11","steps":121,"dist":260.15,"speed":12.1,"cal":12.2,"deviceId":2,"avgRate":80,"minRate":75,"maxRate":125},
//	{"watchId":"355372020368356","startTime":"2012-01-06 17:40:08","endTime":"2012-01-06 17:40:08","steps":31,"dist":66.65,"speed":6.6,"cal":3.1,"deviceId":2,"avgRate":80,"minRate":75,"maxRate":125},
//	{"watchId":"355372020368356","startTime":"2012-01-06 19:52:11","endTime":"2012-01-06 19:52:11","steps":121,"dist":260.15,"speed":12.1,"cal":12.2,"deviceId":2,"avgRate":80,"minRate":75,"maxRate":125}]
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testSetSportData() {
		String sportDatas =
		 "{\"watchId\":\"w1112222\",\"version\":\"1.0.0\",\"type\":\"L28\",\"customer\":\"hometech\",\"values\":["
		 +
		 "{\"startTime\":\"2013-10-21 11:16:11\",\"endTime\":\"2013-10-22 11:15:11\",\"steps\":121,\"dist\":260.15,\"speed\":12.1,\"cal\":12.2,\"deviceId\":2,\"avgRate\":80,\"minRate\":75,\"maxRate\":125}"
		 + "]}";
				
		  System.out.println(sportDatas.length()); 
		  List list = new ArrayList();
		  list.add(new BasicNameValuePair("sportDatas", sportDatas));
		  try{ 
		 // http://health.appscomm.cn:8000 , ip 是http://58.248.19.204:8000 String
		  String responseStr = null; 
		  if (DEBUG) 
			  responseStr = HttpUtils.sendRequest("http://58.248.19.204:8000/comm/api/sportdataupload", getHeader(), list); 
		  else 
			  responseStr = HttpUtils.sendRequest("http://ledong.appscomm.cn:8000/comm/api/sportdataupload", getHeader(), list);
		  
		  System.out.println(" Response >>> " + responseStr);
		  }catch(Exception  e){ 
			  e.printStackTrace(); 
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testSportTotal(){
		String resp = null;
		
		String watchId = "";
		String userId = "2064";
		String startTime = "2015-06-21 00:00:00";
		String endTime = "2015-08-27 23:59:59";
		String watchType  = "L28";
		
		List list = new ArrayList();
		list.add(new BasicNameValuePair("watchId", watchId));
		list.add(new BasicNameValuePair("userId", userId));
		list.add(new BasicNameValuePair("startTime", startTime));
		list.add(new BasicNameValuePair("endTime", endTime));
		list.add(new BasicNameValuePair("watchType", watchType));
		BasicNameValuePair bnv = null;
		
		if (DEBUG)
			try {
				// 按小时
				bnv = new BasicNameValuePair("queryType", "1");
				list.add(bnv);
				resp = HttpUtils.sendRequest("http://localhost:8080/sport/api/get_sport_total", getHeader(), list);
				System.out.println(resp);
				//按天
				list.remove(bnv);
				bnv = new BasicNameValuePair("queryType", "2");
				list.add(bnv);
				resp = HttpUtils.sendRequest("http://localhost:8080/sport/api/get_sport_total", getHeader(), list);
				System.out.println(resp);
				// 按周
				list.remove(bnv);
				bnv = new BasicNameValuePair("queryType", "3");
				list.add(bnv);
				resp = HttpUtils.sendRequest("http://localhost:8080/sport/api/get_sport_total", getHeader(), list);
				System.out.println(resp);
				
				// 按月
				list.remove(bnv);
				bnv = new BasicNameValuePair("queryType", "4");
				list.add(bnv);
				resp = HttpUtils.sendRequest("http://localhost:8080/sport/api/get_sport_total", getHeader(), list);
				System.out.println(resp);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	@Test
	public void testSetDeviceConfig(){
		List list = new ArrayList();
		list.add(new BasicNameValuePair("watchId", "FL123456789"));
		list.add(new BasicNameValuePair("brightness", "2"));
		list.add(new BasicNameValuePair("ringMode", "1"));
		list.add(new BasicNameValuePair("volume", "3"));
		String resp = null;
		try {
			resp = HttpUtils.sendRequest("http://localhost:8080/sport/api/set_device_config", getHeader(), list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(resp);
	}
	
	@Test
	public void testGetDeviceConfig(){
		List list = new ArrayList();
		list.add(new BasicNameValuePair("watchId", "FL123456789"));
		String resp = null;
		try {
			resp = HttpUtils.sendRequest("http://localhost:8080/sport/api/get_device_config", getHeader(), list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(resp);
	}
}
