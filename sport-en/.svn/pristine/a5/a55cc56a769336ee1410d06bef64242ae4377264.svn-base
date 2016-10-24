package com.appscomm.sport.service.impl;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appscomm.sport.dao.CommonDao;
import com.appscomm.sport.model.SuggestContentVO;
import com.appscomm.sport.model.VersionCheckVO;
import com.appscomm.sport.service.CommonService;

/**
 * @author zxh
 * @date 2013-11-05
 */

@Service("commonService")
public class ComServiceImpl implements CommonService {
	@Autowired
	CommonDao daoInstance;
	private static Log logger = LogFactory.getLog(ComServiceImpl.class);
	
	private static String bytesToHexString(byte[] src, int n){   
	    StringBuilder stringBuilder = new StringBuilder("");   
	    if (src == null || /*src.length*/n <= 0) {   
	        return null;   
	    }   
	    for (int i = 0; i < /*src.length*/n; i++) {   
	        int v = src[i] & 0xFF;   
	        String hv = Integer.toHexString(v);   
	        if (hv.length() < 2) {   
	            stringBuilder.append(0);   
	        }   
	        stringBuilder.append(hv);   
	    }   
	   
	    return  stringBuilder.toString();
	}	
 	private static byte[] hexStringToBytes(String hexString) {   
	    if (hexString == null || hexString.equals("")) {   
	        return null;   
	    }   
	    hexString = hexString.toUpperCase();   
	    int length = hexString.length() / 2;   
	    char[] hexChars = hexString.toCharArray();   
	    byte[] d = new byte[length];   
	    for (int i = 0; i < length; i++) {   
	        int pos = i * 2;   
	        d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));   
	    }   
	    return d;   
	}  	
 	
	private static byte charToByte(char c) {   
	    return (byte) "0123456789ABCDEF".indexOf(c);   
	}   	
	
	private  boolean isNumeric(String str){ 
	    Pattern pattern = Pattern.compile("[0-9]*"); 
	    return pattern.matcher(str).matches();    
	 } 
	@Override
	public Object request(Map<String, Object> kv){
		String deviceType = null;
		String deviceName = null;
		
		if(kv.containsKey("deviceName")){
			deviceName = ((String[])kv.get("deviceName"))[0];
		}
		if(kv.containsKey("deviceType")){
			deviceType = ((String[])kv.get("deviceType"))[0];
		}
		String version = ((String[])kv.get("version"))[0];
		
		VersionCheckVO obj = daoInstance.qryVersion(deviceName, deviceType);
		if(obj != null){
			String serverVersion = obj.getVersion();
			String[] serverSubVersion = serverVersion.split("\\.");//分割以.隔开的版本信息
			
			String[] clientSubVersion = version.split("\\.");
			
			obj.setIsneedupdate(false);
			for(int i = 0; i < serverSubVersion.length && i < clientSubVersion.length; i++){
				if(isNumeric(serverSubVersion[i]) && isNumeric(clientSubVersion[i])){
					int serV = Integer.parseInt(serverSubVersion[i]);
					int cliV = Integer.parseInt(clientSubVersion[i]);
					if(serV != cliV){
						obj.setIsneedupdate(serV > cliV);
						break;
					}
				}
			}
			//obj.setIsneedupdate(obj.getVersion().compareTo(version) > 0);
		}
		return obj;
	}
	
	@Override
	public Boolean savePicData(String userId, String hexValue, String filePath){
		Boolean result = false;
	    try{
	    	byte[] out = hexStringToBytes(hexValue);	    	
	    	
	    	//String contextpath = "f:";//ServletActionContext.getRequest().getContextPath();
	    	String filename = "userid_" + userId + ".jpg";
	    	//filename = filename.replaceAll("\\/", "\\\\");
	    	logger.info("real img path:" + filePath + "/" + filename);
	    	FileOutputStream fos = new FileOutputStream(filePath + "/" + filename);
	    	fos.write(out);	    	
	    	fos.close();
	    	result = daoInstance.savePicUrl(userId, filename) > 0;//保存文件名到用户表image_url字段里去
	    }catch(Exception e){
	    	logger.error(e);
    	}
	    return result;
	}
	
	@Override
	public String getPicData(String userId, boolean ifSmall){
		String hexStr = "";
		byte[] buff = new byte[8192];
		int n = 0;
		try{
	    	String contextpath = "f:";//ServletActionContext.getRequest().getContextPath();
	    	String filename = contextpath + "/uploadpic/" + userId + (ifSmall ? "_small" : "_big") + ".jpg";
			FileInputStream fis = new FileInputStream(filename);
			while((n = fis.read(buff)) != -1){
				hexStr += bytesToHexString(buff, n);
			}
			fis.close();
		}catch(Exception e){
			logger.error(e);
		}
		return hexStr;
	}
	
	@Override
	public int suggestContent(SuggestContentVO suggest) {
		return daoInstance.setSuggestContent(suggest);
	}
	@Override
	public VersionCheckVO getFirmware(String deviceType, String deviceName) {
		return daoInstance.getFirmware(deviceType, deviceName);
	}

}
