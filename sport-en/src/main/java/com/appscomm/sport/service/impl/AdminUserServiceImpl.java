/**
 * Copyright appscomm 2014. All rights reserved.
 */

package com.appscomm.sport.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.appscomm.sport.dao.AdminUserDao;
import com.appscomm.sport.model.PersonWatchVO;
import com.appscomm.sport.model.SysParamVO;
import com.appscomm.sport.service.AdminUserService;

/**
 * @ClassName:AdminUserServiceImpl.java
 * @Description:AdminUserService接口实现
 * @author:  叶子丰
 * @date:    2014-7-29
 */
@Service("adminUserService")
@Transactional
public class AdminUserServiceImpl implements AdminUserService{
	
	@Autowired
	private AdminUserDao adminUserDao;

	@Override
	public List<PersonWatchVO> qryWatchByUser(String userAccount) throws Exception {
		List<PersonWatchVO> list=null;
		if(StringUtils.isBlank(userAccount)){
			return list;
		}
		list=this.adminUserDao.qryWatchByUser(userAccount);
		if(list!=null&&!list.isEmpty()){
			for(PersonWatchVO vo : list){
				vo.setUserAccount(userAccount);
			}
		}
		
		return list;
	}

	@Override
	public List<PersonWatchVO> qryWatchByWatchId(String[] watches) throws Exception {		
		if(watches==null||watches.length<=0){
			return null;
		}
		List<PersonWatchVO> list=new ArrayList<PersonWatchVO>();
		for(String watchId:watches){
			PersonWatchVO vo=qryByWatchId(watchId);
			if(vo!=null){
				list.add(vo);
			}			
		}
		
		return list;
	}
	
	private PersonWatchVO qryByWatchId(String watchId) throws Exception{
		PersonWatchVO vo=null;
		if(StringUtils.isBlank(watchId)){
			return null;
		}
		List<PersonWatchVO> list=this.adminUserDao.qryWatchByWatchId(watchId);
		if(list!=null&&!list.isEmpty()){
			vo=list.get(0);
		}
		return vo;
	}

	@Override
	public void realseOneWatch(Integer sid) throws Exception {
		if(sid!=null&&sid>0){
			this.adminUserDao.bindOrRealseOne(sid, PersonWatchVO.STATUS_REALES);
		}
	}

	@Override
	public void batchRealse(Integer[] sids) throws Exception {
		this.adminUserDao.batcheRealse(sids);
	}

	@Override
	public String[] querySpecialAdmin() {
		List<SysParamVO> paramList = adminUserDao.querySpecialAdmin();
		String[] paramArray = new String[paramList.size()];
		if(!CollectionUtils.isEmpty(paramList)){
			for(int i = 0;i < paramList.size();i++){
				paramArray[i] = paramList.get(i).getParamValue();
			}
		}
		return paramArray;
	}

}
