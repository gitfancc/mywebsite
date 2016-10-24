package com.appscomm.sport.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.util.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.appscomm.sport.common.SportApiCode;
import com.appscomm.sport.model.UserVO;
import com.appscomm.sport.service.SendMailForPassword;
import com.appscomm.sport.service.UserService;
import com.appscomm.sport.utils.AESEncrypty;
import com.appscomm.sport.utils.AuthImg;
import com.appscomm.sport.utils.HttpUtils;
import com.appscomm.sport.utils.ImageUtil;
import com.appscomm.sport.utils.PropertiesUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author qindf
 *
 */
public class IndexAction extends BaseAction {
	private static final long serialVersionUID = -8160664524280068733L;

	private static Logger log = Logger.getLogger(IndexAction.class);
	private String basicToken = "Basic NjM5MjI0QzEwODUyQzRCNDo5MWFlMjc0NGU5MWNkZWEzMmQ3Zjg2YmFmMWQ0MjUzZg==";
	@Autowired
	private UserService userService;
	private UserVO user;
	private String account;
	private String password;
	private String txtVarcode;
	private Boolean results = true;
	private String checkLogin;
	private Boolean register = false;
	private Integer heightFt; // 英尺
	private Integer heightIn; // 英寸
	static final String IS_LOGINED = "isLogined";
	
	private String KEY_HEX = "C1511E2A29B3C721EC1E4E1C0A396559";
	AESEncrypty encrypty = new AESEncrypty(KEY_HEX);
	private static final String LANGUAGE = "language";// application中key
	@Autowired
	private SendMailForPassword sendMailForPassword;
	private String type;
	private File uploadFile;
	private String uploadFileFileName;
	private String logined;
	
	private String email;
	private String role;
	private String locale1;
	private String tz;
	private String last_name;
	private String first_name;
	private String active;
	
	public String login_index(){
		String logined = (String) session.get("isLogined");
		request.setAttribute("logined", logined);
		return Action.SUCCESS;
	}
	
	public String index_logined(){
		log.error(logined);
		session.put(IS_LOGINED, "logined");
		return Action.SUCCESS;
	}
	
	
	/**
	 * 登录入口
	 * @return
	 */
	public void login(){
	 	String domain = getText("server.domain");
	 	JSONObject param = new JSONObject();
	 	param.put("email", account);
	 	param.put("password", password);
//	 	String basicToken = "Basic "+Base64.encode((account+":"+password).getBytes());
	 	log.info("basic token login:"+basicToken);
		JSONObject data = HttpUtils.postRequest(domain+"v1/account/auth",basicToken,param);
		if(data != null && data.containsKey("token")){
			String token = (String) data.get("jwt");
			session.put("isLogined", "login");
		}
		data.put("domain", domain);
		super.writeJson(data);
	}
	
	// 默认初始语言 或是 找用户计算机上的cookie信息
	public String getLanguage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Cookie[] cookies = request.getCookies();
		Object lan = ServletActionContext.getContext().getApplication().get(LANGUAGE);
		String language = "zh_CN";
		if(lan!=null){
			language = lan.toString();
		}
		// cookie 中没有语言 默认从application取
		for (Cookie ck : cookies) {
			if (ck.getName().equals(LANGUAGE) && ck.getValue() != null
					&& !"".equals(ck.getValue())) {
				language = ck.getValue();
			}
		}
		ActionContext.getContext().setLocale(
				new Locale(language.split("_")[0], language.split("_")[1]));
		ServletActionContext.getContext().getApplication().put(LANGUAGE, language);
		Properties properties = new Properties();

