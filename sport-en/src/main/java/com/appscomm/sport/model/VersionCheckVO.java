package com.appscomm.sport.model;

public class VersionCheckVO {
	private String devicename;
	private String version;
	private String updateurl;
	private int updatetype;
	private String updatemessage;
	private boolean isneedupdate;
	private Long FileCRCSize;
	
	public String getDevicename() {
		return devicename;
	}
	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getUpdateurl() {
		return updateurl;
	}
	public void setUpdateurl(String updateurl) {
		this.updateurl = updateurl;
	}
	public int getUpdatetype() {
		return updatetype;
	}
	public void setUpdatetype(int updatetype) {
		this.updatetype = updatetype;
	}
	public String getUpdatemessage() {
		return updatemessage;
	}
	public void setUpdatemessage(String updatemessage) {
		this.updatemessage = updatemessage;
	}
	public boolean isIsneedupdate() {
		return isneedupdate;
	}
	public void setIsneedupdate(boolean isneedupdate) {
		this.isneedupdate = isneedupdate;
	}
	public Long getFileCRCSize() {
		return FileCRCSize;
	}
	public void setFileCRCSize(Long fileCRCSize) {
		FileCRCSize = fileCRCSize;
	}
	
}
