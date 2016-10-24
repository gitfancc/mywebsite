/**
 * Copyright appscomm.cn 2013. All rights reserved.
 *
 * @createDate 2013-9-11
 */
package com.appscomm.sport.action;

import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.appscomm.sport.common.SportApiCode;
import com.appscomm.sport.model.AdvancedSetttingVO;
import com.appscomm.sport.model.HeartRateVO;
import com.appscomm.sport.model.LogInLinkInfo;
import com.appscomm.sport.model.ParamSportVO;
import com.appscomm.sport.model.PersonVO;
import com.appscomm.sport.model.PersonWatchVO;
import com.appscomm.sport.model.UserVO;
import com.appscomm.sport.service.AdminUserService;
import com.appscomm.sport.service.ParamSportService;
import com.appscomm.sport.service.SendMailForPassword;
import com.appscomm.sport.service.UserService;
import com.appscomm.sport.utils.AESEncrypty;
import com.appscomm.sport.utils.ChartDataUtils;
import com.appscomm.sport.utils.ImageUtil;
import com.appscomm.sport.utils.Tools;
import com.opensymphony.xwork2.Action;

/**
 * 用户控制层Action
 * 
 * qindf create by 2013-9-11
 * 
 */
public class UserAction extends BaseAction {

	private static final long serialVersionUID = -4502261895346456171L;
	private static Logger log = Logger.getLogger(UserAction.class);

	@Autowired
	private UserService userService;
	@Autowired
	private ParamSportService paramSportService;
	@Autowired
	private SendMailForPassword sendMailForPassword;
	@Autowired
	private AdminUserService adminUserService;
	
	private UserVO user;
	private PersonVO person;
	private List<ParamSportVO> paramSportResults;
	private List<HeartRateVO> hearRates;
	private Map<String, Object> resultMap = new HashMap<String, Object>();
	private String watchId;
	private Long personId;
	private String searchDate;
	private String languageName;
	private String KEY_HEX = "C1511E2A29B3C721EC1E4E1C0A396559";
	private AESEncrypty encrypty = new AESEncrypty(KEY_HEX);
	private String email;
	private String txtVarcode;
	private String type;
	private String uid = "0";
	private Long t;
	private String code;
	private String clientIp;
	
	private File uploadFile;
	private String uploadFileFileName;
	private Integer heightFt; // 英尺
	private Integer heightIn; // 英寸
	private String userAccount;
	private List<PersonWatchVO> watchList;
	private String message;
	private Integer sid;
    private String token;
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String index() {
		log.info("");
//		user = super.getUsers();
	
		return Action.SUCCESS;
	}
	
	private boolean decodePCLogin(String uid, String clientIp, String time, String code){
		String md5Code = DigestUtils.md5Hex(uid+time+"PC");
		if(code.equals(md5Code.substring(8, 24))){
			return true;
		}
		
		return false;
	}
	
	public String mylog_pc() {
		//PC端 实现自动登录
		LogInLinkInfo logInfo = null;
		HashMap<String, Object> params = new HashMap<String, Object>();
		log.info("PC Parameter UID: " + uid);
		
		if(!NumberUtils.isNumber(uid) || "0".equals(uid)){
			return Action.NONE;
		}
		
		if(code == null || t == null){
			return Action.NONE;
		}
		
		if(((System.currentTimeMillis()/1000)-t)>1800){
			log.info("PC link is timeout...");
			return Action.NONE;
		}
	//	logInfo = (LogInLinkInfo) session.get(LOG_IN_LINK);
		if(!decodePCLogin(uid, clientIp, t+"", code)){
			return Action.NONE;
		}
		
		params.put("id", uid);
		//get user info by email or phone which matches the given string.
		List<UserVO> resultUsers = userService.getUserActive(params);
		if(resultUsers!=null&&resultUsers.size()>0){
			//get the first match result
			UserVO userVO = resultUsers.get(0);
			params.clear();
			params.put("personId", userVO.getUserId());
			params.put("active", 1);
			params.put("isDefault", 1);
			List<PersonWatchVO> personWatchList = userService.getPersonWatch(params);
			if(personWatchList != null && personWatchList.size()>0){
				userVO.setWatchId(personWatchList.get(0).getWatchId());
				session.put(DEFAULT_WATCH_TYPE, personWatchList.get(0).getWatchType());
			}
			session.put(LOGIN_SESSION_USER, userVO);
		//	logInfo = new LogInLinkInfo(Long.valueOf(uid+""), userVO.getUserId(), clientIp, t, code);
		//	session.put(LOG_IN_LINK, logInfo);
			
		//	log.debug("Login Default watchType:" + super.getDefaultWatchType());
		}else{
			return Action.NONE;
		}
		user = super.getUsers();
		if (locale_language != null && !locale_language.equals("")) {
			writeLanguage();
		}
		return Action.SUCCESS;
	}
	
