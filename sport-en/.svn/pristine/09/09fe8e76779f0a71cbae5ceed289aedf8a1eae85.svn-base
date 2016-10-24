/**
 * Copyright appscomm.cn 2013. All rights reserved.
 *
 * @createDate 2013-9-4
 */
package com.appscomm.sport.service;

import java.util.List;
import java.util.Map;

import com.appscomm.sport.common.SportApiCode;
import com.appscomm.sport.model.AccessTokenVo;
import com.appscomm.sport.model.AdvancedSetttingVO;
import com.appscomm.sport.model.LoginInfoVO;
import com.appscomm.sport.model.PersonVO;
import com.appscomm.sport.model.PersonWatchVO;
import com.appscomm.sport.model.UserVO;

/**
 *  用户数据操作相关服务接口  
 *	
 *  qindf create by 2013-9-4
 *
 */
public interface UserService {
	
	/**
	 * 根据注册用户ID获取注册用户信息
	 * @param userId
	 * @return
	 */
	public UserVO getUser(Long userId);
	
	/**
	 * 根据传入的参数类型获取未激活注册用户信息
	 * @param params
	 * @return
	 */
	public List<UserVO> getUserUnActive(Map<String,Object> params);
	
	/**
	 * 根据传入的参数类型获取激活注册用户信息
	 * @param params
	 * @return
	 */
	public List<UserVO> getUserActive(Map<String,Object> params);
	
	/**
	 * 是否存在当前的注册用户
	 * 
	 * @param user
	 * @return 
	 * 			如已存在注册用户，则返回相应的提示码
	 */
	public  SportApiCode isExistUser(UserVO user);
	
	/**
	 * 注册用户的同时增加一条个人基本信息
	 * @param user
	 * @return
	 */
	public UserVO addUserAndPerson(UserVO user);
	
	/**
	 * 获取用户设备
	 * @param params
	 * @return
	 */
	public List<PersonWatchVO> getPersonWatch(Map<String,Object> params);
	public List<PersonWatchVO> getPersonBindWatch(Long personId);
	
	/**
	 * 获取一条个人基本信息
	 * @param personId
	 * @return
	 */
	public PersonVO getPerson(Long registerId);
	
	/**
	 * 编辑个人基本信息
	 * @param person
	 * @param user
	 * @return
	 */
	public boolean editPerson(PersonVO person,UserVO user);
	
	/**
	 * 编辑个人基本信息
	 * @param person
	 * @return
	 */
	public int editPerson(PersonVO person);
	
	/**
	 * 获取watchid绑定的用户信息
	 * @param watchid设备信息
	 * @return UserVO
	 */	
	public List<UserVO> getBindUserInfo(String watchid);
	
	
	/**
	 * 根据uid获取Token信息
	 * @param TokenVo 对象
	 * @return List<TokenVo>
	 */
	public List<AccessTokenVo> getAccessTokenLists(AccessTokenVo tokenVo);
	
	
	/**
	 * 修改某条Token记录
	 * @param TokenVo 对象
	 * @return affectedRows
	 */
	public int editAccessTokens(AccessTokenVo tokenVo);
	
	
	/**
	 * 新增某条Token记录
	 * @param TokenVo 对象
	 * @return affectedRows
	 */
	public int insertAccessTokens(AccessTokenVo tokenVo);

	PersonVO getPersonByNickName(String nickName);
	
	int checkDupNickName(String nickName, long userId);

	PersonVO getPersonById(long userId);	
	
	int updatePwd(UserVO userVo);

	boolean isExistsByEmail(String email);
	Long getRegisterIdByEmail(String email);
	/**
	 * 根据账号id和密码判断用户是否存在
	 * @param accountId
	 * @param oldPasword
	 * @return
	 */
	public Integer getUserByPassword(Long accountId, String oldPassword);
	public Integer getUserByPassword(String account, String oldPassword);
	
	/**
	 * 修改用户密码
	 * @param accountId
	 * @param newPassword
	 * @return
	 */
	public Integer modifyUserPassword(Long accountId, String newPassword);
	public Integer modifyUserPassword(String account, String newPassword);
	/**
	 * 添加新用户（法国）
	 * @param user
	 * @return
	 */
	public UserVO addNewUser(UserVO user);
	
	/**
	 * 获取用户设备高级设置信息
	* @description:
	* @return(设定参数)
	* @return AdvancedSetttingVO(返回值说明)
	* @author Administrator  2014-6-21
	 */
	public AdvancedSetttingVO getUserAdvancedSetttingInfo(Long personId, String watchId);
	/**
	 * 添加用户设备高级设置信息
	* @description:
	* @param advanced
	* @return(设定参数)
	* @return Long(返回值说明)
	* @author Administrator  2014-6-21
	 */
	public Integer setUserAdvancedSetttingInfo(AdvancedSetttingVO advanced);
	
	/**
	 * 删除用户账号及关联信息
	* @description:
	* @param user
	* @return(设定参数)
	* @return boolean(返回值说明)
	* @author Administrator  2014-7-22
	 */
	public boolean deleteUserInfo(UserVO user, String watchId);
	/**
	 * 判断用户账号是否存在
	* @description:
	* @param user
	* @return(设定参数)
	* @return boolean(返回值说明)
	* @author Administrator  2014-7-22
	 */
	public UserVO  isExistsAccount(UserVO user);
	
	/**
	 * 设置个人国家代码
	* @description:
	* @param personId
	* @param countryCode(设定参数)
	* @return void(返回值说明)
	* @author Administrator  2014-11-19
	 */
	public void setUserCountryCode(Long personId, String countryCode);
	/**
	 * 获取个人国家代码
	* @description:
	* @param personId
	* @return(设定参数)
	* @return String(返回值说明)
	* @author Administrator  2014-11-19
	 */
	public String getUserCountryCode(Long personId);
	
	/**
	 * 获取用户头像
	* @description:
	* @param userId
	* @return(设定参数)
	* @return String(返回值说明)
	* @author Administrator  2014-12-13
	 */
	public String getUserImg(Long userId);
	/**
	 * 设置用户头像
	* @description:
	* @param userId
	* @param imgUrl
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2014-12-13
	 */
	public Integer setUserImg(Long userId, String imgUrl);
	
	/**
	 * 获取注册信息
	* @description:
	* @param account
	* @return(设定参数)
	* @return LoginInfo(返回值说明)
	* @author Administrator  2015-1-28
	 */
	public LoginInfoVO getRegisterInfo(String account);
	
	public List<UserVO> getAllUser();
}
