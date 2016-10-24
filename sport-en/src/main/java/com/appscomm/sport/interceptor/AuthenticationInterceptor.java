/**
 * Copyright appscomm.cn 2013. All rights reserved.
 *
 * @createDate 2013-9-4
 */
package com.appscomm.sport.interceptor;

import java.util.Locale;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.appscomm.sport.action.IndexAction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.sun.xml.internal.ws.developer.StreamingAttachment;

/**
 * 权限拦截器
 * 
 * qindf create by 2013-9-4
 * 
 */
public class AuthenticationInterceptor implements Interceptor {

	private static final long serialVersionUID = 5511077335011495501L;

	private static Logger logger = Logger
			.getLogger(AuthenticationInterceptor.class);

	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		String requestUrl = ServletActionContext.getRequest().getRequestURL().toString();
		if(requestUrl.contains("user/sport/index_pc")){
			return actionInvocation.invoke();
		}
		if(requestUrl.contains("user/convertPwd")){
			return actionInvocation.invoke();
		}
		if(requestUrl.contains("user/changePassword")){
			return actionInvocation.invoke();
		}
		if(requestUrl.contains("user/forgetPassword_emailinput")){
			return actionInvocation.invoke();
		}
		if(requestUrl.contains("user/forgetPassword_sendEmail")){
			return actionInvocation.invoke();
		}
		Map<String, Object> session = actionInvocation.getInvocationContext()
				.getSession();
//		Object obj = session.get(IndexAction.LOGIN_SESSION_USER);
		Object obj = session.get("isLogined");
		if (obj == null)
			return Action.LOGIN;
		///logger.debug("登录用户:" + obj);
		/***Map paramMap = actionInvocation.getInvocationContext().getParameters();
		String languageName = "zw";
		
		
		if (paramMap.get("languageName") != null) {
			languageName = ((String[]) paramMap.get("languageName"))[0];
		} else if (session.get("languageName") != null) {
			languageName = ((String[]) session.get("languageName"))[0];
		}
		Locale l = Locale.getDefault();
		if ("ft".equals(languageName)) {
			l = new Locale("zh", "TW");
		} else if ("en".equals(languageName)) {
			l = new Locale("en", "US");
		} else {
			l = new Locale("zh", "CN");
		}
		ActionContext.getContext().setLocale(l);
		session.put("languageName", new String[] { languageName });***/
		return actionInvocation.invoke();
	}

}
