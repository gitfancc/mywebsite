package com.appscomm.sport.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CookieUtils {
	private static Log logger = LogFactory.getLog(CookieUtils.class);
	public static void addCookie(String name,String value,HttpServletResponse response ) {
		try{
		Cookie u_cookie = new Cookie(name, value);
		u_cookie.setMaxAge(3600 * 60 * 24 * 14);
		u_cookie.setPath("/");
		u_cookie.setDomain("localhost");
		response.addHeader("p3p", "CP=\"CAO PSA OUR\"");//跨域iframe
		response.addCookie(u_cookie);
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}
	}


	public static void deleteCookie(String name,String value,HttpServletResponse response ) {
		try{
		Cookie u_cookie = new Cookie(name, value);
		u_cookie.setMaxAge(0);
		u_cookie.setPath("/");
		u_cookie.setDomain("localhost");
		response.addHeader("p3p", "CP=\"CAO PSA OUR\"");//跨域iframe
		response.addCookie(u_cookie);
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}
	}
	public static String getCookie(HttpServletRequest request, String key) {
		try{
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				if(cookies.length==0)return null;
				for (Cookie cookie : cookies) {
				//	logger.debug("cookieName: " + cookie.getName());
					if (key.equals(cookie.getName())) {
						String value = cookie.getValue();
						// logger.debug("cookieValue: " + cookie.getValue());
						return value;
					}
				}
			}
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}
		return "";
	}
	

}
