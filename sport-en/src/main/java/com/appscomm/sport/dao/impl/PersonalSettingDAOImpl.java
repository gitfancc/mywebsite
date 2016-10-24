package com.appscomm.sport.dao.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.appscomm.sport.dao.PersonalSettingDAO;
import com.appscomm.sport.vo.PersonalSetting;

@Repository
public class PersonalSettingDAOImpl implements PersonalSettingDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public int insertTarget(long personId, String property, int target) {
		String sql = "insert into personal_setting (person_id, property, value) values (?,?,?)";
		return jdbcTemplate.update(sql, personId, property, target);
	}

	@Override
	public void updateTarget(long personId, String property, int target) {
		String sql = "update personal_setting set value=? where person_id=? and property=?";
		jdbcTemplate.update(sql, target, personId, property);
	}

	@Override
	public void deleteTarget(long personId, String property) {
		String sql = "delete from personal_setting where person_id=? and property=?";
		jdbcTemplate.delete(sql, personId, property);
	}

	@Override
	public PersonalSetting getTarget(long personId, String property) {
		String sql = "select id as id, person_id as personId, property as property, value as value from personal_setting " +
				" where person_id=? and property=?";
		return jdbcTemplate.queryForObject(sql, PersonalSetting.class, personId, property);
	}

	@Override
	public List<PersonalSetting> getTarget(long personId) {
		String sql = "select id as id, person_id as personId, property as property, value as value from personal_setting  " +
				" where person_id=?";
		return jdbcTemplate.query(sql, PersonalSetting.class, personId);
	}
	
}
