package com.appscomm.sport.api.action;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;

import com.appscomm.sport.common.SportApiCode;
import com.appscomm.sport.model.ClientVersionVO;
import com.appscomm.sport.service.ClientVersionService;
import com.appscomm.sport.utils.HttpUtils;
import com.appscomm.sport.utils.JsonUtils;

public class ClientVersionAction  extends BaseAction {
	private static final long serialVersionUID = 3062295136519477561L;
	private static Log logger = LogFactory.getLog(ClientVersionAction.class);
	
	@Autowired
	private ClientVersionService clientVersionService;
	
	public void setClientVersion(){
		 Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		 requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
			try {
				String userId =  ((String[])requestMap.get("userId"))[0];
				String clientName = (((String[])requestMap.get("clientName"))[0]).trim();
				String clientType =  (((String[])requestMap.get("clientType"))[0]).trim();
				String installVersion =  (((String[])requestMap.get("installVersion"))[0]).trim();
				String installTime =  (((String[])requestMap.get("installTime"))[0]).trim();
				
				/*String watchId =  null;
				if(requestMap.containsKey("watchId")){
					watchId = ((String[])requestMap.get("watchId"))[0];
				}
				String watchType = null;
				if(requestMap.containsKey("watchType")){
					watchType = ((String[])requestMap.get("watchType"))[0];
				}*/
				ClientVersionVO version = new ClientVersionVO();
				version.setClientName(clientName);
				version.setClientType(clientType);
				version.setInstallVersion(installVersion);
				version.setInstallTime(installTime);
				version.setPersonId(Integer.valueOf(userId));
				Integer versionSeq = this.clientVersionService.addClientVersion(version);
				HttpUtils.sendResponse(JsonUtils.responseJson(SportApiCode.SUCCESS, versionSeq),requestMap);
			}catch (Exception e) {
				logger.error(e);
				try {
					HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.ERROR_9999, null),requestMap);
				} catch (JsonGenerationException e1) {
					logger.error(e);
				} catch (JsonMappingException e1) {
					logger.error(e);
				} catch (IOException e1) {
					logger.error(e);
				}
			}
	}
	
	public void getClientVersion(){
		 Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		 requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		try {
			String userId =  ((String[])requestMap.get("userId"))[0];
			String clientName = ( ((String[])requestMap.get("clientName"))[0]).trim();
			ClientVersionVO version = new ClientVersionVO();
			version.setClientName(clientName);
			version.setPersonId(Integer.valueOf(userId));
			version = this.clientVersionService.getClientVersion(version);
			HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.SUCCESS, version),requestMap);
		}catch (Exception e) {
			logger.error(e);
			try {
				HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.ERROR_9999, null),requestMap);
			} catch (JsonGenerationException e1) {
				logger.error(e);
			} catch (JsonMappingException e1) {
				logger.error(e);
			} catch (IOException e1) {
				logger.error(e);
			}
		}
	}
}
