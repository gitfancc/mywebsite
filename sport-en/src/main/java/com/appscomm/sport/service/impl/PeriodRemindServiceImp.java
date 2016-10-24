package com.appscomm.sport.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appscomm.sport.dao.PeriodResremindDao;
import com.appscomm.sport.model.PeriodResmindVO;
import com.appscomm.sport.service.PeriodRemindService;

@Service("PeriodRemindService")
public class PeriodRemindServiceImp implements PeriodRemindService {
	@Autowired
	private PeriodResremindDao periodDao;
	@Override
	public List<PeriodResmindVO> qryByKey(String watchid, long userid) {
		// TODO Auto-generated method stub
		return periodDao.qryByKey(watchid, userid);
	}

	@Override
	public boolean updPeriodResmind(PeriodResmindVO record) {
		// TODO Auto-generated method stub
		return periodDao.updPeriodResmind(record);
	}

	@Override
	public boolean updMoreSetting(PeriodResmindVO record) {
		// TODO Auto-generated method stub
		return periodDao.updMoreSetting(record);
	}

}
