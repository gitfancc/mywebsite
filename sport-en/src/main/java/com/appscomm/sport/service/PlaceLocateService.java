package com.appscomm.sport.service;
import java.util.List;

import com.appscomm.sport.model.PlaceLocateVO;

/**
 * 运动数据搜索
 * @author kuangzhenming
 *
 */
public interface PlaceLocateService {
	/**
	 * 根据开始时间，结束时间，进行分页搜素
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param currentPageIndex  当前页
	 * @param pageSize 一页显示条数
	 * @return List<PlaceLocateVO>
	 */
	public List<PlaceLocateVO> getList(String watchId,String startTime,String endTime,int currentPageIndex,int pageSize, Long personId, String watchType);
   /**
    * 获取记录总数
    * @param sql
    * @return
    */
	public int getCount(String watchId, String startTime, String endTime, Long personId, String watchType) ;

	
	public List<PlaceLocateVO> getMapList(String watchId, String time, Long personId, String watchType);
}
