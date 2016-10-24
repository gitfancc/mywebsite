package com.appscomm.sport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appscomm.sport.dao.DeviceNotifyRemindDAO;
import com.appscomm.sport.model.DeviceNotifyInfoVO;
import com.appscomm.sport.model.DeviceSleepRemindInfoVO;
import com.appscomm.sport.model.DeviceStayRemindInfoVO;
import com.appscomm.sport.service.DeviceNotifyRemindService;

@Service("deviceNotifyRemindService")
public class DeviceNotifyRemindServiceImpl implements DeviceNotifyRemindService {
	@Autowired
	private DeviceNotifyRemindDAO deviceNotifyRemindDAO;
	
	@Override
	public Integer addDeviceNotifyInfo(DeviceNotifyInfoVO notify) {
		DeviceNotifyInfoVO dni = this.deviceNotifyRemindDAO.queryNotifyInfo(notify.getUserId(), notify.getWatchId());
		//判断记录是否存在
		if(dni == null){
			//不存在则插入记录
			return this.deviceNotifyRemindDAO.insertNotifyInfo(notify);
		}else{
			//存在则更新记录
			return this.deviceNotifyRemindDAO.updateNotifyInfo(notify);
		}
	}
	
	@Override
	public void addDeviceNotifyStatus(DeviceNotifyInfoVO notify){
		this.deviceNotifyRemindDAO.insertNotifyInfo(notify);
	}
	
	@Override
	public void updateDeviceNotifyStatus(DeviceNotifyInfoVO notify){
		this.deviceNotifyRemindDAO.updateNotifyInfo(notify);
	}
	
	@Override
	public DeviceNotifyInfoVO getDeviceNotifyInfo(Long userId, String deviceId) {
		return this.deviceNotifyRemindDAO.queryNotifyInfo(userId, deviceId);
	}

	@Override
	public Integer addSleepRemindInfo(DeviceSleepRemindInfoVO remind) {
		DeviceSleepRemindInfoVO dni = this.deviceNotifyRemindDAO.querySleepRemindInfo(remind.getUserId(), remind.getWatchId());
		//判断记录是否存在
		if(dni == null){
			//不存在则插入记录
			return this.deviceNotifyRemindDAO.insertSleepRemindInfo(remind);
		}else{
			//存在则更新记录
			return this.deviceNotifyRemindDAO.updateSleepRemindInfo(remind);
		}
	}

	@Override
	public DeviceSleepRemindInfoVO getSleepRemindInfo(Long userId, String deviceId) {
		return this.deviceNotifyRemindDAO.querySleepRemindInfo(userId, deviceId);
	}

	@Override
	public Integer addStayRemindInfo(DeviceStayRemindInfoVO remind) {
		DeviceStayRemindInfoVO dni = this.deviceNotifyRemindDAO.queryStayRemindInfo(remind.getUserId(), remind.getWatchId());
		//判断记录是否存在
		if(dni == null){
			//不存在则插入记录
			return this.deviceNotifyRemindDAO.insertStayRemindInfo(remind);
		}else{
			//存在则更新记录
			return this.deviceNotifyRemindDAO.updateStayRemindInfo(remind);
		}
	}

	@Override
	public DeviceStayRemindInfoVO getStayRemindInfo(Long userId, String deviceId) {
		return this.deviceNotifyRemindDAO.queryStayRemindInfo(userId, deviceId);
	}

}
