/**
 * Copyright appscomm.cn 2013. All rights reserved.
 *
 * @createDate 2013-9-16
 */
package com.appscomm.sport.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.appscomm.sport.dao.CityDao;
import com.appscomm.sport.model.DistrictVO;

/**
 *  省、市、区数据操作相关dao接口实现  
 *	
 *  qindf create by 2013-9-16
 *
 */
@Repository("cityDao")
public class CityDaoImpl implements CityDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<DistrictVO> getCitys(int districtId) {
		if(districtId == 0)
			return jdbcTemplate.query("SELECT DISTRICT_ID as districtId,NAME FROM t_district WHERE PARENT_DISTRICT_ID = ? ORDER BY DISTRICT_ID", DistrictVO.class, districtId);
		else
			return jdbcTemplate.query("SELECT t.DISTRICT_ID as districtId,t.NAME FROM t_district t,t_district a WHERE t.PARENT_DISTRICT_ID = a.DISTRICT_ID AND t.PARENT_DISTRICT_ID = ? ORDER BY t.DISTRICT_ID", DistrictVO.class, districtId);
	}

	@Override
	public List<DistrictVO> getCitys(String district) {
		return jdbcTemplate.query("SELECT DISTRICT_ID as districtId, NAME as name FROM t_district WHERE NAME = ? ", DistrictVO.class, district);
	}

	@Override
	public DistrictVO getNameById(int districtId) {
		return jdbcTemplate.queryForObject("SELECT DISTRICT_ID as districtId, NAME as name FROM t_district WHERE DISTRICT_ID = ? ", DistrictVO.class, districtId);
	}

	@Override
	public List<DistrictVO> getCountry(int id) {
		if(id==0){
			return jdbcTemplate.query("select  id as districtId,country as name from  country_zones_code", DistrictVO.class);
		}else {
			 jdbcTemplate.query("select  id as districtId,country as name from  country_zones_code where  id = ?", DistrictVO.class, id);
		}
		return null;
	}

}
