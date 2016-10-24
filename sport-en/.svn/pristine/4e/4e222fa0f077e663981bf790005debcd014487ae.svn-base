/**
 * Copyright appscomm.cn 2013. All rights reserved.
 *
 * @createDate 2013-9-11
 */
package com.appscomm.sport.api.action;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import sun.misc.BASE64Decoder;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.appscomm.sport.common.SportApiCode;
import com.appscomm.sport.model.AdvancedSetttingVO;
import com.appscomm.sport.model.DistrictVO;
import com.appscomm.sport.model.PersonVO;
import com.appscomm.sport.model.UserVO;
import com.appscomm.sport.service.CityService;
import com.appscomm.sport.service.InterfaceDataLogService;
import com.appscomm.sport.service.UserService;
import com.appscomm.sport.utils.AESEncrypty;
import com.appscomm.sport.utils.HttpUtils;
import com.appscomm.sport.utils.ImageUtil;
import com.appscomm.sport.utils.JsonUtils;
import com.appscomm.sport.vo.UploadImgInfo;
import com.opensymphony.xwork2.ActionSupport;

/**
 *  用户控制层Action  
 *	
 *  qindf create by 2013-9-11
 *
 */
public class UserAction extends ActionSupport {

	private static final long serialVersionUID = -4502261895346456171L;
	private static Logger log = Logger.getLogger(UserAction.class);
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private String KEY_HEX = "C1511E2A29B3C721EC1E4E1C0A396559";
	AESEncrypty encrypty = new AESEncrypty(KEY_HEX);
	
	@Autowired
	private UserService userService;
	@Autowired
	private CityService cityService;
	@Autowired
	private InterfaceDataLogService interfaceDataLogService;
	
	/**
	 * 查询个人信息接口
	 * @return
	 */
	public void info(){
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		String registerId = null;
		String responseStr = null;
		PersonVO person = null;
		try{
			registerId = ((String[])requestMap.get("registerId"))[0];
			person = userService.getPerson(Long.valueOf(registerId));
			if(person!=null){
				responseStr = JsonUtils.responseJson(SportApiCode.SUCCESS, person);
			}else{
				responseStr = JsonUtils.responseJson(SportApiCode.ERROR_1114, null);
			}
			HttpUtils.sendResponseJson(responseStr,requestMap);
		}catch(Exception e){
			log.info("查询个人信息接口调用异常:"+e);
		}
		
		//记录操作日志
		String headClientType =  (String)requestMap.get("HeadClientType");
		String headVersion = (String)requestMap.get("HeadVersion");
		String userId = (person==null)?"":String.valueOf(person.getId());
		interfaceDataLogService.saveInterfaceDataLog(userId, "", "", headClientType+headVersion, "Get personal info");
	}
	
