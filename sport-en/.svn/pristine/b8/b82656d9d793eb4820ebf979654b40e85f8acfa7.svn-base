/**
 * Copyright appscomm.cn 2013. All rights reserved.
 *
 * @createDate 2013-8-29
 */
package com.appscomm.sport.api.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.appscomm.sport.model.UserVO;
import com.opensymphony.xwork2.ActionSupport;

/**
 *  基础Action类  
 *	
 *  qindf create by 2013-8-29
 *
 */
public class BaseAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = -4034919971885961577L;
	protected DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	protected Map<String,Object> session;
	
	/**
	 * 获取登录用户的Users对象
	 * 
	 * @return Users
	 */
	protected UserVO getUsers() {
		return (UserVO) getSession().get(IndexAction.LOGIN_SESSION_USER);
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
	}

}
