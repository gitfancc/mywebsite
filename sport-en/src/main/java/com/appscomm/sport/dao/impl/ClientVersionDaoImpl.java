package com.appscomm.sport.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.appscomm.sport.dao.ClientVersionDao;
import com.appscomm.sport.model.ClientVersionVO;

@Repository("clientVersionDao")
public class ClientVersionDaoImpl implements ClientVersionDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public Integer insertClientVersion(ClientVersionVO version) {
		String sql = "insert into t_client_version(person_id, watch_id, watch_type, client_name, client_type, install_version,previous_version, install_time) values " +
				"(:personId, :watchId, :watchType, :clientName, :clientType, :installVersion, :previousVersion, :installTime)";
		return this.jdbcTemplate.saveObjectGetSeq(sql, version);
	}

	@Override
	public void updateClientVersion(ClientVersionVO version) {
		String sql = "update t_client_version set install_version=:installVersion, previous_version=:previousVersion, install_time=:installTime " +
				" where sid=:sid";
		this.jdbcTemplate.updateObject(sql, version);
	}

	@Override
	public ClientVersionVO queryClientVersion(ClientVersionVO version) {
		String sql = "select sid as sid, person_id as personId, watch_id as watchId, watch_type as watchType, client_name as clientName, client_type as clientType, " +
				"install_version as installVersion, previous_version as previousVersion, install_time as installTime" +
				" from t_client_version " +
				" where person_id=?  and client_name=?";
		
		return this.jdbcTemplate.queryForObject(sql, ClientVersionVO.class, version.getPersonId(), version.getClientName());
	}

}
