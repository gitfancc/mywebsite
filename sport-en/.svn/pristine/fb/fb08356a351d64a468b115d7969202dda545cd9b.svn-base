package com.appscomm.sport.service;

import java.util.List;
import java.util.Map;

import com.appscomm.sport.model.RemaindVO;

/**
 * 运动数据搜索
 * 
 * @author kuangzhenming
 * 
 */
public interface RemaindService{

	/**
	 * 获取提醒列表
	 * 
	 * @param watchId
	 * @return
	 */
	public List<RemaindVO> getList(Long userId, String watchId);

	/**
	 * 删除指定设备，指点时间的提醒
	 * 
	 * @param watchId
	 * @param time
	 * @return
	 */
	public int delete(String userId,String watchId, List<Map<String, String>> ids);

	/**
	 * 添加一条提醒
	 * 
	 * @param dao
	 * @return
	 */
	public int add(RemaindVO vo);
	/**
	 * 是否存在提醒
	 * @param userId
	 * @param watchId
	 * @param time
	 * @return
	 */
	public boolean exist(String userId,String watchId, String time);
	/**
	 * 修改提醒状态
	* @description:
	* @param remindId
	* @param status
	* @return(设定参数)
	* @return int(返回值说明)
	 */
	public int modifyStatus(Long remindId, Integer status);
	
	/**
	 * 修改提醒信息
	* @description:
	* @param vo
	* @return(设定参数)
	* @return int(返回值说明)
	 */
	public int update(RemaindVO vo);
	/**
	 * 删除用户的提醒设置
	* @description:
	* @param userId
	* @param watchId
	* @return(设定参数)
	* @return int(返回值说明)
	* @author Administrator  2014-7-31
	 */
	public int deleteRemind(Long userId, String watchId);
}
