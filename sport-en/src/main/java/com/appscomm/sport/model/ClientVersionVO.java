package com.appscomm.sport.model;

import java.io.Serializable;

public class ClientVersionVO implements Serializable{
	private static final long serialVersionUID = -3409008909419775529L;
	private Integer sid;
	private Integer personId;
	private String watchId;
	private String watchType;
	private String clientName;
	private String clientType;
	private String installVersion;
	private String previousVersion;
	private String installTime;
	private String newestVersion;
	private Integer isNeedUpdate;
	private String updateUrl;
	private String updateMessage;
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public String getWatchId() {
		return watchId;
	}
	public void setWatchId(String watchId) {
		this.watchId = watchId;
	}
	public String getWatchType() {
		return watchType;
	}
	public void setWatchType(String watchType) {
		this.watchType = watchType;
	}
	public String getClientType() {
		return clientType;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	public String getInstallVersion() {
		return installVersion;
	}
	public void setInstallVersion(String installVersion) {
		this.installVersion = installVersion;
	}
	public String getPreviousVersion() {
		return previousVersion;
	}
	public void setPreviousVersion(String previousVersion) {
		this.previousVersion = previousVersion;
	}
	public String getInstallTime() {
		return installTime;
	}
	public void setInstallTime(String installTime) {
		this.installTime = installTime;
	}
	public String getNewestVersion() {
		return newestVersion;
	}
	public void setNewestVersion(String newestVersion) {
		this.newestVersion = newestVersion;
	}
	public Integer getIsNeedUpdate() {
		return isNeedUpdate;
	}
	public void setIsNeedUpdate(Integer isNeedUpdate) {
		this.isNeedUpdate = isNeedUpdate;
	}
	public String getUpdateUrl() {
		return updateUrl;
	}
	public void setUpdateUrl(String updateUrl) {
		this.updateUrl = updateUrl;
	}
	public String getUpdateMessage() {
		return updateMessage;
	}
	public void setUpdateMessage(String updateMessage) {
		this.updateMessage = updateMessage;
	}
	@Override
	public String toString() {
		return "ClientVersionVO [sid=" + sid + ", personId=" + personId + ", watchId=" + watchId + ", watchType=" + watchType + ", clientName=" + clientName + ", clientType=" + clientType + ", installVersion=" + installVersion + ", previousVersion="
				+ previousVersion + ", installTime=" + installTime + ", newestVersion=" + newestVersion + ", isNeedUpdate=" + isNeedUpdate + ", updateUrl=" + updateUrl + ", updateMessage=" + updateMessage + "]";
	}
	
}