	public String forgetPassword_emailinput(){
		return Action.SUCCESS;
	}
	
	public String forgetPassword_sendEmail(){
		String domain = getText("server.domain");
		String url = domain+"v1/account/password/reset/"+email;
		String basicToken = "Basic NjM5MjI0QzEwODUyQzRCNDo5MWFlMjc0NGU5MWNkZWEzMmQ3Zjg2YmFmMWQ0MjUzZg==";
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		String message = "";
        try {
        	get.setHeader("Authorization", basicToken);
            CloseableHttpResponse httpResponse = null;
            //发送get请求
            httpResponse = httpClient.execute(get);
                //response实体
            HttpEntity entity = httpResponse.getEntity();
            if (null != entity){
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                log.info("response code:"+ statusCode);
                String content = EntityUtils.toString(entity);
                if(statusCode == 200){
                	JSONObject jsoncContent = JSONObject.parseObject(content);
                	message = jsoncContent.getString("message");
                }else{
                	message = content;
                }
               }
        } catch (Exception e) {
            log.error(e.getMessage());
        }finally{
        	get.releaseConnection();
        }
        request.setAttribute("email", email);
        request.setAttribute("message", message);
		return Action.SUCCESS;
	}
	
	public String forgetPassword_resetinput(){
		String validTime = request.getParameter("date");
		request.setAttribute("date", validTime);
		long times = Long.parseLong(validTime);
		long currentTime = new Date().getTime();
		email = request.getParameter("email");
		txtVarcode = request.getParameter("verCode");
		type = request.getParameter("type");
		if(((currentTime - times)/1000/60/60) > 24){
			log.info("24小时内邮件失效！");
			return "valid";
		}
		return this.SUCCESS;
	}
	
	public String forgetPassword_reset(){
		//String password = encrypty.encrypt(request.getParameter("password"));
		String password =request.getParameter("password");
		//修改密码
		UserVO user = new UserVO();
		user.setPassword(password);
		user.setMail(email.trim());
		if(userService.updatePwd(user) > 0){
			request.setAttribute("resetMsg", getText("reset_password_success"));
		}else{
			request.setAttribute("resetMsg", getText("reset_password_failed"));
		}
		return Action.SUCCESS;
	}
	
	private Boolean results = true;
	
	public Boolean getResults() {
		return results;
	}

	public void setResults(Boolean results) {
		this.results = results;
	}

	//验证邮箱是否存在
	public void checkEmail(){
		log.info("UserAction.checkEmailcheckEmail");
		if(userService.isExistsByEmail(email.trim())){
			results = true;
		}else{
			results = false;
		}
		super.writeJson(results);
	}
	
	public String getTxtVarcode() {
		return txtVarcode;
	}

