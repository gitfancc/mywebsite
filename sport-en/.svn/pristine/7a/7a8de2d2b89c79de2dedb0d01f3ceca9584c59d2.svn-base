/**
 * Copyright appscomm.cn 2013. All rights reserved.
 *
 * @createDate 2013-9-4
 */
package com.appscomm.sport.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.appscomm.sport.dao.UserDao;
import com.appscomm.sport.model.AccessTokenVo;
import com.appscomm.sport.model.AdvancedSetttingVO;
import com.appscomm.sport.model.ApiUserVO;
import com.appscomm.sport.model.AppVersionVO;
import com.appscomm.sport.model.HeartRateVO;
import com.appscomm.sport.model.LoginInfoVO;
import com.appscomm.sport.model.ParamSportVO;
import com.appscomm.sport.model.PersonVO;
import com.appscomm.sport.model.PersonWatchVO;
import com.appscomm.sport.model.UserVO;
import com.appscomm.sport.utils.Tools;
import com.appscomm.sport.vo.RegisterInfo;

/**
 *  用户数据操作相关dao接口实现 
 *	
 *  qindf create by 2013-9-4
 *
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Resource(name = "oldJdbcTemplate")
	private OldJdbcTemplate oldJdbcTemplate;
	
	@Override
	public UserVO getUser(Long userId){
		return jdbcTemplate.queryForObject("SELECT ID AS id,PASSWORD AS password,MAIL AS mail," +
				"TELPHONE AS telphone,REGISTER_TIME AS registerTime,ISACTIVE AS isActive FROM t_register_info " +
				"WHERE ID = ? AND ISACTIVE = 1", UserVO.class, userId);
	}
	
	/**
	 * @param params
	 * @param isActive 是否激活
	 * @return
	 */
	public List<UserVO> getUser(Map<String,Object> params,boolean isActive){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT A.WEIGHT AS weight, A.weight_unit as weightUnit, A.HEIGHT AS height, A.height_unit as heightUnit, A.IMG_URL AS imgUrl, B.ID AS id,B.PASSWORD AS password,B.MAIL AS mail,B.TELPHONE AS telphone," +
				"B.REGISTER_TIME AS registerTime,B.ISACTIVE AS isActive, A.ID as userId, " +
				"A.NICKNAME as nickName,A.USERNAME,A.BIRTHDATE,A.AREA_ID areaId,A.CITY_ID cityId, A.country_id as countryId," +
				"A.PROVINCE_ID provinceId,A.gender,C.value as sleepTarget FROM t_personal_info A " +
				"INNER JOIN t_register_info B on  A.REGISTER_ID = B.ID  left join personal_setting C on A.id=C.person_id and C.property='sleep_target' WHERE 1 = 1");
		if(params!=null){
			if(params.containsKey("mail"))
				sql.append(" AND trim(B.MAIL) =:mail");
			if(params.containsKey("telphone"))
				sql.append(" AND trim(B.TELPHONE) =:telphone");
			if(params.containsKey("nickName")) //add by 陈林 start
				sql.append(" AND trim(A.NICKNAME) = :nickName");
			if(isActive)
				sql.append(" AND B.ISACTIVE = 1");
			if(params.containsKey("id") && Long.valueOf(params.get("id")+"")>0){ //add by 陈林 end
				sql.append(" AND B.ID =:id");
			}
		}
		return jdbcTemplate.query(sql.toString(), UserVO.class, params);
	}
	
	@Override
	public UserVO getUser(UserVO user){
		if(user == null) return null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("select b.id as id, b.password as password, b.mail as mail, b.telphone as telphone ," +
				"b.register_time as registerTime, b.isactive as isActive, a.id as userId, a.nickname as nickName, " +
				"a.username as userName,a.weight,a.height, a.height_unit as heightUnit, a.weight_unit as weightUnit, a.country_id as countryId " +
				"  from t_register_info b inner join  t_personal_info a on a.register_id=b.id  " +
				"where 1=1 ");
		if (StringUtils.isNotEmpty(user.getMail())){
			sql.append(" and trim(b.mail)='").append(user.getMail()).append("'");
		}
		if (StringUtils.isNotEmpty(user.getTelphone())){
			sql.append(" and trim(b.telphone)='").append(user.getTelphone()).append("'");
		}
		sql.append(" and b.isactive=1 limit 1");
		return jdbcTemplate.queryForObject(sql.toString(), UserVO.class);
	}
	
	@Override
	public LoginInfoVO getRegisterInfo(String account){
		StringBuffer sql = new StringBuffer();
		sql.append("select t.id as regId, t.mail as regMail, t.password as regPassword, t.register_time as regTime, t.isactive as isActive ");
		sql.append(" from t_register_info t ");
		sql.append(" where trim(t.mail)='").append(account).append("'");
		sql.append(" and t.isactive=1 limit 1");
		return jdbcTemplate.queryForObject(sql.toString(), LoginInfoVO.class);
	}
	
	@Override
	public long addUser(UserVO user){
			return jdbcTemplate.saveObjectGetSeqLong("INSERT INTO t_register_info(MAIL,TELPHONE,PASSWORD,REGISTER_TIME,ISACTIVE) " +
					"VALUES(:mail,:telphone,:password,:registerTime,:isActive)", user);
	}
	
	@Override
	public long addPerson(PersonVO person){
		return jdbcTemplate.saveObjectGetSeq("INSERT INTO t_personal_info(NICKNAME,REGISTER_ID,IMG_URL) " +
				"VALUES(:nickName,:registerId,:imgUrl)", person);
	}
	
	@Override
	public long addPersonEx(PersonVO person){
		String sql = "insert into t_personal_info(username,nickname,gender,birthdate,telphone,email,qq,weibo,img_url,province_id,city_id,area_id,register_id,group_id,height,weight, height_unit, weight_unit, country_id) " +
				" values (:userName,:nickName,:gender,:birthDate,:telphone,:email, :qq, :weibo, :imgUrl, :provinceId, :cityId, :areaId, :registerId, :groupId, :height, :weight, :heightUnit, :weightUnit, :countryId)";
		return jdbcTemplate.saveObjectGetSeq(sql, person);
	}
	
	@Override
	public List<PersonWatchVO> getPersonWatch(Map<String,Object> params){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id as id, user_id AS userId,watch_id AS watchId,watch_sim AS watchSim,bind_time AS bindTime," +
				"watch_type AS watchType,active AS active, is_default as isDefault " +
				"FROM t_personal_watch WHERE 1 = 1 ");
		if(params!=null){
			if(params.containsKey("personId"))
				sql.append(" AND user_id = :personId");
			if(params.containsKey("active"))
				sql.append(" AND active = :active");
			if(params.containsKey("isDefault"))
				sql.append(" AND is_default = :isDefault");
		}
		sql.append(" ORDER BY bind_time desc");
		return jdbcTemplate.query(sql.toString(), PersonWatchVO.class, params);
	}
	
	@Override
	public List<PersonWatchVO> getPersonBindWatch(Long personId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id as id, user_id AS userId,watch_id AS watchId,watch_sim AS watchSim,bind_time AS bindTime," +
				"watch_type AS watchType,active AS active, is_default as isDefault " +
				"FROM t_personal_watch WHERE  user_id = ?  AND active=1 AND is_default=1");
			sql.append(" ORDER BY bind_time desc");
		return jdbcTemplate.query(sql.toString(), PersonWatchVO.class, personId);
	}
	
	@Override
	public PersonVO getPerson(Long registerId){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT F.ID,F.REGISTER_ID registerId,F.USERNAME,F.NICKNAME,F.GENDER,");
		sql.append("F.email as email, F.qq as qq, F.weibo as weibo, F.img_url as imgUrl,F.BIRTHDATE,F.TELPHONE,F.height,F.weight," +
				" F.height_unit as heightUnit, F.weight_unit as weightUnit, F.country_id as countryId,");
		sql.append("CONCAT(A.province_name,'|',A.city_name,'|',A.name) as CITY,F.IMG_URL imgUrl");
		sql.append(" FROM t_personal_info F left join t_district A ");
		sql.append(" on F.area_id =A.district_id");
		sql.append(" WHERE F.REGISTER_ID = ? limit 1");
		return jdbcTemplate.queryForObject(sql.toString(), PersonVO.class, registerId);
	}
	
	@Override
	public PersonVO getPersonByName(String nickName){
		StringBuffer sql = new StringBuffer();
		sql.append("select id as id, username as userName, nickname as nickName, gender as gender, birthdate as birthDate, telphone as telphone, " +
				"email as email, qq as qq, weibo as weibo, img_url as imgUrl, province_id as provinceId, city_id as cityId, area_id as areaId, register_id as registerId, " +
				"group_id as groupId,height,weight, height_unit as heightUnit, weight_unit as weightUnit, country_id as countryId ");
		sql.append(" from t_personal_info");
		sql.append(" where trim(nickname) = ? limit 1");
		return jdbcTemplate.queryForObject(sql.toString(), PersonVO.class, nickName);
	}
	
	@Override
	public int checkDulpNickName(String nickName, long userId){
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) as count from t_personal_info");
		sql.append(" where trim(nickname) =? and id <>?");
		return jdbcTemplate.queryForInt(sql.toString(), nickName, userId);		
	}
	
	/**
	 * zxh 20131115 add
	 */
	@Override
	public PersonVO getPersonById(long userId){
		StringBuffer sql = new StringBuffer();
		sql.append("select id as id, username as userName, nickname as nickName, gender as gender, birthdate as birthDate, telphone as telphone, " +
				"email as email, qq as qq, weibo as weibo, img_url as imgUrl, province_id as provinceId, city_id as cityId, area_id as areaId, register_id as registerId, " +
				"group_id as groupId,height,weight, height_unit as heightUnit, weight_unit as weightUnit, country_id as countryId ");
		sql.append(" from t_personal_info");
		sql.append(" where id=?");
		return jdbcTemplate.queryForObject(sql.toString(), PersonVO.class, userId);
	}
	
	
	@Override
	public int editPerson(PersonVO person){
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE t_personal_info SET");
		sql.append(" USERNAME=:userName,NICKNAME=:nickName,GENDER=:gender,BIRTHDATE=:birthDate,TELPHONE=:telphone,qq=:qq, weibo=:weibo, " +
				"email=:email, area_id=:areaId,city_Id=:cityId,province_Id=:provinceId,weight=:weight,height=:height, height_unit=:heightUnit, weight_unit=:weightUnit, country_id=:countryId");
		if (person.getImgUrl() != null && !person.getImgUrl().equals("")) {
			sql.append(",IMG_URL=:imgUrl");
		}
		sql.append(" WHERE ID = :id");
		return jdbcTemplate.updateObject(sql.toString(), person);
	}
	
	@Override
	public int editUser(UserVO user){
		return jdbcTemplate.updateObject("update t_personal_info set nickname=:nickName " +
				"where register_id=:id", user);
	}
		
	@Override
	public List<UserVO> getBindUserInfo(String watchid){
		StringBuffer sql = new StringBuffer();
		sql.append("select a.telphone,a.mail,b.nickName,b.id as userId from t_register_info a, t_personal_info b, t_personal_watch c");
		sql.append(" where a.id=b.register_id and b.id=c.user_id and c.watch_id= ? and active=1");
		return jdbcTemplate.query(sql.toString(), UserVO.class, watchid);
	}

	@Override
	public List<AccessTokenVo> getAccessTokenLists(AccessTokenVo tokenVo) {
		StringBuffer sqls = new StringBuffer();
		sqls.append("select * from api_accessToken a where 1=1 ");
		if(tokenVo.getUid()!=null && tokenVo.getUid()!=0){
			sqls.append(" and a.uid="+tokenVo.getUid());
		}
		if(tokenVo.getPersonalId()!=null && tokenVo.getPersonalId()!=0){
			sqls.append(" and a.personalId="+tokenVo.getPersonalId());
		}
		return jdbcTemplate.query(sqls.toString(), AccessTokenVo.class,
				new HashMap<String, Object>());
	}

	@Override
	public int editAccessTokens(AccessTokenVo tokenVo) {
		return jdbcTemplate
				.updateObject(
						"update api_accessToken set accessToken=:accessToken, refreseToken=:refreseToken, currentTime=:currentTime, expireIn=:expireIn, api_type=:api_type, personalId=:personalId "
								+
				"where uid=:uid", tokenVo);
	}

	@Override
	public int insertAccessTokens(AccessTokenVo tokenVo) {
		return jdbcTemplate
				.updateObject(
						"insert api_accessToken(uid, accessToken, refreseToken, currentTime, expireIn, api_type, personalId) values(:uid, :accessToken, :refreseToken, :currentTime, :expireIn, :api_type, :personalId)",
						tokenVo);
	}
	
	@Override
	public AppVersionVO getVersionsByVer(String desc) {
		StringBuffer sqls = new StringBuffer();
		Map<String, Object> map = new HashMap<String, Object>();
		sqls.append("select * from app_version a where 1=1 ");
		if(!StringUtils.isBlank(desc)){
			sqls.append(" and a.type like :type");
			map.put("type", "%"+desc+"%");
		}
		List<AppVersionVO> list = jdbcTemplate.query(sqls.toString(), AppVersionVO.class,
				map);
		return (list!=null && !list.isEmpty())?list.get(0):new AppVersionVO();
	}

	@Override
	public int updatePwd(UserVO userVo) {
		return jdbcTemplate
				.updateObject(
						"update t_register_info set password=:password where mail=:mail",
						userVo);
	}

	@Override
	public boolean isExistsByEmail(String email) {
		StringBuffer sqls = new StringBuffer();
		Map<String, Object> map = new HashMap<String, Object>();
		sqls.append("select * from t_register_info a where 1=1 ");
		if(!StringUtils.isBlank(email)){
			sqls.append(" and trim(a.mail) = :mail");
			map.put("mail", email);
		}
		List<RegisterInfo> list = jdbcTemplate.query(sqls.toString(), RegisterInfo.class,
				map);
		return (list!=null && !list.isEmpty())?true:false;
	}
	public Long getRegisterIdByEmail(String email){
		StringBuffer sqls = new StringBuffer();
		Map<String, Object> map = new HashMap<String, Object>();
		sqls.append("select * from t_register_info a where 1=1 ");
		sqls.append(" and trim(a.mail) = :mail");
		map.put("mail", email);
		List<RegisterInfo> list = jdbcTemplate.query(sqls.toString(), RegisterInfo.class,map);
		return (CollectionUtils.isEmpty(list))?null:list.get(0).getId();
	}
	@Override
	public Integer getUserByPassword(Long accountId, String oldPasword) {
		String sql = "select count(1) from t_register_info " +
				"where id=? and password=?";
		return jdbcTemplate.queryForInt(sql, accountId, oldPasword);
	}

	@Override
	public Integer getUserByPassword(String account, String oldPasword) {
		String sql = "select count(1) from t_register_info " +
				"where mail=? and password=?";
		return jdbcTemplate.queryForInt(sql, account, oldPasword);
	}
	
	@Override
	public Integer modifyUserPassword(Long accountId, String newPassword) {
		String sql = "update t_register_info set password=? where id=?";
		return jdbcTemplate.update(sql, newPassword, accountId);
	}
	
	@Override
	public Integer modifyUserPassword(String account, String newPassword) {
		String sql = "update t_register_info set password=? where mail=?";
		return jdbcTemplate.update(sql, newPassword, account);
	}

	@Override
	public AdvancedSetttingVO getUserAdvancedSetttingInfo(Long personId, String watchId) {
		String sql = "select sid as sid, watch_id as watchId, person_id as personId, time_mode as timeMode, length_unit as lengthUnit, weight_unit as weightUnit " +
				" from advanced_setttings where person_id=? and watch_id=?";
			return jdbcTemplate.queryForObject(sql, AdvancedSetttingVO.class, personId, watchId);
	}

	@Override
	public Integer addUserAdvancedSettingInfo(AdvancedSetttingVO advanced) {
		String sql = "insert advanced_setttings (watch_id, person_id, time_mode, length_unit, weight_unit) values (:watchId, :personId, :timeMode, :lengthUnit, :weightUnit)";
		return jdbcTemplate	.updateObject(sql, advanced);
	}

	@Override
	public Integer modifyAdvancedSettingInfo(AdvancedSetttingVO advanced) {
		String sql = "update advanced_setttings set time_mode=?, length_unit=?, weight_unit=? where sid=?";
		return jdbcTemplate.update(sql, advanced.getTimeMode(), advanced.getLengthUnit(), advanced.getWeightUnit(), advanced.getSid());
	}

	@Override
	public Integer updateUserStatus(Long accountId) {
		String sql = "update t_register_info set isactive=0 where id=?";
		return jdbcTemplate.update(sql, accountId);
	}

	@Override
	public void deleteSportData(Long personId, String watchId) {
		String tableName = Tools.getSportTableName(Tools.getCurrentDate(), null);
		String sql = "delete from "+ tableName+" where personid=? and watch_id=?";
		this.oldJdbcTemplate.delete(sql, personId, watchId);
	}

	@Override
	public void deleteSleepData(Long personId, String deviceType) {
		String sql = "select id from sleep_record where person_id=? and device_type=?";
		List<Long>idList = this.jdbcTemplate.query(sql, Long.class, personId, deviceType);
		String delSql = "delete from sleep_record_detail where sleep_record_id=?";
		for(Long id : idList){
			this.jdbcTemplate.delete(delSql, id);
		}
		String delSql2 = "delete from sleep_record where person_id=? and device_type=?";
		this.jdbcTemplate.delete(delSql2, personId, deviceType);
	}

	@Override
	public void deleteRemind(Long personId, String watchId) {
		String sql = "delete from  t_remaind where  userId=? and watchId=?";
		jdbcTemplate.delete(sql, personId,watchId);
	}

	@Override
	public void deleteTarget(Long personId) {
		String sql = "delete from personal_setting where person_id=?";
		jdbcTemplate.delete(sql, personId);
	}

	@Override
	public PersonWatchVO getBindWatch(Long personId, String watchId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select user_id as userId,watch_id as watchId,watch_sim as watchSim,bind_time as bindTime, watch_type as watchType, active as active, is_default as isDefault  " +
				" from  t_personal_watch where user_id=? and watch_id=? limit 1");
	
		return jdbcTemplate.queryForObject(sql.toString(), PersonWatchVO.class, personId, watchId);
	}

	@Override
	public void updateUserCountry(Long personId, String countryCode) {
		 jdbcTemplate.update("update t_personal_info set country_id=? where id=?",  countryCode, personId);
	}
	
	@Override
	public Integer getUserCountry(Long personId) {
		return  jdbcTemplate.queryForInt("select country_id from  t_personal_info where id=?", personId);
	}

	@Override
	public Integer addUserImg(Long userId, String imgUrl) {
		String sql = "update t_personal_info set img_url=? where id=?";
		return this.jdbcTemplate.update(sql, imgUrl, userId);
	}

	@Override
	public String queryUserImg(Long userId) {
		String sql = "select img_url as imgUrl from t_personal_info where id=?";
		Map<String,Object> map =  this.jdbcTemplate.queryForMap(sql, userId);
		if(map!=null){
			return (String) map.get("imgUrl");
		}
		return null;
	}

	@Override
	public List<UserVO> getAllUser() {
		return jdbcTemplate.query( "select id, password as  password from  t_register_info", UserVO.class);
	}
}
