package com.appscomm.sport.dao.impl;
import com.appscomm.sport.utils.DateUtils;
import com.appscomm.sport.utils.Tools;
import com.appscomm.sport.vo.PersonalSetting;
import com.appscomm.sport.vo.SleepRecordStatistic;
import com.appscomm.sport.dao.ParamSportDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.appscomm.sport.model.HeartRateVO;
import com.appscomm.sport.model.ParamSportVO;
import com.appscomm.sport.model.SportTotalVO;
import com.appscomm.sport.model.StatisticsVO;

/**
 * 运动数据搜索
 * 
 * @author kuangzhenming
 * 
 */
@Repository("paramSportDao")
public class ParamSportDaoImpl implements ParamSportDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Resource(name = "oldJdbcTemplate")
	private OldJdbcTemplate oldJdbcTemplate;

	/**
	 * 根据开始时间，结束时间，进行分页搜素
	 * 
	 * @param watchId
	 *            表ID
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param currentPageIndex
	 *            当前页
	 * @param pageSize
	 *            一页显示条数
	 * @return List<ParamSportVO>
	 */
	public List<ParamSportVO> getList(String watchId, String startTime,
			String endTime, int currentPageIndex, int pageSize) {
		int start = (currentPageIndex - 1) * pageSize;
		// int end=currentPageIndex*pageSize-1;
		String tableName = Tools.getSportTableName(startTime, endTime);
		String select = "select watch_id,starttime,   endtime, ifnull(steps,0)steps,  ifnull(dist,0)dist, ifnull(speed,0)speed,   ifnull(cal,0)cal, deviceid, ifnull(avgrate,0)avgrate,  ifnull(minrate,0)minrate,  ifnull(maxrate,0)maxrate from  " + tableName;
		String where = " where watch_id='" + watchId + "'" + " and endtime>='" + startTime + "' and endtime<='"
				+ endTime + "'";
		String limit = " limit " + start + "," + pageSize;
		String sql = select + where + limit;
		return oldJdbcTemplate.query(sql, ParamSportVO.class);
	}
	
	public List<ParamSportVO> getList(
			String watchId, 
			String startTime,
			String endTime, 
			int currentPageIndex,
			int pageSize, 
			Long personId,  
			String watchType) {
		int start = (currentPageIndex - 1) * pageSize;
		// int end=currentPageIndex*pageSize-1;
		StringBuffer sb = new StringBuffer();
		String tableName = Tools.getSportTableName(startTime, endTime);
		sb.append("select sport.watch_id as watchId, sport.starttime, sport.endtime, ifnull(sport.steps,0)steps, ifnull(sport.dist,0)dist, ifnull(sport.speed,0)speed, ifnull(sport.cal,0)cal, sport.deviceid, ifnull(sport.avgrate,0)avgrate, ifnull(sport.minrate,0)minrate, ifnull(sport.maxrate,0)maxrate,sport.personid ");
		sb.append(" from ").append(tableName).append( " sport ");
		if (watchType != null){
			sb.append(" inner join t_head_param head on head.id=sport.deviceid ");
		}
		sb.append(" where 1=1");
		sb.append(" and sport.personid=").append(personId);
		sb.append(" and sport.endtime>='").append(startTime).append("'");
		sb.append(" and sport.endtime<='").append(endTime).append("'");
		if (watchType != null){
			sb.append(" and head.devtype='").append(watchType).append("'");
		}
		sb.append(" ORDER BY sport.endtime DESC  limit ").append(start).append(",").append(pageSize);
		
		return oldJdbcTemplate.query(sb.toString(), ParamSportVO.class);
	}

	/**
	 * 获取记录总数
	 * 
	 * @param sql
	 * @return
	 */
	public int getCount(String watchId,
			String startTime, String endTime) {
		String tableName = Tools.getSportTableName(startTime, endTime);
		
		String select = "select count(1) as 'count' from " + tableName + " t ";
		String where = " where t.watch_id='" + watchId + "' " + " and t.endtime>='" + startTime
				+ "' and t.endtime<='" + endTime + "'"   ;
		String sql = select + where  ;
		return oldJdbcTemplate.queryForInt(sql);
	}
	
	public int getCount(
			String watchId,
			String startTime, 
			String endTime, 
			Long personId,  
			String watchType) {
		StringBuffer sb = new StringBuffer();
		String tableName =Tools.getSportTableName(startTime, endTime);
		sb.append("select count(1)  from " + tableName +" sport ");
		if (watchType != null){
			sb.append(" inner join t_head_param head on head.id=sport.deviceid ");
		}
		sb.append("	where 1=1"); 
		sb.append(" and sport.personid=").append(personId);
		sb.append("	and sport.endtime>='").append(startTime).append("'"); 
		sb.append(" and sport.endtime<='").append(endTime).append("'");
		if (watchType != null){
			sb.append(" and head.devtype='").append(watchType).append("'");
		}

		return oldJdbcTemplate.queryForInt(sb.toString());
	}

	@Override
	public List<StatisticsVO> getStatistics(
			String watchId, 
			int doType,
			String startTime, 
			String endTime, 
			Long personId,  
			String watchType) throws ParseException {
		StringBuffer sb = new StringBuffer();
		String tableName = Tools.getSportTableName(startTime, endTime);
		sb.append("select min(t.endtime) endTime, ifnull(sum(t.cal),0) cal, ifnull(sum(t.steps),0) steps, ifnull(sum(t.dist),0) dist, ifnull(avg(t.speed),0) speed, ifnull(avg(t.avgrate),0) avgrate ");
		sb.append(" from ").append(tableName).append(" t");
		if (watchType != null){
			sb.append(" inner join t_head_param head on head.id=t.deviceid ");
		}
		sb.append("	where 1=1"); 
		sb.append(" and t.personid=").append(personId);
		sb.append("	and t.endtime>='").append(startTime).append("'"); 
		sb.append(" and t.endtime<='").append(endTime).append("'");
		if (watchType != null){
			sb.append(" and head.devtype='").append(watchType).append("'");
		}
		
		String groupby = " group by date_format(t.endtime, '%M') ";
		String orderby="  ORDER BY t.endtime ASC";
		// 默认今天的数据 doType 今天1，最近一周2，最近一月3，最近一年4，全部5，自定义6
		if ( doType== 1)
			 groupby = " group by date_format(t.endtime, '%H')";
		else if (doType == 2)
			 groupby = " group by date_format(t.endtime, '%W')";
		else if (doType== 3)
			 groupby = " group by date_format(t.endtime, '%D')";
		else if (doType== 4)
			 groupby = " group by date_format(t.endtime, '%M')";
		else if (doType== 5)
			 groupby = " group by date_format(t.endtime, '%M')";
		else if (doType == 6){
			long day=DateUtils.DateDiff(startTime, endTime);
			if(day==0) groupby = " group by date_format(t.endtime, '%H')";
			if(day>0 && day<=7) 	groupby = " group by date_format(t.endtime, '%W')";
			if(day>7 && day<=40) 	groupby = " group by date_format(t.endtime, '%j')";
			if(day>40 &&day<90) 	groupby = " group by date_format(t.endtime, '%j')";
			if(day>90)	 groupby = " group by date_format(t.endtime, '%M')";
		}
		else
			groupby = " group by date_format(t.endtime, '%M');";
		
		String sql = sb.toString() + groupby+orderby ;
		return oldJdbcTemplate.query(sql, StatisticsVO.class);
	}
	
	@Override
	public List<SportTotalVO> getSportGroupHour(String watchId, String startTime, String endTime){
		StringBuilder sbd = new StringBuilder();
		String tableName = Tools.getSportTableName(startTime, endTime);
		sbd.append("select ifnull(sum(steps),0) steps,ifnull(sum(cal),0) cal,ifnull(sum(dist),0) dist,0 totaltime," +
				"date_format(endtime,'%Y%m%d%H') hours");
		sbd.append(" from ").append(tableName).append(" where watch_id=? and endtime>=? and endtime<=?");
		sbd.append(" group by date_format(endtime, '%Y%m%d%H') order by date_format(endtime, '%Y%m%d%H')");
		return oldJdbcTemplate.query(sbd.toString(), SportTotalVO.class, watchId, startTime, endTime);
	}
	
	@Override
	public List<SportTotalVO> getSportGroupHour(
			String watchId, 
			String startTime, 
			String endTime, 
			Long personId,  
			String watchType){
		StringBuilder sbd = new StringBuilder();
		String tableName = Tools.getSportTableName(startTime, endTime);
		sbd.append("select ifnull(sum(t.steps),0) steps,ifnull(sum(t.cal),0) cal,ifnull(sum(t.dist),0) dist, 0 totaltime," +
				"date_format(t.endtime, '%Y%m%d%H') hours");
		sbd.append(" from ").append(tableName).append(" t " );
		if (watchType != null){
			sbd.append(" inner join t_head_param head on head.id=t.deviceid ");
		}
		sbd.append(" where t.personid=? and t.endtime>=? and t.endtime<=? ");
		if (watchType != null){
			sbd.append(" and head.devtype='").append(watchType).append("'");
		}
		sbd.append(" group by date_format(t.endtime, '%Y%m%d%H') ");
		sbd.append(" order by date_format(t.endtime, '%Y%m%d%H') ");
		
		return oldJdbcTemplate.query(sbd.toString(), SportTotalVO.class, personId, startTime, endTime);
	}
	
	@Override
	public List<SportTotalVO> getSportGroupDay(String watchId, String startTime, String endTime){
		StringBuilder sbd = new StringBuilder();
		String tableName = Tools.getSportTableName(startTime, endTime);
		sbd.append("select ifnull(sum(steps),0) steps,ifnull(sum(cal),0) cal,ifnull(sum(dist),0) dist,0 totaltime," +
				"date_format(endtime,'%Y%m%d') days");
		sbd.append(" from ").append(tableName).append(" where watch_id=? and endtime>=? and endtime<=? ");
		sbd.append(" group by date_format(endtime,'%Y%m%d') order by date_format(endtime,'%Y%m%d')");
		return oldJdbcTemplate.query(sbd.toString(), SportTotalVO.class, watchId, startTime, endTime);
		
	}
	
	@Override
	public List<SportTotalVO> getSportGroupDay(
			String watchId, 
			String startTime, 
			String endTime, 
			Long personId,  
			String watchType){
		StringBuilder sbd = new StringBuilder();
		String tableName = Tools.getSportTableName(startTime, endTime);
		sbd.append("select ifnull(sum(t.steps),0) steps,ifnull(sum(t.cal),0) cal,ifnull(sum(t.dist),0) dist, 0 totaltime," +
				"date_format(t.endtime,'%Y%m%d') days");
		sbd.append(" from ").append(tableName).append(" t");
		if (watchType != null){
			sbd.append(" inner join t_head_param head on head.id=t.deviceid");
		}
		sbd.append(" where t.personid=? and t.endtime>=? and t.endtime<=?");
		if (watchType != null){
			sbd.append(" and head.devtype='").append(watchType).append("'");
		}
		sbd.append(" group by date_format(t.endtime,'%Y%m%d') ");
		sbd.append(" order by date_format(t.endtime,'%Y%m%d')");
		return oldJdbcTemplate.query(sbd.toString(), SportTotalVO.class, personId, startTime, endTime);
		
	}

	
	/**
	* 函数功能说明:通过watchId统计总步数
	* xc 
	* 2013-11-6
	* @param 
	* @return   
	* @throws
	 */
	@Override
	public Long sumStepsByWatchId(
			String watchId,
			Long personId,  
			String watchType) {
		StringBuffer sb = new StringBuffer();
		String tableName = Tools.getSportTableName(null, null);
		sb.append("select ifnull(sum(t.steps),0) from ").append(tableName).append(" t");
		if (watchType != null){
			sb.append(" inner join t_head_param head on head.id=t.deviceid ");
		}
		sb.append(" where t.personid=").append(personId);
		if (watchType != null){
			sb.append(" and head.devtype='").append(watchType).append("'");
		}
		return this.oldJdbcTemplate.queryForLong(sb.toString());
	}
	
	@Override
	public List<ParamSportVO> getParamSports(Map<String,Object> params){
		String watchType = (String) params.get("watchType");
		StringBuffer sql = new StringBuffer();
		String searchDate = (String) params.get("searchDate");
		String tableName = Tools.getSportTableName(searchDate, null);
		sql.append("select ifnull(min(t.endtime),0) endTime,ifnull(sum(t.cal),0)  cal, ifnull(sum(t.steps),0)  steps, ifnull(sum(t.dist),0)  dist, ifnull(avg(t.speed),0)  speed, ifnull(avg(t.avgrate),0)  avgrate ");
		sql.append(" from ").append(tableName).append(" t" );
		if(params!=null){
			if (watchType != null){
				sql.append(" inner join t_head_param head on head.id=t.deviceid ");
			}
			sql.append(" where 1=1 ");
			sql.append(" and t.personid=").append(params.get("personId"));
			sql.append(" and t.endtime >= '"+ searchDate+" 00:00:00' ");
			sql.append(" and t.endtime <= '"+ searchDate+" 23:59:59'");
			
			if (watchType != null){
				sql.append(" and head.devtype='").append(watchType).append("'");
			}
		}
		sql.append(" group by date_format(t.endtime, '%H');");
		return oldJdbcTemplate.query(sql.toString(), ParamSportVO.class, params);
	}
	
	@Override
	public List<HeartRateVO> getHeartRates(Map<String,Object> params){
		String watchType = (String) params.get("watchType");
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT A.WATCH_ID watchId,A.SETUPTIME,A.CURRENT FROM t_heart_rate A ");
		if(params!=null){
			if (watchType != null){
				sql.append(" inner join t_head_param head on head.id=A.deviceid ");
			}
			sql.append(" where 1=1 ");
			sql.append(" AND A.SETUPTIME BETWEEN CONCAT(:searchDate,' 00:00') " +
					"AND CONCAT(:searchDate,' 23:59')");
			sql.append(" and A.personid= :personId ");
			if (watchType != null){
				sql.append(" and head.devtype='").append(watchType).append("'");
			}
		}
		sql.append(" ORDER BY A.SETUPTIME ASC");
		return oldJdbcTemplate.query(sql.toString(), HeartRateVO.class, params);
	}

	@Override
	public ParamSportVO getLatelySportTime(Map<String,Object> params) {
		String watchType = (String) params.get("watchType");
		StringBuffer sql = new StringBuffer();
		String searchDate = (String) params.get("searchDate");
		String tableName = Tools.getSportTableName(searchDate, null);
		sql.append("select t.watch_id as watchId , t.starttime as startTime, t.endtime as endTime, ifnull(t.steps,0) as steps, ifnull(t.dist,0) as dist," +
				" ifnull(t.speed,0) as speed , ifnull(t.cal,0) as cal, t.deviceid as deviceId, ifnull(t.avgrate,0) as avgRate,  ifnull(t.minrate,0) as minRate,  ifnull(t.maxrate,0) as maxRate " +
				" from ").append(tableName).append(" t");
		if(params != null){
			if (watchType != null){
				sql.append(" inner join t_head_param head on head.id=t.deviceid ");
			}
			sql.append(" where 1=1 ");
			sql.append(" and t.personid=").append(params.get("personId"));
			sql.append(" and t.endtime >=  '"+ params.get("searchDate")+"  00:00:00' and t.endtime <=  '"+ params.get("searchDate")+" 23:59:59'");
			
			if (watchType != null){
				sql.append(" and head.devtype='").append(watchType).append("'");
			}
		}
		sql.append(" order by t.endtime desc limit 1"); 
		
		return this.oldJdbcTemplate.queryForObject(sql.toString(), ParamSportVO.class);
	}

	@Override
	public Integer deleteSportData(Long personId, String dateTime) {
		String tableName = Tools.getSportTableName(dateTime, null);
		String sql = "delete from " +tableName + " where personid=? and endtime>?";
		return this.oldJdbcTemplate.delete(sql, personId, dateTime);
	}
	
	@Override
	public List<SportTotalVO> getSportGroupMonth(Long personId, String watchId,
			String watchType, String startTime, String endTime) {
		StringBuilder sbd = new StringBuilder();
		String tableName = Tools.getSportTableName(startTime, endTime);
		sbd.append("select ifnull(sum(t.steps),0) steps,ifnull(sum(t.cal),0) cal,ifnull(sum(t.dist),0) dist, 0 totaltime," +
				"date_format(t.endtime,'%Y%m') months");
		sbd.append(" from ").append(tableName).append(" t");
		if (watchType != null){
			sbd.append(" inner join t_head_param head on head.id=t.deviceid");
		}
		sbd.append(" where t.personid=? and t.endtime>=? and t.endtime<=?");
		if (watchType != null){
			sbd.append(" and head.devtype='").append(watchType).append("'");
		}
		sbd.append(" group by date_format(t.endtime,'%Y%m') ");
		return oldJdbcTemplate.query(sbd.toString(), SportTotalVO.class, personId, startTime, endTime);
	}

	@Override
	public List<SportTotalVO> getSportGroupWeek(Long personId, String watchId,
			String watchType, String startTime, String endTime) {
		StringBuilder sbd = new StringBuilder();
		String tableName = Tools.getSportTableName(startTime, endTime);
		sbd.append("select ifnull(sum(t.steps),0) steps,ifnull(sum(t.cal),0) cal,ifnull(sum(t.dist),0) dist,0 totaltime," +
				"date_format(t.endtime, '%W') weeks");
		sbd.append(" from ").append(tableName).append(" t");
		if (watchType != null){
			sbd.append(" inner join t_head_param head on head.id=t.deviceid");
		}
		sbd.append(" where t.personid=? and t.endtime>=? and t.endtime<=?");
		if (watchType != null){
			sbd.append(" and head.devtype='").append(watchType).append("'");
		}
		sbd.append(" group by date_format(t.endtime, '%W') ");
		return oldJdbcTemplate.query(sbd.toString(), SportTotalVO.class, personId, startTime, endTime);
	}

	@Override
	public List<SleepRecordStatistic> getSleepStatistics(String watchId, int doType, String startTime, String endTime, Long personId, String watchType) throws ParseException {
		StringBuffer sb = new StringBuffer();
		sb.append("select min(end_time) endTime, ifnull(SUM(light_duration),0)/60 as lightDuration, ifnull(SUM(deep_duration),0)/60 as deepDuration, " +
				"ifnull(avg(awake_count),0) as awakeCount,ifnull(SUM(total_duration),0)/60 as totalDuration ");
		sb.append(" from sleep_record  ");
		sb.append("	where 1=1"); 
		sb.append(" and person_id=").append(personId);
		sb.append("	and sleep_date>='").append(startTime).append("'"); 
		sb.append(" and sleep_date<='").append(endTime).append("'");
		String groupby = " group by date_format(sleep_date, '%M') ";
		String orderby="  ORDER BY endTime ASC";
		// 默认今天的数据 doType 今天1，最近一周2，最近一月3，最近一年4，全部5，自定义6
		if ( doType== 1)
			 groupby = " group by date_format(sleep_date, '%H')";
		else if (doType == 2)
			 groupby = " group by date_format(sleep_date, '%W')";
		else if (doType== 3)
			 groupby = " group by date_format(sleep_date, '%D')";
		else if (doType== 4)
			 groupby = " group by date_format(sleep_date, '%M')";
		else if (doType== 5)
			 groupby = " group by date_format(sleep_date, '%M')";
		else if (doType == 6){
			long day=DateUtils.DateDiff(startTime, endTime);
			if(day==0) groupby = " group by date_format(sleep_date, '%H')";
			if(day>0 && day<=7) 	groupby = " group by date_format(sleep_date, '%W')";
			if(day>7 && day<=40) 	groupby = " group by date_format(sleep_date, '%j')";
			if(day>40 &&day<90) 	groupby = " group by date_format(sleep_date, '%j')";
			if(day>90)	 groupby = " group by date_format(sleep_date, '%M')";
		}
		else
			groupby = " group by date_format(sleep_date, '%M');";
		
		String sql = sb.toString() + groupby+orderby ;
		return this.jdbcTemplate.query(sql, SleepRecordStatistic.class);
	}

	@Override
	public List<SleepRecordStatistic> getSleepList(String watchId, String startTime, String endTime, int currentPageIndex, int pageSize, Long personId, String watchType) {
		int start = (currentPageIndex - 1) * pageSize;
		StringBuffer sb = new StringBuffer();
		sb.append("select person_id as personId, start_time as startTime, end_time as endTime, ifnull(quality,0) as quality, ifnull(sleep_duration,0) as sleepDuration ," +
				" ifnull(awake_duration,0) as awakeDuration, ifnull(light_duration,0) as lightDuration, ifnull(deep_duration,0) as deepDuration, ifnull(awake_count,0) as awakeCount, ifnull(total_duration,0) as totalDuration");
		sb.append(" from sleep_record ");
		sb.append(" where 1=1");
		sb.append(" and person_id=").append(personId);
		sb.append(" and sleep_date>='").append(startTime).append("'");
		sb.append(" and sleep_date<='").append(endTime).append("'");
		sb.append(" ORDER BY starttime DESC  limit ").append(start).append(",").append(pageSize);
		
		return this.jdbcTemplate.query(sb.toString(), SleepRecordStatistic.class);
	}

	@Override
	public int getSleepCount(String watchId, String startTime, String endTime, Long personId, String watchType) {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(1)  from sleep_record  ");
		sb.append("	where 1=1"); 
		sb.append(" and person_id=").append(personId);	
		sb.append("	and sleep_date>='").append(startTime).append("'"); 
		sb.append(" and sleep_date<='").append(endTime).append("'");
		return this.jdbcTemplate.queryForInt(sb.toString());
	}
	

	@SuppressWarnings("deprecation")
	@Override
	public Date getLastSyncTime(Long personId, String watchId){
		String sql = "select max(endtime) as Time, max(inserttime) as insertTime from t_param_sport where personid=?";
		Map<String, Object> map = this.oldJdbcTemplate.queryForMap(sql,personId);
		Date now = new Date();
		Date syncTime = (Date) map.get("Time");
		if(syncTime != null){
			if(syncTime.getYear()>now.getYear()){
				syncTime = (Date) map.get("insertTime");
				syncTime = new Date(syncTime.getTime()+8*3600000);
			}
		}
		return syncTime;
	}

	@Override
	public List<PersonalSetting> getPersonSettingTarget(Long personId) {
		String sql = "select property as property, value as value  from personal_setting where person_id=?";
		return this.jdbcTemplate.query(sql,PersonalSetting.class, personId);
	}
}
