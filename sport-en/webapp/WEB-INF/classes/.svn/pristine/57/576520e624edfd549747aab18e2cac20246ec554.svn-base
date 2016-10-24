/**
 * Copyright appscomm.cn 2013. All rights reserved.
 *
 * @createDate 2013-9-4
 */
package com.appscomm.sport.api.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.appscomm.sport.common.SportApiCode;
import com.appscomm.sport.utils.AuthImg;
import com.appscomm.sport.utils.HttpUtils;
import com.appscomm.sport.utils.JsonUtils;
import com.opensymphony.xwork2.ActionSupport;


/**
 * 验证码
 * 
 * qindf create by 2013-9-4
 *
 */
public class AuthImgAction extends ActionSupport {

	private static final long serialVersionUID = 5919209162216574140L;
	
	public void authCode() throws JsonGenerationException, JsonMappingException, IOException{

		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String tmp = getRandomChar();
			sRand += tmp;
		}

		HttpSession session = ServletActionContext.getRequest().getSession(true);
		session.setAttribute(AuthImg.AUTH_VERCODE, sRand);
		String responseStr= "{\"result\":\""+SportApiCode.SUCCESS.getCode()+"\",\"message\":\""+SportApiCode.SUCCESS.getDesc()+"\",\"data\":{\"authCode\":\""+sRand+"\"}}";
		HttpUtils.sendResponseJson(responseStr,requestMap);
	}

	private String getRandomChar() {
		int rand = (int) Math.round(Math.random() * 1);
		long itmp = 0;
		char ctmp = '\u0000';
		switch (rand) {
//		case 1:
//			itmp = Math.round(Math.random() * 25 + 65);
//			ctmp = (char) itmp;
//			return String.valueOf(ctmp);
		case 1:
			itmp = Math.round(Math.random() * 25 + 97);
			ctmp = (char) itmp;
			return String.valueOf(ctmp);
		default:
			itmp = Math.round(Math.random() * 9);
			return String.valueOf(itmp);
		}
	}

}
