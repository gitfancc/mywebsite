package com.appscomm.sport.service;

import com.appscomm.sport.model.DeviceNotifyInfoVO;
import com.appscomm.sport.model.DeviceSleepRemindInfoVO;
import com.appscomm.sport.model.DeviceStayRemindInfoVO;

public interface DeviceNotifyRemindService {
	/**
	 * 添加通知提醒
	* @description:
	* @param notify
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public Integer addDeviceNotifyInfo(DeviceNotifyInfoVO notify);
	public void addDeviceNotifyStatus(DeviceNotifyInfoVO notify);
	public void updateDeviceNotifyStatus(DeviceNotifyInfoVO notify);
	/**
	 * 获取通知提醒
	* @description:
	* @param userId
	* @param deviceId
	* @return(设定参数)
	* @return DeviceNotifyInfoVO(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public DeviceNotifyInfoVO getDeviceNotifyInfo(Long userId, String deviceId);
	
	/**
	 * 添加睡眠提醒
	* @description:
	* @param remind
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public Integer addSleepRemindInfo(DeviceSleepRemindInfoVO remind);
	/**
	 * 获取睡眠提醒
	* @description:
	* @param userId
	* @param deviceId
	* @return(设定参数)
	* @return DeviceSleepRemindInfoVO(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public DeviceSleepRemindInfoVO getSleepRemindInfo(Long userId, String deviceId);
	/**
	 * 添加久坐提醒
	* @description:
	* @param remind
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public Integer addStayRemindInfo(DeviceStayRemindInfoVO remind);
	/**
	 * 获取久坐提醒
	* @description:
	* @param userId
	* @param deviceId
	* @return(设定参数)
	* @return DeviceStayRemindInfoVO(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public DeviceStayRemindInfoVO getStayRemindInfo(Long userId, String deviceId);
}
