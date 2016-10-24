/**
 * Copyright appscomm.cn 2013. All rights reserved.
 *
 * @createDate 2013-8-29
 */
package com.appscomm.sport.action;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.alibaba.fastjson.JSON;
import com.appscomm.sport.model.UserVO;
import com.appscomm.sport.utils.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 *  基础Action类  
 *	@author xc
 */
public class BaseAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = -4034919971885961577L;
	
	protected Map<String,Object> session;
	
	private String defaultWatchType;
	
	public static final String LOGIN_SESSION_USER = "loginUsers";
	public static final String DEFAULT_WATCH_TYPE = "defalutWatchType";
	public static final String LOG_IN_LINK = "loginLink";
	public static final String LOGINED = "isLogined";
	
	/**
	 * 获取登录用户的Users对象
	 * 
	 * @return Users
	 */
	protected UserVO getUsers() {
		return (UserVO) getSession().get(LOGIN_SESSION_USER);
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	
	protected String getLogin(){
		return (String) request.getSession().getAttribute(LOGINED);
	}
	
	protected HttpServletRequest request = ServletActionContext.getRequest();
	protected HttpServletResponse response = ServletActionContext.getResponse();
	
	/**
     * 获取request对象中所有参数，并设置到map中
     * @param request
     * @return
     */
	public static Map getMapByRequest(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// 检查输入请求是否为multipart表单数据
		try {
			if (isMultipart) {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				List<FileItem> items  = upload.parseRequest(request);
				Iterator<FileItem> it = items.iterator();
				while (it.hasNext()) {
					FileItem item = (FileItem) it.next();
					if (item.isFormField()) {
						String fileName = item.getFieldName();
						map.put(fileName, item.getString().trim());
					}
				}
			} else {
				Enumeration enu = request.getParameterNames();
				while (enu.hasMoreElements()) {
					String paraName = (String) enu.nextElement();
					String paraValue = request.getParameter(paraName).trim();
					if (paraValue != null && !"".equals(paraValue)) {
						map.put(paraName, paraValue);
					}
				}
				if (StringUtil.checkObj(request.getRequestURI())) {// 获取当前请求url
					map.put("sURLs", request.getRequestURI().toString());
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 将对象转换成JSON字符串，并响应回前台
	 * 
	 * @param object
	 * @throws IOException
	 */
	public void writeJson(Object object) {
		try {
			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getDefaultWatchType() {
		//this.defaultWatchType =  (String) session.get(IndexAction.DEFAULT_WATCH_TYPE);
		//return this.defaultWatchType;
		return getText("deviceType", "L28");
	}

	public void setDefaultWatchType(String defaultWatchType) {
		session.put(DEFAULT_WATCH_TYPE, defaultWatchType);
		this.defaultWatchType = defaultWatchType;
	}
}
