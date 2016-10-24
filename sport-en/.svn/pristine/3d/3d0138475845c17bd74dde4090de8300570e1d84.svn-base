package com.appscomm.sport.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.appscomm.sport.dao.CommonDao;
import com.appscomm.sport.model.SuggestContentVO;
import com.appscomm.sport.model.VersionCheckVO;

@Repository("commonDao")
public class CommonDaoImpl implements CommonDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public VersionCheckVO qryVersion(String deviceName, String deviceType){
		StringBuilder sbd = new StringBuilder();
		sbd.append("select devicename,version,updateurl,updatetype,updatemessage,FileCRCSize");
		sbd.append(" from t_version");
		
		if(deviceName!=null && deviceType!=null){
			sbd.append(" where devicename=? and devicetype=?  order by time desc limit 1");
			return jdbcTemplate.queryForObject(sbd.toString(), VersionCheckVO.class, deviceName, deviceType);
		}else if (deviceName!=null){
			sbd.append(" where devicename=?  order by time desc limit 1");
			return jdbcTemplate.queryForObject(sbd.toString(), VersionCheckVO.class, deviceName);
		}else if (deviceType!=null){
			sbd.append(" where devicetype=?  order by time desc limit 1");
			return jdbcTemplate.queryForObject(sbd.toString(), VersionCheckVO.class, deviceType);
		}
		return null;
	}
	
	public int setSuggestContent(SuggestContentVO suggest){
		StringBuilder sbd = new StringBuilder();
		sbd.append("insert into t_suggest_content(userid,watchid,time,clienttype,content)");
		sbd.append(" values(:userid, :watchid,now(),:clienttype,:content)");
		return jdbcTemplate.saveObjectGetSeq(sbd.toString(), suggest);
	}
	
	public int savePicUrl(String userid, String url){
		StringBuilder sbd = new StringBuilder();
		sbd.append("update t_personal_info set img_url=?");
		sbd.append(" where id=?");
		return jdbcTemplate.update(sbd.toString(), url, userid);		
	}

	@Override
	public VersionCheckVO getFirmware(String deviceType, String deviceName) {
		StringBuilder sbd = new StringBuilder();
		sbd.append("select devicename,version,updateurl,updatetype,updatemessage,FileCRCSize");
		sbd.append(" from t_version where ");
		if(deviceType.equals("L30")){
			sbd.append(" updatetype=30 ");
		}else{
			sbd.append(" updatetype=1 ");
		}
		
		if(deviceName!=null){
			sbd.append(" and devicename='").append(deviceName).append("' ");
		}
		sbd.append(" order by version desc limit 1");
		
		return jdbcTemplate.queryForObject(sbd.toString(), VersionCheckVO.class);
	}
}
