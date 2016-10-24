/**
 * Copyright appscomm.cn 2013. All rights reserved.
 *
 * @createDate 2013-9-10
 */
package com.appscomm.sport.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 *  个人基本信息实体对象
 *	
 *  qindf create by 2013-9-10
 *
 */
public class PersonVO implements Serializable {

	private static final long serialVersionUID = -333085398567093432L;
	
	private Long registerId;
	private String userName;
	private String nickName;
	private int gender;
	//private Date birthDate;
	private String birthDate="1970-01-01";
	private String telphone;
	private String email;
	private String qq;
	private String weibo;
	private String imgUrl;
	private int countryId=0;
	private int provinceId;
	private int cityId;
	private int areaId;
	private String groupId;
	private String city;
	private Long id;
	private String height;
	private int heightUnit=0;
	private double weight;
	private int weightUnit=0;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getWeibo() {
		return weibo;
	}
	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Long getRegisterId() {
		return registerId;
	}
	public void setRegisterId(Long registerId) {
		this.registerId = registerId;
	}
	public int getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	public String getBirthDate() {
		if(StringUtils.isBlank(this.birthDate)){
			this.birthDate = "1970-01-01";
		}
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		if(StringUtils.isBlank(birthDate)){
			birthDate = "1970-01-01";
		}
		this.birthDate = birthDate;
	}
	//	public Date getBirthDate() {
//		return birthDate;
//	}
//	public void setBirthDate(Date birthDate) {
//		this.birthDate = birthDate;
//	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public int getHeightUnit() {
		return heightUnit;
	}
	public void setHeightUnit(int heightUnit) {
		this.heightUnit = heightUnit;
	}
	public int getWeightUnit() {
		return weightUnit;
	}
	public void setWeightUnit(int weightUnit) {
		this.weightUnit = weightUnit;
	}
	@Override
	public String toString() {
		return "PersonVO [registerId=" + registerId + ", userName=" + userName + ", nickName=" + nickName + ", gender=" + gender + ", birthDate=" + birthDate + ", telphone=" + telphone + ", email=" + email + ", qq=" + qq + ", weibo=" + weibo
				+ ", imgUrl=" + imgUrl + ", countryId=" + countryId + ", provinceId=" + provinceId + ", cityId=" + cityId + ", areaId=" + areaId + ", groupId=" + groupId + ", city=" + city + ", id=" + id + ", height=" + height + ", heightUnit="
				+ heightUnit + ", weight=" + weight + ", weightUnit=" + weightUnit + "]";
	}

}
