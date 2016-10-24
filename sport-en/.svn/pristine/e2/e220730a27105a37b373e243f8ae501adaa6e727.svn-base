package com.appscomm.sport.service;

import com.appscomm.sport.model.AccessToken;
import com.appscomm.sport.vo.BaseResponseT;


public interface AccessTokenService {
	/**获取用户的access_toke,access_token为合法认证用户标志<br>
	 * applyCode为加密后的用户密码，加密算法使用AES。密钥为 md5(personId+appId) 后的字符串。
	 * @param personId 用户id
	 * @param appId  应用id
	 * @param applyCode 申请代码
	 * @return
	 */
	BaseResponseT<String> retrieveToken(long personId,
			String appId,
			 String applyCode);
	String generateKronozToken(long personId,String appId);
	int validKronozToken(String kronozToken);
	/**根据token获取AccessToken信息，从而可获取当前请求的用户
	 * @param token
	 * @return
	 */
	AccessToken getAccessTokenByToken(String token);

	
	/**
	*根据personId获取token值 
	* Administrator 
	* 2014-2-13
	* @param 
	* @return   
	* @throws
	 */
	AccessToken getTokenById( long personId);
}
