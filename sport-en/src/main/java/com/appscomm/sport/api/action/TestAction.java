package com.appscomm.sport.api.action;

import org.apache.log4j.Logger;


public class TestAction extends BaseAction {
	private static final long serialVersionUID = 2016092113412000L;

	private static Logger log = Logger.getLogger(TestAction.class);

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
		log.error(token);
	}

}
