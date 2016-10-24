/**
 * Copyright appscomm 2014. All rights reserved.
 */

package com.appscomm.sport.interceptor;

import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * @ClassName:I18nInterceptor.java
 * @Description:国际化资源拦截器
 * @author:  叶子丰
 * @date:    2014-6-24
 */

public class I18nInterceptor implements Interceptor {

	private static final long serialVersionUID = -1324102175023783814L;

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
		Map<String, Object> paramMap = actionInvocation.getInvocationContext().getParameters();
		String i18n=(String)paramMap.get("i18n");
		String language="en";
		String country="US";
		if(StringUtils.isNotBlank(i18n)){
			if(i18n.contains("_")){
				String[] s=i18n.split("_");
				if(s!=null&&s.length>0){
					language=s[0];
					country=s[1];
				}
			}else{
				language=i18n;
			}
			/***String[] s=i18n.split("_");
			if(s!=null&&s.length>0){
				language=s[0];
				country=s[1];
			}else{
				language=i18n;
			}***/			
		}
		Locale local=new Locale(language, country);
		Map<String, Object> session = actionInvocation.getInvocationContext()
				.getSession();
		session.put("WW_TRANS_I18N_LOCALE", local);
		
		return actionInvocation.invoke();
	}

}
