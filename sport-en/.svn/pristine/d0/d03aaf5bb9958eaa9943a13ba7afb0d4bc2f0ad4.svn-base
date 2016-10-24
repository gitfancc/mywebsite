package com.appscomm.sport.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appscomm.sport.dao.PersonalSettingDAO;
import com.appscomm.sport.service.PersonalSettingService;
import com.appscomm.sport.vo.PersonalSetting;

@Service
public class PersonalSettingServiceImpl implements PersonalSettingService {
	protected static final String SLEEP_TARGET_PROPERTY = "sleep_target";
	protected static final String STEPS_TARGET_PROPERTY = "steps_target";
	protected static final String DISTANCE_TARGET_PROPERTY = "distance_target";
	protected static final String ACTIVE_TARGET_PROPERTY = "active_target";
	protected static final String CALORIES_TARGET_PROPERTY = "calories_target";

	    @Autowired
	    private PersonalSettingDAO personalSettingDAO;
	@Override
	public PersonalSetting updateSleepTarget(long personId, int target) {
		return updateTarget(personId, SLEEP_TARGET_PROPERTY, target);
	}

	@Override
	public PersonalSetting getSleepTarget(long personId) {
		return personalSettingDAO.getTarget(personId, SLEEP_TARGET_PROPERTY);
	}

	@Override
	public PersonalSetting updateStepsTarget(long personId, int target) {
		return updateTarget(personId, STEPS_TARGET_PROPERTY, target);
	}

	@Override
	public PersonalSetting updateDistanceTarget(long personId, int target) {
		return updateTarget(personId, DISTANCE_TARGET_PROPERTY, target);
	}

	@Override
	public PersonalSetting updateActiveTimeTarget(long personId, int target) {
		return updateTarget(personId, ACTIVE_TARGET_PROPERTY, target);
	}

	@Override
	public PersonalSetting updateCaloriesTarget(long personId, int target) {
		return updateTarget(personId, CALORIES_TARGET_PROPERTY, target);
	}

	@Override
	public List<PersonalSetting> getPersonalTarget(long personId) {
		return personalSettingDAO.getTarget(personId);
	}

	@Override
	public List<PersonalSetting> updatePersonalTarget(long personId, int stepsTarget, int distanceTarget, int activeTimeTarget, int caloriesTarget, int sleepTarget) {
		updateTarget(personId, SLEEP_TARGET_PROPERTY, sleepTarget);
		updateTarget(personId, STEPS_TARGET_PROPERTY, stepsTarget);
		updateTarget(personId, DISTANCE_TARGET_PROPERTY, distanceTarget);
		updateTarget(personId, ACTIVE_TARGET_PROPERTY, activeTimeTarget);
		updateTarget(personId, CALORIES_TARGET_PROPERTY, caloriesTarget);
		
		return personalSettingDAO.getTarget(personId);
	}

	private PersonalSetting updateTarget(long personId,String property, int target){
		PersonalSetting ps = personalSettingDAO.getTarget(personId, property);
		if(ps != null){
			ps.setValue(target+"");
			personalSettingDAO.updateTarget(personId, property, target);
		}else{
			ps = new PersonalSetting();
			int id = personalSettingDAO.insertTarget(personId, property, target);
			ps.setId((long)id);
			ps.setPersonId(personId);
			ps.setProperty(property);
			ps.setValue(target+"");
			//ps = personalSettingDAO.getTarget(personId, property);
		}
		return ps;
	}
}
