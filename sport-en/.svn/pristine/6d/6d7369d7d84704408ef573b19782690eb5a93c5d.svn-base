package com.appscomm.sport.dao.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.appscomm.sport.dao.DeviceSetDao;
import com.appscomm.sport.model.DeviceConfigVO;
import com.appscomm.sport.model.DeviceSyncInfoVO;
import com.appscomm.sport.model.DeviceVersionInfoVO;
import com.appscomm.sport.model.PersonWatchLogVO;
import com.appscomm.sport.model.PersonWatchVO;
import com.appscomm.sport.model.WatchVO;
import com.appscomm.sport.utils.StringUtil;
import com.appscomm.sport.utils.Tools;

@Repository("deviceSetDao")
public class DeviceSetDaoImpl implements DeviceSetDao {
	private static Log logger = LogFactory.getLog(DeviceSetDaoImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Resource(name = "oldJdbcTemplate")
	private OldJdbcTemplate oldJdbcTemplate;
	
	@Override
	public List<PersonWatchVO> getPersonByPId(Long personId) {
		List<PersonWatchVO> personWatch = null;
		String qrySql = "select id as id, user_id as userId, watch_id as watchId," +
				" watch_sim as watchSim, bind_time as bindTime, watch_type as watchType," +
				" active as active, is_default as isDefault " +
				" from t_personal_watch " +
				" where user_id='" + personId + "' " +
				" and active=1";
		try{
			personWatch =  this.jdbcTemplate.query(qrySql, PersonWatchVO.class);
			
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return personWatch;
	}

	@Override
	public PersonWatchVO getPersonByWId(String watchId, Long personId) {
		PersonWatchVO personWatch = null;
		StringBuffer sb = new StringBuffer();
		sb.append("select id as id, user_id as userId, watch_id as watchId,watch_sim as watchSim, " +
				" bind_time as bindTime, watch_type as watchType, active as active, is_default as isDefault" +
				" from t_personal_watch " +
				" where watch_id='");
		sb.append(watchId).append("'");
		sb.append(" and active=1 limit 1");
		
		try{
			personWatch = this.jdbcTemplate.queryForObject(sb.toString(), PersonWatchVO.class);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return personWatch;
	}

	@Override
	public PersonWatchVO getPersonByWType(Long personId, String watchType) {
		PersonWatchVO personWatch = null;
		StringBuffer sb = new StringBuffer();
		sb.append("select id as id, user_id as userId, watch_id as watchId,watch_sim as watchSim, " +
				" bind_time as bindTime, watch_type as watchType, active as active, is_default as isDefault" +
				" from t_personal_watch " +
				" where user_id='");
		sb.append(personId).append("'");
		sb.append(" and watch_type='").append(watchType).append("'");
		sb.append(" and active=1 limit 1");
		
		try{
			personWatch = this.jdbcTemplate.queryForObject(sb.toString(), PersonWatchVO.class);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return personWatch;
	}

	@Override
	public PersonWatchVO getPersonBySim(String watchSim, Long personId) {
		PersonWatchVO personWatch = null;
		String qrySql = "select id as id, user_id as userId, watch_id as watchId,watch_sim as watchSim, " +
				" bind_time as bindTime, watch_type as watchType, active as active, is_default as isDefault" +
				" from t_personal_watch " +
				" where watch_sim='" + watchSim + "' " +
				" and user_id=" + personId + 
				" and active=1 limit 1";
		try{
			personWatch = this.jdbcTemplate.queryForObject(qrySql, PersonWatchVO.class);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return personWatch;
	}
	
	@Override
	public PersonWatchVO getDefaultDevice(Long personId){
		PersonWatchVO personWatch = null;
		String qrySql = "select id as id, user_id as userId, watch_id as watchId, watch_sim as watchSim, " +
				" bind_time as bindTime, watch_type as watchType, active as active, is_default as isDefault" +
				" from t_personal_watch " +
				" where user_id=" + personId + 
				" and is_default=1 limit 1";
		personWatch = this.jdbcTemplate.queryForObject(qrySql, PersonWatchVO.class);
		
		return personWatch;
	}
	

	@Override
	public Integer setDefautDevice(Long personId, String watchType) {
		// 更新原有的缺省标志
		String updateSql = "update t_personal_watch set is_default=0 where user_id=" + personId
				+ " and is_default=1";
		this.jdbcTemplate.update(updateSql);
		
		// 设置新的缺省标志
		String updateSqlCur = "update t_personal_watch set is_default=1 where user_id=" + personId
		+ " and watch_type='" + watchType + "'"
		+ " and active=1";
		
		return this.jdbcTemplate.update(updateSqlCur);
	}

	
	@Override
	public PersonWatchVO getUnbindDevice(Long userId, String watchId){
		PersonWatchVO personWatch = null;
		String qrySql = "select id as id, user_id as userId, watch_id as watchId, watch_sim as watchSim, " +
				" bind_time as bindTime, watch_type as watchType, active as active, is_default as isDefault" +
				" from t_personal_watch " +
				" where user_id=" + userId +
				" and watch_id='" + watchId + "'" +
				" and active=0 limit 1";
		personWatch = this.jdbcTemplate.queryForObject(qrySql, PersonWatchVO.class);
		
		return personWatch;
	}
	
	@Override
	public Integer modifyBindDevice(Long userId, String watchId, Integer isDefault, String bindTime){
		String updateSql = "";
		if (watchId == null){
			updateSql = "update t_personal_watch set is_default=1, bind_time='" +bindTime+"'"+
					" where user_id=" + userId + 
					" and active=1 limit 1";
		} else {
			updateSql = "update t_personal_watch set active=1, is_default=" + isDefault + ",bind_time='" +bindTime+"'"+
				" where user_id=" + userId + 
				" and watch_id='" + watchId + "'";
		}
		return this.jdbcTemplate.update(updateSql);
	}
	
	@Override
	public Integer addDeviceId(Long personId, String watchId, Integer isDefault, String watchType, String bindTime) {
		String insertSql = "insert into t_personal_watch (user_id,watch_id, watch_sim, bind_time, watch_type, is_default) " +
				"values ("
				+ personId
				+ ",'"
				+ watchId
				+ "','"
				+ "','"
				+ bindTime
				+ "','"
				+ watchType
				+ "',"
				+ isDefault
				+ ")";

		this.jdbcTemplate.update(insertSql);
		return 0;
	}

	@Override
	public Integer addDeviceSim(String watchId, String watchSim) {
		Integer retCode = 0;
		String insertSql = "update t_personal_watch set watch_sim='" + watchSim
				+ "' where watch_id='" + watchId + "'" 
				+ " and active=1";
		retCode = this.jdbcTemplate.update(insertSql);
		return retCode;
	}

	@Override
	public Integer delDeviceId(Long personId, String watchId) {
		Integer retCode = 0;
		String delSql = "update t_personal_watch set active=0, is_default=0 " +
				" where watch_id='" + watchId + "'" +
				" and active=1";
		try{
			retCode = this.jdbcTemplate.update(delSql);
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}
		return retCode;
	}

	@Override
	public Integer delDeviceSim(Long personId, String watchId) {
		Integer retCode = 0;
		String updateSql = "update t_personal_watch set watch_sim='' " +
				 " where watch_id='" + watchId + "'" +
				 " and active=1";
		try {
			retCode = this.jdbcTemplate.update(updateSql);
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}
		return retCode;
	}

	@Override
	public Integer addDeviceBind(Long personId, WatchVO watch, Integer isDefault) {
		String insertSql = "insert into t_personal_watch (user_id,watch_id, watch_sim,bind_time,watch_type,is_default) " +
				"values ("
				+ personId + ",'"
				+ watch.getWatchId() + "','"
				+ watch.getWatchSim() + "','"
				+ Tools.getCurrentDateTime() + "','"
				+ watch.getType() + "',"
				+ isDefault + ")";
		
		this.jdbcTemplate.update(insertSql);
		
		return 0;
	}

	@Override
	public WatchVO getDeviceByWatchId(String watchId, Long personId) {
		WatchVO watch = null;
		StringBuffer sb = new StringBuffer();
		sb.append("select watch_id as watchId, heartstatus as heartStatus, sportstatus as sportStatus,"
				+ "gpsstatus as gpsStatus, heartinterval as heartInterval, sportinterval as sportInterval,"
				+ "gpsinterval as gpsInterval, gpsofftime as gpsOffTime "
				+ "from t_watch_setting"
				+ " where watch_id='");
		sb.append(watchId).append("'");
		if (personId != null){
			sb.append(" and personid=").append(personId);
		}
		sb.append(" limit 1");

		try{
			watch = this.oldJdbcTemplate.queryForObject(sb.toString(), WatchVO.class);
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}
		return watch;
	}

	@Override
	public Integer modifyDevice(String watchId, String updateField, Long personId) {
		StringBuffer sb = new StringBuffer();
		sb.append("update t_watch_setting set ");
		sb.append(updateField)	.append(" where watch_id='").append(watchId)	.append("'");
		if (personId != null){
			sb.append(" and personid=").append(personId);
		}
		
		return this.oldJdbcTemplate.update(sb.toString());
	}

	@Override
	public Integer addDevice(WatchVO device) {
		String insertSql = "insert into t_watch_setting (watch_id,heartstatus,sportstatus,gpsstatus,heartinterval,"
				+ "sportinterval,gpsinterval,gpsofftime, personid, watchtype) "
				+ "values (:watchId,:heartStatus,:sportStatus,:gpsStatus,:heartInterval,:sportInterval,"
				+ ":gpsInterval,:gpsOffTime,:personId, :type)";
		return this.oldJdbcTemplate.saveObject(insertSql, device);
	}

	@Override
	public Integer insertDeviceSyncInfo(DeviceSyncInfoVO syncInfo) {
		String sql = "insert into t_notify_stay_remind (user_id, device_id, `status`, `type`) values (:userId, :watchId, :status, "+StringUtil.TYPE_DEVICE_SYNC+")";
		return this.jdbcTemplate.saveObjectGetSeq(sql, syncInfo);
	}

	@Override
	public Integer updateDeviceSyncInfo(DeviceSyncInfoVO syncInfo) {
		String sql = "update t_notify_stay_remind set `status`=:status where user_id=:userId and device_id=:watchId and `type`="+ StringUtil.TYPE_DEVICE_SYNC;
		return this.jdbcTemplate.updateObject(sql, syncInfo);
	}

	@Override
	public Integer deleteDeviceSyncInfo(Long userId, String deviceId) {
		String sql = "delete from t_notify_stay_remind where user_id=? and device_id=?  and `type`="+ StringUtil.TYPE_DEVICE_SYNC;
		return this.jdbcTemplate.delete(sql, userId, deviceId);
	}

	@Override
	public DeviceSyncInfoVO queryDeviceSyncInfo(Long userId, String deviceId) {
		String sql = "select  user_id as userId, device_id as watchId, `status` as `status` from t_notify_stay_remind " +
				" where user_id=? and device_id=? and `type`="+ StringUtil.TYPE_DEVICE_SYNC;
		return this.jdbcTemplate.queryForObject(sql, DeviceSyncInfoVO.class, userId, deviceId);
	}

	@Override
	public Integer insertDeviceVersionInfo(DeviceVersionInfoVO version) {
		String sql = "insert into t_device_version (device_id, firmware_name, firmware_version, previous_version, udid) values (:watchId, :firmwareName, :firmwareVersion, :previousVersion, :udid)";
		return this.jdbcTemplate.saveObjectGetSeq(sql, version);
	}

	@Override
	public Integer updateDeviceVersionInfo(DeviceVersionInfoVO version) {
		String sql = "update t_device_version set firmware_name=:firmwareName, firmware_version=:firmwareVersion, previous_version=:previousVersion, udid=:udid " +
				" where device_id=:watchId";
		return this.jdbcTemplate.updateObject(sql, version);
	}

	@Override
	public Integer deleteDeviceVersionInfo(String deviceId) {
		String sql = "delete from t_device_version where device_id=?";
		return this.jdbcTemplate.delete(sql, deviceId);
	}

	@Override
	public DeviceVersionInfoVO queryDeviceVersionInfo(String deviceId) {
		String sql = "select device_id as watchId, firmware_name as firmwareName, firmware_version as firmwareVersion, previous_version as previousVersion, udid as udid " +
				" from t_device_version  where device_id=?";
		return this.jdbcTemplate.queryForObject(sql, DeviceVersionInfoVO.class, deviceId);
	}

	@Override
	public DeviceConfigVO queryDeviceConfig(String deviceId) {
		String sql = "select device_id as watchId,  brightness as brightness, ring_mode as ringMode, volume as volume  " +
				" from t_device_config where device_id=? ";
		return this.jdbcTemplate.queryForObject(sql, DeviceConfigVO.class, deviceId);
	}

	@Override
	public Integer insertDeviceConfig(DeviceConfigVO deviceConfig) {
		String sql = "insert into t_device_config (device_id, brightness, ring_mode, volume) values (:watchId,  :brightness, :ringMode, :volume)";
		return this.jdbcTemplate.saveObjectGetSeq(sql, deviceConfig);
	}
	public void savePersonWatchLog(PersonWatchLogVO personWatchLogVO){
		String sql = "insert into t_person_watch_log (person_id, watch_id, unbind_time, operator_id) values (:personId,  :watchId, :unBindTime, :operatorId)";
		jdbcTemplate.saveObjectGetSeq(sql, personWatchLogVO); 
	}
}
