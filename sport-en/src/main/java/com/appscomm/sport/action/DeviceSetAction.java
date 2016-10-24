package com.appscomm.sport.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.appscomm.sport.common.SportApiCode;
import com.appscomm.sport.model.PersonWatchVO;
import com.appscomm.sport.model.UserVO;
import com.appscomm.sport.model.WatchVO;
import com.appscomm.sport.service.DeviceSetService;
import com.appscomm.sport.service.UserService;
import com.opensymphony.xwork2.Action;

public class DeviceSetAction extends BaseAction {
	private static final long serialVersionUID = -6748925554476869953L;
	private static Log logger = LogFactory.getLog(DeviceSetAction.class);
	@Autowired
	private DeviceSetService deviceSetService;
	@Autowired
	private UserService userService;	
	private WatchVO watch;
	private String result;
	private UserVO user;
	private Long personId;
	private Integer defaultDevice;
	private List<Integer> timeIntervalList = new ArrayList<Integer>();
	private List<Integer> timeHourList = new ArrayList<Integer>();
	private List<PersonWatchVO> personWatchList;

	/**
	 * 初始化页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initDevice() throws Exception {
		logger.debug("<<< device setting init >>>");
		user = super.getUsers();
		if (user == null){
			return Action.LOGIN;
		}
		
		// 加载页面列表默认项
		for (int i = 1; i <= 12; i++) {
			timeIntervalList.add(i * 5);
		}
		for (int i = 0; i < 24; i++) {
			timeHourList.add(i);
		}
		watch = deviceSetService.getDeviceInfo(watch, user.getUserId());
		logger.debug(watch.toString());

		return Action.INPUT;
	}

	public String listDevice() throws Exception {
		logger.info("device list");
		String logined = super.getLogin();
//		String logined = (String) session.get("isLogined");
		request.setAttribute("logined", logined);
//		user = super.getUsers();
//		if (user == null){
//			return Action.LOGIN;
//		}
//		personId = user.getUserId();
//		logger.debug("List Device >>> personId=" + personId);
//		personWatchList = deviceSetService.getDeviceList(personId);
//		if(personWatchList.size()==0){
//			personWatchList = null;
//		}
		return Action.SUCCESS;
	}

	/**
	 * 保存设备设置信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveDevice() throws Exception {
		user = super.getUsers();
		if (user == null){
			return Action.LOGIN;
		}
		logger.debug("Device Info <<< " + watch.toString() + " >>>");
		
		personId = user.getUserId();
		if (null == personId) {
			// 用户不存在
			result = SportApiCode.ERROR_3001.getDesc();
			return Action.SUCCESS;
		}

		Integer code = deviceSetService.saveDeviceInfo(watch, user.getUserId());
		if (code < 0) {
			result = SportApiCode.ERROR_7787.getDesc();
		} else {
			result = SportApiCode.SUCCESS.getDesc();
			// 发送短信,告知设备同步配置参数
			deviceSetService.sendSms(watch);
		}
		return Action.SUCCESS;
	}

	/**
	 * 保存新增的设备绑定信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveBind() throws Exception {
		user = super.getUsers();
		if (user == null){
			return Action.LOGIN;
		}
		personId = user.getUserId();
		logger.debug("Device WatchId <<<" + watch.getWatchId() + ", PersonId:"	+ personId	+ ", WatchSim:" + watch.getWatchSim()	+ " >>>");
		
		Integer code = deviceSetService.bindDevice(personId, watch, defaultDevice);
		if (code == -1) {
			result = SportApiCode.ERROR_7782.getDesc() ;
			result = result + "<" + getBindPersonAccount(watch.getWatchId()) + ">";
		} else if(code == -2) {
			result = SportApiCode.ERROR_7795.getDesc();
		} else {
			result = SportApiCode.SUCCESS.getDesc();
		}

		return Action.SUCCESS;
	}

	/**
	 * 绑定腕表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String bindWatchId() throws Exception {
		user = super.getUsers();
		if (user == null){
			return Action.LOGIN;
		}
		personId = user.getUserId();
		logger.debug("Device WatchId <<<" + watch.getWatchId() + ", PersonId:"	+ personId + " >>>");
		
		if (null == personId) {
			// 用户不存在
			result = SportApiCode.ERROR_3001.getDesc();
			return Action.SUCCESS;
		}
		
		Integer code = deviceSetService.bindDeviceId(personId, watch.getWatchId(), defaultDevice, watch.getType());
		if (code == -1) {
			result = SportApiCode.ERROR_7782.getDesc();
			result = result + "<" + getBindPersonAccount(watch.getWatchId()) + ">";
		} else if(code == -2) {
			result = SportApiCode.ERROR_7795.getDesc();
		}else {
			result = SportApiCode.SUCCESS.getDesc();
		}
		
		//保存watchId到session中
		user.setWatchId(watch.getWatchId());
		super.session.remove(IndexAction.LOGIN_SESSION_USER);
		super.session.put(IndexAction.LOGIN_SESSION_USER, user);
		
		return Action.SUCCESS;
	}

	/**
	 * 解绑腕表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String unBindWatchId() throws Exception {
		user = super.getUsers();
		if (user == null){
			return Action.LOGIN;
		}
		personId = user.getUserId();
		logger.debug("Device WatchId <<<" + watch.getWatchId() + " >>>");
		
		if (null == personId) {
			// 用户不存在
			result = SportApiCode.ERROR_3001.getDesc();
			return Action.SUCCESS;
		}

		Integer code = deviceSetService.unBindDeviceId(personId, watch.getWatchId());
		if (code == -1) {
			result = SportApiCode.ERROR_7789.getDesc();
		} else {
			result = SportApiCode.SUCCESS.getDesc();
		}
		
		// 从session中清除watchId
		user.setWatchId("");
		super.session.remove(IndexAction.LOGIN_SESSION_USER);
		super.session.put(IndexAction.LOGIN_SESSION_USER, user);
		
		return Action.SUCCESS;
	}

	/**
	 * 绑定电话号码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String bindWatchSim() throws Exception {
		user = super.getUsers();
		if (user == null){
			return Action.LOGIN;
		}
		personId = user.getUserId();

		logger.debug("Device WatchSim <<< " + watch.getWatchSim() 	+ ",watchId " + watch.getWatchId()	+ " >>>");
		if (null == personId) {
			// 用户不存在
			result = SportApiCode.ERROR_3001.getDesc();
			return Action.SUCCESS;
		}

		Integer code = deviceSetService.bindDeviceSim(watch.getWatchId(),watch.getWatchSim());
		if (code < 0) {
			result = SportApiCode.ERROR_7785.getDesc();
		} else {
			result = SportApiCode.SUCCESS.getDesc();
		}
		return Action.SUCCESS;
	}

	/**
	 * 解绑电话号码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String unBindWatchSim() throws Exception {
		user = super.getUsers();
		if (user == null){
			return Action.LOGIN;
		}
		personId = user.getUserId();
		logger.debug("Device WatchSim <<< " + watch.getWatchSim() + " >>>");
		if (null == personId) {
			// 用户不存在
			result = SportApiCode.ERROR_3001.getDesc();
			return Action.SUCCESS;
		}

		Integer code = deviceSetService.unBindDeviceSim(personId, watch.getWatchId());
		if (code < 0) {
			result = SportApiCode.ERROR_7786.getDesc();
		} else {
			result = SportApiCode.SUCCESS.getDesc();
		}
		return Action.SUCCESS;
	}

	/**
	 * 根据绑定的设备号获取其绑定的人的信息
	 * 
	 * @param watchId
	 * @return
	 */
	private String getBindPersonAccount(String watchId){
		String account = "";
		if (StringUtils.isEmpty(watchId)) return account;
		
		List<UserVO> list = userService.getBindUserInfo(watchId);
		if(list != null && list.size() > 0){
			UserVO u = list.get(0);
			String mail = u.getMail();
			String tel = u.getTelphone();
			if (StringUtils.isNotEmpty(mail)){
				account = mail;
			} else {
				account = tel;
			}
		}
		return account;
	}

