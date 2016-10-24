/**
 * Copyright appscomm.cn 2013. All rights reserved.
 *
 * @createDate 2013-9-16
 */
package com.appscomm.sport.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appscomm.sport.dao.RemaindDao;
import com.appscomm.sport.model.RemaindVO;
import com.appscomm.sport.service.RemaindService;

;

/**
 * 运动数据
 * 
 * kuangzhenming create by 2013-9-16
 * 
 */
@Service("remaindService")
@Transactional
public class RemaindServiceImpl implements RemaindService {

	@Autowired
	private RemaindDao dao;

	@Override
	public List<RemaindVO> getList(Long userId, String watchId) {
		return dao.getList( userId, watchId);
	}

	@Override
	public int delete(String userId,String watchId, List<Map<String, String>> ids) {
		if(ids == null) return 0;
		if(ids.size() == 1){
			String id = ids.get(0).get("id");
			if(id.equals("-1")){
				System.out.println("userId,watchId:"+userId+"**"+watchId);
				return dao.delete(userId, watchId, -1);
			}
		}
		
		int result = 0;
		for(Map<String, String> iter : ids){//分批删除，可以考虑在sql使用in方式删除。。。
			System.out.println("userId,watchId:"+userId+"**"+iter.get("id"));
			result += dao.delete( userId, watchId, Long.parseLong(iter.get("id")));
		}
		return result;
	}

	@Override
	public int add(RemaindVO vo) {
		return dao.add(vo);
	}
	
	public boolean exist(String userId,String watchId, String time){
		return dao.exist(userId, watchId,  time);
	}
	
	@Override
	public int modifyStatus(Long remindId, Integer status) {
		return dao.updateRemindStatus(remindId, status);
	}

	@Override
	public int update(RemaindVO vo) {
		return dao.update(vo);
	}

	@Override
	public int deleteRemind(Long userId, String watchId) {
		return dao.deleteRemind(userId, watchId);
	}
	
}
