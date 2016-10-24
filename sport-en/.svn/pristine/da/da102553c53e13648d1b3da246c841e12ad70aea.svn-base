package com.appscomm.sport.dao.impl;
import com.appscomm.sport.dao.PlaceLocateDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

import com.appscomm.sport.model.PlaceLocateVO;

/**
 * 运动数据搜索
 * 
 * @author kuangzhenming
 * 
 */
@Service("PlaceLocateDao")
public class PlaceLocateDaoImpl implements PlaceLocateDao {
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
	 * @return List<PlaceLocateVO>
	 */
	public List<PlaceLocateVO> getList(String watchId, String startTime,
			String endTime, int currentPageIndex, int pageSize, Long personId, String watchType) {
		int start = (currentPageIndex - 1) * pageSize;
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT t.watch_id as watchId, t.time, t.lat, t.ns, t.lng, t.ew, t.sealevel, t.pressure, t.humidity,  t.mcc, t.mnc, t.lac, t.cellid, t.battery, t.deviceid FROM  t_place_locate t");
		if (watchType != null){
			sb.append(" inner join t_head_param head on head.id=t.deviceid");
		}
		sb.append(" where 1=1");
		sb.append(" and t.personid=").append(personId);
		sb.append(" and t.time>='").append(startTime).append("'");
		sb.append(" and t.time<='").append(endTime).append("'");
		if (watchType != null){
			sb.append(" and head.devtype='").append(watchType).append("'");
		}
		sb.append(" limit ").append(start).append(",").append(pageSize);
		
		return oldJdbcTemplate.query(sb.toString(), PlaceLocateVO.class);
	}

	/**
	 * 获取记录总数
	 * 
	 * @param sql
	 * @return
	 */
	public int getCount(String watchId,
			String startTime, String endTime, Long personId, String watchType) {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(1) as count from t_place_locate t");
		if (watchType != null){
			sb.append(" inner join t_head_param head on head.id=t.deviceid");
		}
		sb.append(" where 1=1");
		sb.append(" and t.personid=").append(personId);
		sb.append(" and t.time>='").append(startTime).append("'");
		sb.append(" and t.time<='").append(endTime).append("'");
		
		if (watchType != null){
			sb.append(" and head.devtype='").append(watchType).append("'");
		}
	
		return oldJdbcTemplate.queryForInt(sb.toString());
	}

	
	
	/**
	 * 根据开始时间，结束时间，进行分页搜素
	 * 
	 * @param watchId
	 *            表ID
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return List<PlaceLocateVO>
	 */
	public List<PlaceLocateVO> getMapList(String watchId,String startTime,  String endTime, Long personId, String watchType) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT t.watch_id as watchId, MAX(t.time) as time, t.lat, t.lng FROM  t_place_locate t");
		if (watchType != null){
			sb.append(" inner join t_head_param head on head.id=t.deviceid");
		}
		sb.append(" where 1=1");
		sb.append(" and t.personid=").append(personId);
		sb.append(" and t.time>='").append(startTime).append("'");
		sb.append(" and t.time<='").append(endTime).append("'");
		if (watchType != null){
			sb.append(" and head.devtype='").append(watchType).append("'");
		}
	
		return oldJdbcTemplate.query(sb.toString(), PlaceLocateVO.class);
	}
}
