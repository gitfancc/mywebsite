package com.appscomm.sport.dao.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.appscomm.sport.dao.RemaindDao;
import com.appscomm.sport.model.RemaindVO;
import com.appscomm.sport.utils.DateUtils;

/**
 * 运动数据搜索
 * 
 * @author kuangzhenming
 * 
 */
@Repository("remaindDaoImpl")
public class RemaindDaoImpl implements RemaindDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
/**
 * 获取列表
 */
	@Override
	public List<RemaindVO> getList(Long userId, String watchId) {
		String select = "SELECT id, userId, watchId, `repeat`, `time`,type,text,detail,status, doType,upload_time  FROM  t_remaind";
		String where = "  WHERE watchId=? and userId=?";
		String sql = select + where;
		return jdbcTemplate.query(sql, RemaindVO.class, watchId, userId);
	}
	
	@Override
	public RemaindVO getRemindById(long id){
		String select = "SELECT id, userId, watchId, `repeat`, `time`,type,text,detail,status, doType,upload_time  FROM  t_remaind";
		String where = "  WHERE id=?";
		String sql = select + where;
		return jdbcTemplate.queryForObject(sql, RemaindVO.class, id);		
	}
	
/**
 * 删除
 * 直接删除还是应该设doType=0???
 */
	@Override
	public int delete(String userId,String watchId, long id) {
		String select = "DELETE FROM t_remaind";
		String where = "  WHERE userId=? and watchId=? and id=?";
		if(id == -1){//删除全部
			where = "  WHERE userId=? and watchId=?";
			String sql = select + where;
			return jdbcTemplate.delete(sql, userId,watchId);
		}		
		String sql = select + where;
		return jdbcTemplate.delete(sql, userId,watchId, id);
	}
/**
 * 添加
 */
	@Override
	public int add(RemaindVO vo) {
//		if(exist(vo.getUserId(),vo.getWatchId(),vo.getTime())){
//			return 0;
//		}
			
		String insert = "INSERT INTO t_remaind(`userId`, `watchId`,`repeat`, `time`, `type`,`text`,`detail`, `status`,`doType`,`upload_time`)";
		String values = "  VALUES (:userId, :watchId, :repeat,:time, :type,:text, :detail,:status, :doType, :upload_time);";
		String sql = insert + values;
		return jdbcTemplate.saveObjectGetSeq(sql, vo);//返回自增长ID
	}

	@Override
	public int update(RemaindVO vo){
		String sql = "update t_remaind set `repeat`=:repeat, `time`=:time, `type`=:type, `text`=:text, `detail`=:detail, `status`=:status, `doType`=:doType  where `id`=:id";
		return jdbcTemplate.updateObject(sql, vo);
	}
	/**
	 * 是否存在相同时间的记录。app有可能修改time字段值！
	 */
	@Override
	public boolean exist(String userId, String watchId, String time) {
		String sql = "select count(*) from t_remaind where  userId=? and watchId=? and time=?";
		return jdbcTemplate.queryForInt(sql, userId, watchId, time) > 0;
	}

	@Override
	public int updateRemindStatus(Long remindId, Integer status) {
		String sql = "update t_remaind set `status`=? where id=?";
		return this.jdbcTemplate.update(sql, status, remindId);
	}

	@Override
	public int deleteRemind(Long userId, String watchId) {
		String sql = "delete from t_remaind where userId=? and watchId=?";
		return this.jdbcTemplate.delete(sql, userId, watchId);
	}
}
