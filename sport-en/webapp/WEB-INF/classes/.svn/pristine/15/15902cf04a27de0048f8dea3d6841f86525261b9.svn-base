package com.appscomm.sport.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appscomm.sport.dao.InterfaceDataLogDao;
import com.appscomm.sport.model.InterfaceDataLogVO;
import com.appscomm.sport.service.InterfaceDataLogService;
import com.appscomm.sport.utils.DateUtils;

@Service("interfaceDataLogService")
public class InterfaceDataLogServiceImpl implements InterfaceDataLogService{
	@Autowired
	private InterfaceDataLogDao interfaceDataLogDao;
	@Override
	public void saveInterfaceDataLog(String personId, String watchId, String watchType, String clientType, String operatorType) {
		/*InterfaceDataLogVO log = new InterfaceDataLogVO();
		log.setClientType(clientType);
		log.setOperatorTime(DateUtils.getCurrentDate());
		log.setOperatorType(operatorType);
		log.setPersonId(personId);
		log.setWatchId(watchId);
		log.setWatchType(watchType);
		this.interfaceDataLogDao.insertDataLog(log);*/
	}

	@Override
	public void saveInterfaceDataLog(InterfaceDataLogVO log) {
		this.interfaceDataLogDao.insertDataLog(log);
	}

	@Override
	public List<InterfaceDataLogVO> getInterfaceDataLog(InterfaceDataLogVO log) {
		return this.interfaceDataLogDao.queryDataLog(log);
	}

	
}
