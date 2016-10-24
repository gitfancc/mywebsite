package com.appscomm.sport.dao;

import java.util.List;

import com.appscomm.sport.model.DeviceConfigVO;
import com.appscomm.sport.model.DeviceSyncInfoVO;
import com.appscomm.sport.model.DeviceVersionInfoVO;
import com.appscomm.sport.model.PersonWatchLogVO;
import com.appscomm.sport.model.PersonWatchVO;
import com.appscomm.sport.model.WatchVO;

public interface DeviceSetDao {
	/* 表 t_personal_watch相关 */
	/**
	 * 根据personid获取用户下所有的设备
	 * 
	 * @param personId
	 * @return
	 */
	public List<PersonWatchVO> getPersonByPId(Long personId);

	/**
	 * 通过机器号获取登记用户
	 * 
	 * @param watchId
	 * @return
	 */
	public PersonWatchVO getPersonByWId(String watchId, Long personId);
	
	/**
	 * 根据设备类型查找设备绑定信息
	 * 
	 * @param personId
	 * @param watchType
	 * @return
	 */
	public PersonWatchVO getPersonByWType(Long personId, String watchType);
	
	/**
	 * 通过SIM号获取登记用户
	 * 
	 * @param watchSim
	 * @return
	 */
	public PersonWatchVO getPersonBySim(String watchSim, Long personId);
	
	/**
	 * 获取缺省的设备
	 * @param personId
	 * 
	 * @return
	 */
	public PersonWatchVO getDefaultDevice(Long personId);
	
	/**
	 * 设置缺省的设备
	 * 
	 * @param personId
	 * @param watchType
	 * @return
	 */
	public Integer setDefautDevice(Long personId, String watchType);
	
	/**
	 * 获取解绑的设备
	 * 
	 * @param userId
	 * @param watchId
	 * @return
	 */
	public PersonWatchVO getUnbindDevice(Long userId, String watchId);
	
	/**
	 * 修改设备绑定信息
	 * 
	 * @param userId
	 * @param watchid
	 * @return
	 */
	public Integer modifyBindDevice(Long userId, String watchId, Integer isDefault, String bindTime);
	
	/**
	 * 绑定设备号
	 * 
	 * @param personId 绑定用户
	 * @param watchId
	 * @param isDefault
	 * 
	 * @return
	 */
	public Integer addDeviceId(Long personId, String watchId, Integer isDefault, String watchType,String bindTime);

	/**
	 * 绑定设备SIM卡号
	 * 
	 * @param watchSim
	 * @return
	 */
	public Integer addDeviceSim(String watchId, String watchSim);

	/**
	 * 解绑定设备号
	 * 
	 * @param personId
	 * @param watchid
	 * @return
	 */
	public Integer delDeviceId(Long personId, String watchId);

	/**
	 * 解绑定SIM卡号
	 * 
	 * @param personId
	 * @param watchSim
	 * @return
	 */
	public Integer delDeviceSim(Long personId, String watchId);

	/**
	 * 保存新增的设备绑定信息
	 * 
	 * @param personId
	 * @param watch
	 * @param isDefault
	 * @return
	 */
	public Integer addDeviceBind(Long personId, WatchVO watch, Integer isDefault);

	
	
	/* 表 t_watch_setting相关 */
	/**
	 * 通过机器号获取设备参数
	 * 
	 * @param watchId
	 * @return
	 */
	public WatchVO getDeviceByWatchId(String watchId, Long personId);

	/**
	 * 更新设备参数
	 * 
	 * @param watchId
	 * @param updateField
	 * @return
	 */
	public Integer modifyDevice(String watchId, String updateField, Long personId);

	/**
	 * 保存设备设置参数
	 * 
	 * @param device
	 * @return
	 */
	public Integer addDevice(WatchVO device);
	
	/////////////////////////////////// 设备同步信息设置 //////////////////////////////////////////
	/**
	 * 添加设备同步信息
	* @description:
	* @param syncInfo
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public Integer insertDeviceSyncInfo(DeviceSyncInfoVO syncInfo);
	/**
	 * 修改设备同步信息
	* @description:
	* @param syncInfo
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public Integer updateDeviceSyncInfo(DeviceSyncInfoVO syncInfo);
	/**
	 * 删除设备同步信息
	* @description:
	* @param userId
	* @param deviceId
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public Integer deleteDeviceSyncInfo(Long userId, String deviceId);
	/**
	 * 查询设备同步信息
	* @description:
	* @param userId
	* @param deviceId
	* @return(设定参数)
	* @return DeviceSyncInfoVO(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public DeviceSyncInfoVO queryDeviceSyncInfo(Long userId, String deviceId);
	
	//////////////////////////////// 设备固件版本设置 /////////////////////////////////
	/**
	 * 添加设备固件版本信息
	* @description:
	* @param version
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public Integer insertDeviceVersionInfo(DeviceVersionInfoVO version);
	/**
	 * 修改设备固件版本信息
	* @description:
	* @param version
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public Integer updateDeviceVersionInfo(DeviceVersionInfoVO version);
	/**
	 * 删除设备固件版本信息
	* @description:
	* @param deviceId
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public Integer deleteDeviceVersionInfo(String deviceId);
	/**
	 * 查询设备固件版本信息
	* @description:
	* @param deviceId
	* @return(设定参数)
	* @return DeviceVersionInfoVO(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public DeviceVersionInfoVO queryDeviceVersionInfo(String deviceId);
	/**
	 * 查询设备配置(铃声/亮度)
	* @description:
	* @param deviceId
	* @return(设定参数)
	* @return DeviceConfigVO(返回值说明)
	* @author Administrator  2015-5-8
	 */
	public DeviceConfigVO queryDeviceConfig(String deviceId);
	/**
	 * 保存设备配置
	* @description:
	* @param deviceConfig
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2015-5-8
	 */
	public Integer insertDeviceConfig(DeviceConfigVO deviceConfig);

	public void savePersonWatchLog(PersonWatchLogVO personWatchLogVO);
}
