package com.appscomm.sport.api.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.appscomm.sport.common.SportApiCode;
import com.appscomm.sport.model.SuggestContentVO;
import com.appscomm.sport.model.VersionCheckVO;
import com.appscomm.sport.service.CommonService;
import com.appscomm.sport.utils.HttpUtils;
import com.appscomm.sport.utils.JsonUtils;
import com.opensymphony.xwork2.ActionSupport;

public class CommonAction  extends ActionSupport {
	private static final long serialVersionUID = 1L;

	@Autowired
	CommonService commService;
	
	private static Log logger = LogFactory.getLog(CommonAction.class);
	
	public void checkVersion(){
		String resp = "";
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		try{			
	  		if(!requestMap.containsKey("deviceName") 
	  				&&	!requestMap.containsKey("deviceType")){
	  			resp = JsonUtils.responseJson(SportApiCode.ERROR_7791, null);
	  			HttpUtils.sendResponseJson(resp,requestMap);
	  			return;
	  		}
	  		if(!requestMap.containsKey("version")){
	  			resp = JsonUtils.responseJson(SportApiCode.ERROR_7791, null);
	  			HttpUtils.sendResponseJson(resp,requestMap);
	  			return;
	  		}
	  		
	  		Object retObj = commService.request(requestMap);
	  		if(retObj != null){
	  			resp = JsonUtils.responseJson(SportApiCode.SUCCESS, retObj);
	  		}else{
	  			resp = JsonUtils.responseJson(SportApiCode.ERROR_7792, null);//返回记录不存在
	  		}
		}catch(Exception e){
			logger.error(e);
			try{
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			}catch(Exception ee){
			}
		}
		HttpUtils.sendResponseJson(resp,requestMap);
	}
	
	public void suggestContent(){
		String resp = "";
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();	
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		try{			
	  		  		
	  		if(!requestMap.containsKey("userId") || !requestMap.containsKey("watchId")){
				resp = JsonUtils.responseJson(SportApiCode.ERROR_7791, null);
				HttpUtils.sendResponseJson(resp,requestMap);
				return;
	  		}
	  		
	  		String userid = ((String[])requestMap.get("userId"))[0];
	  		String watchid = ((String[])requestMap.get("watchId"))[0];
	  		String clienttype = ((String[])requestMap.get("clientType"))[0];
	  		String content = ((String[])requestMap.get("content"))[0];
	  		
	  		SuggestContentVO dao = new SuggestContentVO();
	  		dao.setClienttype(clienttype);
	  		dao.setWatchid(watchid);
	  		dao.setUserid(Integer.parseInt(userid));
	  		dao.setContent(content);
	  		
	  		int ret = commService.suggestContent(dao);//返回递增的ID
	  		if(ret > 0){
	  			resp = JsonUtils.responseJson(SportApiCode.SUCCESS, null);
	  		}else{
	  			resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
	  		}
		}catch(Exception e){
			logger.error(e);
			try{
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			}catch(Exception ee){
			}
		}
		HttpUtils.sendResponseJson(resp,requestMap);		
	}
	
	public void uploadPic(){
		String resp = "";
		String filePath = getText("file.upload.path");//.replaceAll("\\/", "\\\\");
		String vmPath = getText("static.file.path");//.replaceAll("\\/", "\\\\");\
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();	
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		try{			
			String userid = ((String[])requestMap.get("userId"))[0];
			String pic = ((String[])requestMap.get("pic"))[0];
			
			String filename = "images/userid_" + userid + ".jpg";
			logger.info("filepath:" + filePath);
			Boolean ret = commService.savePicData(userid, pic, filePath);
			if(!ret){
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			}else{
				Map<String, String> map = new HashMap<String, String>();
				String result = vmPath + "/" + filename;
				//result = result.replaceAll("\\/", "\\\\");
				map.put("picURL", result);
				resp = JsonUtils.responseJson(SportApiCode.SUCCESS, map);
			}
		}catch(Exception e){
			logger.error(e);
			try{
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			}catch(Exception ee){
			}			
		}
		HttpUtils.sendResponseJson(resp,requestMap);
	}
	
	public void downloadPic(){
		String resp = "";
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();	
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		try{			
			String userid = ((String[])requestMap.get("userId"))[0];
			String ifSmall = ((String[])requestMap.get("ifSmall"))[0];
			String data = commService.getPicData(userid, ifSmall.endsWith("1") ? true : false);
			resp = JsonUtils.responseJson(SportApiCode.SUCCESS, data);
		}catch(Exception e){
			logger.error(e);
			try{
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			}catch(Exception ee){
			}			
		}
		HttpUtils.sendResponseJson(resp,requestMap);		
	}
	
	public void getTime(){
		String resp = "";
		long epoch = System.currentTimeMillis() / 1000;
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		try{
			requestMap.put("time", epoch);
			resp = JsonUtils.responseJson(SportApiCode.SUCCESS, requestMap);				
		}catch(Exception e){
			logger.info("getTime error");
		}
		HttpUtils.sendResponseJson(resp,requestMap);
	}
	
	public void getFirmwareInfo(){
		String resp = "";
		String deviceType = null;
		String deviceName = null;
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		if (requestMap.containsKey("deviceType")){
			deviceType = ((String[])requestMap.get("deviceType"))[0];
			deviceName = deviceType;
		}
		if(deviceType == null) {
			deviceType = getText("deviceType");
		}
		
		VersionCheckVO fwInfo = commService.getFirmware(deviceType, deviceName);
		try{
			if(fwInfo == null){
				resp = JsonUtils.responseJson(SportApiCode.ERROR_1000, null);
			}else{
				Map<String, String> map = new HashMap<String, String>();
				map.put("version",fwInfo.getVersion());
				resp = JsonUtils.responseJson(SportApiCode.SUCCESS, map);
			}
		}catch(Exception e){
			logger.error(e);
			try{
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			}catch(Exception ee){}
		}
		HttpUtils.sendResponseJson(resp,requestMap);
	}	
	
}
