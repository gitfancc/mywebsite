/**
 * Copyright appscomm.cn 2013. All rights reserved.
 *
 * @createDate 2013-9-16
 */
package com.appscomm.sport.dao;

import java.util.List;

import com.appscomm.sport.model.DistrictVO;

/**
 *  省、市、区数据操作相关dao接口  
 *	
 *  qindf create by 2013-9-16
 *
 */
public interface CityDao {
	
	/**
	 * 获取省、市、区数据列表
	 * @param districtId
	 * @return
	 */
	public List<DistrictVO> getCitys(int districtId);
	
	/**
	 * 根据名称获取省、市、区数据列表
	 * @param district
	 * @return
	 */
	public List<DistrictVO> getCitys(String district);

	/**
	 * 根据id获取省、市、区名称
	 * @param districtId
	 * @return
	 */
	public DistrictVO getNameById(int districtId);
	
	public List<DistrictVO> getCountry(int id);
}
