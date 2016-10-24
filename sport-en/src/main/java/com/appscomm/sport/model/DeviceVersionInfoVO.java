package com.appscomm.sport.model;

import java.io.Serializable;

public class DeviceVersionInfoVO  implements Serializable {
	private static final long serialVersionUID = 9079091537588812427L;

	private String watchId;
	private String firmwareName;
	private String firmwareVersion;
	private String previousVersion;
	private String udid;
	
	
	public String getWatchId() {
		return watchId;
	}
	public void setWatchId(String watchId) {
		this.watchId = watchId;
	}
	public String getFirmwareName() {
		return firmwareName;
	}
	public void setFirmwareName(String firmwareName) {
		this.firmwareName = firmwareName;
	}
	public String getFirmwareVersion() {
		return firmwareVersion;
	}
	public void setFirmwareVersion(String firmwareVersion) {
		this.firmwareVersion = firmwareVersion;
	}
	public String getPreviousVersion() {
		return previousVersion;
	}
	public void setPreviousVersion(String previousVersion) {
		this.previousVersion = previousVersion;
	}
	public String getUdid() {
		return udid;
	}
	public void setUdid(String udid) {
		this.udid = udid;
	}
	@Override
	public String toString() {
		return "DeviceVersionInfoVO [watchId=" + watchId + ", firmwareName=" + firmwareName + ", firmwareVersion=" + firmwareVersion + ", previousVersion=" + previousVersion + ", UDID=" + udid + "]";
	}
	
}
