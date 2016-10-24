package com.appscom.sport.api.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import com.appscomm.sport.utils.HttpUtils;

public class InterfaceDataLogActionTest {
	private List<Header> getHeader() {
		List<Header> hl = new ArrayList<Header>();
		hl.add(new BasicHeader("oauthToken", "000000"));
	//	hl.add(new BasicHeader("clientType", "00"));
	//	hl.add(new BasicHeader("version", "1.0.0"));
		hl.add(new BasicHeader("weblogid", "1"));
		return hl;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testSaveClientVersion() {
		List list = new ArrayList();
		list.add(new BasicNameValuePair("userId", "1"));
		list.add(new BasicNameValuePair("clientName", "WPL28V1.02"));
		list.add(new BasicNameValuePair("clientType", "Apps_winphone"));
		list.add(new BasicNameValuePair("installVersion", "1.0.8"));
		list.add(new BasicNameValuePair("installTime", "2015-01-24"));

		try {
			String responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/set_client_version", getHeader(), list);
			System.out.println(" Response >>> " + responseStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testGetClientVersion() {
		List list = new ArrayList();
		list.add(new BasicNameValuePair("userId", "1"));
		list.add(new BasicNameValuePair("clientName", "WPL28V1.02"));

		try {
			String responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/get_client_version", getHeader(), list);
			System.out.println(" Response >>> " + responseStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testDataLog() {
		String registerId = "4164893255";
		List list = new ArrayList();
		list.add(new BasicNameValuePair("registerId", registerId));

		try {
			String responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/get_user_info", getHeader(), list);
			System.out.println(" Response >>> " + responseStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
