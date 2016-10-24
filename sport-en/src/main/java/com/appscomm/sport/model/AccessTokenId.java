package com.appscomm.sport.model;

import java.io.Serializable;

public class AccessTokenId implements Serializable{
	private static final long serialVersionUID = -8956762444683350720L;
	private long personId;//用户id
	private String appId;//由接口分配的appid
	public long getPersonId() {
		return personId;
	}
	public void setPersonId(long personId) {
		this.personId = personId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appId == null) ? 0 : appId.hashCode());
		result = prime * result + (int) (personId ^ (personId >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccessTokenId other = (AccessTokenId) obj;
		if (appId == null) {
			if (other.appId != null)
				return false;
		} else if (!appId.equals(other.appId))
			return false;
		if (personId != other.personId)
			return false;
		return true;
	}
	
}
