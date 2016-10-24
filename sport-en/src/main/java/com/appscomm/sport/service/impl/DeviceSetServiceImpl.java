package com.appscomm.sport.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appscomm.sport.dao.DeviceSetDao;
import com.appscomm.sport.dao.ParamSportDao;
import com.appscomm.sport.model.DeviceConfigVO;
import com.appscomm.sport.model.DeviceSyncInfoVO;
import com.appscomm.sport.model.DeviceVersionInfoVO;
import com.appscomm.sport.model.PersonWatchLogVO;
import com.appscomm.sport.model.PersonWatchVO;
import com.appscomm.sport.model.WatchVO;
import com.appscomm.sport.service.DeviceSetService;
import com.appscomm.sport.utils.Tools;


@Service("deviceSetService")
public class DeviceSetServiceImpl implements DeviceSetService {
	private static Log logger = LogFactory.getLog(DeviceSetServiceImpl.class);
	@Autowired
	private DeviceSetDao deviceSetDao;
	@Autowired
	private ParamSportDao paramSportDao;
//	@Autowired
//	private SmsService smsService;
	
	
	@Override
	public WatchVO getDeviceInfo(WatchVO watch, Long personId) {
		PersonWatchVO personWatch = null;
		String watchId = null;
		if (watch != null) {
			// 查找是否存在此设备
			watchId = watch.getWatchId();
			if (watchId != null) {
				// 获取设备配置信息
				watch = deviceSetDao.getDeviceByWatchId(watch.getWatchId(), personId);
				// 获取设备电话号码相关信息
				personWatch = deviceSetDao.getPersonByWId(watchId, null);
			}
		}
		
		// 设置缺省配置
		if (watch == null) {
			watch = new WatchVO();
			watch.setWatchId(watchId);
		}
		
		// 设置已填写的电话号码
		if (personWatch != null) {
			watch.setWatchSim(personWatch.getWatchSim());
		}

		// 重置GPS开启时间
		String[] times = getGpsTime(watch.getGpsOffTime());
		watch.setGpsStartTime(times[1]);
		watch.setGpsEndTime(times[0]);
		
		return watch;
	}
	
	/**
	 * 处理GPS 开启时间
	 * 
	 * @param gpsTime
	 * @return
	 */
	private String[] getGpsTime(String gpsTime) {
		String[] times = gpsTime.split("-");
		for (int i = 0; i < times.length; i++) {
			if (times[i].startsWith("0"))
				times[i] = times[i].substring(1);
		}
		return times;
	}
	
	@Override
	public PersonWatchVO getDevice(String watchId){
		if (!(StringUtils.isEmpty(watchId))){
			return deviceSetDao.getPersonByWId(watchId, null);
		}
		return null;
	}
	
	@Override
	public List<PersonWatchVO> getDeviceList(Long personId) {
		if (personId != null) {
			return deviceSetDao.getPersonByPId(personId);
		}
		//else {
		//	return  new ArrayList<PersonWatchVO>();
		//}
		return null;
	}

	@Override
	public Integer saveDeviceInfo(WatchVO watch,  Long personId) {
		// 由于界面设计上是GPS开启时间段，而实际设备是需要设置关闭时间段，
		// 因此这里需要进行转换
		String gpsStart = watch.getGpsStartTime();
		if (gpsStart.length() < 2) {
			gpsStart = "0" + gpsStart;
		}

		String gpsEnd = watch.getGpsEndTime();
		if (gpsEnd.length() < 2) {
			gpsEnd = "0" + gpsEnd;
		}
		watch.setGpsOffTime(gpsEnd + "-" + gpsStart);
		
		//保存电话号码 
		deviceSetDao.addDeviceSim(watch.getWatchId(), watch.getWatchSim());
		// 判断在设备设置表中是否已存在此设备
		WatchVO tmpDev = deviceSetDao.getDeviceByWatchId(watch.getWatchId(), personId);
		if (tmpDev != null){
			// 是否需要更新设备参数信息
			return modifyDevice(tmpDev, watch, personId);
			
		}else{
			watch.setPersonId(personId);
			return deviceSetDao.addDevice(watch);
		}
	}
	
