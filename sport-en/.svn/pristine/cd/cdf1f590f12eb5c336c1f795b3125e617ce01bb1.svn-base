package com.appscomm.sport.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.appscomm.sport.dao.SleepRecordDAO;
import com.appscomm.sport.vo.SleepRecord;
import com.appscomm.sport.vo.SleepRecordDetail;
import com.appscomm.sport.vo.SleepRecordDetailEx;
import com.appscomm.sport.vo.SleepRecordEx;
import com.appscomm.sport.vo.SleepRecordStatistic;

@Repository
public class SleepRecordDAOImpl implements SleepRecordDAO{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public List<SleepRecordStatistic> getSleepRecordStatisticsByDay(long personId, String deviceType, Date startDate, Date endDate) {
		String sql = "select ifnull(AVG(quality),0)  as quality, ifnull(SUM(sleep_duration),0)/60 as sleepDuration, ifnull(SUM(awake_duration),0)/60 as awakeDuration, " +
				" ifnull(SUM(light_duration),0)/60 as lightDuration, ifnull(SUM(deep_duration),0)/60 as deepDuration, ifnull(avg(awake_count),0) as awakeCount, " +
				" ifnull(SUM(total_duration),0)/60 as totalDuration, date_format(end_time, '%Y%m%d') as days" +
				" from sleep_record where person_id=:personId " +
				" and end_time>=:startDate " +
				" and end_time<=:endDate " +
				" group by date_format(end_time, '%Y%m%d')";
		
		Map<String,Object> values = new HashMap<String, Object>(); 
		values.put("personId", personId);
		values.put("startDate", startDate);
		values.put("endDate", endDate);
		List<SleepRecordStatistic> list = jdbcTemplate.query(sql, SleepRecordStatistic.class, values);
		
		return list;
	}

	@Override
	public List<SleepRecordStatistic> getSleepRecordStatisticsByWeek(long personId, String deviceType, Date startDate, Date endDate) {
		String sql = "select ifnull(AVG(quality),0)  as quality, ifnull(SUM(sleep_duration),0)/60 as sleepDuration, ifnull(SUM(awake_duration),0)/60 as awakeDuration, " +
				" ifnull(SUM(light_duration),0)/60 as lightDuration, ifnull(SUM(deep_duration),0)/60 as deepDuration, ifnull(avg(awake_count),0) as awakeCount, " +
				" ifnull(SUM(total_duration),0)/60 as totalDuration, date_format(end_time, '%W') as weeks" +
				" from sleep_record where person_id=:personId " +
				" and end_time>=:startDate " +
				" and end_time<=:endDate " +
				" group by date_format(end_time, '%W')";
		Map<String,Object> values = new HashMap<String, Object>();
		values.put("personId", personId);
		values.put("startDate", startDate);
		values.put("endDate", endDate);
		List<SleepRecordStatistic> list =  jdbcTemplate.query(sql, SleepRecordStatistic.class, values);
		
		return list;
	}

	@Override
	public List<SleepRecordStatistic> getSleepRecordStatisticsByMonth(long personId, String deviceType, Date startDate, Date endDate) {
		String sql = "select ifnull(AVG(quality),0)  as quality, ifnull(SUM(sleep_duration),0)/60 as sleepDuration, ifnull(SUM(awake_duration),0)/60 as awakeDuration, " +
				" ifnull(SUM(light_duration),0)/60 as lightDuration, ifnull(SUM(deep_duration),0)/60 as deepDuration, ifnull(avg(awake_count),0) as awakeCount, " +
				" ifnull(SUM(total_duration),0)/60 as totalDuration, date_format(end_time, '%Y%m') as months" +
				" from sleep_record where person_id=:personId " +
				" and end_time>=:startDate " +
				" and end_time<=:endDate " +
				" group by date_format(end_time, '%Y%m')";
		Map<String,Object> values = new HashMap<String, Object>();
		values.put("personId", personId);
		values.put("startDate", startDate);
		values.put("endDate", endDate);
		List<SleepRecordStatistic> list =  jdbcTemplate.query(sql, SleepRecordStatistic.class, values);
		
		return list;
	}