	/**
	 * 设置系统默认设备(腕表)
	 * 
	 * @return
	 */
	public void setDefaultWatch(){
		UserVO user = super.getUsers();
		if (user == null) {
			super.writeJson("ERROR");
		}
		//if (StringUtils.isNotEmpty(super.getDefaultWatchType())){
			// 更新数据库中缺省的设备
			//deviceSetService.setDefaultWatch(personId, defaultType);
	//	}
		super.writeJson("OK");
	}
	
	public WatchVO getWatch() {
		return watch;
	}

	public void setWatch(WatchVO watch) {
		this.watch = watch;
	}

	public DeviceSetService getDeviceSetService() {
		return deviceSetService;
	}

	public void setDeviceSetService(DeviceSetService deviceSetService) {
		this.deviceSetService = deviceSetService;
	}

	public List<Integer> getTimeIntervalList() {
		return timeIntervalList;
	}

	public void setTimeIntervalList(List<Integer> timeIntervalList) {
		this.timeIntervalList = timeIntervalList;
	}

	public List<Integer> getTimeHourList() {
		return timeHourList;
	}

	public void setTimeHourList(List<Integer> timeHourList) {
		this.timeHourList = timeHourList;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public List<PersonWatchVO> getPersonWatchList() {
		return personWatchList;
	}

	public void setPersonWatchList(List<PersonWatchVO> personWatchList) {
		this.personWatchList = personWatchList;
	}

	public Integer getDefaultDevice() {
		return defaultDevice;
	}

	public void setDefaultDevice(Integer defaultDevice) {
		this.defaultDevice = defaultDevice;
	}

}
