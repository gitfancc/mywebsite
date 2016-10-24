package com.appscomm.sport.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.appscomm.sport.vo.PersonalSetting;

public class LoginInfoVO implements Serializable {
	private static final long serialVersionUID = 4935797053943106264L;
	private Long regId = 0l;
	private String regMail;
	private String regTelphone;
	private String regPassword;
	private Date regTime;
	private Integer isActive;
	//用户信息
	private PersonVO personInfo;
	//绑定信息
	private PersonWatchVO bindInfo;
	//获取目标
	private List<PersonalSetting> personSettingInfo;
	//获取提醒
	private List<RemaindVO> remindInfo;
	//获取自动睡眠
	private DeviceSleepRemindInfoVO sleepRemindInfo;
	//获取久坐提醒
	private DeviceStayRemindInfoVO stayRemindInfo;
	//获取通知开关
	private DeviceNotifyInfoVO deviceNotifyInfo;
	//获取固件版本
	private DeviceVersionInfoVO deviceVersionInfo;
	//动一动提醒
	private List<PeriodResmindVO> perodRemindInfo;
	
	//登录验证成功之后返回给客户端的token
	private String kronoz_token;
	
	public String getKronoz_token() {
		return kronoz_token;
	}
	public void setKronoz_token(String kronoz_token) {
		this.kronoz_token = kronoz_token;
	}
	public Long getRegId() {
		return regId;
	}
	public void setRegId(Long regId) {
		this.regId = regId;
	}
	public String getRegMail() {
		return regMail;
	}
	public void setRegMail(String regMail) {
		this.regMail = regMail;
	}
	public String getRegTelphone() {
		return regTelphone;
	}
	public void setRegTelphone(String regTelphone) {
		this.regTelphone = regTelphone;
	}
	public String getRegPassword() {
		return regPassword;
	}
	public void setRegPassword(String regPassword) {
		this.regPassword = regPassword;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	public PersonVO getPersonInfo() {
		return personInfo;
	}
	public void setPersonInfo(PersonVO personInfo) {
		this.personInfo = personInfo;
	}
	public PersonWatchVO getBindInfo() {
		return bindInfo;
	}
	public void setBindInfo(PersonWatchVO bindInfo) {
		this.bindInfo = bindInfo;
	}
	public List<PersonalSetting> getPersonSettingInfo() {
		return personSettingInfo;
	}
	public void setPersonSettingInfo(List<PersonalSetting> personSettingInfo) {
		this.personSettingInfo = personSettingInfo;
	}
	
	public List<RemaindVO> getRemindInfo() {
		return remindInfo;
	}
	public void setRemindInfo(List<RemaindVO> remindInfo) {
		this.remindInfo = remindInfo;
	}
	public DeviceSleepRemindInfoVO getSleepRemindInfo() {
		return sleepRemindInfo;
	}
	public void setSleepRemindInfo(DeviceSleepRemindInfoVO sleepRemindInfo) {
		this.sleepRemindInfo = sleepRemindInfo;
	}
	public DeviceStayRemindInfoVO getStayRemindInfo() {
		return stayRemindInfo;
	}
	public void setStayRemindInfo(DeviceStayRemindInfoVO stayRemindInfo) {
		this.stayRemindInfo = stayRemindInfo;
	}
	public DeviceNotifyInfoVO getDeviceNotifyInfo() {
		return deviceNotifyInfo;
	}
	public void setDeviceNotifyInfo(DeviceNotifyInfoVO deviceNotifyInfo) {
		this.deviceNotifyInfo = deviceNotifyInfo;
	}
	public DeviceVersionInfoVO getDeviceVersionInfo() {
		return deviceVersionInfo;
	}
	public void setDeviceVersionInfo(DeviceVersionInfoVO deviceVersionInfo) {
		this.deviceVersionInfo = deviceVersionInfo;
	}
	public List<PeriodResmindVO> getPerodRemindInfo() {
		return perodRemindInfo;
	}
	public void setPerodRemindInfo(List<PeriodResmindVO> perodRemindInfo) {
		this.perodRemindInfo = perodRemindInfo;
	}
	
}
