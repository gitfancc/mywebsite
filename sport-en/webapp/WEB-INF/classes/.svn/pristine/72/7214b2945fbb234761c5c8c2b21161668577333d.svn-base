package com.appscomm.sport.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.appscomm.sport.dao.PeriodResremindDao;
import com.appscomm.sport.model.PeriodResmindVO;
import com.appscomm.sport.model.RemaindVO;

@Repository("PeriodResremindDao")
public class PeriodResremindImp implements PeriodResremindDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<PeriodResmindVO> qryByKey(String watchid, long userid) {
		// TODO Auto-generated method stub
		String select = "select userid,watchid,periodonoff1,periodonoff2,periodonoff3,periodonoff4,period1,period2,period3,period4,remindonoff,calonoff,steponoff,targetonoff from t_period_remind";
		String where = " where userid=? and watchid=?";
		String sql = select + where;
		return jdbcTemplate.query(sql, PeriodResmindVO.class, userid, watchid);
	}

	@Override
	public boolean updPeriodResmind(PeriodResmindVO record) {
		// TODO Auto-generated method stub
		String update = "update t_period_remind set userid=:userid,watchid=:watchid,periodonoff1=:periodonoff1,periodonoff2=:periodonoff2,periodonoff3=:periodonoff3,periodonoff4=:periodonoff4,period1=:period1,period2=:period2,period3=:period3,period4=:period4";
		String where = " where userid=:userid and watchid=:watchid";
		String sql = update + where;
		int ret = jdbcTemplate.updateObject(sql, record);
		if(ret == 0)
		{
			String insert ="insert into t_period_remind(userid,watchid,periodonoff1,periodonoff2,periodonoff3,periodonoff4,period1,period2,period3,period4)";
			insert += "values(:userid, :watchid,:periodonoff1,:periodonoff2,:periodonoff3,:periodonoff4,:period1,:period2,:period3,:period4)";
			ret = jdbcTemplate.saveObject(insert, record);
		}
		return ret > 0;
	}
	
	@Override
	public boolean updMoreSetting(PeriodResmindVO  record){
		String update = "update t_period_remind set remindonoff=:remindonoff,calonoff=:calonoff,steponoff=:steponoff,targetonoff=:targetonoff";
		String where = " where userid=:userid and watchid=:watchid";
		String sql = update + where;
		int ret = jdbcTemplate.updateObject(sql, record);
		if(ret == 0)
		{
			String insert ="insert into t_period_remind(userid,watchid,remindonoff,calonoff,steponoff,targetonoff)";
			insert += "values(:userid,:watchid,:remindonoff,:calonoff,:steponoff,:targetonoff)";
			ret = jdbcTemplate.saveObject(insert, record);			
		}
		return ret > 0;
	}

}
