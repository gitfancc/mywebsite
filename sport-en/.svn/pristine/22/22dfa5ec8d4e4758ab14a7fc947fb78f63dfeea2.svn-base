package com.appscomm.sport.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.appscomm.sport.dao.DeviceNotifyRemindDAO;
import com.appscomm.sport.model.DeviceNotifyInfoVO;
import com.appscomm.sport.model.DeviceSleepRemindInfoVO;
import com.appscomm.sport.model.DeviceStayRemindInfoVO;
import com.appscomm.sport.utils.StringUtil;

@Repository("deviceNotifyRemindDAO")
public class DeviceNotifyRemindDAOImpl  implements DeviceNotifyRemindDAO{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Integer insertNotifyInfo(DeviceNotifyInfoVO notify) {
		String sql = "insert into t_notify_stay_remind (user_id, device_id, `status`, `type`) values (:userId, :watchId, :status," +StringUtil.TYPE_REMIND_NOTIFY+") ";
		return this.jdbcTemplate.saveObjectGetSeq(sql, notify);
	}

	@Override
	public Integer updateNotifyInfo(DeviceNotifyInfoVO notify) {
		String sql = "update t_notify_stay_remind set `status`=:status where user_id=:userId and device_id=:watchId and `type`="+StringUtil.TYPE_REMIND_NOTIFY;
		return this.jdbcTemplate.updateObject(sql, notify);
	}
	
	@Override
	public Integer deleteNotifyInfo(Long userId, String deviceId) {
		String sql = "delete from t_notify_stay_remind where user_id=? and device_id=? and `type`="+StringUtil.TYPE_REMIND_NOTIFY;
		return this.jdbcTemplate.delete(sql, userId, deviceId);
	}

	@Override
	public DeviceNotifyInfoVO queryNotifyInfo(Long userId, String deviceId) {
		String sql = "select user_id as userId, device_id as watchId, `status` as `status` from t_notify_stay_remind where user_id=? and device_id=? and `type`="+StringUtil.TYPE_REMIND_NOTIFY;
		return this.jdbcTemplate.queryForObject(sql, DeviceNotifyInfoVO.class, userId, deviceId);
	}

	@Override
	public Integer insertSleepRemindInfo(DeviceSleepRemindInfoVO remind) {
		String sql = "insert into t_notify_stay_remind (user_id, device_id, start_time, end_time, `status`, `type`) values (:userId, :watchId, :startTime, :endTime, :status," +StringUtil.TYPE_REMIND_SLEEP+") ";
		return this.jdbcTemplate.saveObjectGetSeq(sql, remind);
	}

	@Override
	public Integer updateSleepRemindInfo(DeviceSleepRemindInfoVO remind) {
		String sql = "update t_notify_stay_remind set start_time=:startTime, end_time=:endTime, `status`=:status " +
				" where user_id=:userId and device_id=:watchId and `type`="+StringUtil.TYPE_REMIND_SLEEP;
		return this.jdbcTemplate.updateObject(sql, remind);
	}

	@Override
	public Integer deleteSleepRemindInfo(Long userId, String deviceId) {
		String sql = "delete from t_notify_stay_remind where user_id=? and device_id=? and `type`="+StringUtil.TYPE_REMIND_SLEEP;
		return this.jdbcTemplate.delete(sql, userId, deviceId);
	}

	@Override
	public DeviceSleepRemindInfoVO querySleepRemindInfo(Long userId, String deviceId) {
		String sql = "select user_id as userId, device_id as watchId, start_time as startTime, end_time as endTime, `status` as `status` from t_notify_stay_remind" +
				" where user_id=? and device_id=? and `type`="+StringUtil.TYPE_REMIND_SLEEP;
		return this.jdbcTemplate.queryForObject(sql, DeviceSleepRemindInfoVO.class, userId, deviceId);
	}

	@Override
	public Integer insertStayRemindInfo(DeviceStayRemindInfoVO remind) {
		String sql = "insert into t_notify_stay_remind (user_id, device_id, start_time, end_time, `interval`, `repeat`, `status`, `type`) " +
				" values (:userId, :watchId, :startTime, :endTime,:interval, :repeat, :status," +StringUtil.TYPE_REMIND_STAY+") ";
		return this.jdbcTemplate.saveObjectGetSeq(sql, remind);
	}

	@Override
	public Integer updateStayRemindInfo(DeviceStayRemindInfoVO remind) {
		String sql = "update t_notify_stay_remind set start_time=:startTime, end_time=:endTime, `interval`=:interval, `repeat`=:repeat, `status`=:status " +
				" where user_id=:userId and device_id=:watchId and `type`="+StringUtil.TYPE_REMIND_STAY;
		return this.jdbcTemplate.updateObject(sql, remind);
	}

	@Override
	public Integer deleteStayRemindInfo(Long userId, String deviceId) {
		String sql = "delete from t_notify_stay_remind where user_id=? and device_id=? and type="+StringUtil.TYPE_REMIND_STAY;
		return this.jdbcTemplate.delete(sql, userId, deviceId);
	}

	@Override
	public DeviceStayRemindInfoVO queryStayRemindInfo(Long userId, String deviceId) {
		String sql = "select user_id as userId, device_id as watchId, start_time as startTime, end_time as endTime, `interval` as `interval`, `repeat` as `repeat`, `status` as `status` from t_notify_stay_remind" +
				" where user_id=? and device_id=? and type="+StringUtil.TYPE_REMIND_STAY;
		return this.jdbcTemplate.queryForObject(sql, DeviceStayRemindInfoVO.class, userId, deviceId);
	}

}
