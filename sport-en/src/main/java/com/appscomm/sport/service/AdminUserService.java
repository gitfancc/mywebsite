/**
 * Copyright appscomm 2014. All rights reserved.
 */

package com.appscomm.sport.service;

import java.util.List;

import com.appscomm.sport.model.PersonWatchVO;

/**
 * @ClassName:AdminUserService.java
 * @Description:管理用户查询设备绑定情况服务接口
 * @author:  叶子丰
 * @date:    2014-7-28
 */

public interface AdminUserService {

	/***
	 * 
	* @description:查询账号的设备绑定信息
	* @param userAccount
	* @return
	* @throws Exception
	* @return List<PersonWatchVO>
	* @author 叶子丰  2014-7-29
	 */
	List<PersonWatchVO> qryWatchByUser(String userAccount)  throws Exception;
	
	/****
	 * 
	* @description:查询设备的绑定信息
	* @param watchId
	* @return
	* @throws Exception
	* @return List<PersonWatchVO>
	* @author 叶子丰  2014-7-29
	 */
	List<PersonWatchVO> qryWatchByWatchId(String[] watches) throws Exception;
	
	/****
	 * 
	* @description:解绑单条记录
	* @param sid
	* @throws Exception
	* @return void
	* @author 叶子丰  2014-7-29
	 */
	void realseOneWatch(Integer sid) throws Exception;
	
	/***
	 * 
	* @description:批量解绑
	* @param sids
	* @throws Exception
	* @return void
	* @author 叶子丰  2014-7-29
	 */
	void batchRealse(Integer[] sids) throws Exception;

	String[] querySpecialAdmin();
}
