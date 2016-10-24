/**
 * Copyright lenovo-cw.com 2013. All rights reserved.
 *
 * @createDate 2013-11-22
 */
package com.appscomm.sport.action;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.appscomm.sport.dao.UserDao;
import com.appscomm.sport.model.AppVersionVO;
import com.appscomm.sport.utils.StringUtil;

/**
 * TODO 类/接口描述信息
 *
 * @author Administrator
 *
 */
@SuppressWarnings("all")
@ParentPackage(value="struts-default")
@Namespace("/")
@Action(value="dispaterHtml",
results={
@Result(name="contactus",location="/WEB-INF/view/users/contactus.html",type="freemarker"),
@Result(name="download",location="/WEB-INF/view/users/download.html",type="freemarker"),
@Result(name="downloadAndroid",location="/WEB-INF/view/users/downloadAndroid.html",type="freemarker"),
@Result(name="legal",location="/WEB-INF/view/users/legal.html",type="freemarker"),
@Result(name="downloadpc",location="/WEB-INF/view/users/downloadPC.html",type="freemarker"),
@Result(name="businessConsulting",location="/WEB-INF/view/users/businessConsulting.html",type="freemarker")})
public class DispaterHtml extends BaseAction{
	@Autowired
	private UserDao userDao;
	
	private AppVersionVO appVersionVO;
	
	public AppVersionVO getAppVersionVO() {
		return appVersionVO;
	}

	public void setAppVersionVO(AppVersionVO appVersionVO) {
		this.appVersionVO = appVersionVO;
	}

	public String dispaterContactus(){
		return "contactus";
	}
	
	private String toDateStr(String str) {
		if(StringUtil.isNotNull(str)){
			str = str.split("-")[0] +"年" + str.split("-")[1] +"月" + str.split("-")[2] +"日";
		}
		return str;
	}
	
	public String dispaterdownhtml(){
		appVersionVO = userDao.getVersionsByVer("1:2");
		//英文不需要转换
		appVersionVO.setStrDate(appVersionVO.getDate());
		return "download";
	}
	
	public String dispaterLegal(){
		return "legal";
	}
	
	public String dispaterBusinessConsulting(){
		return "businessConsulting";
	}
	
	public String dispaterdownloadpc(){
		appVersionVO = userDao.getVersionsByVer("1:1");
		//英文不需要转换
		appVersionVO.setStrDate(appVersionVO.getDate());
		return "downloadpc";
	}
	
	public String dispaterdownloadAndroid(){
		appVersionVO = userDao.getVersionsByVer("1:3");
		appVersionVO.setStrDate(toDateStr(appVersionVO.getDate()));
		return "downloadAndroid";
	}
}
