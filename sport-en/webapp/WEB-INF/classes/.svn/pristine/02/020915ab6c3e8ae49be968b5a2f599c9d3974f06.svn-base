package com.appscomm.sport.dao;
import java.text.ParseException;
import java.util.List;

import com.appscomm.sport.model.PlaceLocateVO;
import com.appscomm.sport.model.StatisticsVO;

/**
 * �˶��������
 * @author kuangzhenming
 *
 */
public interface PlaceLocateDao {
	/**
	 * ��ݿ�ʼʱ�䣬����ʱ�䣬���з�ҳ����
	 * @param startTime ��ʼʱ��
	 * @param endTime ����ʱ��
	 * @param currentPageIndex  ��ǰҳ
	 * @param pageSize һҳ��ʾ����
	 * @return List<PlaceLocateVO>
	 */
	public List<PlaceLocateVO> getList(String watchId,String startTime,String endTime,int currentPageIndex,int pageSize, Long personId, String watchType);
   /**
    * ��ȡ��¼����
    * @param sql
    * @return
    */
	public int getCount(String watchId, String startTime, String endTime, Long personId, String watchType) ;

	
	public List<PlaceLocateVO> getMapList(String watchId, String startTime, String endTime, Long personId, String watchType);
}
