package com.appscomm.sport.api.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.appscomm.sport.common.SportApiCode;
import com.appscomm.sport.model.DeviceConfigVO;
import com.appscomm.sport.model.DeviceSyncInfoVO;
import com.appscomm.sport.model.DeviceVersionInfoVO;
import com.appscomm.sport.model.ParamSportVO;
import com.appscomm.sport.model.PersonWatchVO;
import com.appscomm.sport.model.SportTotalVO;
import com.appscomm.sport.model.UserVO;
import com.appscomm.sport.model.WatchVO;
import com.appscomm.sport.service.DeviceSetService;
import com.appscomm.sport.service.InterfaceDataLogService;
import com.appscomm.sport.service.ParamSportService;
import com.appscomm.sport.service.RemaindService;
import com.appscomm.sport.service.SleepInfoService;
import com.appscomm.sport.service.UserService;
import com.appscomm.sport.utils.DateUtils;
import com.appscomm.sport.utils.HttpUtils;
import com.appscomm.sport.utils.JsonUtils;
import com.appscomm.sport.utils.Tools;

public class DeviceManagerAction extends BaseAction {
	private static final long serialVersionUID = -6748925554476869953L;
	private static Log logger = LogFactory.getLog(DeviceManagerAction.class);
	@Autowired
	private DeviceSetService deviceSetService;
	@Autowired
	private UserService userService;	
	
	@Autowired
	private ParamSportService paramService;
	@Autowired
	private RemaindService remaindService;
	@Autowired
	private InterfaceDataLogService interfaceDataLogService;
	@Autowired
	private SleepInfoService sleepInfoService; 

	/**
	 * 设备绑定(通过设备机器码绑定)
	 * @param userId 
	 * 		- 用户标识
	 * @param watchId 
	 * 		- 设备机器码
	 * @param isDefault
	 * 		- 是否缺省设备
	 * 
	 * 入参(json）- {"userId":"01","watchId":"w1001","isDefault":"0"}
	 * 
	 * @return (json)
	 */
	public void bindDeviceId() {
		 Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		 requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		String resp = "";
		String userId = "";
		String watchId = "";
		String watchType = "";
		try {
			userId =  ((String[])requestMap.get("userId"))[0];
			watchId =  ((String[])requestMap.get("watchId"))[0];
			//String isDefault =  ((String[])requestMap.get("isDefault"))[0];
			watchType = getText("deviceType", "L28"); 
			if(requestMap.containsKey("watchType")){
				watchType = ((String[])requestMap.get("watchType"))[0];
			}
			
			if (StringUtils.isEmpty(userId)) {
				resp = JsonUtils.responseJson(SportApiCode.ERROR_2001, null);
				HttpUtils.sendResponseJson(resp,requestMap);
				return;
			}
			
			if(!Tools.isValidWatchId(watchId)){
				logger.error("watchId is invalid! ");
				HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.ERROR_2004, null),requestMap);
				return ;
			}
			
			String dateTime = DateUtils.getCurrentDate();//DateUtils.Formart(new Date(),  "yyyy-MM-dd");
			if(requestMap.containsKey("watchTime")){
				dateTime = ((String[])requestMap.get("watchTime"))[0];
				//需要判断时间有效性？？？
				if(DateUtils.timeDifference(dateTime) > 48){
					resp = JsonUtils.responseJson(SportApiCode.ERROR_9000, null);
					HttpUtils.sendResponseJson(resp,requestMap);
					return;
				}
				
				//兼容yyyy-MM-dd和yyyy-MM-dd HH:mm:ss
				if(dateTime!=null && dateTime.trim().length()==10){
					dateTime = dateTime+" 00:00:00";
				}
			}
			
