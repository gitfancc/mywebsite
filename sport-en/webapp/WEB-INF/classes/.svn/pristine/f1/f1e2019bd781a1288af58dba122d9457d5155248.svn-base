/**
 * Copyright appscomm.cn 2013. All rights reserved.
 *
 * @createDate 2013-9-4
 */
package com.appscomm.sport.dao;

import java.util.List;
import java.util.Map;

import com.appscomm.sport.model.LoginInfoVO;
import com.appscomm.sport.model.AccessTokenVo;
import com.appscomm.sport.model.AdvancedSetttingVO;
import com.appscomm.sport.model.AppVersionVO;
import com.appscomm.sport.model.PersonVO;
import com.appscomm.sport.model.PersonWatchVO;
import com.appscomm.sport.model.UserVO;

/**
 *  用户数据操作相关dao接口  
 *	
 *  qindf create by 2013-9-4
 *
 */
public interface UserDao {

	/**
	 * 根据注册用户ID获取注册用户信息
	 * @param userId
	 * @return
	 */
	public UserVO getUser(Long userId);
	
	/**
	 * 根据传入的参数类型获取注册用户信息
	 * @param params
	 * @return
	 */
	public List<UserVO> getUser(Map<String,Object> params,boolean isActive);
	
	/**
	 * 根据传入的用户信息获取注册用户的详细信息
	 * 
	 * @param user
	 * @return
	 */
	public UserVO  getUser(UserVO user);
	
	/**
	 * 存储一条注册用户信息
	 * @param user
	 * @return
	 */
	public long addUser(UserVO user);
	
	/**
	 * 保存一条个人基本信息
	 * @param person
	 * @return
	 */
	public long addPerson(PersonVO person);
	public long addPersonEx(PersonVO person);
	/**
	 * 获取用户设备
	 * @param params
	 * @return
	 */
	public List<PersonWatchVO> getPersonWatch(Map<String,Object> params);
	public List<PersonWatchVO> getPersonBindWatch(Long personId);
	/**
	 * 获取一条个人基本信息
	 * @param registerId
	 * @return
	 */
	public PersonVO getPerson(Long registerId);
	
	/**
	 * 根据用户昵称获取用户
	 * 
	 * @param nickName
	 * @return
	 */
	public PersonVO getPersonByName(String nickName);
	/**
	 * 编辑个人基本信息
	 * @param person
	 * @return
	 */
	public int editPerson(PersonVO person);
	
	/**
	 * 编辑用户信息
	 * @param user
	 * @return
	 */
	public int editUser(UserVO user);
		
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

	PersonVO getPersonById(long userId);

	int checkDulpNickName(String nickName, long userId);

	/**
	 * 根据版本 获取版本信息
	 * @param desc
	 * @return AppVersionVO
	 */
	public AppVersionVO getVersionsByVer(String desc);

	public int updatePwd(UserVO userVo);

	public boolean isExistsByEmail(String email);
	/**
	 * 判断数据库中是否存在此用户
	 * @param accountId
	 * @param oldPasword
	 * @return
	 */
	public Integer getUserByPassword(Long accountId, String oldPasword);
	public Integer getUserByPassword(String account, String oldPasword);
	/**
	 * 根据账号id修改用户密码
	 * @param accountId
	 * @param newPassword
	 * @return
	 */
	public Integer modifyUserPassword(Long accountId, String newPassword);
	public Integer modifyUserPassword(String account, String newPassword);
	
	/**
	 * 查询用户设备高级设置信息
	* @description:
	* @return(设定参数)
	* @return AdvancedSetttingVO(返回值说明)
	* @author Administrator  2014-6-21
	 */
	public AdvancedSetttingVO getUserAdvancedSetttingInfo(Long personId, String watchId);
	/**
	 * 设置用户设备高级设置信息
	* @description:
	* @param advanced
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2014-6-21
	 */
	public Integer addUserAdvancedSettingInfo(AdvancedSetttingVO advanced) ;
	/**
	 * 修改用户设备高级设置信息
	* @description:
	* @param advanced
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2014-6-21
	 */
	public Integer modifyAdvancedSettingInfo(AdvancedSetttingVO advanced);
	
	/**
	 * 更新注册用户状态
	* @description:
	* @param user
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2014-7-22
	 */
	public Integer updateUserStatus(Long accountId);
	
	/** 重置设备是，清理以下数据 */
	public void deleteSportData(Long personId, String watchId);
	public void deleteSleepData(Long personId, String deviceType);
	public void deleteRemind(Long personId, String watchId);
	public void deleteTarget(Long personId);
	public PersonWatchVO getBindWatch(Long personId, String watchId);
	/**
	 * 修改个人国家代码
	* @description:
	* @param personId
	* @param countryCode(设定参数)
	* @return void(返回值说明)
	* @author Administrator  2014-11-19
	 */
	public void updateUserCountry(Long personId, String countryCode);
	/**
	 * 查询个人国家代码
	* @description:
	* @param personId
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2014-11-19
	 */
	public Integer getUserCountry(Long personId);
	
	/**
	 * 用户头像
	* @description:
	* @param imgUrl
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2014-12-12
	 */
	public Integer addUserImg(Long userId, String imgUrl);
	public String queryUserImg(Long userId);

	/**
	 * 获取注册信息
	* @description:
	* @param account
	* @return(设定参数)
	* @return LoginInfoVO(返回值说明)
	* @author Administrator  2015-1-28
	 */
	LoginInfoVO getRegisterInfo(String account);
	
	
	public List<UserVO> getAllUser();

	public Long getRegisterIdByEmail(String email);
}
