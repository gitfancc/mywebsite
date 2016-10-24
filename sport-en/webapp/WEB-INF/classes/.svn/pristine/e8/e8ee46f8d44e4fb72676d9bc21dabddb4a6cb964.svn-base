package com.appscomm.sport.api.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.appscomm.sport.common.SportApiCode;
import com.appscomm.sport.model.DeviceNotifyInfoVO;
import com.appscomm.sport.model.DeviceSleepRemindInfoVO;
import com.appscomm.sport.model.DeviceStayRemindInfoVO;
import com.appscomm.sport.service.DeviceNotifyRemindService;
import com.appscomm.sport.service.InterfaceDataLogService;
import com.appscomm.sport.utils.HttpUtils;
import com.appscomm.sport.utils.JsonUtils;
import com.appscomm.sport.utils.Tools;

public class DeviceNotifyRemindAction extends BaseAction {

	private static final long serialVersionUID = -7110681010924439986L;
	private static Log logger = LogFactory.getLog(DeviceNotifyRemindAction.class);
	
	@Autowired
	private DeviceNotifyRemindService deviceNotifyRemindService;
	@Autowired
	private InterfaceDataLogService interfaceDataLogService;
	
	public void setSleepRemind(){
		String resp = "";
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		String watchId = null;
		String userId = null;
		String startTime = null;
		String endTime = null;
		String status = null;
		
		try {	
			if (!requestMap.containsKey("watchId")){
				logger.error("watchId is null! ");
				HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.ERROR_2001, null),requestMap);
				return ;
			}
			watchId = ((String[])requestMap.get("watchId"))[0];
			if(!Tools.isValidWatchId(watchId)){
				logger.error("watchId is invalid! ");
				HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.ERROR_2004, null),requestMap);
				return ;
			}
			if (requestMap.containsKey("userId")){
				userId = ((String[])requestMap.get("userId"))[0];
			}
			if (requestMap.containsKey("startTime")){
				startTime = ((String[])requestMap.get("startTime"))[0];
			}
			if (requestMap.containsKey("endTime")){
				endTime = ((String[])requestMap.get("endTime"))[0];
			}
			if (requestMap.containsKey("status")){
				status = ((String[])requestMap.get("status"))[0];
			}
			DeviceSleepRemindInfoVO remind = new DeviceSleepRemindInfoVO();
			remind.setUserId(Long.valueOf(userId));
			remind.setWatchId(watchId);
			remind.setStartTime(startTime);
			remind.setEndTime(endTime);
			remind.setStatus(status);
			Integer sid = this.deviceNotifyRemindService.addSleepRemindInfo(remind);
			resp = JsonUtils.responseJson(SportApiCode.SUCCESS, sid);
		}catch(Exception e){
			logger.error(e);	
			try{
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			}catch(Exception ee){}
		}
		HttpUtils.sendResponseJson(resp,requestMap);
		//记录操作日志
		String headClientType =  (String)requestMap.get("HeadClientType");
		String headVersion = (String)requestMap.get("HeadVersion");
		interfaceDataLogService.saveInterfaceDataLog(userId, watchId, "", headClientType+headVersion, "Set sleep remind");
	}
	
	public void getSleepRemind(){
		String resp = "";
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		String watchId = null;
		String userId = null;
		
		try {	
			if (!requestMap.containsKey("watchId")){
				logger.error("watchId is null! ");
				HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.ERROR_2001, null),requestMap);
				return ;
			}
			watchId = ((String[])requestMap.get("watchId"))[0];
			if(!Tools.isValidWatchId(watchId)){
				logger.error("watchId is invalid! ");
				HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.ERROR_2004, null),requestMap);
				return ;
			}
			if (requestMap.containsKey("userId")){
				userId = ((String[])requestMap.get("userId"))[0];
			}
			DeviceSleepRemindInfoVO remind = this.deviceNotifyRemindService.getSleepRemindInfo(Long.valueOf(userId), watchId);
			resp = JsonUtils.responseJson(SportApiCode.SUCCESS, remind);
		}catch(Exception e){
			logger.error(e);	
			try{
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			}catch(Exception ee){}
		}
		HttpUtils.sendResponseJson(resp,requestMap);
		
		//记录操作日志
		String headClientType =  (String)requestMap.get("HeadClientType");
		String headVersion = (String)requestMap.get("HeadVersion");
		interfaceDataLogService.saveInterfaceDataLog(userId, watchId, "", headClientType+headVersion, "Get sleep remind");
	}
	
	public void setNotifyInfo(){
		String resp = "";
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		String watchId = null;
		String userId = null;
		String status = null;
		
		try {	
			if (!requestMap.containsKey("watchId")){
				logger.error("watchId is null! ");
				HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.ERROR_2001, null),requestMap);
				return ;
			}
			watchId = ((String[])requestMap.get("watchId"))[0];
			if(!Tools.isValidWatchId(watchId)){
				logger.error("watchId is invalid! ");
				HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.ERROR_2004, null),requestMap);
				return ;
			}
			if (requestMap.containsKey("userId")){
				userId = ((String[])requestMap.get("userId"))[0];
			}
			if (requestMap.containsKey("status")){
				status = ((String[])requestMap.get("status"))[0];
			}
			
			DeviceNotifyInfoVO notify = new DeviceNotifyInfoVO();
			notify.setUserId(Long.valueOf(userId));
			notify.setWatchId(watchId);
			notify.setStatus(status);
			Integer sid = this.deviceNotifyRemindService.addDeviceNotifyInfo(notify);
			resp = JsonUtils.responseJson(SportApiCode.SUCCESS, sid);
		}catch(Exception e){
			logger.error(e);	
			try{
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			}catch(Exception ee){}
		}
		HttpUtils.sendResponseJson(resp,requestMap);
		
		//记录操作日志
		String headClientType =  (String)requestMap.get("HeadClientType");
		String headVersion = (String)requestMap.get("HeadVersion");
		interfaceDataLogService.saveInterfaceDataLog(userId, watchId, "", headClientType+headVersion, "set notify info");
	}
	
	public void getNotifyInfo(){
		String resp = "";
		String watchId = null;
		String userId = null;
		
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		try {	
			if (!requestMap.containsKey("watchId")){
				logger.error("watchId is null! ");
				HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.ERROR_2001, null),requestMap);
				return ;
			}
			watchId = ((String[])requestMap.get("watchId"))[0];
			if(!Tools.isValidWatchId(watchId)){
				logger.error("watchId is invalid! ");
				HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.ERROR_2004, null),requestMap);
				return ;
			}
			if (requestMap.containsKey("userId")){
				userId = ((String[])requestMap.get("userId"))[0];
			}
			
			DeviceNotifyInfoVO notify = this.deviceNotifyRemindService.getDeviceNotifyInfo(Long.valueOf(userId), watchId);
			resp = JsonUtils.responseJson(SportApiCode.SUCCESS, notify);
		}catch(Exception e){
			logger.error(e);	
			try{
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			}catch(Exception ee){}
		}
		HttpUtils.sendResponseJson(resp,requestMap);
		
		//记录操作日志
		String headClientType =  (String)requestMap.get("HeadClientType");
		String headVersion = (String)requestMap.get("HeadVersion");
		interfaceDataLogService.saveInterfaceDataLog(userId, watchId, "", headClientType+headVersion, "get notify info");
	}
	
	public void setStayRemind(){
		String resp = "";
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		String watchId = null;
		String userId = null;
		String startTime = null;
		String endTime = null;
		String interval  = null;
		String repeat = null;
		String status = null;
		
		try {	
			if (!requestMap.containsKey("watchId")){
				logger.error("watchId is null! ");
				HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.ERROR_2001, null),requestMap);
				return ;
			}
			watchId = ((String[])requestMap.get("watchId"))[0];
			if(!Tools.isValidWatchId(watchId)){
				logger.error("watchId is invalid! ");
				HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.ERROR_2004, null),requestMap);
				return ;
			}
			if (requestMap.containsKey("userId")){
				userId = ((String[])requestMap.get("userId"))[0];
			}
			if (requestMap.containsKey("startTime")){
				startTime = ((String[])requestMap.get("startTime"))[0];
			}
			if (requestMap.containsKey("endTime")){
				endTime = ((String[])requestMap.get("endTime"))[0];
			}
			if (requestMap.containsKey("interval")){
				interval = ((String[])requestMap.get("interval"))[0];
			}
			if (requestMap.containsKey("repeat")){
				repeat = ((String[])requestMap.get("repeat"))[0];
			}
			if (requestMap.containsKey("status")){
				status = ((String[])requestMap.get("status"))[0];
			}
			DeviceStayRemindInfoVO remind = new DeviceStayRemindInfoVO();
			remind.setUserId(Long.valueOf(userId));
			remind.setWatchId(watchId);
			remind.setStartTime(startTime);
			remind.setEndTime(endTime);
			remind.setInterval(Integer.valueOf(interval));
			remind.setRepeat(repeat);
			remind.setStatus(status);
			Integer sid = this.deviceNotifyRemindService.addStayRemindInfo(remind);
			resp = JsonUtils.responseJson(SportApiCode.SUCCESS, sid);
		}catch(Exception e){
			logger.error(e);	
			try{
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			}catch(Exception ee){}
		}
		HttpUtils.sendResponseJson(resp,requestMap);
		
		//记录操作日志
		String headClientType =  (String)requestMap.get("HeadClientType");
		String headVersion = (String)requestMap.get("HeadVersion");
		interfaceDataLogService.saveInterfaceDataLog(userId, watchId, "", headClientType+headVersion, "set staty remind");
	}
	
	public void setStayRemindPC(){
		String resp = "";
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		String watchId = null;
		String userId = null;
		String startTime = null;
		String endTime = null;
		String interval  = null;
		String repeat = null;
		String status = null;
		String antiLostStatus = "0";
		try {	
			if (!requestMap.containsKey("watchId")){
				logger.error("watchId is null! ");
				HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.ERROR_2001, null),requestMap);
				return ;
			}
			watchId = ((String[])requestMap.get("watchId"))[0];
			if(!Tools.isValidWatchId(watchId)){
				logger.error("watchId is invalid! ");
				HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.ERROR_2004, null),requestMap);
				return ;
			}
			if (requestMap.containsKey("userId")){
				userId = ((String[])requestMap.get("userId"))[0];
			}
			if (requestMap.containsKey("startTime")){
				startTime = ((String[])requestMap.get("startTime"))[0];
			}
			if (requestMap.containsKey("endTime")){
				endTime = ((String[])requestMap.get("endTime"))[0];
			}
			if (requestMap.containsKey("interval")){
				interval = ((String[])requestMap.get("interval"))[0];
			}
			if (requestMap.containsKey("repeat")){
				repeat = ((String[])requestMap.get("repeat"))[0];
			}
			if (requestMap.containsKey("status")){
				status = ((String[])requestMap.get("status"))[0];
			}
			if (requestMap.containsKey("antiLostStatus")){
				antiLostStatus = ((String[])requestMap.get("antiLostStatus"))[0];
			}
			DeviceStayRemindInfoVO remind = new DeviceStayRemindInfoVO();
			remind.setUserId(Long.valueOf(userId));
			remind.setWatchId(watchId);
			remind.setStartTime(startTime);
			remind.setEndTime(endTime);
			remind.setInterval(Integer.valueOf(interval));
			remind.setRepeat(repeat);
			remind.setStatus(status);
			Integer sid = this.deviceNotifyRemindService.addStayRemindInfo(remind);
			//设置防丢开关
			DeviceNotifyInfoVO notify = this.deviceNotifyRemindService.getDeviceNotifyInfo(Long.valueOf(userId), watchId);
			String notifyStatus =  "000000"+antiLostStatus;
			if(notify != null){
				notifyStatus = notify.getStatus().substring(0,6)+antiLostStatus;
				notify.setStatus(notifyStatus);
				this.deviceNotifyRemindService.updateDeviceNotifyStatus(notify);
			}else{
				notify = new DeviceNotifyInfoVO();
				notify.setUserId(Long.valueOf(userId));
				notify.setWatchId(watchId);
				notify.setStatus(notifyStatus);
				this.deviceNotifyRemindService.addDeviceNotifyStatus(notify);
			}
			resp = JsonUtils.responseJson(SportApiCode.SUCCESS, sid);
		}catch(Exception e){
			logger.error(e);	
			try{
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			}catch(Exception ee){}
		}
		HttpUtils.sendResponseJson(resp,requestMap);
		
		//记录操作日志
		String headClientType =  (String)requestMap.get("HeadClientType");
		String headVersion = (String)requestMap.get("HeadVersion");
		interfaceDataLogService.saveInterfaceDataLog(userId, watchId, "", headClientType+headVersion, "set staty remind");
	}
	
	public void getStayRemind(){
		String resp = "";
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		String watchId = null;
		String userId = null;
		try {	
			if (!requestMap.containsKey("watchId")){
				logger.error("watchId is null! ");
				HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.ERROR_2001, null),requestMap);
				return ;
			}
			watchId = ((String[])requestMap.get("watchId"))[0];
			if(!Tools.isValidWatchId(watchId)){
				logger.error("watchId is invalid! ");
				HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.ERROR_2004, null),requestMap);
				return ;
			}
			if (requestMap.containsKey("userId")){
				userId = ((String[])requestMap.get("userId"))[0];
			}
			
			DeviceStayRemindInfoVO remind = this.deviceNotifyRemindService.getStayRemindInfo(Long.valueOf(userId), watchId);
			resp = JsonUtils.responseJson(SportApiCode.SUCCESS, remind);
		}catch(Exception e){
			logger.error(e);	
			try{
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			}catch(Exception ee){}
		}
		HttpUtils.sendResponseJson(resp,requestMap);
		
		//记录操作日志
		String headClientType =  (String)requestMap.get("HeadClientType");
		String headVersion = (String)requestMap.get("HeadVersion");
		interfaceDataLogService.saveInterfaceDataLog(userId, watchId, "", headClientType+headVersion, "get staty remind");
	}
	
	public void getStayRemindPC(){
		String resp = "";
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		String watchId = null;
		String userId = null;
		try {	
			if (!requestMap.containsKey("watchId")){
				logger.error("watchId is null! ");
				HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.ERROR_2001, null),requestMap);
				return ;
			}
			watchId = ((String[])requestMap.get("watchId"))[0];
			if(!Tools.isValidWatchId(watchId)){
				logger.error("watchId is invalid! ");
				HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.ERROR_2004, null),requestMap);
				return ;
			}
			if (requestMap.containsKey("userId")){
				userId = ((String[])requestMap.get("userId"))[0];
			}
			
			DeviceStayRemindInfoVO remind = this.deviceNotifyRemindService.getStayRemindInfo(Long.valueOf(userId), watchId);
			DeviceNotifyInfoVO notify = this.deviceNotifyRemindService.getDeviceNotifyInfo(Long.valueOf(userId), watchId);
			String notifyStatus = "0";
			if(notify != null){
				notifyStatus = notify.getStatus().substring(6);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("antiLostStaut", notifyStatus);
			map.put("StayRemind", remind);
			//resp = JsonUtils.responseJson(SportApiCode.SUCCESS, remind);
			resp = JsonUtils.responseJson(SportApiCode.SUCCESS, map);
		}catch(Exception e){
			logger.error(e);	
			try{
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			}catch(Exception ee){}
		}
		HttpUtils.sendResponseJson(resp,requestMap);
		
		//记录操作日志
		String headClientType =  (String)requestMap.get("HeadClientType");
		String headVersion = (String)requestMap.get("HeadVersion");
		interfaceDataLogService.saveInterfaceDataLog(userId, watchId, "", headClientType+headVersion, "get staty remind");
	}
	
	public void testChar()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		//logger.debug(request.getAttribute("tester"));
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		String tester = ((String[])requestMap.get("tester"))[0];
		logger.debug("Get char:" + tester);
		try {
			logger.debug("Get char2: " + new String(tester.getBytes(), "UTF-8"));
			logger.debug("Get char3:" + new String(tester.getBytes("iso8859-1"), "UTF-8"));
			logger.debug("Get char4:" + stringToHexString(tester));
			byte[] bs = tester.getBytes();
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<bs.length; i++){
				sb.append(bs[i]).append(",");
			}
			logger.debug("Get char6:" + sb.toString());
			logger.debug("Get char7:" + URLDecoder.decode(tester));
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public static String stringToHexString(String strPart) {
        String hexString = "";
        for (int i = 0; i < strPart.length(); i++) {
            int ch = (int) strPart.charAt(i);
            String strHex = Integer.toHexString(ch);
            hexString = hexString + strHex;
        }
        return hexString;
    }
}
