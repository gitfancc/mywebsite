package com.appscom.sport.api.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.appscomm.sport.utils.AESEncrypty;
import com.appscomm.sport.utils.HttpUtils;

public class IndexActionTest {
	private boolean DEBUG = true;
	
	 private List<Header> getHeader(){
	    	List<Header> hl = new ArrayList<Header>();
			hl.add(new BasicHeader("oauthToken", "000000"));
			hl.add(new BasicHeader("clientType", "00"));
			hl.add(new BasicHeader("version", "1.0.0"));
			hl.add(new BasicHeader("weblogid", "1"));
			return hl;
	    }
	    
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Test
		public void testLogin() {
			String account = "lijinliang@appscomm.cn";
			//String account = "13101478520";
			String password = DigestUtils.md5Hex("123456");
			try{
				List list = new ArrayList();
				list.add(new BasicNameValuePair("account", account));
				list.add(new BasicNameValuePair("password", password));
				list.add(new BasicNameValuePair("encryptMode", "1"));
				
				String responseStr = null;
				//if (DEBUG)
				//	responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/login", getHeader(),  list);
				//else
					responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/login", getHeader(),  list);
				
				System.out.println(" Response >>> " + responseStr);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		@Test
		public void testLoginEx() {
			String account = "lijinliang@appscomm.cn";
			String password = "654321";
			//String account = "123456@ggg.com";
		//	String password = "123321";
			try{
				List list = new ArrayList();
				list.add(new BasicNameValuePair("account", account));
				list.add(new BasicNameValuePair("password", password));
				String responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/login_ex", getHeader(),  list);
				System.out.println(" Response >>> " + responseStr);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Test
		public void testRegister() {
			String mail = "kaka@163.com";
			String telephone = "13101478520";
			String nickName = "hoho";
			String password = "123456";
			String volidCode = "";

			List list = new ArrayList();
			list.add(new BasicNameValuePair("mail", mail));
			list.add(new BasicNameValuePair("telephone", telephone));
			list.add(new BasicNameValuePair("nickName", nickName));
			list.add(new BasicNameValuePair("password", password));
			list.add(new BasicNameValuePair("volidCode", volidCode));
			
			try{
				String responseStr = null;
				if (DEBUG)
					responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/reg", getHeader(),  list);
				else
					responseStr = HttpUtils.sendRequest("http://ledong.appscomm.cn/sport/api/reg", getHeader(),  list);
				
				System.out.println(" Response >>> " + responseStr);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		@Test
		public void testVolidCode() {
			getVolidCode();
		}
		
		@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
		@Test
		public void testForgetPassword(){
			String mail = "lijinliang@appscomm.cn";
			String telephone = "13101478520";

			List list = new ArrayList();
			list.add(new BasicNameValuePair("email", mail));
			try{
				String responseStr = null;
				if (DEBUG)
					responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/forgot_passwd", getHeader(),  list);
				else
					responseStr = HttpUtils.sendRequest("http://ledong.appscomm.cn/sport/api/forgot_passwd", getHeader(),  list);
				
				System.out.println(" Response >>> " + responseStr);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		@Test
		public void testModifyPassword(){
			String accountId = "lijinliang@appscomm.cn";
			String oldPassword = DigestUtils.md5Hex("123456");
			String newPassword = DigestUtils.md5Hex("654321");

			List list = new ArrayList();
			list.add(new BasicNameValuePair("accountId", accountId));
			list.add(new BasicNameValuePair("oldPassword", oldPassword));
			list.add(new BasicNameValuePair("newPassword", newPassword));
			list.add(new BasicNameValuePair("encryptMode", "1"));
			
			try{
				String responseStr = null;
				if (DEBUG)
					responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/modify_password", getHeader(),  list);
				else
					responseStr = HttpUtils.sendRequest("http://ledong.appscomm.cn/sport/api/modify_password", getHeader(),  list);
				
				System.out.println(" Response >>> " + responseStr);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
		@Test
		public void testRegisterForFrance(){
			String telephone = "13101478520";
			String userName = "heihei33";
			String email = "lijinliang@appscomm.cn";
			String password = DigestUtils.md5Hex("123456789");
			String gender  = "1";
			String birthDay = "2010-10-10";
			String height = "156";
			String weight = "113.5";
			String heightUnit = "1";
			String weightUnit = "1";
			String client="ios";

			List list = new ArrayList();
			list.add(new BasicNameValuePair("userName", userName));
			list.add(new BasicNameValuePair("email", email));
			list.add(new BasicNameValuePair("password", password));
			list.add(new BasicNameValuePair("gender", gender));
			list.add(new BasicNameValuePair("birthDay", birthDay));
			list.add(new BasicNameValuePair("height", height));
			list.add(new BasicNameValuePair("weight", weight));
			list.add(new BasicNameValuePair("heightUnit", heightUnit));
			list.add(new BasicNameValuePair("weightUnit", weightUnit));
			list.add(new BasicNameValuePair("encryptMode", "1"));
			list.add(new BasicNameValuePair("client", client));
			
			try{
				String responseStr = null;
				responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/reg_for_france", getHeader(),  list);
				System.out.println(" Response >>> " + responseStr);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String getVolidCode() {
		String volidCode = "";
		try {
			/*StringBuilder sb = new StringBuilder();
			sb.append("{");
			sb.append("\"").append("tag").append("\"").append(":").append("\"")	.append("").append("\"");
			sb.append("}");
			String jsonData = sb.toString();*/
			List list = new ArrayList();
			list.add(new BasicNameValuePair("tag", volidCode));
					
			String responseStr = null;
			if (DEBUG)
				responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/get_valid_img", getHeader(),  list);
			else
				responseStr = HttpUtils.sendRequest("http://ledong.appscomm.cn/sport/api/get_valid_img", getHeader(),  list);
			
			System.out.println(" Response >>> " + responseStr);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(responseStr);
			volidCode = node.get("data").get("authCode").getTextValue();
			System.out.println("Code:" + volidCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return volidCode;
	}
	
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	@Test
	public void testGetNewPassword(){
		String telephone = "13101478520";
		String email = "lijinliang@appscomm.cn";
		List list = new ArrayList();
		list.add(new BasicNameValuePair("account", email));
		try{
			String responseStr = null;
			if (DEBUG)
				responseStr = HttpUtils.sendRequest("http://localhost:8080/sport/api/get_new_password", getHeader(),  list);
			else
				responseStr = HttpUtils.sendRequest("http://ledong.appscomm.cn/sport/api/get_new_password", getHeader(),  list);
			
			System.out.println(" Response >>> " + responseStr);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void getUserConfig(){
		String KEY_HEX = "C1511E2A29B3C721EC1E4E1C0A396559";
		AESEncrypty encrypty = new AESEncrypty(KEY_HEX);
		System.out.println("pwd1:" + encrypty.encrypt("0926323269"));
		//System.out.println("pwd2:" + encrypty.encrypt("654321"));
		
		//System.out.println("rpwd1:" + encrypty.decrypt("7D0BF2AC72AB09B325521AF6CF826113"));
		System.out.println("rpwd2:" + encrypty.decrypt("EC7EC6756FE82678904EB9F8A59A0B61"));
		
		//System.out.println("md5:"  + DigestUtils.md5Hex("123456"));
	}
	
	@Test
	public void testMd5(){
		System.out.println(DigestUtils.md5Hex("1234567890"));
		System.out.println(DigestUtils.md5Hex("123456"));
		System.out.println(DigestUtils.md5Hex("12345678901234567890"));
		
		String KEY_HEX = "C1511E2A29B3C721EC1E4E1C0A396559";
		AESEncrypty encrypty = new AESEncrypty(KEY_HEX);
		System.out.println(encrypty.encrypt("1234567890"));
		System.out.println(encrypty.encrypt("123456"));
		System.out.println(encrypty.encrypt("1234567812345678"));
	}
}
