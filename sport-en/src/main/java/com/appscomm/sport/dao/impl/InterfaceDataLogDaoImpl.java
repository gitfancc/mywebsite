package com.appscomm.sport.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.appscomm.sport.dao.InterfaceDataLogDao;
import com.appscomm.sport.model.InterfaceDataLogVO;

@Repository("interfaceDataLogDao")
public class InterfaceDataLogDaoImpl implements InterfaceDataLogDao{

@Autowired
private JdbcTemplate jdbcTemplate;
	@Override
	public void insertDataLog(InterfaceDataLogVO log) {
		String sql = "insert t_interface_data_log (person_id, watch_id, watch_type, client_type, operator_type, operator_time) " +
				"values (:personId, :watchId, :watchType, :clientType, :operatorType, :operatorTime)";
		this.jdbcTemplate.saveObjectGetSeq(sql, log);
	}

	@Override
	public List<InterfaceDataLogVO> queryDataLog(InterfaceDataLogVO log) {
		String sql = "select person_id as personId, watch_id as watchId, watch_type as watchType, client_type as clientType," +
				" operator_type as operatorType, operator_time as operatorTime " +
				" from t_interface_data_log " +
				" where person_id=? and client_type=?";
		return this.jdbcTemplate.query(sql, InterfaceDataLogVO.class, log.getPersonId(), log.getClientType());
	}

}
