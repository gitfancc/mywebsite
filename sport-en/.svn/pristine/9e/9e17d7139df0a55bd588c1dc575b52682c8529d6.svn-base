package com.appscomm.sport.api.action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;

import com.appscomm.sport.service.AccessTokenService;
import com.appscomm.sport.common.SportApiCode;
import com.appscomm.sport.model.AdvancedSetttingVO;
import com.appscomm.sport.model.DeviceNotifyInfoVO;
import com.appscomm.sport.model.DeviceSleepRemindInfoVO;
import com.appscomm.sport.model.DeviceStayRemindInfoVO;
import com.appscomm.sport.model.DeviceVersionInfoVO;
import com.appscomm.sport.model.LoginInfoVO;
import com.appscomm.sport.model.PeriodResmindVO;
import com.appscomm.sport.model.PersonVO;
import com.appscomm.sport.model.PersonWatchVO;
import com.appscomm.sport.model.RemaindVO;
import com.appscomm.sport.model.UserVO;
import com.appscomm.sport.service.DeviceNotifyRemindService;
import com.appscomm.sport.service.DeviceSetService;
import com.appscomm.sport.service.InterfaceDataLogService;
import com.appscomm.sport.service.PeriodRemindService;
import com.appscomm.sport.service.PersonalSettingService;
import com.appscomm.sport.service.RemaindService;
import com.appscomm.sport.service.SendMailForPassword;
import com.appscomm.sport.service.UserService;
import com.appscomm.sport.utils.AESEncrypty;
import com.appscomm.sport.utils.AuthImg;
import com.appscomm.sport.utils.HttpUtils;
import com.appscomm.sport.utils.JsonUtils;
import com.appscomm.sport.vo.PersonalSetting;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author qindf
 *
 */
public class IndexAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = -8160664524280068733L;

	private static Logger log = Logger.getLogger(IndexAction.class);
	
	public static final String LOGIN_SESSION_USER = "loginUsers";
	@Autowired
	private UserService userService;
	@Autowired
	private PeriodRemindService periodResremind;
	@Autowired
	private SendMailForPassword sendMailForPassword;
	@Autowired
	private InterfaceDataLogService interfaceDataLogService;
	@Autowired
	private AccessTokenService accessTokenService;
	
	private Map<String,Object> session;
	private String txtVarcode;
	private Boolean results = true;
	private String checkLogin;
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static String KEY_HEX = "C1511E2A29B3C721EC1E4E1C0A396559";
	AESEncrypty encrypty = new AESEncrypty(KEY_HEX);
	
	// email configration info
	private String rootPath = null;
	private String staticFilePath = null;
	private String companyMail = null;
	private String registSubject = null;
	private String findPwdSubject = null;
	private String newPwdSubject = null;
	private String watchName = null;
	private String emailRegisterContent = null;
	private String emailForgotPwdContent = null;
	private String emailNewPwdContent = null;