	@Override
	public long saveSleepRecord(SleepRecord sleepRecord) {

		String sql = "insert into sleep_record(person_id, device_type, start_time, end_time, quality, sleep_duration, awake_duration, light_duration, deep_duration, awake_count, total_duration) " +
				" values(:personId, :deviceType, :startTime, :endTime, :quality, :sleepDuration, :awakeDuration, :lightDuration, :deepDuration, :awakeCount, :totalDuration)";
		return jdbcTemplate.saveObjectGetSeqLong(sql, sleepRecord);
	}
	
	@Override
	public long saveSleepRecordDetail(SleepRecordDetail sleepRecordDetail) {
		String sql = "insert into sleep_record_detail(sleep_record_id, start_time,  status) values (:sleepRecordId, :startTime, :status)";
		return jdbcTemplate.saveObjectGetSeqLong(sql, sleepRecordDetail);
	}

	@Override
	public List<SleepRecordEx> getSleepRecords(long personId, String deviceType, Date startDate, Date endDate) {
		String sql = "select id as id, person_id as personId, device_type as deviceType, UNIX_TIMESTAMP(start_time) as startTime, UNIX_TIMESTAMP(end_time) as endTime, ifnull(quality,0) as quality, ifnull(sleep_duration,0) as sleepDuration," +
				" ifnull(awake_duration,0) as awakeDuration, ifnull(light_duration,0) as lightDuration, ifnull(deep_duration,0) as deepDuration, ifnull(awake_count,0) as  awakeCount, ifnull(total_duration,0) as totalDuration" +
				" from sleep_record where person_id=:personId and end_time>=:startDate and end_time<=:endDate";
		Map<String,Object> values = new HashMap<String, Object>();
		values.put("personId", personId);
		values.put("startDate", startDate);
		values.put("endDate", endDate);
		return  jdbcTemplate.query(sql, SleepRecordEx.class, values);
	}

	@Override
	public SleepRecordDetail getSleepRecordDetails(long sleepRecordId) {
		String sql = "select id as id , sleep_record_id as sleepRecordId, start_time as startTime, status as status from sleep_record_detail" +
				" where id=?" ;
		List<SleepRecordDetail> list = jdbcTemplate.query(sql, SleepRecordDetail.class, sleepRecordId);
		if(list!= null && list.size()>0)
			return list.get(0);
		
		return null;
	}

	@Override
	public List<SleepRecordDetailEx> getSleepRecordDetails(long personId, String deviceType, Date startDate, Date endDate) {
		String sql = "select detail.id as id , detail.sleep_record_id as sleepRecordId, UNIX_TIMESTAMP(detail.start_time) as startTime, detail.status as status " +
				"from sleep_record_detail detail inner join sleep_record sleep on detail.sleep_record_id=sleep.id " +
				"where  sleep.person_id=:personId " +
				" and sleep.end_time>=:startDate " +
				" and sleep.end_time<=:endDate " ;
		Map<String,Object> values = new HashMap<String, Object>();
		values.put("personId", personId);
		values.put("startDate", startDate);
		values.put("endDate", endDate);
		return  jdbcTemplate.query(sql, SleepRecordDetailEx.class, values);
	}
	
	@Override
	public List<SleepRecordEx> getSleepRecords(long personId, String dateTime) {
		String sql = "select id as id from sleep_record where person_id=? and end_time>?";
		return this.jdbcTemplate.query(sql, SleepRecordEx.class, personId, dateTime);
	}
	
	@Override
	public int deleteSleepRecord(long personId,String dateTime) {
		String sql = "delete from sleep_record where person_id=? and end_time>?";
		return this.jdbcTemplate.delete(sql, personId, dateTime);
	}
	
	@Override
	public List<SleepRecordEx> getSleepRecordsL11(long personId, String dateTime) {
		String sql = "select id as id from sleep_record where person_id=? and sleep_date>=?";
		return this.jdbcTemplate.query(sql, SleepRecordEx.class, personId, dateTime);
	}

	@Override
	public int deleteSleepRecordL11(long personId,String dateTime) {
		String sql = "delete from sleep_record where person_id=? and sleep_date>=?";
		return this.jdbcTemplate.delete(sql, personId, dateTime);
	}

	@Override
	public int deleteSleepRecordDetail(long sleepId) {
		String sql = "delete from sleep_record_detail where sleep_record_id=?";
		return this.jdbcTemplate.delete(sql, sleepId);
	}

}
