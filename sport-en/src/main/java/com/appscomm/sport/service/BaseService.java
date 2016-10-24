/**
 * Copyright lenovo-cw.com 2013. All rights reserved.
 *
 * @createDate 2013-10-29
 */
package com.appscomm.sport.service;

import com.appscomm.sport.utils.StringUtil;


/**
 * TODO 类/接口描述信息
 *
 * @author xc
 *
 */
public class BaseService {

	private static final String  restfulURL = StringUtil.getProperty("restfulURL");
	
	public static void main(String[] args) {
		System.out.println(restfulURL);
	}
}
