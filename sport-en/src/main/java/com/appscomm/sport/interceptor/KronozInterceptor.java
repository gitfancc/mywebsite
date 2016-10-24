package com.appscomm.sport.interceptor;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;

import com.appscomm.sport.common.SportApiCode;
import com.appscomm.sport.dao.AdminUserDao;
import com.appscomm.sport.model.SysParamVO;
import com.appscomm.sport.service.AccessTokenService;
import com.appscomm.sport.utils.HttpUtils;
import com.appscomm.sport.utils.JsonUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class KronozInterceptor implements Interceptor {

	private static final long serialVersionUID = 9187422646361036235L;
	private static Logger logger = Logger.getLogger(KronozInterceptor.class);
	private final static String KRONOZ_TOKEN = "kronoz_token";
	@Autowired
	private AccessTokenService accessTokenService;
	@Autowired
	private AdminUserDao adminUserDao;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		SysParamVO param = adminUserDao.queryKronozTokenLimit();
    	Date currentDate = new Date();
    	boolean valid = currentDate.after(DateUtils.parseDate(param.getParamValue(), "yyyy-MM-dd HH:mm:ss"));
    	if(valid){
    		//验证
    		int result = validateKronozToken(ServletActionContext.getRequest(),ServletActionContext.getResponse(),valid);
    		if(result!=1){
    			return null;
    		}
    	}
    	validateKronozToken(ServletActionContext.getRequest(),ServletActionContext.getResponse(),valid);
		return actionInvocation.invoke();
	}
	private int validateKronozToken(HttpServletRequest request,HttpServletResponse response,boolean valid) {
		int result = 1;
		try {
			String kronozToken = getKronozToken(request);
			//header
		    if(valid && StringUtils.isBlank(kronozToken)){
		    	logger.info(">>>>>>>null kronozToken Error" + "\n" );
		    	HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.ERROR_8089, null),null);
		    	return 808;
		    }else {
		    	logger.info(" >>>>>>>get kronozToken from Header" + "\n" );
		    }
		    if(valid){
		    	result = accessTokenService.validKronozToken(kronozToken);
		    	if(result != 1){
		    		HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.getSportApiCode(String.valueOf(result)), null),null);
			    }
		    }
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	public String getKronozToken(HttpServletRequest request) {
		String token = request.getHeader(KRONOZ_TOKEN);
		return token != null ? token : request.getParameter(KRONOZ_TOKEN);
	}
}