	@Override
	public Integer modifyDevice(WatchVO oldWatch, WatchVO newWatch, Long personId){
		Integer ret = 0;
		StringBuilder sb = new StringBuilder();
		if (!(newWatch.getHeartStatus().equals(oldWatch.getHeartStatus()))){
			sb.append("heartstatus='" + newWatch.getHeartStatus() + "',");
		}
		if (!(newWatch.getHeartInterval().equals(oldWatch.getHeartInterval()))){
			sb.append("heartinterval='" + newWatch.getHeartInterval() + "',");
		}
		if (!(newWatch.getGpsStatus().equals(oldWatch.getGpsStatus()))){
			sb.append("gpsstatus='" + newWatch.getGpsStatus() + "',");
		}
		if (!(newWatch.getGpsInterval().equals(oldWatch.getGpsInterval()))){
			sb.append("gpsinterval='" + newWatch.getGpsInterval() + "',");
		}
		if (!(newWatch.getSportStatus().equals(oldWatch.getSportStatus()))){
			sb.append("sportstatus='" + newWatch.getSportStatus() + "',");
		} 
		if (!(newWatch.getSportInterval().equals(oldWatch.getSportInterval()))){
			sb.append("sportinterval='" + newWatch.getSportInterval() + "',");
		}
		if(!(newWatch.getGpsOffTime().equals(oldWatch.getGpsOffTime()))){
			sb.append("gpsofftime='" + newWatch.getGpsOffTime() + "',");
		}
		
		String updateField = sb.toString();
		if ((updateField != null) && !("".equals(updateField))){
			ret = deviceSetDao.modifyDevice(oldWatch.getWatchId(), 
					updateField.substring(0, updateField.length()-1),
					personId);
		}
	
		return ret;
	}
	
	@Override
	public void sendSms(WatchVO watch) {
		String smsNum = watch.getWatchSim();
		if ((smsNum != null) && !(smsNum.equals(""))) {
//			String resp = smsService.sendSms(smsNum, SmsEncoder.encodeQryPlatParam());
//			logger.debug(resp);
		}
	}

	@Override
	public Integer bindDevice(Long personId, WatchVO watch, Integer isDefault) {
		PersonWatchVO pwatch = null;
		
		// 判断是否有同类型设备已被该用户所绑定
		if (StringUtils.isNotEmpty(watch.getType())){
			pwatch = deviceSetDao.getPersonByWType(personId, watch.getType());
			if (pwatch != null){
				logger.debug("当前用户已有同类型设备绑定.");
				return -2;
			}
		}
				
				
		// 判断该设备是否已被绑定
		pwatch = deviceSetDao.getPersonByWId(watch.getWatchId(), null);
		if (pwatch != null) {
			if(personId.equals(pwatch.getUserId())){
				logger.debug("当前用户腕表已绑定.");
				return 0;
			}else{
//				logger.debug("当前用户腕表已被其他用户绑定.");
//				return -1;】
				//原来的解绑绑定
				deviceSetDao.delDeviceId(pwatch.getUserId(), watch.getWatchId());
				//写入到日志记录起来
				deviceSetDao.savePersonWatchLog(new PersonWatchLogVO(pwatch.getUserId(),
						watch.getWatchId(),personId));
			}
		}
		
		if (isDefault == null || isDefault < 0){
			isDefault = 0;
		}
		// 是否有缺省的绑定设备
		pwatch = deviceSetDao.getDefaultDevice(personId);
		if (pwatch == null) {
			isDefault = 1;
		} else {
			isDefault = 0;
		}
		pwatch = deviceSetDao.getUnbindDevice(personId, watch.getWatchId());
		if (pwatch != null) {
			return deviceSetDao.modifyBindDevice(personId, watch.getWatchId(), isDefault, Tools.getCurrentDateTime());
		}
		return deviceSetDao.addDeviceBind(personId, watch, isDefault);
	}

	public PersonWatchVO bindDeviceIdEx(Long personId, String watchId, Integer isDefault, String watchType, String bindTime){
		//查询此人是否已有绑定,这里的判断在绑定前需要调用接口判断
		// 查找该设备是否已经被绑定
		PersonWatchVO	pwatch = deviceSetDao.getPersonByWId(watchId, null);
		if(pwatch!=null){
			//原来的解绑绑定
			deviceSetDao.delDeviceId(pwatch.getUserId(), watchId);
			//TODO 写入到日志记录起来
			deviceSetDao.savePersonWatchLog(new PersonWatchLogVO(pwatch.getUserId(),
					watchId,personId));
//			return null;
		}
		
		/**绑定流程**/
		// 是否是解绑后重绑定
		pwatch = deviceSetDao.getUnbindDevice(personId, watchId);
		if (pwatch != null) {
			deviceSetDao.modifyBindDevice(personId, watchId, isDefault, bindTime);
		}else{
			//添加新的绑定记录
			deviceSetDao.addDeviceId(personId, watchId, isDefault, watchType, bindTime);
		}
		pwatch  = new PersonWatchVO();
		pwatch.setActive(PersonWatchVO.STATUS_BIND);
		pwatch.setBindTime(new Timestamp(System.currentTimeMillis()));
		pwatch.setIsDefault(PersonWatchVO.IS_DEFAULT);
		pwatch.setUserId(personId);
		pwatch.setWatchId(watchId);
		pwatch.setWatchType(watchType);
		return pwatch;
	}
	
