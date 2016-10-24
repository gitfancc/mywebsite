/**
 * Copyright appscomm 2014. All rights reserved.
 */

package com.appscomm.sport.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.appscomm.sport.dao.AdminUserDao;
import com.appscomm.sport.model.PersonWatchVO;
import com.appscomm.sport.model.SysParamVO;
import com.appscomm.sport.model.UserVO;

/**
 * @ClassName:AdminUserDaoImpl.java
 * @Description:AdminUserDao接口实现
 * @see AdminUserDao
 * @author:  叶子丰
 * @date:    2014-7-25
 */
@Repository("adminUserDao")
public class AdminUserDaoImpl implements AdminUserDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<PersonWatchVO> qryWatchByUser(String userAccount)  throws Exception{
		List<PersonWatchVO> list=null;
		UserVO user=jdbcTemplate.queryForObject("SELECT u.id AS userId FROM t_register_info r,t_personal_info u WHERE r.mail=? AND r.isactive=? " +
				"AND r.id=u.register_id", UserVO.class, userAccount,1);
		Long userId=user.getUserId();
		if(userId!=null){
			String sql="SELECT t.id AS id,t.watch_id AS watchId,t.bind_time AS bindTime,t.watch_type AS watchType,t.is_default AS isDefault" +
					" FROM t_personal_watch t WHERE t.user_id=? AND t.active=? ORDER BY t.bind_time DESC";
			list=jdbcTemplate.query(sql, PersonWatchVO.class, userId,1);
		}
		
		return list;
	}

	@Override
	public List<PersonWatchVO> qryWatchByWatchId(String watchId) throws Exception{
		List<PersonWatchVO> list=null;
		if(StringUtils.isNotBlank(watchId)){
			String sql="SELECT t.id AS id,t.watch_id AS watchId,t.bind_time AS bindTime,t.watch_type AS watchType,t.is_default AS isDefault,t.user_id AS userId" +
					" FROM t_personal_watch t WHERE t.watch_id=? AND t.active=? ORDER BY t.bind_time DESC";
			list=jdbcTemplate.query(sql, PersonWatchVO.class, watchId,1);
			if(list!=null&&!list.isEmpty()){
				for(PersonWatchVO vo:list){
					Long userId=vo.getUserId();
					UserVO user=jdbcTemplate.queryForObject("SELECT r.mail AS mail FROM t_register_info r,t_personal_info u WHERE u.id=? AND r.isactive=? " +
							"AND r.id=u.register_id", UserVO.class, userId,1);
					if(user!=null) vo.setUserAccount(user.getMail());
				}
			}
		}		
				
		return list;
	}

	@Override
	public void bindOrRealseOne(Integer sid, Integer status) throws Exception{
		String sql="UPDATE t_personal_watch SET active=?,is_default=? WHERE id=?";
		jdbcTemplate.update(sql, status,PersonWatchVO.IS_NOT_DEFAULT,sid);	
	}

	@Override
	public void batcheRealse(Integer[] sids) throws Exception{
		if(sids!=null){
			for(Integer id:sids){
				String sql="UPDATE t_personal_watch SET active=?,is_default=? WHERE id=?";
				jdbcTemplate.update(sql, 0,PersonWatchVO.IS_NOT_DEFAULT,id);
			}
		}
	}

	@Override
	public List<SysParamVO> querySpecialAdmin() {
		return jdbcTemplate.query("select t.param_id as paramId,t.param_key as paramKey,t.param_value as paramValue from T_SYS_PARAM t where t.param_key='SpecialAdmin'", SysParamVO.class);
	}
	public SysParamVO queryKronozTokenHour(){
		return jdbcTemplate.queryForObject("select t.param_id as paramId,t.param_key as paramKey,t.param_value as paramValue from T_SYS_PARAM t where t.param_key='KronozTokenHour'", SysParamVO.class);
	}
	public SysParamVO queryKronozTokenLimit(){
		return jdbcTemplate.queryForObject("select t.param_id as paramId,t.param_key as paramKey,t.param_value as paramValue from T_SYS_PARAM t where t.param_key='KronozTokenLimit'", SysParamVO.class);
	}
}
