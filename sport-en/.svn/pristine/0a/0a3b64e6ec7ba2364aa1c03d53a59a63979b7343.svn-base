/**
 * Copyright lenovo-cw.com 2014. All rights reserved.
 *
 * @createDate 2014-3-21
 */
package com.appscomm.sport.utils;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * TODO 类/接口描述信息
 * 
 * @author Administrator
 * 
 */
public class MyAuthenticator  extends Authenticator{
	String userName = null;
	String password = null;

	public MyAuthenticator() {
	}

	public MyAuthenticator(String username, String password) {
		this.userName = username;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}
}
