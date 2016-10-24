/**
 * Copyright appscomm.cn 2013. All rights reserved.
 *
 * @createDate 2013-9-16
 */
package com.appscomm.sport.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.appscomm.sport.model.DistrictVO;
import com.appscomm.sport.service.CityService;
import com.opensymphony.xwork2.Action;

/**
 *  地市区控制类 
 *	
 *  qindf create by 2013-9-16
 *
 */
public class CityAction extends BaseAction {

	private static final long serialVersionUID = -2595198043148720741L;
	private static Logger log = Logger.getLogger(CityAction.class);
	private   List<DistrictVO> countryList = null;
	private static List<DistrictVO> frCountryList = null;
	private static List<DistrictVO> enCountryList = null;
	@Autowired
	private CityService cityService;
	private int districtId;
	private String distric;
	private List<DistrictVO> result;
	private String language = "english";
	
	public String load(){  
		try{
			result = cityService.getCitys(districtId);
		}catch(Exception e){
			log.info("加载地市异常====:"+e);
			result = null;
		}
		return Action.SUCCESS;
	}

	public String loadByName(){
		try{
			result = cityService.getCitys(districtId);
		}catch(Exception e){
			log.info("加载地市异常:"+e);
			result = null;
		}
		return Action.SUCCESS;
	}
	
	public int getDistrictId() {
		return districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	public List<DistrictVO> getResult() {
		return result;
	}

	public String getDistric() {
		return distric;
	}

	public void setDistric(String distric) {
		this.distric = distric;
	}
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	public void setCountryList(List<DistrictVO> countryList) {
		this.countryList = countryList;
	}

	public void loadCountry(){
		if( language.endsWith("french")){
			if(frCountryList == null){ 
				frCountryList = getCountryList();
			}
			countryList = frCountryList;
		}else{
			if(enCountryList == null){ 
				enCountryList = getCountryList();
			}
			countryList = enCountryList;
		}
		
		/*if(countryList == null){
			//countryList = cityService.getCountry(districtId);
			countryList = getCountryList();
		//	log.info(countryList.toString());
		}*/
		
		super.writeJson(countryList);
	}
	
	private List<DistrictVO> getCountryList(){
		Integer count = Integer.valueOf(getText("country.count"));
		List<DistrictVO> list = new ArrayList<DistrictVO>();
		for(int i=0; i<count; i++){
			DistrictVO district = new DistrictVO();
			String index = "country." + String.valueOf(i);
			String names = getText(index);
			if (names.contains(",")) {
				String[] namess = names.split(",");
				district.setName(namess[0]);
				district.setShortName(namess[1]);
			}
			list.add(district);
		}
		return list;
	}

}