			if (StringUtils.isEmpty(watchType)){
				// 设备类型为空时，默认为L28
				watchType = getText("deviceType", "L28"); 
			}
			PersonWatchVO pw = deviceSetService.bindDeviceIdEx(Long.valueOf(userId), watchId, 1, watchType, dateTime);
			if(pw==null){
				//如果被绑定，返回被绑定人的信息
				List<UserVO> list = userService.getBindUserInfo(watchId);
				UserVO bindUser = null;
				if(list != null && list.size() > 0){
					//返回被绑定的用户账号(mail or telephone)
					bindUser = list.get(0);
					if(Long.valueOf(userId).equals(bindUser.getId())){
						//被当前用户绑定
						resp = JsonUtils.responseJson(SportApiCode.SUCCESS, null);
					}else{
						//被其他用户绑定
						resp = JsonUtils.responseJson(SportApiCode.ERROR_7782, bindUser);
					}
				}else{
					//未找到绑定人信息
					resp = JsonUtils.responseJson(SportApiCode.ERROR_7782, bindUser);
				}
			}else{
				dateTime = DateUtils.Formart(dateTime, "yyyy-MM-dd");
				// 清除绑定的用户的当天的数据
				int retCode = paramService.deleteSportData(Long.valueOf(userId), dateTime);
				//清除提醒数据
				int retCode2 = remaindService.deleteRemind(Long.valueOf(userId), watchId);
				//清除睡眠数据
				int retCode3 = sleepInfoService.deleteSleepRecordL11(Long.valueOf(userId), watchId, dateTime);
				if (retCode<0 || retCode2<0 || retCode3<0) {
					resp = JsonUtils.responseJson(SportApiCode.ERROR_7796, null);
				} else {
					resp = JsonUtils.responseJson(SportApiCode.SUCCESS, pw);
				}
			}
			HttpUtils.sendResponseJson(resp,requestMap);
		} catch (Exception e) {
			logger.error(e);
			try {
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			} catch (JsonGenerationException e1) {
				logger.error(e);
			} catch (JsonMappingException e1) {
				logger.error(e);
			} catch (IOException e1) {
				logger.error(e);
			}
			HttpUtils.sendResponseJson(resp,requestMap);
		}
		
		//记录操作日志
		String headClientType =  (String)requestMap.get("HeadClientType");
		String headVersion = (String)requestMap.get("HeadVersion");
		interfaceDataLogService.saveInterfaceDataLog(userId, watchId, watchType, headClientType+headVersion, "Bind Device");
	}
	
	/**
	 * 解绑设备(通过设备机器码解绑)
	 * @param watchId
	 * 		- 设备机器码
	 * 入参(json) - {"watchId":"w1001"}
	 * 
	 * @return (json)
	 */
	public void unBindDeviceId(){
		String resp = null;
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		String userId = "";
		String watchId = "";
		try {
			userId =  ((String[])requestMap.get("userId"))[0];
			watchId =  ((String[])requestMap.get("watchId"))[0];
			if (StringUtils.isEmpty(userId)) {
				resp = JsonUtils.responseJson(SportApiCode.ERROR_2001, null);
				HttpUtils.sendResponseJson(resp,requestMap);
				return;
			}
			
			if(!Tools.isValidWatchId(watchId)){
				logger.error("watchId is invalid! ");
				HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.ERROR_2004, null),requestMap);
				return ;
			}
			
			Integer code = deviceSetService.unBindDeviceId(Long.valueOf(userId), watchId);
			resp = JsonUtils.responseJson(SportApiCode.SUCCESS, null);
			HttpUtils.sendResponseJson(resp,requestMap);
			
		}catch (Exception e) {
			logger.error(e);
			try {
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			} catch (JsonGenerationException e1) {
				logger.error(e);
			} catch (JsonMappingException e1) {
				logger.error(e);
			} catch (IOException e1) {
				logger.error(e);
			}
			HttpUtils.sendResponseJson(resp,requestMap);
		}
		// 记录操作日志
		String headClientType =  (String)requestMap.get("HeadClientType");
		String headVersion = (String)requestMap.get("HeadVersion");
		interfaceDataLogService.saveInterfaceDataLog(userId, watchId, "", headClientType+headVersion, "unBind Device");
	}
	
	/**
	 * 绑定SIM卡
	 * @param watchId 
	 * 		- 设备机器码
	 * @param watchSim
	 * 		- 设备SIM卡
	 * 入参(json) - {"watchId":"w1001", "watchSim":"13134567890"}
	 * 
	 * @return  (json)
	 */
	public void bindDeviceSim(){
		String resp = null;
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		try {
			String watchSim =  ((String[])requestMap.get("watchSim"))[0];
			String watchId =  ((String[])requestMap.get("watchId"))[0];
			if (StringUtils.isEmpty(watchId)) {
				resp = JsonUtils.responseJson(SportApiCode.ERROR_2001, null);
				HttpUtils.sendResponseJson(resp,requestMap);
				return;
			}
			if (StringUtils.isEmpty(watchSim)) {
				resp = JsonUtils.responseJson(SportApiCode.ERROR_2001, null);
				HttpUtils.sendResponseJson(resp,requestMap);
				return;
			}
			Integer code = deviceSetService.bindDeviceSim(watchId, watchSim);
			if (code < 0) {
				resp = JsonUtils.responseJson(SportApiCode.ERROR_7785, null);
			} else {
				PersonWatchVO pw = deviceSetService.getDevice(watchId);
				resp = JsonUtils.responseJson(SportApiCode.SUCCESS, pw);
			}
			HttpUtils.sendResponseJson(resp,requestMap);
			
		}catch (Exception e) {
			logger.error(e);
			try {
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			} catch (JsonGenerationException e1) {
				logger.error(e);
			} catch (JsonMappingException e1) {
				logger.error(e);
			} catch (IOException e1) {
				logger.error(e);
			}
			HttpUtils.sendResponseJson(resp,requestMap);
		}
	}
	
	/**
	 * 解绑SIM卡
	 * @param watchSim
	 * 		- 设备SIM卡
	 * 入参(json) - {"watchId":"w1001", "watchSim":"13134567890"}
	 * 
	 * @return (json)
	 */
	@SuppressWarnings("unused")
	public void unBindDeviceSim(){
		String resp = null;
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		try {
			String watchSim =  ((String[])requestMap.get("watchSim"))[0];
			String watchId =  ((String[])requestMap.get("watchId"))[0];
			if (StringUtils.isEmpty(watchId)) {
				resp = JsonUtils.responseJson(SportApiCode.ERROR_2001, null);
				HttpUtils.sendResponseJson(resp,requestMap);
				return;
			}
			
			Integer code = deviceSetService.unBindDeviceSim(null, watchId);
			if (code < 0) {
				resp = JsonUtils.responseJson(SportApiCode.ERROR_7786, null);
			} else {
				resp = JsonUtils.responseJson(SportApiCode.SUCCESS, null);
			}
			HttpUtils.sendResponseJson(resp,requestMap);
			
		} catch (Exception e) {
			logger.error(e);
			try {
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			} catch (JsonGenerationException e1) {
				logger.error(e);
			} catch (JsonMappingException e1) {
				logger.error(e);
			} catch (IOException e1) {
				logger.error(e);
			}
			HttpUtils.sendResponseJson(resp,requestMap);
		}
	}
	
	/**
	 * 查询用户设备列表
	 * @param userId
	 * 		- 用户唯一标识
	 * 入参(json) - {"userId":"1"}
	 * 
	 * @return (json)
	 */
	public void listDevices(){
		String resp = null;
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		try {
			String userId =  ((String[])requestMap.get("userId"))[0];
			if (StringUtils.isEmpty(userId)) {
				resp = JsonUtils.responseJson(SportApiCode.ERROR_2001, null);
				HttpUtils.sendResponseJson(resp,requestMap);
				return;
			}
			
			List<PersonWatchVO> devList = deviceSetService.getDeviceList(Long.valueOf(userId));
			resp = JsonUtils.responseJson(SportApiCode.SUCCESS, devList);
			HttpUtils.sendResponseJson(resp,requestMap);
			
		} catch (Exception e) {
			logger.error(e);
			try {
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			} catch (JsonGenerationException e1) {
				logger.error(e);
			} catch (JsonMappingException e1) {
				logger.error(e);
			} catch (IOException e1) {
				logger.error(e);
			}
			HttpUtils.sendResponseJson(resp,requestMap);
		}
	}
	
	/**
	 * 设备参数设置
	 * @param WatchVO的成员
	 * 
	 * 入参(json) - {"watchId":"w1001",  "watchSim":"13501234567",  "heartStatus":"1",  "gpsStatus":"1",  "sportStatus":"1",  
	 * 	"heartInterval":"10", "gpsInterval":"10",  "sportInterval":"10",  "gpsStartTime":"08",  "gpsEndTime":"18",  
	 * 	"lon":"",  "ns":"","lat":"","ew":"","radius":"","address":"","alarmMode":""}
	 * 
	 * @return (json)
	 */
	@SuppressWarnings("unused")
	public void saveDevice(){
		String resp = null;
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		ObjectMapper mapper = null;
		WatchVO watch = null;
		try {
			String userId =  ((String[])requestMap.get("userId"))[0];
			String device = ((String[])requestMap.get("device"))[0];
			mapper = new ObjectMapper();
			watch = mapper.readValue(device, WatchVO.class);
			Integer code = deviceSetService.saveDeviceInfo(watch, Long.valueOf(userId));
			if (code < 0) {
				resp = JsonUtils.responseJson(SportApiCode.ERROR_7787, null);
			} else {
				// 发送短信,告知设备同步配置参数
				deviceSetService.sendSms(watch);
				resp = JsonUtils.responseJson(SportApiCode.SUCCESS, null);
			}
			HttpUtils.sendResponseJson(resp,requestMap);
			
		}catch (Exception e) {
			logger.error(e);
			try {
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			} catch (JsonGenerationException e1) {
				logger.error(e);
			} catch (JsonMappingException e1) {
				logger.error(e);
			} catch (IOException e1) {
				logger.error(e);
			}
			HttpUtils.sendResponseJson(resp,requestMap);
		}
	}
	
	/**
	 * 查询设备参数信息
	 * 
	 * @param 设备机器码
	 * 入参(json) - {"watchId":"w1001"}
	 * 
	 * @return (json) - {"watchId":"w1001",  "watchSim":"13501234567",  "heartStatus":"1",  "gpsStatus":"1",  "sportStatus":"1",  
	 * 	"heartInterval":"10", "gpsInterval":"10",  "sportInterval":"10",  "gpsStartTime":"08",  "gpsEndTime":"18",  
	 * 	"lon":"",  "ns":"","lat":"","ew":"","radius":"","address":"","alarmMode":""}
	 */
	public void getDeviceParam(){
		String resp = null;
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		try {
			String watchId =((String[])requestMap.get("watchId"))[0];
			if (StringUtils.isEmpty(watchId)) {
				resp = JsonUtils.responseJson(SportApiCode.ERROR_2001, null);
				HttpUtils.sendResponseJson(resp,requestMap);
				return;
			}
			
			WatchVO watch = new WatchVO();
			watch.setWatchId(watchId);
			watch = deviceSetService.getDeviceInfo(watch, null);
			resp = JsonUtils.responseJson(SportApiCode.SUCCESS, watch);
			HttpUtils.sendResponseJson(resp,requestMap);
			
		}catch (Exception e) {
			logger.error(e);
			try {
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			} catch (JsonGenerationException e1) {
				logger.error(e);
			} catch (JsonMappingException e1) {
				logger.error(e);
			} catch (IOException e1) {
				logger.error(e);
			}
			HttpUtils.sendResponseJson(resp,requestMap);
		}
	}
	
    /**
	* 查询历史运动数据 zxh 20131021 add
	* @return (json)
	 */
	
	public void qryHisSportData() throws IOException
	{
		String resp = null;
  		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
  		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		String watchId = ((String[])requestMap.get("watchId"))[0];
		String userId = "";
		String watchType = "";
		
		if (requestMap.containsKey("userId")){
			userId = ((String[])requestMap.get("userId"))[0];
		}
		if (requestMap.containsKey("watchType")){
			watchType = ((String[])requestMap.get("watchType"))[0];
		}
		String starttime = ((String[])requestMap.get("startTime"))[0];
		String endtime = ((String[])requestMap.get("endTime"))[0];
		String scurindex = ((String[])requestMap.get("curPageIndex"))[0];
		String spagesize = ((String[])requestMap.get("pageSize"))[0];
		if(watchId.isEmpty()){
			resp = JsonUtils.responseJson(SportApiCode.ERROR_2001, null);
			HttpUtils.sendResponseJson(resp,requestMap);
			return;
		}
		
		if (StringUtils.isEmpty(watchType)){
			// 默认设备为L28
			watchType = getText("deviceType", "L28");  ;
		}
		
		int curindex;
		int pagesize;
		try{
			curindex = Integer.parseInt(scurindex);
			pagesize = Integer.parseInt(spagesize);
		}
		catch(NumberFormatException e)
		{
			logger.error("无效的页数、页面大小！");
			resp = JsonUtils.responseJson(SportApiCode.ERROR_2001, null);
			HttpUtils.sendResponseJson(resp,requestMap);
			return;
		}
		
		List<ParamSportVO> list = null;
		
		int count = 0;
		try{
			watchType = null;
			if (StringUtils.isNotEmpty(userId)){
				count = paramService.getSportDataCount(watchId, starttime, endtime, Long.valueOf(userId), watchType);
				list = paramService.getSportData(watchId, starttime, endtime, curindex, pagesize, Long.valueOf(userId), watchType);
			} else {
				 count = paramService.getSportDataCount(watchId, starttime, endtime);
				 list = paramService.getSportData(watchId, starttime, endtime, curindex, pagesize);
			}
			 String json=JsonUtils.toJson(list);
			 if(json==null || json.equals(""))json="{}";
			 resp = "{\"result\":\""+SportApiCode.SUCCESS.getCode()+"\",\"message\":\""+
					 				SportApiCode.SUCCESS.getDesc()+"\",\"data\":{\"recordCount\":\""+ count +"\",\"arrs\":"+json+"}}";
		}
		catch(Exception e)
		{
			logger.error(e);	
			resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
		}	
		
		HttpUtils.sendResponseJson(resp,requestMap);
		
		// 记录操作日志
		String headClientType =  (String)requestMap.get("HeadClientType");
		String headVersion = (String)requestMap.get("HeadVersion");
		interfaceDataLogService.saveInterfaceDataLog(userId, watchId, watchType, headClientType+headVersion, "query history sport data");
	}
	/**
	 * 运动统计
	* @description:(设定参数)
	* @return void(返回值说明)
	 */
	public void qryTotalSport(){
		String resp = "";
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		String userId = "";
		String watchType = null;
		String watchId = null;
		try {		
			watchId = ((String[])requestMap.get("watchId"))[0];
			if (requestMap.containsKey("userId")){
				userId = ((String[])requestMap.get("userId"))[0];
			}
			if (requestMap.containsKey("watchType")){
				watchType = ((String[])requestMap.get("watchType"))[0];
			}
			String startTime = ((String[])requestMap.get("startTime"))[0];
			String endTime = ((String[])requestMap.get("endTime"))[0];
			String queryType = ((String[])requestMap.get("queryType"))[0];
			if (StringUtils.isEmpty(watchType)){
				// 默认设备为L28
				watchType = getText("deviceType", "L28");  ;
			}
			if(StringUtils.isEmpty(userId)){
				resp = JsonUtils.responseJson(SportApiCode.ERROR_7797, null);
				HttpUtils.sendResponseJson(resp,requestMap);
				return ;
			}
			
			List<SportTotalVO> result = null;
			watchType = null;
			
			if(queryType.equals("1")){
				// 如果查询的参数中userID和watchtype都不为空，则根据用户ID来查询数据
				if (StringUtils.isNotEmpty(userId)){
					result = paramService.SportGroupDay(userId, startTime, endTime, Long.valueOf(userId), watchType);
				} 
//				else{
//					result = paramService.SportGroupDay(watchId, startTime, endTime);
//				}
			}else if (queryType.equals("2")){
				if (StringUtils.isNotEmpty(userId)){
					result = paramService.SportGroupHour(watchId, startTime, endTime, Long.valueOf(userId), watchType);
				} 
//				else{
//					result = paramService.SportGroupHour(watchId, startTime, endTime);
//				}
			} else if (queryType.equals("3")){	
				// 按周统计
				result = paramService.SportgroupWeek(Long.valueOf(userId), watchId, watchType, startTime, endTime);
			} else if (queryType.equals("4")){	
				// 按月统计
				result = paramService.SportgroupMonth(Long.valueOf(userId), watchId, watchType, startTime, endTime);
			}			
			resp = JsonUtils.responseJson(SportApiCode.SUCCESS, result);
			if(StringUtils.isEmpty(userId)){
				resp = JsonUtils.responseJson(SportApiCode.ERROR_7797, null);
			}
		}catch(Exception e){
			logger.error(e);	
			try{
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			}catch(Exception ee){}
		}	
		HttpUtils.sendResponseJson(resp,requestMap);
		
		// 记录操作日志
		String headClientType =  (String)requestMap.get("HeadClientType");
		String headVersion = (String)requestMap.get("HeadVersion");
		interfaceDataLogService.saveInterfaceDataLog(userId, watchId, watchType, headClientType+headVersion, "query Total sport data");
	}

	public void setDeviceFirmwareVersion(){
		String resp = "";
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		String watchId = null;
		String firmwareName = null;
		String firmwareVersion = null;
		String udid="";
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
			
			if (requestMap.containsKey("firmwareVersion")){
				firmwareVersion = ((String[])requestMap.get("firmwareVersion"))[0];
			}
			if (requestMap.containsKey("firmwareName")){
				firmwareName = ((String[])requestMap.get("firmwareName"))[0];
			}
			if(requestMap.containsKey("udid")){
				udid = ((String[])requestMap.get("udid"))[0];
			}
			
			DeviceVersionInfoVO version = new DeviceVersionInfoVO();
			version.setWatchId(watchId);
			version.setFirmwareName(firmwareName);
			version.setFirmwareVersion(firmwareVersion);
			version.setUdid(udid);
			Integer sid = this.deviceSetService.addDeviceVersionInfo(version);
			resp = JsonUtils.responseJson(SportApiCode.SUCCESS, sid);
		}catch(Exception e){
			logger.error(e);	
			try{
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			}catch(Exception ee){}
		}
		HttpUtils.sendResponseJson(resp,requestMap);
		
		// 记录操作日志
		String headClientType =  (String)requestMap.get("HeadClientType");
		String headVersion = (String)requestMap.get("HeadVersion");
		interfaceDataLogService.saveInterfaceDataLog("", watchId, "", headClientType+headVersion, "Setting device firmware version");
	}
	
	public void getDeviceFirmwareVersion(){
		String resp = "";
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		String watchId = null;
		
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
			
			DeviceVersionInfoVO version = this.deviceSetService.getDeviceVersionInfo(watchId);
			resp = JsonUtils.responseJson(SportApiCode.SUCCESS, version);
		}catch(Exception e){
			logger.error(e);	
			try{
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			}catch(Exception ee){}
		}
		HttpUtils.sendResponseJson(resp,requestMap);
		
		// 记录操作日志
		String headClientType =  (String)requestMap.get("HeadClientType");
		String headVersion = (String)requestMap.get("HeadVersion");
		interfaceDataLogService.saveInterfaceDataLog("", watchId, "", headClientType+headVersion, "Geting device firmware version");
	}
	
	public void setDeviceSyncInfo(){
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
			
			if (requestMap.containsKey("status")){
				status = ((String[])requestMap.get("status"))[0];
			}
			if (requestMap.containsKey("userId")){
				userId = ((String[])requestMap.get("userId"))[0];
			}
			
			DeviceSyncInfoVO syncInfo = new DeviceSyncInfoVO();
			syncInfo.setWatchId(watchId);
			syncInfo.setUserId(Long.valueOf(userId));
			syncInfo.setStatus(status);
			Integer sid = this.deviceSetService.addDeviceSyncInfo(syncInfo);
			resp = JsonUtils.responseJson(SportApiCode.SUCCESS, sid);
		}catch(Exception e){
			logger.error(e);	
			try{
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			}catch(Exception ee){}
		}
		HttpUtils.sendResponseJson(resp,requestMap);
		
		// 记录操作日志
		String headClientType =  (String)requestMap.get("HeadClientType");
		String headVersion = (String)requestMap.get("HeadVersion");
		interfaceDataLogService.saveInterfaceDataLog(userId, watchId, "", headClientType+headVersion, "Set device auto sync status");
	}
	
	public void getDeviceSyncInfo(){
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
			
			DeviceSyncInfoVO syncInfo = this.deviceSetService.getDeviceSyncInfo(Long.valueOf(userId), watchId);
			resp = JsonUtils.responseJson(SportApiCode.SUCCESS, syncInfo);
		}catch(Exception e){
			logger.error(e);	
			try{
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			}catch(Exception ee){}
		}
		HttpUtils.sendResponseJson(resp,requestMap);
		
		// 记录操作日志
		String headClientType =  (String)requestMap.get("HeadClientType");
		String headVersion = (String)requestMap.get("HeadVersion");
		interfaceDataLogService.saveInterfaceDataLog(userId, watchId, "", headClientType+headVersion, "Get device auto sync status");
	}
	
	public void setDeviceConfig(){
		//亮度;铃声开关; 声音大小
		String resp = "";
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		String watchId = null;
		String brightness = "0";
		String ringMode = "0";
		String volume = "0";
		
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
			if (requestMap.containsKey("brightness")){
				brightness = ((String[])requestMap.get("brightness"))[0];
			}
			if (requestMap.containsKey("ringMode")){
				ringMode = ((String[])requestMap.get("ringMode"))[0];
			}
			if (requestMap.containsKey("volume")){
				volume = ((String[])requestMap.get("volume"))[0];
			}
			
			//保存
			DeviceConfigVO deviceConfig = new DeviceConfigVO();
			deviceConfig.setWatchId(watchId);
			deviceConfig.setBrightness(brightness);
			deviceConfig.setRingMode(Integer.valueOf(ringMode));
			deviceConfig.setVolume(Integer.valueOf(volume));
			
			this.deviceSetService.addDeviceConfig(deviceConfig);
			resp = JsonUtils.responseJson(SportApiCode.SUCCESS, null);
		}catch(Exception e){
			logger.error(e);	
			try{
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			}catch(Exception ee){}
		}
		HttpUtils.sendResponseJson(resp,requestMap);
	}
	
	public void getDeviceConfig(){
		String resp = "";
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		String watchId = null;
	
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
			
			DeviceConfigVO deviceConfig =this.deviceSetService.getDeviceConfig(watchId) ;
			resp = JsonUtils.responseJson(SportApiCode.SUCCESS, deviceConfig);
		}catch(Exception e){
			logger.error(e);	
			try{
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			}catch(Exception ee){}
		}
		HttpUtils.sendResponseJson(resp,requestMap);
	}
}
