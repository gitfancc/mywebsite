/**
 * Copyright appscomm.cn 2013. All rights reserved.
 *
 * @createDate 2013-9-16
 */
package com.appscomm.sport.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appscomm.sport.dao.CityDao;
import com.appscomm.sport.model.DistrictVO;
import com.appscomm.sport.service.CityService;

/**
 *  省、市、区数据操作相关服务接口实现  
 *	
 *  qindf create by 2013-9-16
 *
 */
@Service("cityService")
@Transactional
public class CityServiceImpl implements CityService {
	
	@Autowired
	private CityDao cityDao;

	@Override
	public List<DistrictVO> getCitys(int districtId) {
		return cityDao.getCitys(districtId);
	}
	
	@Override
	public DistrictVO getNameById(int districtId) {
		return cityDao.getNameById(districtId);
	}

	@Override
	public DistrictVO getDistrictByName(String name) {
		List<DistrictVO> list = cityDao.getCitys(name);
		if (list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<DistrictVO> getCountry(int id) {
		return cityDao.getCountry(id);
	}
}
