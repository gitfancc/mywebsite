package com.appscomm.sport.api.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.appscomm.sport.common.SportApiCode;
import com.appscomm.sport.model.PeriodResmindVO;
import com.appscomm.sport.service.PeriodRemindService;
import com.appscomm.sport.utils.HttpUtils;
import com.appscomm.sport.utils.JsonUtils;

public class PcAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(PcAction.class);
	@Autowired
	PeriodRemindService resremind;
	
	public void periodSet(){
		String resp = "";
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		try{			
			String watchid = ((String[])requestMap.get("watchId"))[0];
			String userid = ((String[])requestMap.get("userId"))[0];
			if(StringUtils.isEmpty(watchid) || StringUtils.isEmpty(userid))
			{
				logger.error("watchid or userid is empty!");
				resp = JsonUtils.responseJson(SportApiCode.ERROR_7791, null);
				HttpUtils.sendResponseJson(resp,requestMap);
				return;
			}
			
			PeriodResmindVO record = new PeriodResmindVO();

			//再检查操作是更新还是查询
			String savetype = ((String[])requestMap.get("saveType"))[0];
			
			//save
			if(savetype.equals("1")){		
				String periodStatus1 = ((String[])requestMap.get("periodStatus1"))[0];
				String periodStatus2 = ((String[])requestMap.get("periodStatus2"))[0];
				String periodStatus3 = ((String[])requestMap.get("periodStatus3"))[0];
				String periodStatus4 = ((String[])requestMap.get("periodStatus4"))[0];		
				String period1 = ((String[])requestMap.get("period1"))[0];
				String period2 = ((String[])requestMap.get("period2"))[0];
				String period3 = ((String[])requestMap.get("period3"))[0];
				String period4 = ((String[])requestMap.get("period4"))[0];			
				
				
				logger.info("periodStatus1:[" + periodStatus1 + "]periodStatus2:[" + periodStatus2 +
							"]periodStatus3:[" + periodStatus3 + "]periodStatus4:[" + periodStatus4 +
							"]period1:[" + period1 + "]period2:[" + period2 + "]period3:[" + period3 +
							"]period4:[" + period4 + "]");
				record.setWatchid(watchid);
				record.setUserid(Long.parseLong(userid));
				record.setPeriodonoff1(Integer.parseInt(periodStatus1));
				record.setPeriodonoff2(Integer.parseInt(periodStatus2));
				record.setPeriodonoff3(Integer.parseInt(periodStatus3));
				record.setPeriodonoff4(Integer.parseInt(periodStatus4));
				record.setPeriod1(Integer.parseInt(period1));
				record.setPeriod2(Integer.parseInt(period2));
				record.setPeriod3(Integer.parseInt(period3));
				record.setPeriod4(Integer.parseInt(period4));			
				boolean ret = resremind.updPeriodResmind(record );
				if(!ret){
					logger.error("更新失败!");
					resp = JsonUtils.responseJson(SportApiCode.ERROR_7790, null);
				}
				else{
					resp = JsonUtils.responseJson(SportApiCode.SUCCESS, null);
				}
			}
			//query
			else{
				List<PeriodResmindVO> result = resremind.qryByKey(watchid, Long.parseLong(userid));
				resp = JsonUtils.responseJson(SportApiCode.SUCCESS, 
						(result != null && result.size() > 0) ? result.get(0) : null);
			}			
		}catch(NumberFormatException e){
			logger.error("无效的数字格式：" + e);
			try{
				resp = JsonUtils.responseJson(SportApiCode.ERROR_2001, null);
			}catch(Exception ee){
				
			}			
		}
		catch(Exception e)
		{
			logger.error(e);
			try{
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			}catch(Exception ee){
				
			}
		}
		HttpUtils.sendResponseJson(resp,requestMap);
	}
	
	public void moreSeting(){
		String resp = "";
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		try{			
			String watchid = ((String[])requestMap.get("watchId"))[0];
			String userid = ((String[])requestMap.get("userId"))[0];
			if(StringUtils.isEmpty(watchid) || StringUtils.isEmpty(userid))
			{
				logger.error("watchid or userid is empty!");
				resp = JsonUtils.responseJson(SportApiCode.ERROR_7791, null);
				HttpUtils.sendResponseJson(resp,requestMap);
				return;
			}
			
			PeriodResmindVO record = new PeriodResmindVO();

			//再检查操作是更新还是查询
			String savetype = ((String[])requestMap.get("saveType"))[0];
			
			//save
			if(savetype.equals("1")){		
				String remindSwitch = ((String[])requestMap.get("remindSwitch"))[0];
				String calNum = ((String[])requestMap.get("calNum"))[0];
				String stepNum = ((String[])requestMap.get("stepNum"))[0];
				String targetComplete = ((String[])requestMap.get("targetComplete"))[0];		
			
				record.setWatchid(watchid);
				record.setUserid(Long.parseLong(userid));
				record.setRemindonoff(Integer.parseInt(remindSwitch));
				record.setCalonoff(Integer.parseInt(calNum));
				record.setSteponoff(Integer.parseInt(stepNum));
				record.setTargetonoff(Integer.parseInt(targetComplete));
		
				boolean ret = resremind.updMoreSetting(record );
				if(!ret){
					logger.error("更新失败!");
					resp = JsonUtils.responseJson(SportApiCode.ERROR_7790, null);
				}
				else{
					resp = JsonUtils.responseJson(SportApiCode.SUCCESS, null);
				}
			}
			//query
			else{
				List<PeriodResmindVO> result = resremind.qryByKey(watchid, Long.parseLong(userid));
				resp = JsonUtils.responseJson(SportApiCode.SUCCESS, 
											  (result != null && result.size() > 0) ? result.get(0) : null);
			}			
		}catch(Exception e)
		{
			logger.error(e);
			try{
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			}catch(Exception ee){
				
			}
		}
		HttpUtils.sendResponseJson(resp,requestMap);
	}
}
