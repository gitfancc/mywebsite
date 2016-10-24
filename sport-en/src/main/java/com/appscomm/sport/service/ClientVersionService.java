package com.appscomm.sport.service;

import com.appscomm.sport.model.ClientVersionVO;

public interface ClientVersionService {
	/**
	 * 添加客户端版本记录
	* @description:
	* @param version
	* @return(设定参数)
	* @return Integer(返回值说明)
	* @author Administrator  2015-1-21
	 */
	public Integer addClientVersion(ClientVersionVO version);
	/**
	 * 获取客户端版本记录
	* @description:
	* @param version
	* @return(设定参数)
	* @return ClientVersionVO(返回值说明)
	* @author Administrator  2015-1-21
	 */
	public ClientVersionVO getClientVersion(ClientVersionVO version);
}
