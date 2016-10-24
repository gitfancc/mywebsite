/**
 * Copyright appscomm.cn 2013. All rights reserved.
 *
 * @createDate 2013-9-16
 */
package com.appscomm.sport.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appscomm.sport.dao.PlaceLocateDao;
import com.appscomm.sport.model.PlaceLocateVO;
import com.appscomm.sport.service.PlaceLocateService;
import com.appscomm.sport.utils.DateUtils;

;

/**
 * 运动数据
 * 
 * kuangzhenming create by 2013-9-16
 * 
 */
@Service("placeLocateService")
@Transactional
public class PlaceLocateServiceImpl implements PlaceLocateService {

	@Autowired
	private PlaceLocateDao placeLocateDao;

	@Override
	public List<PlaceLocateVO> getList(String watchId, String startTime,	String endTime, int currentPageIndex, int pageSize, Long personId, String watchType) {

		return placeLocateDao.getList(watchId, startTime, endTime,	currentPageIndex, pageSize, personId, watchType);
	}

	@Override
	public int getCount(String watchId, String startTime, String endTime, Long personId, String watchType) {
		return placeLocateDao.getCount(watchId, startTime, endTime, personId, watchType);
	}

	public List<PlaceLocateVO> getMapList(String watchId, String time, Long personId, String watchType){
		if (StringUtils.isEmpty(time)){
			time = DateUtils.getCurrentDate();
		}
		String[] timeSplit = time.split(" ");
		String startTime = timeSplit[0] + " 00:00:00";
		String endTime  = timeSplit[0] + " 23:59:59";
				
		return placeLocateDao.getMapList(watchId, startTime, endTime, personId, watchType);
	}
}