	/**
	 * 设置个人资料接口
	 */
	@SuppressWarnings("unchecked")
	public void set(){
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		String userId = "";
		try{
			String userInfo =  ((String[])requestMap.get("userInfo"))[0];
			ObjectMapper mapper = new ObjectMapper();
			Map<String,String> userMap = mapper.readValue(userInfo, Map.class);
			
			userId =userMap.get("userId");
			String userName = userMap.get("userName").trim(); 
			///String nickName = userMap.get("nickName"); 
			String gender = userMap.get("gender");
			String birthDay = userMap.get("birthDay");
			String height = userMap.get("height");
			String weight = userMap.get("weight");
			
			String telphone = null;
			String city = null;
			String qq = null;
			String weibo = null;
			String email = null;
			String img = null;
			String heightUnit = null;
			String weightUnit = null;
			String countryCode =  "0";
			
			if (userMap.containsKey("telephone")){
				telphone =userMap.get("telephone");
			}
			if (userMap.containsKey("city")){
				city =userMap.get("city");
			}
			if (userMap.containsKey("QQ")){
				qq =userMap.get("QQ");
			}
			if (userMap.containsKey("weibo")){
				weibo =userMap.get("weibo");
			}
			if (userMap.containsKey("imgUrl")){
				img =userMap.get("imgUrl");
			}
			if (userMap.containsKey("email")){
				email =userMap.get("email");
			}
			if (userMap.containsKey("heightUnit")){
				heightUnit =userMap.get("heightUnit");
			}
			if (userMap.containsKey("weightUnit")){
				 weightUnit =userMap.get("weightUnit");			
			}
			if (userMap.containsKey("countryCode")){
				countryCode =  userMap.get("countryCode");
			}
			
			//当前传入的nickname不允许重复！
		/*	int iCount = userService.checkDupNickName(nickName, Long.parseLong(userId));
			if(iCount > 0){
				String responseStr = JsonUtils.responseJson(SportApiCode.ERROR_7794, null);
				HttpUtils.sendResponse(responseStr);
				return;
			}*/
			
			//为了防止原先用户信息被覆盖，再一次查询当前用户信息。
			PersonVO person = userService.getPersonById(Long.parseLong(userId)); 
			person = (person == null ? new PersonVO() : person);
			
			person.setId(Long.valueOf(userId));
			if(userName != null && !userName.isEmpty()) {
				person.setUserName(userName);
				person.setNickName(userName);
			}
			//if(nickName != null && !nickName.isEmpty()) person.setNickName(nickName);
			if(gender != null && !gender.isEmpty()) person.setGender(Integer.valueOf(gender));
			if(birthDay != null && !birthDay.isEmpty()) {
//				Date date = Tools.stringToDate(birthDay, "yyyy-MM-dd");
//				//如果日期为null或者格式不正确，默认取2014-01-01
//				if (date == null) date = Tools.stringToDate("2014-01-01", "yyyy-MM-dd");
//				person.setBirthDate(date);
				person.setBirthDate(birthDay);
			}
			
			if(StringUtils.isNotBlank(telphone)) person.setTelphone(telphone);
			if(StringUtils.isNotBlank(weight)) person.setWeight(Double.parseDouble(weight));
			if(StringUtils.isNotBlank(height)) person.setHeight(height);
			if(StringUtils.isNotBlank(qq))person.setQq(qq);
			if(StringUtils.isNotBlank(weibo))person.setWeibo(weibo);
			if(StringUtils.isNotBlank(email))person.setEmail(email);
			if(StringUtils.isNotBlank(img))person.setImgUrl(img);
			if (StringUtils.isNotBlank(heightUnit)) person.setHeightUnit(Integer.valueOf(heightUnit));
			if(StringUtils.isNotBlank(weightUnit)) person.setWeightUnit(Integer.valueOf(weightUnit));
			if(StringUtils.isNotBlank(countryCode)) person.setCountryId(Integer.valueOf(countryCode));
			
			if(StringUtils.isNotBlank(city)){
				String[] cityArray = StringUtils.splitPreserveAllTokens(city, "|");
				if(cityArray.length == 3){
					person.setProvinceId(Integer.valueOf(cityArray[0]));
					person.setCityId(Integer.valueOf(cityArray[1]));
					person.setAreaId(Integer.valueOf(cityArray[2]));
				}else if(cityArray.length == 2){
					person.setProvinceId(Integer.valueOf(cityArray[0]));
					person.setCityId(Integer.valueOf(cityArray[1]));
				}else if(cityArray.length == 1){
					person.setProvinceId(Integer.valueOf(cityArray[0]));
				}
			}
			int result = userService.editPerson(person);
			String responseStr = null;
			if(result > 0)
				responseStr = JsonUtils.responseJson(SportApiCode.SUCCESS, null);
			else
				responseStr = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			HttpUtils.sendResponseJson(responseStr,requestMap);
		}catch(Exception e){
			log.info("设置个人信息接口调用异常:"+e);
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
		interfaceDataLogService.saveInterfaceDataLog(userId, "", "", headClientType+headVersion, "Set personal info");
	}
	
	public void districtInfo(){
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		try{
			String districtId =  ((String[])requestMap.get("districtId"))[0];
			List<DistrictVO> districtList = cityService.getCitys(Integer.valueOf(districtId));
			
			String responseStr = null;
			if (districtList != null && districtList.size() > 0){
				responseStr=JsonUtils.responseJson(SportApiCode.SUCCESS, districtList);
			} else {
				responseStr = JsonUtils.responseJson(SportApiCode.ERROR_7788, null);
			}
			HttpUtils.sendResponseJson(responseStr,requestMap);
		}catch(Exception e){
			log.info("获取区域信息接口调用异常:"+e);
			String responseStr = null;
			try {
				responseStr = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			} catch (JsonGenerationException e1) {
			} catch (JsonMappingException e1) {
			} catch (IOException e1) {
			}
			HttpUtils.sendResponseJson(responseStr,requestMap);
		}
	}
	/**
	 * 根据设备ID查询其绑定的用户信息
	 * @return email,telephone,昵称
	 */
	public void qryBindDevice()
	{
		String resp = null;
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		try{
//			String watchid =  ((String[])requestMap.get("watchId"))[0];
//			List<UserVO> record = userService.getBindUserInfo(watchid);
//			if(record == null || record.size() == 0){
			//2015-11-17曹刚 无论如何都返回7789，让客户端进行绑定设备
				resp = JsonUtils.responseJson(SportApiCode.ERROR_7789, null);
//			}else{
//				resp = JsonUtils.responseJson(SportApiCode.SUCCESS, record.get(0));
//			}
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
	}	
	
	public void getAdvanceInfo(){
		String resp = null;
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		try{
			String personId = ((String[])requestMap.get("personId"))[0];
			String watchId = ((String[])requestMap.get("watchId"))[0];
			AdvancedSetttingVO advanced = userService.getUserAdvancedSetttingInfo(Long.valueOf(personId), watchId);
			if(advanced == null){
				resp = JsonUtils.responseJson(SportApiCode.ERROR_8000, null);
			}else{
				resp = JsonUtils.responseJson(SportApiCode.SUCCESS, advanced);
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
	}
	public void setAdvanceInfo(){
		String resp = null;
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		try{
			String personId = ((String[])requestMap.get("personId"))[0];
			String watchId = ((String[])requestMap.get("watchId"))[0];
			String timeMode = ((String[])requestMap.get("timeMode"))[0];
			String lengthUnit = ((String[])requestMap.get("lengthUnit"))[0];
			String weightUnit = ((String[])requestMap.get("weightUnit"))[0];

			AdvancedSetttingVO advanced = new AdvancedSetttingVO();
			advanced.setPersonId(Long.valueOf(personId));
			advanced.setWatchId(watchId);
			advanced.setTimeMode(timeMode);
			advanced.setLengthUnit(lengthUnit);
			advanced.setWeightUnit(weightUnit);
			Integer ret = userService.setUserAdvancedSetttingInfo(advanced);
			resp = JsonUtils.responseJson(SportApiCode.SUCCESS, ret);
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
	}
	
	/**
	 * 删除用户账号及其关联信息
	* @description:(设定参数)
	* @return void(返回值说明)
	* @author Administrator  2014-7-22
	 */
	public void removeUserData(){
		String resp = null;
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		try{
			String account = ((String[])requestMap.get("account"))[0];
			String watchId = ((String[])requestMap.get("watchId"))[0];

			if (StringUtils.isNotBlank(account) && StringUtils.isNotBlank(watchId)){
				UserVO delUser = new UserVO();
				//获取账号信息
				if(account.indexOf("@")!=-1){
					delUser.setMail(account);
				} else {
					delUser.setTelphone(account);
					HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.ERROR_1115, null),requestMap);
					return;
				}
			
				//删除账号相关信息
				this.userService.deleteUserInfo(delUser, watchId);
				resp = JsonUtils.responseJson(SportApiCode.SUCCESS, null);
			}else{
				//账号为空，返回参数为空提示
				resp = JsonUtils.responseJson(SportApiCode.ERROR_2001, null);
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
	}

	public void syncUserCountry(){
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		try{
			String countryCode =  "0";
			if(!(requestMap.containsKey("userId")) || !(requestMap.containsKey("optType"))){
				HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.ERROR_2001, null),requestMap);
				return;
			}
		
			String userId = ((String[])requestMap.get("userId"))[0];
			String optType = ((String[])requestMap.get("optType"))[0];
			try{
				Map<String, String> map  = new HashMap<String, String>();
				if(optType.equals("0")){ //设置
					if(!(requestMap.containsKey("countryCode"))) {
						HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.ERROR_2001, null),requestMap);
						return;
					}
					countryCode = ((String[])requestMap.get("countryCode"))[0];
					userService.setUserCountryCode(Long.valueOf(userId), countryCode);
				}else if(optType.equals("1")){ //查询
					countryCode = userService.getUserCountryCode(Long.valueOf(userId));
				} else{
					HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.ERROR_2001, null),requestMap);
					return;
				}
				
				map.put("countryCode", countryCode);
				HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.SUCCESS, map),requestMap);
			}catch(Exception ex){
				HttpUtils.sendResponseJson(JsonUtils.responseJson(SportApiCode.ERROR_7790, null),requestMap);
			}
		}catch(Exception e){
			log.info("设置国家信息接口调用异常:"+e);
			String responseStr = null;
			try {
				responseStr = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			} catch (JsonGenerationException e1) {
			} catch (JsonMappingException e1) {
			} catch (IOException e1) {
			}
			HttpUtils.sendResponseJson(responseStr,requestMap);
		}
	}
	
	private String photoFileName;
	private File photo;
	private String userId;

	public void setUploadImg() {
		String resp = null;
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		try {
			String filePath = getText("file.upload.path");
			String suffix = photoFileName.substring(photoFileName.lastIndexOf('.') + 1);
			String imageUrl = ImageUtil.saveFile(photo, suffix, filePath);
			if(imageUrl != null){
				imageUrl = imageUrl.substring(filePath.length());
			}
			//String photoUrl = getText("prefix.url");
			//map.put("imgUrl", photoUrl+"/"+imageUrl);
			requestMap.put("imgUrl", imageUrl);
			
			PersonVO userInfo = null;
			if(userId!=null){
				userInfo = this.userService.getPersonById(Long.valueOf(userId));
			}
		
			if (userInfo != null) {
				// 用户存在,更新头像地址
				this.userService.setUserImg(Long.valueOf(userId), imageUrl);
			}/*
			 * else{ //用户不存在 resp =
			 * JsonUtils.responseJson(SportApiCode.ERROR_1114, map); }
			 */
			resp = JsonUtils.responseJson(SportApiCode.SUCCESS, requestMap);
		} catch (Exception e) {
			try {
				 resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			} catch (Exception e1) {
			}
		}
		HttpUtils.sendResponseJson(resp,requestMap);
	}
	
	public void setUploadImgBase64() {
		String resp = null;
		String filePath = getText("file.upload.path");
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			ObjectMapper mapper = new ObjectMapper();
			UploadImgInfo imgInfo = mapper.readValue(request.getInputStream(), UploadImgInfo.class);
			log.info("upload image info:::" + imgInfo.toString());

			String uId = imgInfo.getUserId();
			String images = imgInfo.getImage();
			String suffix = imgInfo.getImageSuffix();
			// Base64解码
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] imgBytes = decoder.decodeBuffer(images);
			for (int i = 0; i < imgBytes.length; ++i) {
				if (imgBytes[i] < 0) {
					imgBytes[i] += 256; // 调整异常数据
				}
			}
			// 保存图片
			String imageUrl = ImageUtil.saveFile(imgBytes, suffix, filePath);
			if (imageUrl != null) {
				imageUrl = imageUrl.substring(filePath.length());
			}
			requestMap.put("imgUrl", imageUrl);

			// 更新个人信息中的URL
			PersonVO userInfo = null;
			if (uId != null) {
				userInfo = this.userService.getPersonById(Long.valueOf(uId));
			}
			if (userInfo != null) {
				// 用户存在,更新头像地址
				this.userService.setUserImg(Long.valueOf(uId), imageUrl);
			}
			resp = JsonUtils.responseJson(SportApiCode.SUCCESS, requestMap);
		} catch (Exception e) {
			log.info("upload image error:::" + e.getMessage());
			try {
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			} catch (Exception e1) {
			}
		}
		HttpUtils.sendResponseJson(resp,requestMap);
	}
	
	public void getUploadImg(){
		String resp = null;
		Map<String, Object> requestMap = HttpUtils.receiveRequestMap();
		requestMap.put("seq", String.valueOf(System.currentTimeMillis()));
		try {
			if (!(requestMap.containsKey("userId"))) {
				HttpUtils.sendResponse(JsonUtils.responseJson(SportApiCode.ERROR_2001, null),requestMap);
				return;
			}
			String userId = ((String[]) requestMap.get("userId"))[0];
			String imgUrl = this.userService.getUserImg(Long.valueOf(userId));
		
			Map<String, String> map = new HashMap<String, String>();
			//String photoUrl = getText("prefix.url");
			//map.put("imgUrl", photoUrl+"/"+imgUrl);
			map.put("imgUrl",imgUrl);
			resp = JsonUtils.responseJson(SportApiCode.SUCCESS, map);
		} catch (Exception ex) {
			try {
				resp = JsonUtils.responseJson(SportApiCode.ERROR_9999, null);
			} catch (Exception e) {}
		}
		HttpUtils.sendResponseJson(resp,requestMap);
	}

	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	//method

}