		return language;
	}
	
	public void newRegister(){
//		String url = "https://api.mykronoz.com/v1/account/jwt";
//		String basicToken = "Basic NjM5MjI0QzEwODUyQzRCNDo5MWFlMjc0NGU5MWNkZWEzMmQ3Zjg2YmFmMWQ0MjUzZg==";
		JSONObject param = new JSONObject();
		JSONObject param2 = new JSONObject();
		Locale locale=(Locale)request.getSession().getAttribute("WW_TRANS_I18N_LOCALE");
		if(locale==null){
			locale=Locale.getDefault();
		}
		String language=locale.getLanguage();
		String country=locale.getCountry();	
	 	param.put("email", email);
	 	param.put("locale", language+"-"+country);
	 	param.put("tz", tz);
	 	param.put("last_name", last_name);
	 	param.put("first_name", first_name);
	 	param.put("role", role);
	 	param2.put("account_detail", param);
	 	param2.put("password", password);
	 	param2.put("active", active);
//	 	String domain = PropertiesUtil.getValueByKey("src/main/resources/config.properties", "server.domain");
	 	String domain = getText("server.domain");
//		String basicToken = "Basic "+Base64.encode((account+":"+password).getBytes());
	 	
		log.info("basic token:"+basicToken);
		JSONObject data = HttpUtils.postRequest(domain+"v1/account/jwt",basicToken,param2);
		if(data != null && data.containsKey("token")){
			session.put("isLogined", "login");
		}
		data.put("domain", domain);
		super.writeJson(data);
	}
	
	public String register_index(){
		return Action.NONE;
	}
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 注册用户
	 * @return
	 */
	public String register(){
				String account = user.getAccount().trim();
				if(account.indexOf("@")!=-1)
					user.setMail(account);
				else
					user.setTelphone(account);
				//user.setPassword(encrypty.encrypt(user.getPassword()));
				user.setPassword(user.getPassword());
				user.setNickName(String.valueOf(System.currentTimeMillis()));
				
				user.setUserName(user.getUserName().trim());
				user.setRegisterTime(new Date());
				user.setIsActive(1);
								
				try{
					SportApiCode retCode = userService.isExistUser(user);
					if (retCode != null){
						// 用户已存在
						super.addFieldError("register_error", retCode.getDesc());
						this.register = true;
						user = null;
						return Action.INPUT;
					}
					// upload user picture
					String filePath = getText("file.upload.path");
					String uploadPath = "";
					if (uploadFile != null) {
						// 上传
						int suffixIndex = uploadFileFileName.lastIndexOf('.');
						if (suffixIndex == -1) {
							log.error("Failed to upload the file name suffix! No, not allowed to upload, please try again later！");
						}
						String suffix = uploadFileFileName.substring(suffixIndex + 1)
								.toLowerCase();
						uploadPath = ImageUtil.saveFile(uploadFile, suffix, filePath);
						String imgUrl = uploadPath.substring(filePath.length());
						user.setImgUrl(imgUrl);
					}
					
					if(user.getHeightUnit() == 1){
						Integer ht = this.heightFt*12 + this.heightIn;
						user.setHeight(ht+"");
					}
					
					UserVO resultUser = null;
					if((resultUser = userService.addUserAndPerson(user))!=null){
						session.put(LOGIN_SESSION_USER, resultUser);
						//发送邮件
						sendEmail(account);
						return Action.SUCCESS;
					}
				}catch(Exception e){
					log.info("注册"+e);
					super.addFieldError("register_error", getText("register_error"));
				}
	//		}else
	//			super.addFieldError("verCode_error", getText("verCode_error"));
	//	}
		this.register = true;
		user = null;
		return Action.INPUT;
	}
	
	private void sendEmail(String email) {
		String rootPath = getText("email.url");
		String staticFilePath = getText("static.file.path");
		String companyMail = getText("emailAddress");
		String registSubject = getText("registSubject");
		String emailRegisterContent = getText("email.register");
		String deviceType = getText("eviceType");
		//String watchName = registSubject.split(" ")[0];
		String 
		//客户端的注册密码
		type = request.getParameter("type");
		if(StringUtils.isNotBlank(email)){
			log.info("Start send email: " + email);
			if(StringUtils.isNotBlank(deviceType) && (deviceType.trim().equalsIgnoreCase("L28w") || 
					deviceType.trim().equalsIgnoreCase("L38"))){
				emailRegisterContent = getText("email.register.ext");
				if(deviceType.trim().equalsIgnoreCase("L38")){
					registSubject = "Welcome to ZeFit2 Pulse by MyKronoz";
				}
			}
			//String contents = sendMailForPassword.getMailRegistContents(rootPath, email, txtVarcode, staticFilePath, type, companyMail, watchName);
			String contents = sendMailForPassword.getMailRegistContents(rootPath, email, txtVarcode, emailRegisterContent);
			sendMailForPassword.sendMail(companyMail, registSubject, email, contents);
		}
		log.info("End send email: " + email);
	}
	
	public String validAccount(){
		Map<String,Object> params = new HashMap<String,Object>();
		params.clear();
		String account = user.getAccount();
		if(account.indexOf("@")!=-1)
			params.put("mail", account);
		else
			params.put("telphone", account);
		List<UserVO> userVOs = userService.getUserUnActive(params);
		if(userVOs!=null&&userVOs.size()>0)
			results = false;
		return Action.SUCCESS;
	}
	
	
	public String validNickname(){
		Map<String,Object> params = new HashMap<String,Object>();
		params.clear();
		String nickName = user.getNickName().trim();
		params.put("nickName", nickName);
		List<UserVO> userVOs = userService.getUserUnActive(params);
		if(userVOs!=null&&userVOs.size()>0)
			results = false;
		return Action.SUCCESS;
	}
	
	/**
	 * 系统登出功能
	 * @return
	 */
	public String logout(){
		session.remove("isLogined");
		return Action.SUCCESS;
	}
	
	public String mobileAppDownload() {
		user = super.getUsers();
		return Action.SUCCESS;
	}
	
	public String pcAppDownload() {
		user = super.getUsers();
		return Action.SUCCESS;
	}
	
	public String iosDownload() {
		user = super.getUsers();
		return Action.SUCCESS;
	}
	
	public String andriodDownload() {
		user = super.getUsers();
		return Action.SUCCESS;
	}
	
	/**
	 * 验证登录密码与数据库密码是否相符
	 * @param loginPassword
	 * @param databasePassword
	 * @return
	 */
	private boolean authUser(String loginPassword,String databasePassword){
		//return encrypty.decrypt(databasePassword).equals(loginPassword);
		return loginPassword.equals(databasePassword);
	}

	public String getTxtVarcode() {
		return txtVarcode;
	}

	public void setTxtVarcode(String txtVarcode) {
		this.txtVarcode = txtVarcode;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public Boolean getResults() {
		return results;
	}

	public String getCheckLogin() {
		return checkLogin;
	}

	public void setCheckLogin(String checkLogin) {
		this.checkLogin = checkLogin;
	}

	public Boolean getRegister() {
		return register;
	}

	public void setRegister(Boolean register) {
		this.register = register;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public Integer getHeightFt() {
		return heightFt;
	}

	public void setHeightFt(Integer heightFt) {
		this.heightFt = heightFt;
	}

	public Integer getHeightIn() {
		return heightIn;
	}

	public void setHeightIn(Integer heightIn) {
		this.heightIn = heightIn;
	}

	public String getLogined() {
		return logined;
	}

	public void setLogined(String logined) {
		this.logined = logined;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLocale1() {
		return locale1;
	}

	public void setLocale(String locale) {
		this.locale1 = locale1;
	}

	public String getTz() {
		return tz;
	}

	public void setTz(String tz) {
		this.tz = tz;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
