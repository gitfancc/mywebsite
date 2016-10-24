/**
 * Copyright appscomm.cn 2013. All rights reserved.
 *
 * @createDate 2013-9-16
 */
package com.appscomm.sport.service;

import java.util.List;

import com.appscomm.sport.model.DistrictVO;

/**
 *  省、市、区数据操作相关服务接口  
 *	
 *  qindf create by 2013-9-16
 *
 */
public interface CityService {
	
	/**
	 * 获取省、市、区数据列表
	 * @param districtId
	 * @return
	 */
	public List<DistrictVO> getCitys(int districtId);

	/**
	 * 根据id获取省、市、区名称
	 * 
	 * @param districtId
	 * @return
	 */
	public DistrictVO getNameById(int districtId);
	
	/**
	 * 根据名称获取district 的详细信息
	 * 
	 * @param name
	 * @return
	 */
	public DistrictVO getDistrictByName(String name);
	
	/**
	 * 获取国家信息
	* @description:
	* @param id
	* @return(设定参数)
	* @return List<DistrictVO>(返回值说明)
	* @author Administrator  2014-6-17
	 */
	public List<DistrictVO> getCountry(int id);
}