//	private String companyMailPassword= null;
//	private String emailServerHost= null ;
//	private String emailServerPort = null;
//	private String encryType = null;
	
	/**
	 * 登录入口
	 * @return
	 */
	public void login(){
		//解析用户传递的 参数
		//验证用户名，密码是否合法，验证码是否正确(如果验证码错误，可以给个result值，明确指定是验证码错误，而不是笼统的：用户名或密码错误。我们应该精确定位是什么错误)
		//验证用户名密码是否符合数据库记录
		//如果符合：搜索出用户信息，解析成JSON格式，返回给客户端
		//如果不符合：返回错误码
	
		 Map<String, Object> requestMap =   HttpUtils.receiveRequestMap();
		 requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		 String watchId = null;
		 Long userId = 0L;
		 String watchType = null;
		 String account = null;
		 Integer encryptMode = 0;
		 
		 try{
			 account = ((String[])requestMap.get("account"))[0];
			 account = account.trim();
			 
			 String password = ((String[])requestMap.get("password"))[0];
			
			 String clienttype = HttpUtils.getHeader("clientType");
			 if(requestMap.containsKey("watchId")){
				 watchId = ((String[])requestMap.get("watchId"))[0];//zxh add
			 }
			 if(requestMap.containsKey("encryptMode")){
				 encryptMode = Integer.valueOf(((String[])requestMap.get("encryptMode"))[0].trim());
			 }
			 if(0==encryptMode){
				 password = DigestUtils.md5Hex(password);
			 }

			if (StringUtils.isNotEmpty(account)){
			//	account = account.toLowerCase();
				List<UserVO> resultUsers = null;
				Map<String,Object> params = new HashMap<String,Object>();
				if(account.indexOf("@")!=-1){
					params.put("mail", account);
				} else {
					params.put("telphone", account);
				}
				
				try {
					resultUsers = userService.getUserActive(params);
					if (resultUsers != null && resultUsers.size() > 0) {
						UserVO user = resultUsers.get(0);
						if (StringUtils.isNotEmpty(user.getPassword())
								&& authUser(password, user.getPassword())) {
							session.put(LOGIN_SESSION_USER, user);
							params.clear();
							params.put("personId", user.getUserId());
							params.put("active", 1);
							params.put("isDefault", 1);
							List<PersonWatchVO> personWatchList = userService.getPersonWatch(params);
							if (personWatchList == null	|| personWatchList.size() == 0) {
								// 未绑定设备
								user.setWatchId(null);
								user.setIsBind(0);
							}else{
								user.setWatchId(personWatchList.get(0).getWatchId());
								user.setIsBind(1);
							}
							userId = user.getUserId();
							// 获取设备高级设置信息
							AdvancedSetttingVO advanced = userService.getUserAdvancedSetttingInfo(user.getUserId(), user.getWatchId());
							if(advanced == null){
								advanced  = new AdvancedSetttingVO();
								advanced.setPersonId(user.getUserId());
								advanced.setWatchId(user.getWatchId());
							}
							user.setAdvancedSettring(advanced);
							user.setKronoz_token(accessTokenService.generateKronozToken(user.getId(),"mixing_client"));
							session.remove(LOGIN_SESSION_USER);
							session.put(LOGIN_SESSION_USER, user);
							String responseStr=JsonUtils.responseJson(SportApiCode.SUCCESS, user);
							if(watchId != null && clienttype != null && clienttype.toUpperCase().equals("PC")){//pc client
								responseStr = doPcClient(watchId, user, account);
							}
							
							HttpUtils.sendResponseJson(responseStr,requestMap);
						} else {
							// 密码不正确
							String responseStr=JsonUtils.responseJson(SportApiCode.ERROR_1101, null);
							HttpUtils.sendResponseJson(responseStr,requestMap);
						}
					}else {
						// 账户不存在
						String responseStr=JsonUtils.responseJson(SportApiCode.ERROR_1102, null);
						HttpUtils.sendResponseJson(responseStr,requestMap);
					}
				}catch(Exception e){
					log.info(e);
					String responseStr=JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
					HttpUtils.sendResponseJson(responseStr,requestMap);
				}
			}
		}catch(Exception e){
			log.info(e);
			String responseStr = null;
			try {
				responseStr = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			} catch (JsonGenerationException e1) {
			} catch (JsonMappingException e1) {
			} catch (IOException e1) {
			}
			HttpUtils.sendResponseJson(responseStr,requestMap);
		}
	
	//记录操作日志
		String headClientType =  (String)requestMap.get("HeadClientType");
		String headVersion = (String)requestMap.get("HeadVersion");
		interfaceDataLogService.saveInterfaceDataLog(account, watchId, "", headClientType+headVersion, "Login");
	}
	
	@Autowired
	private PersonalSettingService personalSettingService;
	@Autowired
	private RemaindService remaindService;
	@Autowired
	private DeviceNotifyRemindService deviceNotifyRemindService;
	@Autowired
	private DeviceSetService deviceSetService;
	public void loginEx(){
		 Map<String, Object> requestMap =   HttpUtils.receiveRequestMap();
		 requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		 String watchId = "";
		 Long userId = 0L;
		 String account = null;
		 String appId = "mixing_client";
		 Integer encryptMode = 0;
		 try{
			 account = (((String[])requestMap.get("account"))[0]).trim();
			 String password = ((String[])requestMap.get("password"))[0];
			 if(requestMap.containsKey("watchId")){
				 watchId = ((String[])requestMap.get("watchId"))[0];
			 }
			 if(requestMap.containsKey("encryptMode")){
				 encryptMode = Integer.valueOf(((String[])requestMap.get("encryptMode"))[0].trim());
			 }
			 if(0==encryptMode){
				 password = DigestUtils.md5Hex(password);
			 }

			 if (StringUtils.isNotEmpty(account)){
				try {
					//获取注册信息和个人信息
					LoginInfoVO loginInfo = userService.getRegisterInfo(account);
					if (loginInfo != null) {
						if (StringUtils.isNotEmpty(loginInfo.getRegPassword())&& authUser(password, loginInfo.getRegPassword())) {
							loginInfo.setRegPassword("");
							//获取用户信息
							PersonVO person = userService.getPerson(loginInfo.getRegId());
							loginInfo.setPersonInfo(person);
							userId = person==null ? 0 : person.getId();
							//获取绑定信息
							List<PersonWatchVO> personWatchList = userService.getPersonBindWatch(userId);
							if(personWatchList!=null && personWatchList.size()>0) {
								watchId = personWatchList.get(0).getWatchId();
								loginInfo.setBindInfo(personWatchList.get(0));
							}
							//获取目标
							List<PersonalSetting> personSettingList = this.personalSettingService.getPersonalTarget(userId);
							loginInfo.setPersonSettingInfo(personSettingList);
							//获取提醒
							List<RemaindVO> remindInfoList =  this.remaindService.getList(userId, watchId);
							loginInfo.setRemindInfo(remindInfoList);
							//获取自动睡眠
							DeviceSleepRemindInfoVO sleepRemind = this.deviceNotifyRemindService.getSleepRemindInfo(userId, watchId);
							loginInfo.setSleepRemindInfo(sleepRemind);
							//获取久坐提醒
							DeviceStayRemindInfoVO stayRemind = this.deviceNotifyRemindService.getStayRemindInfo(userId, watchId);
							loginInfo.setStayRemindInfo(stayRemind);
							//获取通知开关
							DeviceNotifyInfoVO notifyStaus = this.deviceNotifyRemindService.getDeviceNotifyInfo(userId, watchId);
							loginInfo.setDeviceNotifyInfo(notifyStaus);
							//获取固件版本
							DeviceVersionInfoVO deviceVersion = this.deviceSetService.getDeviceVersionInfo(watchId);
							loginInfo.setDeviceVersionInfo(deviceVersion);
							//动一动提醒
							List<PeriodResmindVO> periodRemindList = periodResremind.qryByKey(watchId, userId);
							loginInfo.setPerodRemindInfo(periodRemindList);
							loginInfo.setKronoz_token(accessTokenService.generateKronozToken(person.getId(),appId));
							String responseStr=JsonUtils.responseJson(SportApiCode.SUCCESS, loginInfo);
							HttpUtils.sendResponseJson(responseStr,requestMap);
						} else {
							// 密码不正确
							String responseStr=JsonUtils.responseJson(SportApiCode.ERROR_1101, null);
							HttpUtils.sendResponseJson(responseStr,requestMap);
						}
					}else {
						// 账户不存在
						String responseStr=JsonUtils.responseJson(SportApiCode.ERROR_1102, null);
						HttpUtils.sendResponseJson(responseStr,requestMap);
					}
				}catch(Exception e){
					log.info(e);
					String responseStr=JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
					HttpUtils.sendResponseJson(responseStr,requestMap);
				}
			}
		}catch(Exception e){
			log.info(e);
			String responseStr = null;
			try {
				responseStr = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			} catch (JsonGenerationException e1) {
			} catch (JsonMappingException e1) {
			} catch (IOException e1) {
			}
			HttpUtils.sendResponseJson(responseStr,requestMap);
		}
	
	//记录操作日志
		String headClientType =  (String)requestMap.get("HeadClientType");
		String headVersion = (String)requestMap.get("HeadVersion");
		interfaceDataLogService.saveInterfaceDataLog(account, watchId, "", headClientType+headVersion, "Login");
	}
	
	/**
	 * pc端登录入口
	 * @param watchid 设备编号
	 * @param user	用户对象
	 * @param account
	 * @return JSON
	 */
	private String doPcClient(String watchid, UserVO user, String account) {
		String result = null;
		try{
			//根据watchid 获取其绑定的用户，注意此处获取的用户和入参user不一定相同，因为传入的user是通过account获取的用户
			//与通过watchid获取的绑定用户不一定相同
			List<UserVO> rec = userService.getBindUserInfo(watchid);
			if(rec == null || rec.size() == 0){
				//Map<String, String> map = new HashMap<String, String>();
				//map.put("userId", user.getUserId().toString());//返回当前account对应的userid给pc，方便pc做绑定操作
				//map.put("watchId", user.getWatchId());//返回当前登录用户绑定的watchId，若无绑定，则空。方便客户端判断是否用这个用户绑定
				
				//老账户重新绑定，适用于恢复出厂设置后又用当前账户绑定
				List<Object> toJson = new ArrayList<Object>();
				List<PeriodResmindVO> list = periodResremind.qryByKey(watchid, user.getUserId());
				if(list != null && list.size() > 0)
				{
					PeriodResmindVO obj = list.get(0);					
					toJson.add(obj);
				}
				toJson.add(user);
				return JsonUtils.responseJson(SportApiCode.ERROR_7789, toJson);
			} else if(rec.get(0).getUserId().longValue() != user.getUserId().longValue()){
				Map<String, String> map = new HashMap<String, String>();
				map.put("logon_userId", user.getUserId().toString());//返回当前account对应的userid	
				map.put("bind_userId", rec.get(0).getUserId().toString());//返回设备绑定的userid
				map.put("logon_watchId", user.getWatchId());
				return JsonUtils.responseJson(SportApiCode.ERROR_7793, map);
			}
			
			boolean isSameUser = false; 
			if(account.contains("@")){
				String mail = account;
				if(rec.get(0).getMail().trim().equalsIgnoreCase(mail.trim()))//登录用户与watchid属于同一个人绑定
				{
					isSameUser = true;
				}
			}else{
				String tel = account;
				if(rec.get(0).getTelphone().trim().equals(tel.trim()))//登录用户与watchid属于同一个人绑定
				{
					isSameUser = true;
				}				
			}
			
			if(isSameUser)//相同用户，此时获取的用户和入参user相同
			{
				List<Object> toJson = new ArrayList<Object>();
				List<PeriodResmindVO> list = periodResremind.qryByKey(watchid, user.getUserId());
				if(list != null && list.size() > 0)
				{
					PeriodResmindVO obj = list.get(0);					
					toJson.add(obj);
				}
				else{//default values
					PeriodResmindVO obj = new PeriodResmindVO();
					obj.setUserid(user.getUserId());
					obj.setWatchid(watchid);
					obj.setPeriod1(2000);
					obj.setPeriod2(2000);
					obj.setPeriod3(2000);
					obj.setPeriod4(2000);
					obj.setPeriodonoff1(1);
					obj.setPeriodonoff2(1);
					obj.setPeriodonoff3(1);
					obj.setPeriodonoff4(1);
					obj.setCalonoff(1);
					obj.setSteponoff(1);
					obj.setTargetonoff(1);
					obj.setRemindonoff(1);
					toJson.add(obj);
				}
				
				// JSON返回值中传入的绑定设备应为用户传入的设备ID
				user.setWatchId(watchid);
				toJson.add(user);		
				result = JsonUtils.responseJson(SportApiCode.SUCCESS, toJson);
			}
			else{
				result = JsonUtils.responseJson(SportApiCode.ERROR_1102, null);
			}
		}catch(Exception e){
			log.error(e);
		}
		return result;
	}

	public String register_index(){
		UserVO loginUsers = (UserVO)session.get(LOGIN_SESSION_USER);
		if(loginUsers!=null)
			return Action.SUCCESS;
		return Action.NONE;
	}
	
	public void authCode(){
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		String responseStr = "{\"result\":\""+SportApiCode.SUCCESS.getCode()+"\",\"message\":\""+SportApiCode.SUCCESS.getDesc()+"\",\"data\":{\"authCode\":\""+session.get(AuthImg.AUTH_VERCODE)+"\"}}";
		HttpUtils.sendResponseJson(responseStr,requestMap);
	}

	/**
	 * 注册用户
	 * @return
	 */
	public void register(){
		//  还有验证码未处理，我们如何标识用户已注册登录(只要客户端保持着长连接，我们服务端就可以取到session中的值)
		 Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		 requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		 Long userId = 0L;
		 String mail = null;
		 Integer encryptMode = 0;
		try{
			 mail = ((String[])requestMap.get("mail"))[0].trim();
			 String telephone = ((String[])requestMap.get("telephone"))[0].trim();
			 mail = mail.toLowerCase().trim();
			 if(requestMap.containsKey("encryptMode")){
				 encryptMode = Integer.valueOf(((String[])requestMap.get("encryptMode"))[0].trim());
			 }
			 String nickName = null;
			 if(requestMap.containsKey("nickName")){
				 nickName =  ((String[])requestMap.get("nickName"))[0].trim();
				//当前传入的nickname不允许重复！
				if(nickName != null && nickName.length() > 0){
					PersonVO nickPerson = userService.getPersonByNickName(nickName);
					if(nickPerson != null){
						String responseStr = JsonUtils.responseJson(SportApiCode.ERROR_7794, nickPerson);
						HttpUtils.sendResponseJson(responseStr,requestMap);
						return;
					}
				}				 
			 }
			 
			 if(nickName == null || nickName.length() == 0){//若未设置昵称，则默认有一个昵称，因为数据库此字段非空，且为索引字段
				 long time = System.currentTimeMillis();
				 nickName = mail + telephone + String.valueOf(time);
				 log.info("default nickname:" + nickName);
			 }
			 String password =  ((String[])requestMap.get("password"))[0];
		
			if(authRequest(requestMap))
				return;
			
			UserVO user = new UserVO();
			user.setMail(mail);
			user.setTelphone(telephone);
			user.setNickName(nickName);
			//user.setPassword(encrypty.encrypt(password));
			if (1==encryptMode){
				user.setPassword(password);
			}else{
				user.setPassword(DigestUtils.md5Hex(password));
			}
			user.setRegisterTime(new Date());
			user.setIsActive(1);
			
			try{
				SportApiCode retCode = userService.isExistUser(user);
				if (retCode != null){
					// 用户已存在
					String responseStr = JsonUtils.responseJson(retCode, null);
					HttpUtils.sendResponseJson(responseStr,requestMap);
				} else {
					UserVO resultUser = null;
					if((resultUser = userService.addUserAndPerson(user))!=null){
						String responseStr = JsonUtils.responseJson(SportApiCode.SUCCESS, resultUser);
						HttpUtils.sendResponseJson(responseStr,requestMap);
					}
				}
			}catch(Exception e){
				log.info("注册时发生异常："+e);
				String responseStr = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
				HttpUtils.sendResponseJson(responseStr,requestMap);
			}
			
		}catch(Exception e){
			log.info("注册接口调用异常:"+e);
			String responseStr = null;
			try {
				responseStr = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			} catch (JsonGenerationException e1) {
			} catch (JsonMappingException e1) {
			} catch (IOException e1) {
			}
			HttpUtils.sendResponseJson(responseStr,requestMap);
		}
		
		//记录操作日志
		String headClientType =  (String)requestMap.get("HeadClientType");
		String headVersion = (String)requestMap.get("HeadVersion");
		interfaceDataLogService.saveInterfaceDataLog(mail, "", "", headClientType+headVersion, "Register");
	}
	
	/**
	 * 系统登出功能
	 * @return
	 */
	public String logout(){
		UserVO loginUsers = (UserVO)session.get(LOGIN_SESSION_USER);
		if(loginUsers!=null){
			session.remove(LOGIN_SESSION_USER);
		}
		return Action.NONE;
	}
	
	/**
	 * 验证客户端请求数据是否完整
	 * @param requestMap
	 * @return
	 */
	private boolean authRequest(Map<String,Object> requestMap){
		 String mail = ((String[])requestMap.get("mail"))[0].trim();
		 String telephone = ((String[])requestMap.get("telephone"))[0];
		 String password =  ((String[])requestMap.get("password"))[0];
	//	 String volidCode =  ((String[])requestMap.get("validCode"))[0];
		 
		try {
			//String verCode = (String) session.get(AuthImg.AUTH_VERCODE);
			/*
			 * if(StringUtils.isEmpty(volidCode)){ String responseStr =
			 * "{\"result\":\""
			 * +SportApiCode.ERROR_2002.getCode()+"\",\"message\":\""
			 * +SportApiCode.ERROR_2002.getDesc()+"\",\"data\":\"\"}";
			 * HttpUtils.sendResponse(responseStr); return true; }
			 * if(!verCode.equals(volidCode)){ String responseStr =
			 * "{\"result\":\""
			 * +SportApiCode.ERROR_2003.getCode()+"\",\"message\":\""
			 * +SportApiCode.ERROR_2003.getDesc()+"\",\"data\":\"\"}";
			 * HttpUtils.sendResponse(responseStr); return true; }
			 */
			if (StringUtils.isEmpty(telephone) && StringUtils.isEmpty(mail)) {
				String responseStr = JsonUtils.responseJson(
						SportApiCode.ERROR_1103, null);
				HttpUtils.sendResponseJson(responseStr,requestMap);
				return true;
			}
			if (StringUtils.isEmpty(password)) {
				String responseStr = JsonUtils.responseJson(
						SportApiCode.ERROR_1104, null);
				HttpUtils.sendResponseJson(responseStr,requestMap);
				return true;
			}
		} catch (Exception ex) {
			log.error(ex);
		}
		return false;
	}
	
	/**
	 * 修改密码
	 * @return json
	 */
	public void modifyPassword(){
		String resp = null;
		String accountId = null;
		Integer encryptMode = 0;
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		try{
			accountId =  ((String[])requestMap.get("accountId"))[0];
			String oldPassword =  ((String[])requestMap.get("oldPassword"))[0];
			String newPassword =  ((String[])requestMap.get("newPassword"))[0];
			 if(requestMap.containsKey("encryptMode")){
				 encryptMode = Integer.valueOf(((String[])requestMap.get("encryptMode"))[0].trim());
			 }
			//验证旧密码
			Integer ret = 1;
			if(accountId.indexOf("@") != -1){
				//ret = userService.getUserByPassword(accountId, encrypty.encrypt(oldPassword));
				if (1==encryptMode){
					ret = userService.getUserByPassword(accountId, oldPassword);
				}else{
					ret = userService.getUserByPassword(accountId, DigestUtils.md5Hex(oldPassword));
				}
			}else{
				//ret = userService.getUserByPassword(Long.valueOf(accountId), encrypty.encrypt(oldPassword));
				if (1==encryptMode){
					ret = userService.getUserByPassword(Long.valueOf(accountId), oldPassword);
				}else{
					ret = userService.getUserByPassword(Long.valueOf(accountId), DigestUtils.md5Hex(oldPassword));
				}
			}
			
			//是否有匹配的账户
			if (ret<1){
				// 账户或原始密码不正确
				resp = JsonUtils.responseJson(SportApiCode.ERROR_1111, null);
			}else{
				//修改新密码
				if(accountId.indexOf("@") != -1){
					//ret = userService.modifyUserPassword(accountId, encrypty.encrypt(newPassword));
					if (1==encryptMode){
						ret = userService.modifyUserPassword(accountId, newPassword);
					}else{
						ret = userService.modifyUserPassword(accountId, DigestUtils.md5Hex(newPassword));
					}
				}else{
					//ret = userService.modifyUserPassword(Long.valueOf(accountId),encrypty.encrypt(newPassword));
					if (1==encryptMode){
						ret = userService.modifyUserPassword(Long.valueOf(accountId),newPassword);
					}else{
						ret = userService.modifyUserPassword(Long.valueOf(accountId),DigestUtils.md5Hex(newPassword));
					}
				}
				
				//是否修改成功
				if (ret<0){
					resp = JsonUtils.responseJson(SportApiCode.ERROR_1112, null);
				}else{
					resp = JsonUtils.responseJson(SportApiCode.SUCCESS, null);
				}
			}
			HttpUtils.sendResponseJson(resp,requestMap);
		}catch(Exception e){
			log.error(e);
			try{
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
				HttpUtils.sendResponseJson(resp,requestMap);
			}catch(Exception ee){
				log.error(ee);
			}
		}
		
		//记录操作日志
		String headClientType =  (String)requestMap.get("HeadClientType");
		String headVersion = (String)requestMap.get("HeadVersion");
		interfaceDataLogService.saveInterfaceDataLog(accountId, "", "", headClientType+headVersion, "Modify password");
	}
	
	/**
	 * 忘记密码接口
	* @description:(设定参数)
	* @return void(返回值说明)
	 */
	public void forgetPassword(){
		String resp = null;
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		String eMail = null;
		try{
			eMail = null;
			String telphone = null;
			if (requestMap.containsKey("email")){
				eMail =  (((String[])requestMap.get("email"))[0]).trim();
			}
			if (requestMap.containsKey("telphone")){
				telphone =  ((String[])requestMap.get("telphone"))[0].trim();	
			}
			// 查看邮箱是否存在
			boolean exists = userService.isExistsByEmail(eMail);
			if(!exists){
				resp = JsonUtils.responseJson(SportApiCode.ERROR_4001, null);
				HttpUtils.sendResponseJson(resp,requestMap);
				return;
			} 
			
			resp = JsonUtils.responseJson(SportApiCode.SUCCESS, null);
			if (StringUtils.isNotBlank(eMail)){
				// 邮件通知
				getEmailConfig();
				//String contents = sendMailForPassword.getMailFindPwdContents(rootPath, eMail, null, staticFilePath, "App", companyMail, watchName);
				String contents = sendMailForPassword.getMailFindPwdContents(rootPath, eMail, null, "App", this.emailForgotPwdContent);
				boolean ret = sendMailForPassword.sendMail(companyMail, findPwdSubject, eMail, contents);
				if (!ret){
					//邮件发送失败
//					resp = JsonUtils.responseJson(SportApiCode.ERROR_4000, null);
				}
			} else if (StringUtils.isNotBlank(telphone)){
				// 短信通知
			} 
			HttpUtils.sendResponseJson(resp,requestMap);
		}catch(Exception e){
			log.error(e);
			try{
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
				HttpUtils.sendResponseJson(resp,requestMap);
			}catch(Exception ee){
				log.error(ee);
			}
		}
		
		//记录操作日志
		String headClientType =  (String)requestMap.get("HeadClientType");
		String headVersion = (String)requestMap.get("HeadVersion");
		interfaceDataLogService.saveInterfaceDataLog(eMail, "", "", headClientType+headVersion, "Forgot password");
	}
	
	/**
	 * 法国客户专用注册接口
	 * @return json
	 */
	public void registerForFrance(){
		String resp = null;
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		String eMail = null;
		Integer encryptMode = 0;
		try {
			eMail =  ((String[])requestMap.get("email"))[0];
			String userName =  ((String[])requestMap.get("userName"))[0];
			String password =  ((String[])requestMap.get("password"))[0];
			String gender =  ((String[])requestMap.get("gender"))[0];
			String birthDay =  ((String[])requestMap.get("birthDay"))[0];
			String height =  ((String[])requestMap.get("height"))[0];
			String weight =  ((String[])requestMap.get("weight"))[0];
			
			String heightUnit = null;
			String weightUnit = null;
			String countryCode =  null;
			String imgUrl = null;
			if (requestMap.containsKey("heightUnit")){
				heightUnit = ((String[])requestMap.get("heightUnit"))[0];
			}
			if (requestMap.containsKey("weightUnit")){
				 weightUnit = ((String[])requestMap.get("weightUnit"))[0];			
			}
			if (requestMap.containsKey("countryCode")){
				countryCode =  ((String[])requestMap.get("countryCode"))[0];
			}
			if (requestMap.containsKey("imgUrl")){
				imgUrl =  ((String[])requestMap.get("imgUrl"))[0];
			}
			if(requestMap.containsKey("encryptMode")){
				 encryptMode = Integer.valueOf(((String[])requestMap.get("encryptMode"))[0].trim());
			 }
			//检查注册的账号是否存在
			eMail = eMail.trim();
			userName = userName.trim();
			
			Long registerId = userService.getRegisterIdByEmail(eMail);
			if(null != registerId){
				PersonVO personVO = userService.getPerson(registerId);
				if(userName.trim().equals(personVO.getUserName())){
					//修改密码
					if (1==encryptMode){
					}else{
						password = DigestUtils.md5Hex(password);
					}
					userService.modifyUserPassword(registerId, password);
					resp = "{\"result\":\""+SportApiCode.SUCCESS.getCode()+"\",\"message\":\""+SportApiCode.SUCCESS.getDesc()+"\"," +
							"\"data\":{\"id\":\""+registerId+"\"," +  "\"userId\":\"" + personVO.getId() + "\"}}";
				}else {
					//账户已被注册
					resp = JsonUtils.responseJson(SportApiCode.ERROR_1105, null);
				}
			}else {
				//添加账号信息到库中
				UserVO user = new UserVO();
				user.setMail(eMail);
				user.setTelphone("");
				if(StringUtils.isNotBlank(userName)){
					user.setNickName(userName);
					user.setUserName(userName);
				} else{
					user.setNickName(String.valueOf(System.currentTimeMillis()));
				}
				
				//user.setPassword(encrypty.encrypt(password));
				if (1==encryptMode){
					user.setPassword(password);
				}else{
					user.setPassword(DigestUtils.md5Hex(password));
				}
				
				user.setRegisterTime(new Date());
				user.setIsActive(1);
				if (StringUtils.isNotBlank(heightUnit))  user.setHeightUnit(Integer.valueOf(heightUnit));
				if(StringUtils.isNotBlank(weightUnit))  user.setWeightUnit(Integer.valueOf(weightUnit));
				if(StringUtils.isNotBlank(countryCode))  user.setCountryId(Integer.valueOf(countryCode));
				if(StringUtils.isNotBlank(imgUrl))  user.setImgUrl(imgUrl);
				
				if(StringUtils.isNotBlank(birthDay)){
//					Date date = Tools.stringToDate(birthDay, "yyyy-MM-dd");
//					//如果日期为null或者格式不正确，默认取2014-01-01
//					if (date == null) date = Tools.stringToDate("2014-01-01", "yyyy-MM-dd");
//					user.setBirthDate(date);
					user.setBirthDate(birthDay);
				}
				if(StringUtils.isNotBlank(gender)){
					user.setGender(Integer.valueOf(gender));
				}
				if(StringUtils.isNotBlank(height)){
					user.setHeight(height);
				}
				if(StringUtils.isNotBlank(weight)){
					user.setWeight(Float.valueOf(weight));
				}
				user = userService.addNewUser(user);
				if(user != null){
					resp = "{\"result\":\""+SportApiCode.SUCCESS.getCode()+"\",\"message\":\""+SportApiCode.SUCCESS.getDesc()+"\"," +
							"\"data\":{\"id\":\""+user.getId()+"\"," +  "\"userId\":\"" + user.getUserId() + "\"}}";
					getEmailConfig();
					//String contents = sendMailForPassword.getMailRegistContents(rootPath, eMail, null, staticFilePath, "App", companyMail, watchName);
					String contents = sendMailForPassword.getMailRegistContents(rootPath, eMail, null, this.emailRegisterContent);
					boolean ret = sendMailForPassword.sendMail(companyMail, registSubject, eMail, contents);
					if (!ret){
						//邮件发送失败
//						= JsonUtils.responseJson(SportApiCode.ERROR_4000, null);
					}
				}else{
					resp = JsonUtils.responseJson(SportApiCode.ERROR_1113, null);
				}
			}
			
			HttpUtils.sendResponseJson(resp,requestMap);
			
		} catch (Exception e) {
			log.error(e);
			try{
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
				HttpUtils.sendResponseJson(resp,requestMap);
			}catch(Exception ee){
				log.error(ee);
			}
		}
		//记录操作日志
		String headClientType =  (String)requestMap.get("HeadClientType");
		String headVersion = (String)requestMap.get("HeadVersion");
		interfaceDataLogService.saveInterfaceDataLog(eMail, "", "", headClientType+headVersion, "register for France");
	}
	
	public void getNewPassword(){
		String resp = null;
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		String account = null;
		try{
			account = (((String[])requestMap.get("account"))[0]).trim();
			//参数为空
			if (StringUtils.isEmpty(account)){
				HttpUtils.sendResponseJson( JsonUtils.responseJson(SportApiCode.ERROR_2001, null),requestMap);
				return;
			}
			
			UserVO user = new UserVO();
			//获取账号信息
			if(account.indexOf("@")!=-1){
				user.setMail(account);
			} else {
				//user.setTelphone(account);
				// 目前只有邮件账号才可以发送邮件通知
				HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.ERROR_1115, null),requestMap);
				return;
			}
			
			//账号不存在
			user = userService.isExistsAccount(user);
			if(user == null || user.getId()==0){
				HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.ERROR_1102, null),requestMap);
				return ;
			}
				
			String newPassword = RandomStringUtils.random(6, false, true);
			// 设置用户新密码
			if (this.userService.modifyUserPassword(user.getId(),  DigestUtils.md5Hex(newPassword)) > 0){
					if(StringUtils.isNotBlank(user.getMail())){
					// 邮件通知
					getEmailConfig();
					String contents = sendMailForPassword.getMailGetNewPwdContents(this.rootPath, user.getMail(), this.txtVarcode, newPassword, this.emailNewPwdContent);
					boolean ret = sendMailForPassword.sendMail(companyMail, newPwdSubject, user.getMail(), contents);	
					if (!ret){
						//邮件发送失败
//						resp = JsonUtils.responseJson(SportApiCode.ERROR_4000, null);
					}else{
						resp = JsonUtils.responseJson(SportApiCode.SUCCESS, null);
					}
				}
			}else{
				resp = JsonUtils.responseJson(SportApiCode.ERROR_1112, null);
			}
			HttpUtils.sendResponseJson(resp,requestMap);
		}catch(Exception e)
		{
			log.error(e);
			try{
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
				HttpUtils.sendResponseJson(resp,requestMap);
			}catch(Exception ee){
				log.error(ee);
			}
		}
		
		//记录操作日志
		String headClientType =  (String)requestMap.get("HeadClientType");
		String headVersion = (String)requestMap.get("HeadVersion");
		interfaceDataLogService.saveInterfaceDataLog(account, "", "", headClientType+headVersion, "Get new password");
	}
	
	private void getEmailConfig(){
		this. rootPath = getText("email.url");
		this.staticFilePath = getText("static.file.path");
		this.companyMail = getText("emailAddress");
	//	this.companyMailPassword= getText("emailPwd");
	//	this.emailServerHost= getText("serverHost") ;
	//	this.emailServerPort = getText("serverPort");
		this.registSubject = getText("registSubject");
		this.findPwdSubject = getText("findPwdsubject");
		this.newPwdSubject = getText("newPwdSubject");
		
		this.emailForgotPwdContent = getText("email.forgotPwd") ;
		this.emailNewPwdContent = getText("email.newPwd");
		this.emailRegisterContent = getText("email.register");
		// 取标题的第一个字段
		this.watchName = this.findPwdSubject.split(" ")[0];
		String deviceType = getText("deviceType");
		
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		
		if(StringUtils.isNotBlank(deviceType) && (deviceType.trim().equalsIgnoreCase("L28w") &&
				deviceType.trim().equalsIgnoreCase("L38"))){
			if (requestMap.containsKey("client")){
				String client = ((String[])requestMap.get("client"))[0];
				if(client.equalsIgnoreCase("web") || 
						client.equalsIgnoreCase("pc") || 
						client.equalsIgnoreCase("mac"))  {
					this.emailForgotPwdContent = getText("email.forgotPwd.ext") ;
					this.emailNewPwdContent = getText("email.newPwd.ext");
					this.emailRegisterContent = getText("email.register.ext");
					if(emailForgotPwdContent.equals("")){
						this.emailForgotPwdContent = getText("email.forgotPwd","") ;
					}
					if(emailNewPwdContent.equals("")){
						this.emailNewPwdContent = getText("email.newPwd","") ;
					}
					if(emailNewPwdContent.equals("")){
						this.emailNewPwdContent = getText("email.newPwd","") ;
					}
				}
			}
		}else if (requestMap.containsKey("client")){
			String client = ((String[])requestMap.get("client"))[0];
			if(client.equalsIgnoreCase("ios") || client.equalsIgnoreCase("android"))  {
				this.registSubject = getText("registSubject.ext","");
				this.findPwdSubject = getText("findPwdsubject.ext","");
				this.newPwdSubject = getText("newPwdSubject.ext","");
				this.emailForgotPwdContent = getText("email.forgotPwd.ext","") ;
				this.emailNewPwdContent = getText("email.newPwd.ext","");
				this.emailRegisterContent = getText("email.register.ext","");
				if(emailForgotPwdContent.equals("")){
					this.emailForgotPwdContent = getText("email.forgotPwd","") ;
				}
				if(emailNewPwdContent.equals("")){
					this.emailNewPwdContent = getText("email.newPwd","") ;
				}
				if(emailNewPwdContent.equals("")){
					this.emailNewPwdContent = getText("email.newPwd","") ;
				}
			}
		}
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

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getTxtVarcode() {
		return txtVarcode;
	}

	public void setTxtVarcode(String txtVarcode) {
		this.txtVarcode = txtVarcode;
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

}
