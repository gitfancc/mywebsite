/**
 * Copyright lenovo-cw.com 2013. All rights reserved.
 *
 * @createDate 2013-11-18
 */
package com.appscomm.sport.action;

import java.util.Locale;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;

/**
 * TODO 类/接口描述信息
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("all")
@ParentPackage(value = "struts-default")
@Namespace("/")
@Action(value = "Change", results = { @Result(name = "internationalization", location = "/WEB-INF/Internationalization.jsp") })
public class InternationalAction extends BaseAction {
	private String languageName;

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public String ChangeLanguage() {
		return "internationalization";
	}

	public String ChangeLanguage2() {
		Locale l = Locale.getDefault();
		if (this.languageName.equals("ft")) {
			l = new Locale("zh", "TW");
		} else if (this.languageName.equals("en")) {
			l = new Locale("en", "US");
		} else {
			l = new Locale("zh", "CN");
		}
		ActionContext.getContext().setLocale(l);
		return "internationalization";
	}
}