	public void setTxtVarcode(String txtVarcode) {
		this.txtVarcode = txtVarcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

	
	public String info() {
		log.info("");
		String logined = (String) session.get("isLogined");
		request.setAttribute("logined", logined);
		return Action.SUCCESS;
	}
	
	private boolean isSystemUser(){
		boolean flag=false;
		user = super.getUsers();
		if(user==null){
			return false;
		}
		String[] sysUser;
		try {
			sysUser = adminUserService.querySpecialAdmin();//getText("watch.search.system.user").split(",");
			if(sysUser!=null&&sysUser.length>0){
				for(String s:sysUser){
					if(s.equals(user.getMail())){
						flag=true;
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/***
	 * 
	* @description:根据账号查询绑定记录
	* @return
	* @return String
	* @author 叶子丰  2014-7-29
	 */
	public String listDevByAccount(){
		if(!isSystemUser()){
			//跳转到错误提示页面
			this.message="illegal User!";
			return "msg";
		}
		if(StringUtils.isNotBlank(this.userAccount)&&StringUtils.isBlank(this.watchId)){//根据账号查询
			try {
				this.watchList=this.adminUserService.qryWatchByUser(this.userAccount.trim());
			} catch (Exception e) {
				log.error("[UserAction.listDevByAccount] - catch exception!",e);
			}
		}else if(StringUtils.isBlank(this.userAccount)&&StringUtils.isNotBlank(this.watchId)){//根据设备号查询
			 int i = 13;//回车Ascii码值
			 int ii = 10;//换行Ascii码值
			 char c = (char)i;
			 char cc = (char)ii;			 
			String[] watches=this.watchId.split(c+""+cc+"");
			try {
				this.watchList=this.adminUserService.qryWatchByWatchId(watches);
			} catch (Exception e) {
				log.error("[UserAction.listDevByAccount] - catch exception!",e);
			}
		}else if(StringUtils.isNotBlank(this.userAccount)&&StringUtils.isNotBlank(this.watchId)){
			this.message="Error Param!";
		}
		return Action.SUCCESS;
	}
	
	/***
	 * 
	* @description:根据账号查询绑定记录
	* @return
	* @return String
	* @author 叶子丰  2014-7-29
	 */
	public void releaseWatch(){
		if(this.sid==null||this.sid<=0){
			log.info("参数异常!sid="+sid);
			super.writeJson(SportApiCode.ERROR_2001.getDesc());
			return;
		}
		if(!isSystemUser()){
			super.writeJson(SportApiCode.ERROR_2001.getDesc());
			return;
		}
		try {
			this.adminUserService.realseOneWatch(sid);
			super.writeJson(SportApiCode.SUCCESS.getDesc());
		} catch (Exception e) {
			log.error("[UserAction.listDevByAccount] - catch exception!",e);
			super.writeJson(SportApiCode.ERROR_9999.getDesc());
		}
	}
		

	public String save() {
		user = super.getUsers();
		log.info("Save userInfo:" + user.toString());
	
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
			person.setHeight(ht+"");
			person.setHeightUnit(1);
			person.setWeightUnit(1);
		}
		person.setEmail(user.getMail());
		person.setId(user.getUserId());
		person.setImgUrl(user.getImgUrl());
		person.setNickName(person.getUserName());
		userService.editPerson(person);
		//刷新用户
		//groupService.flushUser(user.getId()+"");
		return Action.SUCCESS;
	}

	public String mylog() {
//		user = super.getUsers();
//		if (locale_language != null && !locale_language.equals("")) {
//			writeLanguage();
//		}
//		
//		getAdvanceSetting();
		String logined = (String) session.get("isLogined");
		request.setAttribute("logined", logined);
		return Action.SUCCESS;
	}

	private static final String LANGUAGE = "language";// application中key
	private String locale_language;// 界面选择的语言

	public String getLocale_language() {
		return locale_language;
	}

	public void setLocale_language(String localeLanguage) {
		locale_language = localeLanguage;
	}

	// 写cookie
	public void writeLanguage() {
		HttpServletResponse response = ServletActionContext.getResponse();
		Cookie cookie = new Cookie(LANGUAGE, locale_language);
		cookie.setMaxAge(356 * 24 * 60 * 60);
		response.addCookie(cookie);
	}
	

	public String sport() {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> map= this.getMapByRequest(request);
		params.put("watchId", map.get("watchId"));
		if(map.get("searchDate")!=null){
			params.put("searchDate", map.get("searchDate"));
		}else{
			params.put("searchDate", Tools.getCurrentDate());
		}
		// 保存用户id和设备类型
		user = super.getUsers();
		if (user != null){
			params.put("personId", user.getUserId());
		}
		
		/*String watchType = super.getDefaultWatchType();
		if (StringUtils.isNotEmpty(watchType)){
			params.put("watchType",  watchType);
		}*/
		
		paramSportResults = paramSportService.getParamSports(params);
		try {// add by xc
			paramSportResults = ChartDataUtils.today2(paramSportResults, 1,
					Tools.getCurrentDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		hearRates = paramSportService.getHeartRates(params);
		resultMap.put("searchDate", Tools.getCurrentDate());
		resultMap.put("paramSportResults", paramSportResults);
		resultMap.put("hearRates", hearRates);
		setLatelySportTime(params);

		return Action.SUCCESS;
	}

	public String previous() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("watchId", watchId);
		Map<String, String> map= this.getMapByRequest(request);
		if(map.get("current")!=null){
			params.put("searchDate", Tools.dateToString(
					Tools.stringToDate(map.get("searchDate"), "yyyy-MM-dd"),
					"yyyy-MM-dd"));
		}else{
			params.put("searchDate",
					Tools.dateToString(Tools.addDays(
							Tools.stringToDate(searchDate, "yyyy-MM-dd"), -1),
							"yyyy-MM-dd"));
		}
		// 保存用户id和设备类型
		user = super.getUsers();
		if (user != null){
			params.put("personId", user.getUserId());
		}
		
		/*String watchType = super.getDefaultWatchType();
		if (StringUtils.isNotEmpty(watchType)){
			params.put("watchType",  watchType);
		}*/
		paramSportResults = paramSportService.getParamSports(params);
		try {// add by xc
			paramSportResults = ChartDataUtils.today2(paramSportResults, 1,
					Tools.getCurrentDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		hearRates = paramSportService.getHeartRates(params);
		resultMap.put("searchDate",
				Tools.dateToString(Tools.addDays(
						Tools.stringToDate(searchDate, "yyyy-MM-dd"), -1),
						"yyyy-MM-dd"));
		resultMap.put("paramSportResults", paramSportResults);
		resultMap.put("hearRates", hearRates);
		setLatelySportTime(params);
		return Action.SUCCESS;
	}

	public String next() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("watchId", watchId);
		params.put("searchDate", Tools.dateToString(
				Tools.addDays(Tools.stringToDate(searchDate, "yyyy-MM-dd"), 1),
				"yyyy-MM-dd"));
		// 保存用户id和设备类型
		user = super.getUsers();
		if (user != null){
			params.put("personId", user.getUserId());
		}
		
		/*String watchType = super.getDefaultWatchType();
		if (StringUtils.isNotEmpty(watchType)){
			params.put("watchType",  watchType);
		}*/
	
		paramSportResults = paramSportService.getParamSports(params);
		try {// add by xc
			paramSportResults = ChartDataUtils.today2(paramSportResults, 1,
					Tools.getCurrentDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		hearRates = paramSportService.getHeartRates(params);
		resultMap.put("searchDate", Tools.dateToString(
				Tools.addDays(Tools.stringToDate(searchDate, "yyyy-MM-dd"), 1),
				"yyyy-MM-dd"));
		resultMap.put("paramSportResults", paramSportResults);
		resultMap.put("hearRates", hearRates);
		setLatelySportTime(params);
		return Action.SUCCESS;
	}

	private void setLatelySportTime(Map<String, Object> params) {
		ParamSportVO sport = paramSportService.getLatelySport(params);
		if (sport != null) {
			resultMap.put("latelyTime", sport.getStartTime());
		} else {
			resultMap.put("latelyTime", "");
		}
	}

	public void modifyPassword(){
		try{
			user = super.getUsers();
			String oldPassword = request.getParameter("oldPassword");
			String newPassword = request.getParameter("newPassword");
			
			//验证旧密码
			//Integer ret = userService.getUserByPassword(user.getId(), encrypty.encrypt(oldPassword));
			Integer ret = userService.getUserByPassword(user.getId(), oldPassword);
			if (ret<1){
				// 账户或原始密码不正确
				super.writeJson(SportApiCode.ERROR_1111.getDesc());
			}else{
				//修改新密码
			//	ret = userService.modifyUserPassword(user.getId(),encrypty.encrypt(newPassword));
				ret = userService.modifyUserPassword(user.getId(),newPassword);
				if (ret<0){
					super.writeJson(SportApiCode.ERROR_1112.getDesc());
				}else{
					super.writeJson(SportApiCode.SUCCESS.getDesc());
				}
			}
		}catch(Exception e){
			log.error(e);
		}
	}
	public String changePassword(){
		log.error(this.token);
	    Map<String,Object>	localmap = getAccount(token); 
	    String lan = (String) localmap.get("lan");
	    String country = (String) localmap.get("country");
//		Set locale
	    if (lan != null && country != null) {
	    	Locale local=new Locale(lan, country);
	    	request.getSession().setAttribute("WW_TRANS_I18N_LOCALE", local);	
		}
//		Locale local=new Locale("fr", "FR");
//	    	request.getSession().setAttribute("WW_TRANS_I18N_LOCALE", local);	
		return Action.SUCCESS;
	}
	
	public Map<String,Object> getAccount(String token){
		Map<String,Object> localMap = new HashMap<String, Object>();
		String domain = getText("server.domain");
	    String url = domain+"v1/account/validate/password/json/"+token;
	    CloseableHttpClient httpClient = HttpClients.createDefault();
	    HttpGet get = new HttpGet(url);
        try {
        	//用get方法发送http请求
            CloseableHttpResponse httpResponse = null;
            //发送get请求
            httpResponse = httpClient.execute(get);
                //response实体
            HttpEntity entity = httpResponse.getEntity();
            if (null != entity){
            	log.info("response code:"+ httpResponse.getStatusLine());
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                if (statusCode != 200) {
					return localMap;
				}
                String content = EntityUtils.toString(entity);
               if (content.contains("account_detail")) {
            	   JSONObject jsoncContent = JSONObject.parseObject(content);
            	   String local = jsoncContent.getJSONObject("account_detail").getString("locale");
            	   String[] locals = local.split("-");
            	   localMap.put("lan", locals[0]);
            	   localMap.put("country", locals[1]);
               }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }finally{
        	get.releaseConnection();
        }
		return localMap;

	}
	
	public String sportLog() {
		String logined = (String) session.get("isLogined");
		request.setAttribute("logined", logined);
		return Action.SUCCESS;
	}
	public String footprint() {
		
		log.info("");
		user = super.getUsers();
		return Action.SUCCESS;
	}
	private void getAdvanceSetting(){
		user = super.getUsers();
		AdvancedSetttingVO advanced = userService.getUserAdvancedSetttingInfo(user.getUserId(), user.getWatchId());
		if(advanced == null){
			advanced = new AdvancedSetttingVO();
		}
		user.setAdvancedSettring(advanced);
		session.put(LOGIN_SESSION_USER, user);
	}
	
	public void convertPwd(){
		try {
			//查询用户注册表信息
			List<UserVO> userList = this.userService.getAllUser();
			for(UserVO user : userList){
				//解密AES密码
				String orgPwd = this.encrypty.decrypt(user.getPassword());
				//重新用md5加密
				String md5Pwd = DigestUtils.md5Hex(orgPwd);
				this.userService.modifyUserPassword(user.getId(), md5Pwd);
			}
			super.writeJson(SportApiCode.SUCCESS.getDesc()+", cnt:" + userList.size());
		} catch (Exception e) {
			log.error("[UserAction.listDevByAccount] - catch exception!",e);
			super.writeJson(SportApiCode.ERROR_9999.getDesc());
		}
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getLanguageName() {
		return languageName;
	}
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}
	
	public UserVO getUser() {
		return user;
	}
	public void setUser(UserVO user) {
		this.user = user;
	}
	public PersonVO getPerson() {
		return person;
	}
	public void setPerson(PersonVO person) {
		this.person = person;
	}
	public String getWatchId() {
		return watchId;
	}

	public void setWatchId(String watchId) {
		this.watchId = watchId;
	}

	public String getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
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
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public List<PersonWatchVO> getWatchList() {
		return watchList;
	}
	public void setWatchList(List<PersonWatchVO> watchList) {
		this.watchList = watchList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Long getT() {
		return t;
	}

	public void setT(Long t) {
		this.t = t;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

}
