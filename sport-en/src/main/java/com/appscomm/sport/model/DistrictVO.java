/**
 * Copyright appscomm.cn 2013. All rights reserved.
 *
 * @createDate 2013-9-16
 */
package com.appscomm.sport.model;

import java.io.Serializable;

/**
 *  省、市、区实体对象  
 *	
 *  qindf create by 2013-9-16
 *
 */
public class DistrictVO implements Serializable {

	private static final long serialVersionUID = -8712614854308175602L;
	
	private String shortName;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	@Override
	public String toString() {
		return "DistrictVO [shortName=" + shortName + ", name=" + name + "]";
	}

}
