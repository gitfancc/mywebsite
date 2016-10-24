package com.appscomm.sport.service;

import java.util.Map;

import com.appscomm.sport.model.SuggestContentVO;
import com.appscomm.sport.model.VersionCheckVO;

public interface CommonService {
	/**
	 * 软硬件更新版本信息查询
	 * @param kv
	 * @return
	 */
	public Object request(Map<String, Object> kv);

	/**
	 * 
	 * @param suggest
	 * @return 返回插入t_suggest_content表的ID字段值(自动增长）
	 */
	public int suggestContent(SuggestContentVO suggest);

	/**
	 * 保存16进制图片数据
	 * @param hexValue
	 * @return 保存是否成功
	 */
	Boolean savePicData(String userId, String hexValue, String filePath);
	/**
	 * 根据userid获取图片数据
	 * @param userId 用户id
	 * @param ifSmall是否小图，1为小图
	 * @return
	 */
	String getPicData(String userId, boolean ifSmall);
	
	/**
	 * 获取服务器上存放的固件信息
	* @description:
	* @param deviceType
	* @param deviceName
	* @return(设定参数)
	* @return VersionCheckVO(返回值说明)
	* @author Administrator  2014-10-29
	 */
	VersionCheckVO getFirmware(String deviceType, String deviceName);
	
}
