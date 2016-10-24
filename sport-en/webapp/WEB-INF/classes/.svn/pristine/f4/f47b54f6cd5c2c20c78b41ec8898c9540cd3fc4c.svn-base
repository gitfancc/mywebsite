/**
 * Copyright appscomm.cn 2013. All rights reserved.
 *
 * @createDate 2013-9-4
 */
package com.appscomm.sport.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appscomm.sport.common.SportApiCode;
import com.appscomm.sport.dao.UserDao;
import com.appscomm.sport.model.AccessTokenVo;
import com.appscomm.sport.model.AdvancedSetttingVO;
import com.appscomm.sport.model.LoginInfoVO;
import com.appscomm.sport.model.PersonVO;
import com.appscomm.sport.model.PersonWatchVO;
import com.appscomm.sport.model.UserVO;
import com.appscomm.sport.service.UserService;

/**
 *  用户数据操作相关服务接口实现 
 *	
 *  qindf create by 2013-9-4
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public UserVO getUser(Long userId){
		return userDao.getUser(userId);
	}
	
	@Override
	public List<UserVO> getUserUnActive(Map<String,Object> params){
		return userDao.getUser(params,false);
	}
	
	@Override
	public List<UserVO> getUserActive(Map<String,Object> params){
		return userDao.getUser(params,true);
	}
	
	@Override
	public SportApiCode isExistUser(UserVO user){
		UserVO enty = userDao.getUser(user.getId());
		if(enty != null)
		{
			return SportApiCode.ERROR_1110;
		}
		UserVO userTmp = userDao.getUser(user);
		if (userTmp != null){
			if ( StringUtils.isNotEmpty(userTmp.getMail())
					&& userTmp.getMail().trim().equals(user.getMail().trim())){
				return SportApiCode.ERROR_1105;
			}
			if (StringUtils.isNotEmpty(userTmp.getTelphone())
				&& userTmp.getTelphone().trim().equals(user.getTelphone().trim())){
				return SportApiCode.ERROR_1106;
			}
			if(StringUtils.isNotEmpty(userTmp.getNickName())
					&&  userTmp.getNickName().trim().equals(user.getNickName().trim())){
				return SportApiCode.ERROR_1107;
			}
		}
		
		return null;
	}
	
	@Override
	public UserVO addUserAndPerson(UserVO user){
		PersonVO person = new PersonVO();
		person.setRegisterId(userDao.addUser(user));
		person.setNickName(user.getNickName());
		person.setUserName(user.getUserName());
		person.setEmail(user.getMail());
		person.setGender(user.getGender());
		person.setBirthDate(user.getBirthDate());
		person.setHeight(user.getHeight());
		person.setWeight(user.getWeight());
		person.setHeightUnit(user.getHeightUnit());
		person.setWeightUnit(user.getWeightUnit());
		person.setCountryId(user.getCountryId());
		if(StringUtils.isNotBlank(user.getImgUrl())){
			person.setImgUrl(user.getImgUrl());
		}
		
		user.setUserId((long)userDao.addPersonEx(person));
		user.setId(person.getRegisterId());
		if (user.getWatchId() == null){
			user.setIsBind(0);
		} else {
			user.setIsBind(1);
		}
		return user;
	}
	
	@Override
	public List<PersonWatchVO> getPersonWatch(Map<String,Object> params){
		return userDao.getPersonWatch(params);
	}

	@Override
	public PersonVO getPerson(Long registerId){
		return userDao.getPerson(registerId);
	}
	
	@Override
	public PersonVO getPersonById(long userId){
		return userDao.getPersonById(userId);
	}
	
	@Override
	public boolean editPerson(PersonVO person,UserVO user){
		return userDao.editPerson(person)>0&&userDao.editUser(user)>0;
	}
	
	@Override
	public int editPerson(PersonVO person){
		return userDao.editPerson(person);
	}
		
	public List<UserVO> getBindUserInfo(String watchid)
	{
		return userDao.getBindUserInfo(watchid);
	}
	
	@Override
	public  PersonVO getPersonByNickName(String nickName){
		return userDao.getPersonByName(nickName);
	}

	@Override
	public int checkDupNickName(String nickName, long userId) {
		// TODO Auto-generated method stub
		return userDao.checkDulpNickName(nickName, userId);
	}

	@Override
	public List<AccessTokenVo> getAccessTokenLists(AccessTokenVo tokenVo) {
		return userDao.getAccessTokenLists(tokenVo);
	}

	@Override
	public int editAccessTokens(AccessTokenVo tokenVo) {
		return userDao.editAccessTokens(tokenVo);
	}

	@Override
	public int insertAccessTokens(AccessTokenVo tokenVo) {
		return userDao.insertAccessTokens(tokenVo);
	}

	@Override
	public int updatePwd(UserVO userVo) {
		return userDao.updatePwd(userVo);
	}

	@Override
	public boolean isExistsByEmail(String email) {
		return userDao.isExistsByEmail(email);
	}
	public Long getRegisterIdByEmail(String email){
		return userDao.getRegisterIdByEmail(email);
	}
	@Override
	public Integer getUserByPassword(Long accountId, String oldPassword) {
		return userDao.getUserByPassword(accountId, oldPassword);
	}

	@Override
	public Integer modifyUserPassword(Long accountId, String newPassword) {
		return userDao.modifyUserPassword(accountId, newPassword);
	}
	
	@Override
	public Integer modifyUserPassword(String account, String newPassword) {
		return userDao.modifyUserPassword(account, newPassword);
	}

	@Override
	public UserVO addNewUser(UserVO user) {
		PersonVO person = new PersonVO();
		person.setUserName(user.getUserName());
		person.setEmail(user.getMail());
		person.setGender(user.getGender());
		person.setNickName(user.getNickName());
		person.setBirthDate(user.getBirthDate());
		person.setHeight(user.getHeight());
		person.setWeight(user.getWeight());
		person.setHeightUnit(user.getHeightUnit());
		person.setWeightUnit(user.getWeightUnit());
		person.setCountryId(user.getCountryId());
		person.setImgUrl(user.getImgUrl());
		
		Long accountId = userDao.addUser(user);
		person.setRegisterId(accountId);
		Long personId = userDao.addPersonEx(person);
		
		user.setUserId(personId);
		user.setId(accountId);
		return user;
	}

	@Override
	public AdvancedSetttingVO getUserAdvancedSetttingInfo(Long personId, String watchId) {
		return userDao.getUserAdvancedSetttingInfo(personId, watchId);
	}

	@Override
	public Integer setUserAdvancedSetttingInfo(AdvancedSetttingVO advanced) {
		if(advanced.getSid() == null){
			return userDao.addUserAdvancedSettingInfo(advanced);
		}else {
			return userDao.modifyAdvancedSettingInfo(advanced);
		}
	}

	@Override
	public boolean deleteUserInfo(UserVO user, String watchId) {
		// 根据账号获取用户信息
		user = userDao.getUser(user);
		Long personId = user.getUserId();
		String watchType = "";
		PersonWatchVO pw = userDao.getBindWatch(personId, watchId);
		if (user != null && pw!=null){
			try{
				watchType = pw.getWatchType();
				//userDao.updateUserStatus(user.getId());
				userDao.deleteRemind(personId, watchId);
				userDao.deleteTarget(personId);
				userDao.deleteSleepData(personId, watchType);
				userDao.deleteSportData(personId, watchId);
			}catch(Exception e){
				return false;
			}
		}
		return true;
	}
	
	@Override
	public UserVO  isExistsAccount(UserVO user){
		return  userDao.getUser(user);
	}

	@Override
	public void setUserCountryCode(Long personId, String countryCode) {
		userDao.updateUserCountry(personId, countryCode);
	}

	@Override
	public String getUserCountryCode(Long personId) {
		return userDao.getUserCountry(personId)+"";
	}

	@Override
	public String getUserImg(Long userId) {
		return this.userDao.queryUserImg(userId);
	}

	@Override
	public Integer setUserImg(Long userId, String imgUrl) {
		return this.userDao.addUserImg(userId, imgUrl);
	}

	@Override
	public LoginInfoVO getRegisterInfo(String account) {
		return this.userDao.getRegisterInfo(account);
	}

	@Override
	public List<PersonWatchVO> getPersonBindWatch(Long personId) {
		return this.userDao.getPersonBindWatch(personId);
	}

	@Override
	public Integer getUserByPassword(String account, String oldPassword) {
		return this.userDao.getUserByPassword(account, oldPassword);
	}

	@Override
	public List<UserVO> getAllUser() {
		return this.userDao.getAllUser();
	}

}