	@Override
	public Integer bindDeviceId(Long personId, String watchId, Integer isDefault, String watchType) {
		PersonWatchVO pwatch = null;
		// 查找该设备是否已经被绑定
		pwatch = deviceSetDao.getPersonByWId(watchId, null);
		if (pwatch != null) {
			if(personId.equals(pwatch.getUserId())){
				logger.debug("当前用户腕表已绑定.");
				return 0;
			}else{
				logger.debug("当前用户腕表已被其他用户绑定.");
				return -1;
			}
		}
		
		// 判断是否有同类型设备已被该用户所绑定
		if (StringUtils.isNotEmpty(watchType)){
			//判断是否有同类型设备已被该用户所绑定 (提示:" 该账号已绑定有同类型设备L28，请解绑后再操作”)
			pwatch = deviceSetDao.getPersonByWType(personId, watchType);
			if (pwatch != null){
				logger.debug("当前用户已有同类型设备绑定.");
				return -2;
			}
		}
		/*
		if (isDefault == null || isDefault < 0){
			isDefault = 0;
		}
		
		// 是否有缺省的绑定设备(用于缺省显示)
		pwatch = null;
		pwatch =  deviceSetDao.getDefaultDevice(personId);
		if (pwatch == null){
			// 如果没有缺省的绑定设备，则此次绑定作为缺省的绑定设备
			isDefault = 1;
		} else {
			isDefault = 0;
		}*/
		
		isDefault =1;
		// 是否是解绑后重绑定
		pwatch = deviceSetDao.getUnbindDevice(personId, watchId);
		if (pwatch != null) {
			return deviceSetDao.modifyBindDevice(personId, watchId, isDefault, Tools.getCurrentDateTime());
		}
				
		if (watchType == null) {
			watchType = "";
		}
		return deviceSetDao.addDeviceId(personId, watchId, isDefault, watchType, Tools.getCurrentDateTime());
	}

	@Override
	public Integer unBindDeviceId(Long personId, String watchId) {
		Integer code = 0;
		PersonWatchVO pwatch = null; 
		/*// 判断解绑的设备是否存在
		pwatch = deviceSetDao.getPersonByWId(watchId, null);
		if (pwatch == null){
			return -1; //
		}
		*/
		code = deviceSetDao.delDeviceId(personId, watchId);
		/*if (personId != null){
			// 解绑后是否仍有缺省的设备，如没有，需选定一个
			pwatch = deviceSetDao.getDefaultDevice(personId);
			if(pwatch == null){
				deviceSetDao.modifyBindDevice(personId, null, 1);
			}
		}*/
		
		return code;
	}

	@Override
	public Integer bindDeviceSim(String watchId, String watchSim) {
		return deviceSetDao.addDeviceSim(watchId, watchSim);
	}

	@Override
	public Integer unBindDeviceSim(Long personId, String watchId) {
		PersonWatchVO pwatch = null; 
		// 判断解绑的设备是否存在
		pwatch = deviceSetDao.getPersonByWId(watchId, null);
		if (pwatch == null){
			return -1; //
		}
		return deviceSetDao.delDeviceSim(personId, watchId);
	}

	@Override
	public Integer setDefaultWatch(Long personId, String watchType) {
		return deviceSetDao.setDefautDevice(personId, watchType);
	}

	@Override
	public Integer addDeviceVersionInfo(DeviceVersionInfoVO version) {
		DeviceVersionInfoVO firmVer = this.deviceSetDao.queryDeviceVersionInfo(version.getWatchId());
		//判断记录是否存在
		if(firmVer==null){
			//不存在，插入记录
			return this.deviceSetDao.insertDeviceVersionInfo(version);
		}else{
			//存在，更新记录
			version.setPreviousVersion(firmVer.getFirmwareVersion());
			return this.deviceSetDao.updateDeviceVersionInfo(version);
		}
	}

	@Override
	public DeviceVersionInfoVO getDeviceVersionInfo(String deviceId) {
		return this.deviceSetDao.queryDeviceVersionInfo(deviceId);
	}

	@Override
	public Integer addDeviceSyncInfo(DeviceSyncInfoVO syncInfo) {
		DeviceSyncInfoVO firmVer = this.deviceSetDao.queryDeviceSyncInfo(syncInfo.getUserId(), syncInfo.getWatchId());
		//判断记录是否存在
		if(firmVer==null){
			//不存在，插入记录
			return this.deviceSetDao.insertDeviceSyncInfo(syncInfo);
		}else{
			//存在，更新记录
			return this.deviceSetDao.updateDeviceSyncInfo(syncInfo);
		}
	}

	@Override
	public DeviceSyncInfoVO getDeviceSyncInfo(Long userId, String deviceId) {
		return this.deviceSetDao.queryDeviceSyncInfo(userId, deviceId);
	}

	@Override
	public Integer addDeviceConfig(DeviceConfigVO deviceConfig) {
		return this.deviceSetDao.insertDeviceConfig(deviceConfig);
	}

	@Override
	public DeviceConfigVO getDeviceConfig(String deviceId) {
		return this.deviceSetDao.queryDeviceConfig(deviceId);
	}
	
}
