package com.appscomm.sport.dao;

import com.appscomm.sport.model.DeviceNotifyInfoVO;
import com.appscomm.sport.model.DeviceSleepRemindInfoVO;
import com.appscomm.sport.model.DeviceStayRemindInfoVO;


public interface DeviceNotifyRemindDAO {
	///////////////////// 通知信息数据接口 ////////////////////////////////////////
	/**
	 * 添加通知设置信息
	* @description:
	* @param notify
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public Integer insertNotifyInfo(DeviceNotifyInfoVO notify);
	/**
	 * 修改通知设置信息
	* @description:
	* @param notify
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public Integer updateNotifyInfo(DeviceNotifyInfoVO notify);
	/**
	 * 删除通知设置信息
	* @description:
	* @param userId
	* @param deviceId
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public Integer deleteNotifyInfo(Long userId, String deviceId);
	/**
	 * 查询通知设置信息
	* @description:
	* @param userId
	* @param deviceId
	* @return(设定参数)
	* @return DeviceNotifyInfoVO(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public DeviceNotifyInfoVO queryNotifyInfo(Long userId, String deviceId);
	
	///////////////////// 睡眠提醒数据接口 /////////////////////////////////////
	/**
	 * 添加睡眠提醒西信息
	* @description:
	* @param remind
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public Integer insertSleepRemindInfo(DeviceSleepRemindInfoVO remind);
	/**
	 * 修改睡眠提醒信息
	* @description:
	* @param remind
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public Integer updateSleepRemindInfo(DeviceSleepRemindInfoVO remind);
	/**
	 * 删除睡眠提醒信息
	* @description:
	* @param userId
	* @param deviceId
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public Integer deleteSleepRemindInfo(Long userId, String deviceId);
	/**
	 * 查询睡眠提醒信息
	* @description:
	* @param userId
	* @param deviceId
	* @return(设定参数)
	* @return DeviceSleepRemindInfoVO(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public DeviceSleepRemindInfoVO querySleepRemindInfo(Long userId, String deviceId);
	
	////////////////////////////////// 久坐提醒信息 数据接口 //////////////////////////////////////////
	/**
	 * 添加久坐提醒信息
	* @description:
	* @param remind
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public Integer insertStayRemindInfo(DeviceStayRemindInfoVO remind);
	/**
	 * 修改久坐提醒信息
	* @description:
	* @param remind
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public Integer updateStayRemindInfo(DeviceStayRemindInfoVO remind);
	/**
	 * 删除久坐提醒信息
	* @description:
	* @param userId
	* @param deviceId
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public Integer deleteStayRemindInfo(Long userId, String deviceId);
	/**
	 * 查询久坐提醒信息
	* @description:
	* @param userId
	* @param deviceId
	* @return(设定参数)
	* @return DeviceStayRemindInfoVO(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public DeviceStayRemindInfoVO queryStayRemindInfo(Long userId, String deviceId);

}
