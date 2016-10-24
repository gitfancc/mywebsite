/**
 * Copyright appscomm.cn 2013. All rights reserved.
 *
 * @createDate 2013-9-11
 */
package com.appscomm.sport.api.action;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.appscomm.sport.common.SportApiCode;
import com.appscomm.sport.model.RemaindVO;
import com.appscomm.sport.service.InterfaceDataLogService;
import com.appscomm.sport.service.RemaindService;
import com.appscomm.sport.utils.HttpUtils;
import com.appscomm.sport.utils.JsonUtils;
import com.opensymphony.xwork2.ActionSupport;

/**
 *  用户控制层Action  
 *	
 *  qindf create by 2013-9-11
 *
 */
public class RemaindAction extends ActionSupport {

	private static final long serialVersionUID = -4502261895346456171L;
	private static Logger log = Logger.getLogger(RemaindAction.class);
	@Autowired
	private RemaindService remaindService;
	@Autowired
	private InterfaceDataLogService interfaceDataLogService;
	
	/**
	 * 查询个人信息接口
	 * @return
	 */
	@SuppressWarnings("unused")
	public void get_remind_info(){
		String responseStr="";
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		String userId = null;
		String watchId = null;
		try{
			if(requestMap==null){
				responseStr=JsonUtils.responseJson(SportApiCode.ERROR_2001,null);
				HttpUtils.sendResponseJson(responseStr,requestMap);
				return;
			}
			
			userId = ((String[])requestMap.get("userId"))[0];
			watchId = ((String[])requestMap.get("watchId"))[0];
			List<RemaindVO> resultList= remaindService.getList(Long.valueOf(userId), watchId);
			responseStr=JsonUtils.responseJson(SportApiCode.SUCCESS,resultList);
		    HttpUtils.sendResponseJson(responseStr,requestMap);
		}catch(Exception e){
			log.info("获取提醒信息异常:"+e);
			try{
				responseStr = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
				HttpUtils.sendResponseJson(responseStr,requestMap);
			}catch(Exception ee){
				log.error(ee);
			}
		}
		
		//记录操作日志
		String headClientType =  (String)requestMap.get("HeadClientType");
		String headVersion = (String)requestMap.get("HeadVersion");
		interfaceDataLogService.saveInterfaceDataLog(userId, watchId, "", headClientType+headVersion, "Get remind info");
	}
	/**
	 * 添加提醒
	 */
	@SuppressWarnings("unused")
	public void add_remind_info(){
		String responseStr="";
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		String userId = null;
		String watchId = null;
		
		try{
			if(requestMap==null){
				responseStr=JsonUtils.responseJson(SportApiCode.ERROR_2001,null);
				HttpUtils.sendResponseJson(responseStr,requestMap);
				return;
			}
			userId = ((String[])requestMap.get("userId"))[0];
			watchId = ((String[])requestMap.get("watchId"))[0];
			String time = ((String[])requestMap.get("time"))[0];
			String detail =  ((String[])requestMap.get("detail"))[0];
			String type =  ((String[])requestMap.get("type"))[0];
			String text =  ((String[])requestMap.get("text"))[0];
			String repeat =  ((String[])requestMap.get("repeat"))[0];
			String dotype =  ((String[])requestMap.get("doType"))[0];
			String status =  ((String[])requestMap.get("status"))[0];
			long id = 0L;
		
			RemaindVO obj=new RemaindVO();
			//obj.setId(Long.parseLong(strId));
			obj.setUserId(userId);
			obj.setWatchId(watchId);
			obj.setTime(time);
			obj.setDetail(detail);
			obj.setType(type);
			obj.setText(text);
			obj.setRepeat(repeat);
			obj.setDoType(Integer.parseInt(dotype));
			obj.setStatus(Integer.parseInt(status));
			if(requestMap.containsKey("id") && 
					StringUtils.isNotBlank(((String[])requestMap.get("id"))[0])){
				obj.setUpload_time(new Timestamp(System.currentTimeMillis()));
				id =  Long.valueOf(((String[])requestMap.get("id"))[0]);
				obj.setId(id);
			}
			
			int result=0;			
			Map<String, Integer> resultmap = new HashMap<String, Integer>();
			
			if(Integer.valueOf(dotype) == 2){
				//修改提醒
				result = remaindService.update(obj);
			}else{
				if(	remaindService.exist(userId, watchId, time)){
					  responseStr=JsonUtils.responseJson(SportApiCode.ERROR_7781,null);
					   HttpUtils.sendResponseJson(responseStr,requestMap);
					   return;
				}
				//如果已经存在提醒，则提示重复添加，客户端在上传数据时候应该判断是否重复添加。
				//这里添加这句代码，主要是安全性和数据唯一性考虑。
				result= remaindService.add(obj);
			}
			log.info("add remind record return auto id:" + result);				
			resultmap.put("id", result);
			
			SportApiCode code=SportApiCode.SUCCESS;
			if(result < 0){	
				code=SportApiCode.ERROR_1001;
			}
			
		   responseStr=JsonUtils.responseJson(code, resultmap);
		   HttpUtils.sendResponseJson(responseStr,requestMap);
		}catch(Exception e){
			log.info("添加提醒信息异常:"+e);
			try{
				responseStr = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
				HttpUtils.sendResponseJson(responseStr,requestMap);
			}catch(Exception ee){
				log.error(ee);
			}
		}
		
		//记录操作日志
		String headClientType =  (String)requestMap.get("HeadClientType");
		String headVersion = (String)requestMap.get("HeadVersion");
		interfaceDataLogService.saveInterfaceDataLog(userId, watchId, "", headClientType+headVersion, "Add remind info");
	}
	
