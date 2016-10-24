package com.appscomm.sport.service;

import java.util.List;

import com.appscomm.sport.model.InterfaceDataLogVO;

public interface InterfaceDataLogService {
	/**
	 * 保存接口操作日志
	* @description:
	* @param personId
	* @param watchId
	* @param watchType
	* @param clientType
	* @param operatorType(设定参数)
	* @return void(返回值说明)
	* @author Administrator  2015-1-21
	 */
	public void saveInterfaceDataLog(String personId, String watchId, String watchType, String clientType, String operatorType);
	/**
	 * 保存接口操作日志
	 * 
	* @description:
	* @param log(设定参数)
	* @return void(返回值说明)
	* @author Administrator  2015-1-21
	 */
	public void saveInterfaceDataLog(InterfaceDataLogVO log);
	/**
	 * 获取接口操作日志
	* @description:
	* @param log
	* @return(设定参数)
	* @return List<InterfaceDataLogVO>(返回值说明)
	* @author Administrator  2015-1-21
	 */
	public List<InterfaceDataLogVO> getInterfaceDataLog(InterfaceDataLogVO log);
}
