package com.appscomm.sport.service;

import java.util.List;

import com.appscomm.sport.model.DeviceConfigVO;
import com.appscomm.sport.model.DeviceSyncInfoVO;
import com.appscomm.sport.model.DeviceVersionInfoVO;
import com.appscomm.sport.model.ParamSportVO;
import com.appscomm.sport.model.PersonWatchVO;
import com.appscomm.sport.model.WatchVO;


/**
 * 设备参数设置相关数据操作接口
 * 
 * @author Administrator
 *
 */
public interface DeviceSetService {
	/**
	 * 初始化设备信息。如果设备已存在，则使用已配置的数据； 不存在则提供默认数据
	 * 
	 * @param watch
	 * @return
	 */
	public WatchVO getDeviceInfo(WatchVO watch, Long personId);
	/**
	 * 根据设备机器码获取设备绑定信息
	 * 
	 * @param watchId
	 * @return
	 */
	public PersonWatchVO getDevice(String watchId);
	/**
	 * 根据personid获取用户下所有的设备
	 * 
	 * @param personId
	 * @return
	 */
	public List<PersonWatchVO> getDeviceList(Long personId);
	/**
	 * 保存设备配置参数
	 * 
	 * @param device
	 * @return
	 */
	public Integer saveDeviceInfo(WatchVO watch, Long personId);
	/**
	 * 修改已设备配置参数
	 * 
	 * @param oldWatch
	 * @param newWatch
	 * @return
	 */
	public Integer modifyDevice(WatchVO oldWatch, WatchVO newWatch, Long personId);
	/**
	 * 发送短息
	 * 
	 * @param watch
	 */
	public void sendSms(WatchVO watch);
	/**
	 * 保存新增设备绑定信息
	 * 
	 * @param personId
	 * @param watch
	 * @param isDefault
	 * @return
	 */
	public Integer bindDevice(Long personId, WatchVO watch, Integer isDefault);
	/**
	 * 绑定设备机器号
	 * 
	 * @param personId
	 * @param watchId
	 * @param isDefault
	 * 
	 * @return
	 */
	public Integer bindDeviceId(Long personId, String watchId, Integer isDefault,  String watchType);
	public PersonWatchVO bindDeviceIdEx(Long personId, String watchId, Integer isDefault, String watchType, String bindTime);
	/**
	 * 解绑定设备机器号
	 * 
	 * @param personId
	 * @param watchId
	 * @return
	 */
	public Integer unBindDeviceId(Long personId, String watchId);
	/**
	 * 绑定设备点好号码
	 * 
	 * @param watchId
	 * @param watchSim
	 * @return
	 */
	public Integer bindDeviceSim(String watchId, String watchSim);
	/**
	 * 解绑定设备电话号码
	 * 
	 * @param personId
	 * @param watchId
	 * @return
	 */
	public Integer unBindDeviceSim(Long personId, String watchId);
	
	/**
	 * 设置缺省的设备
	 * 
	 * @param personId
	 * @param watchType
	 * @return
	 */
	public Integer setDefaultWatch(Long personId, String watchType);
	
	/**
	 * 添加设备固件版本信息
	* @description:
	* @param version
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public Integer addDeviceVersionInfo(DeviceVersionInfoVO version);
	/**
	 * 获取设备版本信息
	* @description:
	* @param deviceId
	* @return(设定参数)
	* @return DeviceVersionInfoVO(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public DeviceVersionInfoVO getDeviceVersionInfo( String deviceId);
	/**
	 * 添加设备同步信息
	* @description:
	* @param syncInfo
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public Integer addDeviceSyncInfo(DeviceSyncInfoVO syncInfo);
	/**
	 * 获取设备同步信息
	* @description:
	* @param userId
	* @param deviceId
	* @return(设定参数)
	* @return DeviceSyncInfoVO(返回值说明)
	* @author Administrator  2014-12-11
	 */
	public DeviceSyncInfoVO getDeviceSyncInfo(Long userId, String deviceId);
	/**
	 * 保存设备配置(亮度/铃声)
	* @description:
	* @param deviceConfig
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2015-5-8
	 */
	public Integer addDeviceConfig(DeviceConfigVO deviceConfig);
	/**
	 * 查询设备配置
	* @description:
	* @param deviceId
	* @return(设定参数)
	* @return DeviceConfigVO(返回值说明)
	* @author Administrator  2015-5-8
	 */
	public DeviceConfigVO getDeviceConfig(String deviceId);
	
}
