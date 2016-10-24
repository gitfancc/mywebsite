/**
 * Copyright lenovo-cw.com 2013. All rights reserved.
 *
 * @createDate 2013-11-18
 */
package com.appscomm.sport.interceptor;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionContext;


/**
 * TODO 类/接口描述信息
 * @author Administrator
 * 
 */
public class I18NFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest r = (HttpServletRequest) req;		
		String i18n=r.getParameter("i18n");
		String language="";
		String country="";
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
			Locale local=new Locale(language, country);
			r.getSession().setAttribute("WW_TRANS_I18N_LOCALE", local);	
		}else{//如果没有传参数取当前session所设置的值
			Locale locale=(Locale)r.getSession().getAttribute("WW_TRANS_I18N_LOCALE");
			if(locale==null){
				locale=Locale.getDefault();
			}
			language=locale.getLanguage();
			country=locale.getCountry();						
		}		
		MyRequestWrapper request = new MyRequestWrapper(r);
		chain.doFilter(request, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
