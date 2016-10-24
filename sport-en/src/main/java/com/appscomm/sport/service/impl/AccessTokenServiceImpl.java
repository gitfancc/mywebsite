package com.appscomm.sport.service.impl;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appscomm.sport.dao.AdminUserDao;
import com.appscomm.sport.dao.AccessTokenDAO;
import com.appscomm.sport.dao.impl.AccessTokenDAOImpl;
import com.appscomm.sport.model.AccessToken;
import com.appscomm.sport.model.AccessTokenId;
import com.appscomm.sport.model.SysParamVO;
import com.appscomm.sport.service.AccessTokenService;
import com.appscomm.sport.utils.AESEncrypty;
import com.appscomm.sport.vo.BaseResponseT;

@Service
public class AccessTokenServiceImpl implements AccessTokenService{

	   private static final Log LOG = LogFactory.getLog(AccessTokenServiceImpl.class);
	    //默认的AES密钥，与原sport项目同
	    final static String defaultKey = "C1511E2A29B3C721EC1E4E1C0A396559";
		@Autowired
		private AccessTokenDAO accessTokenDAO;
		@Autowired
		private AdminUserDao adminUserDao;

		/*@Autowired
		private PersonalInfoService personalInfoService;*/
		 /**
	     * 构造函数
	     */
	    public AccessTokenServiceImpl()
	    {
	        super();
	        LOG.debug("AccessTokenServiceImpl 初始化");
	    }
	    @Override
	    public String generateKronozToken(long personId,String appId){
	    	AccessTokenId accessTokenId = new AccessTokenId();
			accessTokenId.setPersonId(personId);;
			accessTokenId.setAppId(appId);
			AccessToken accessToken = accessTokenDAO.getAccessTokenById(accessTokenId);
			if(accessToken !=null){
				accessToken.setToken(DigestUtils.sha256Hex(personId+appId+System.currentTimeMillis()));
				accessToken.setGenTime(new Date());
				accessTokenDAO.update(accessToken);
			}else {
				accessToken = new AccessToken();
				String token = DigestUtils.sha256Hex(personId+appId+System.currentTimeMillis());
				accessToken.setAccessTokenId(accessTokenId);
				accessToken.setToken(token);
				accessToken.setGenTime(new Date());
				accessTokenDAO.save(accessToken);
			}
			return accessToken.getToken();
	    }
	    public int validKronozToken(String kronozToken){
	    	int result = 1;
	    	AccessToken accessToken = accessTokenDAO.getByToken(kronozToken);
			if(accessToken!=null){
				SysParamVO sysParamVO = adminUserDao.queryKronozTokenLimit();
				Date tokenValidDate = DateUtils.addHours(accessToken.getGenTime(), Integer.valueOf(sysParamVO.getParamValue()));
				if(tokenValidDate.before(new Date())){
					LOG.info("***************kronozToken Expired:"+kronozToken);
				}else {
					result = 809;
				}
			}else {
				result = 810;
			}
			return result;
	    }
		@Override
		public BaseResponseT<String> retrieveToken(long personId, String appId,
				String applyCode) {
			BaseResponseT<String> resp = new BaseResponseT<String> ();
			String pwd = "";
			/*try {
				pwd = this.getUserPwd(personId, appId, applyCode);
			} catch (Exception e) {
				LOG.error("从apply code中获取用户密码错误"+personId+":"+appId+":"+applyCode,e);
				resp.setError(ErrorNum.APPLY_CODE_ILLEGAL, "申请码有误，请检查");
				return resp;
			}
			
			PersonalInfo pi = personalInfoService.getEntityById(personId);
			if(pi==null){
				resp.setError(ErrorNum.USER_NOT_EXIST, "用户不存在");
				return resp;
			}
			
			if(!checkUserPwd(pwd,pi.getPassword()))
			{
				LOG.warn("apply code中的密码错误："+personId+":"+appId+":"+applyCode);
				resp.setError(ErrorNum.APPLY_CODE_ILLEGAL, "申请码有误，请检查");
				return resp;
			}*/
			AccessTokenId accessTokenId = new AccessTokenId();
			accessTokenId.setPersonId(personId);;
			accessTokenId.setAppId(appId);
			//AccessToken accessToken = accessTokenDAO.getEntityById(accessTokenId);
			AccessToken accessToken = accessTokenDAO.getAccessTokenById(accessTokenId);
			if(accessToken==null)
			{
				accessToken = new AccessToken();
				String token = DigestUtils.sha256Hex(personId+appId+System.currentTimeMillis());
				accessToken.setAccessTokenId(accessTokenId);
				accessToken.setToken(token);
				accessToken.setGenTime(new Date());
				
				accessTokenDAO.save(accessToken);
			}
			
			resp.setData(accessToken.getToken());
			
			return resp;
		}
		
		/**获取申请码
		 * @param personId
		 * @param appId
		 * @param pwd
		 * @return
		 */
		public String getApplyCode(long personId,String appId,String pwd)
		{
			String key = DigestUtils.md5Hex(personId+appId);
			AESEncrypty aes = new AESEncrypty(key);
			return aes.encrypt(pwd);
		}
		
		
		/**判断用户的密码是否正确
		 * @param pwd 用户传入的密码
		 * @param sysPwd 系统记录的密码
		 * @return
		 */
		private boolean checkUserPwd(String pwd,String sysPwd)
		{
			AESEncrypty aes = new AESEncrypty(defaultKey);
			return sysPwd.equals(aes.encrypt(pwd));
		}
		
		
		/**对applyCode进行解密获取用户的原有密码
		 * @param applyCode
		 * @return
		 */
		private String getUserPwd(long personId, String appId,
				String applyCode)
		{
			String key = DigestUtils.md5Hex(personId+appId);
			AESEncrypty aes = new AESEncrypty(key);
			String pwd = aes.decrypt(applyCode);
			return pwd;
		}
		
		@Override
		public AccessToken getAccessTokenByToken(String token) {
		///	return accessTokenDAO.getSigleEntityBySimpleCond(SimpleCond.getNew().eq("token", token).setCacheable(true));
			return accessTokenDAO.getAccessTokenByToken(token);
		}

		@Override
		public AccessToken getTokenById(long personId) {
		///	return accessTokenDAO.getSigleEntityBySimpleCond(SimpleCond.getNew().eq("AccessTokenId.personId", personId).setCacheable(true));
			return accessTokenDAO.getAccessTokenByPersonId(personId);
		}

		public static void main(String[] args) {
			AccessTokenServiceImpl as = new AccessTokenServiceImpl();
		/*	String key = DigestUtils.md5Hex(315+"Apps_ios");
			System.out.println("Key:" + key);
			System.out.println(as.getApplyCode(315, "Apps_ios", "apple123"));*/
			AccessTokenDAOImpl dao =  new AccessTokenDAOImpl();
			AccessToken accessToken = dao.getAccessTokenByToken("34e82a9a58505a89be010eabc644d14b3eeb4f86d74981b5735ff2981950e249");
			System.out.println(accessToken.toString());
			
			//BaseResponseT<String> brs = as.retrieveToken(1L, "Apps_ios", "xxxxx");
			//System.out.println(brs.toString());
		}

}