	/**
	 * 删除提醒
	 */
	@SuppressWarnings("unused")
	public void delete_remind_info(){
		String responseStr="";
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		String userId = null;
		String watchId = null;
		SportApiCode code=SportApiCode.SUCCESS;
		try{
			
			if(requestMap == null){
				responseStr=JsonUtils.responseJson(SportApiCode.ERROR_2001,null);
				HttpUtils.sendResponseJson(responseStr,requestMap);
				return;
			}
			if(StringUtils.isEmpty(((String[])requestMap.get("id"))[0])){
				code = SportApiCode.ERROR_7798;
				responseStr=JsonUtils.responseJson(code,null);
				HttpUtils.sendResponseJson(responseStr,requestMap);
				return;
			}
			userId = ((String[])requestMap.get("userId"))[0];
			watchId = ((String[])requestMap.get("watchId"))[0];
			List<Map<String, String>> ids = null;
			if(requestMap.containsKey("id")){
				String strId =  ((String[])requestMap.get("id"))[0];
				ids = JsonUtils.toList(strId);
			}			
			log.info(">>>>>>>>>delete_remind_info:userId="+userId+",watchId="+watchId+",ids="+ids.toString());
			int result= remaindService.delete(userId, watchId, ids);
			
			if(result < 0){	
				code=SportApiCode.ERROR_1001;
			}
			responseStr=JsonUtils.responseJson(code,null);
			HttpUtils.sendResponseJson(responseStr,requestMap);
		}catch(Exception e){
			log.info("删除提醒信息异常:"+e);
			try{
				responseStr = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
				HttpUtils.sendResponseJson(responseStr,requestMap);
			}catch(Exception ee){
				log.error(ee);
			}
		}
		
		//记录操作日志
		String headClientType =  (String)requestMap.get("HeadClientType");
		String headVersion = (String)requestMap.get("HeadVersion");
		interfaceDataLogService.saveInterfaceDataLog(userId, watchId, "", headClientType+headVersion, "Delete remind info");
	}
	
	/**
	 * 根据ID修改提醒状态
	* @description:(设定参数)
	* @return void(返回值说明)
	 */
	@SuppressWarnings("unused")
	public void modifyRemindStatus(){
		String responseStr="";
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		
		try{
			if(requestMap==null){
				responseStr=JsonUtils.responseJson(SportApiCode.ERROR_2001,null);
				HttpUtils.sendResponseJson(responseStr,requestMap);
				return;
			}
			String remindId = ((String[])requestMap.get("id"))[0];
			String status = ((String[])requestMap.get("status"))[0];
			// 修改提醒状态
			Integer ret = remaindService.modifyStatus(Long.valueOf(remindId), Integer.valueOf(status));
			if (ret < 0){
				responseStr = JsonUtils.responseJson(SportApiCode.ERROR_3002, null);	
			}else{
				responseStr = JsonUtils.responseJson(SportApiCode.SUCCESS, null);	
			}
		   HttpUtils.sendResponseJson(responseStr,requestMap);
		}catch(Exception e){
			log.info("修改提醒状态异常:"+e);
			try{
				responseStr = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
				HttpUtils.sendResponseJson(responseStr,requestMap);
			}catch(Exception ee){
				log.error(ee);
			}
		}
	}
	
}
