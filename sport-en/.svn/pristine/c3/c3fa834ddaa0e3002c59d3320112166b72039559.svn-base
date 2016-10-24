/**
 * Copyright appscomm 2014. All rights reserved.
 */

package com.appscomm.sport.dao;

import java.util.List;

import com.appscomm.sport.model.PersonWatchVO;
import com.appscomm.sport.model.SysParamVO;

/**
 * @ClassName:AdminUserDao.java
 * @Description:查询用户绑定数据模型
 * @author:  叶子丰
 * @date:    2014-7-25
 */

public interface AdminUserDao {

	/***
	 * 
	* @description:根据用户账号查询绑定信息
	* @param userAccount - 用户账号(即邮箱)
	* @return
	* @return List<PersonWatchVO>
	* @author 叶子丰  2014-7-25
	 */
	List<PersonWatchVO> qryWatchByUser(String userAccount) throws Exception;
	
	/***
	 * 
	* @description:根据设备id查询绑定信息
	* @param watchId
	* @return
	* @return List<PersonWatchVO>
	* @author 叶子丰  2014-7-25
	 */
	List<PersonWatchVO> qryWatchByWatchId(String watchId) throws Exception;
	
	/***
	 * 
	* @description:绑定或解绑一条记录
	* @param sid - 记录id
	* @param status - 状态：0-解绑 1-绑定
	* @return void
	* @author 叶子丰  2014-7-25
	 */
	void bindOrRealseOne(Integer sid,Integer status) throws Exception;
	
	/***
	 * 
	* @description:批量解绑设备
	* @param sids - 设备记录数组
	* @return void
	* @author 叶子丰  2014-7-25
	 */
	void batcheRealse(Integer[] sids) throws Exception;

	List<SysParamVO> querySpecialAdmin();
	SysParamVO queryKronozTokenHour();
	SysParamVO queryKronozTokenLimit();
}
