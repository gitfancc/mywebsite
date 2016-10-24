package com.appscomm.sport.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.appscomm.sport.model.PlaceLocateVO;
import com.appscomm.sport.model.StatisticsVO;
import com.appscomm.sport.service.PlaceLocateService;
import com.opensymphony.xwork2.Action;
import com.appscomm.sport.utils.DateUtils;

public class PlaceLocateAction extends BaseAction
{
	private static final long serialVersionUID = 1L;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPageIndex() {
		return currentPageIndex;
	}

	public void setCurrentPageIndex(int currentPageIndex) {
		this.currentPageIndex = currentPageIndex;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private int pageSize;
	private int currentPageIndex;
	private String startTime;
	private String endTime;
	private String time;
	private int action;
	@Autowired
	private PlaceLocateService placeLocateService;

	private List<PlaceLocateVO> result;
	private Map<String,Object> resultMap = new HashMap<String,Object>();

	public List<PlaceLocateVO> getResult() {
		return result;
	}

	public void setResult(List<PlaceLocateVO> result) {
		this.result = result;
	}
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}


/**
 * 获取日志数据
 * @return
 * @throws Exception
 */
	public String GetPlaceLocateData() throws Exception {
		String watchId = this.getUsers().getWatchId();
		Long personId = this.getUsers().getUserId();
		String watchType = super.getDefaultWatchType();
		// 默认今天的数据 今天1，最近一周2，最近一月3，最近一年4，全部5，自定义6
		String[] fromTo = DateUtils.Today();
		int doType=this.getAction();
		if ( doType== 1)
			fromTo = DateUtils.Today();
		else if (doType == 2)
			fromTo = DateUtils.Lastweek();
		else if (doType== 3)
			fromTo = DateUtils.LastMonth();
		else if (doType == 6)
			fromTo = DateUtils.FromTo(this.getStartTime(), this.getEndTime());
		else
			fromTo = DateUtils.Today();
		this.result = placeLocateService.getList(watchId, fromTo[0],
				fromTo[1], this.getCurrentPageIndex(), this.getPageSize(), personId, watchType);
		this.resultMap.put("data", this.result );
		int recordCount=placeLocateService.getCount(watchId,  fromTo[0], fromTo[1], personId, watchType);
		this.resultMap.put("count",recordCount );

		return Action.SUCCESS;
	}

	public String getMapList(){
		String watchId = this.getUsers().getWatchId();
		Long personId = this.getUsers().getUserId();
		String watchType = super.getDefaultWatchType();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String time = request.getParameter("time");
		this.result = this.placeLocateService.getMapList(watchId, time, personId, watchType);
		
		this.result.clear();
		PlaceLocateVO vo1 = new PlaceLocateVO();
		vo1.setLat("116.24");
		vo1.setLng("39.55");
//		PlaceLocateVO vo2 = new PlaceLocateVO();
//		vo2.setLat("113.456129");
//		vo2.setLng("23.175468");
//		PlaceLocateVO vo3 = new PlaceLocateVO();
//		vo3.setLat("113.449949");
//		vo3.setLng("23.16364");
		this.result.add(vo1);
//		this.result.add(vo2);
//		this.result.add(vo3);
		
		this.resultMap.put("data", result);
		return Action.SUCCESS;
	}
	
	
public Map<String,Object> getResultMap() {
	return resultMap;
}

public void setResultMap(Map<String,Object> resultMap) {
	this.resultMap = resultMap;
}
	
}
