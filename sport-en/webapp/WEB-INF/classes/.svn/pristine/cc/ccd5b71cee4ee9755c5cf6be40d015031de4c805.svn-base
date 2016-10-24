package com.appscomm.sport.service;

import java.util.List;

import com.appscomm.sport.vo.PersonalSetting;


public interface PersonalSettingService {
	/**设置用户睡眠目标
	 * @param personId 用户id
	 * @param target 睡眠目标 单位分钟
	 * @return
	 */
	PersonalSetting updateSleepTarget(long personId,int target);
	
	/**获取用户睡眠目标
	 * @param personId
	 * @return
	 */
	PersonalSetting getSleepTarget(long personId);
	
	/**
	 * 设置用户步数目标
	* @description:
	* @param personId
	* @param target
	* @return(设定参数)
	* @return PersonalSetting(返回值说明)
	 */
	PersonalSetting updateStepsTarget(long personId, int target);
	/**
	 * 设置用户距离目标
	* @description:
	* @param personId
	* @param target
	* @return(设定参数)
	* @return PersonalSetting(返回值说明)
	 */
	PersonalSetting updateDistanceTarget(long personId, int target);
	/**
	 * 设置用户活动时间目标
	* @description:
	* @param personId
	* @param target
	* @return(设定参数)
	* @return PersonalSetting(返回值说明)
	 */
	PersonalSetting updateActiveTimeTarget(long personId, int target);
	/**
	 * 设置用户卡路里目标
	* @description:
	* @param personId
	* @param target
	* @return(设定参数)
	* @return PersonalSetting(返回值说明)
	 */
	PersonalSetting updateCaloriesTarget(long personId, int target);
	/**
	 * 获取用户设置的目标
	* @description:
	* @param personId
	* @param target
	* @return(设定参数)
	* @return PersonalSetting(返回值说明)
	 */
	List<PersonalSetting> getPersonalTarget(long personId);
	/**
	 * 设置用户目标值
	* @description:
	* @param personId
	* @param stepsTarget
	* @param distanceTarget
	* @param activeTimeTarget
	* @param caloriesTarget
	* @param sleepTarget
	* @return(设定参数)
	* @return List<PersonalSetting>(返回值说明)
	* @author Administrator  2014-6-23
	 */
	List<PersonalSetting> updatePersonalTarget(long personId, int stepsTarget,	int distanceTarget, int activeTimeTarget, int caloriesTarget,int sleepTarget );
}
