package com.appscomm.sport.dao.impl;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.appscomm.sport.dao.AccessTokenDAO;
import com.appscomm.sport.model.AccessToken;
import com.appscomm.sport.model.AccessTokenId;

@Repository
public class AccessTokenDAOImpl implements AccessTokenDAO {
	private static final Log LOG = LogFactory.getLog(AccessTokenDAOImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public AccessToken getAccessTokenById(AccessTokenId accessTokenId) {
		String sql = "select person_id as personId, app_id as appId, token as token, gen_time as genTime " +
				" from access_token where person_id=? and app_id=? limit 1";
		AccessToken token = null;
		try {
			//Map<String, Object> map = jdbcTemplate.queryForMap(sql, accessTokenId.getPersonId(), accessTokenId.getAppId());
			token = jdbcTemplate.queryForObject(sql, AccessToken.class, accessTokenId.getPersonId(), accessTokenId.getAppId());
			if(token!=null){
				token.setAccessTokenId(accessTokenId);
			}
		} catch (Exception e) {
			LOG.error(e);
			return null;
		}

		return token;
	}

	@Override
	public AccessToken getAccessTokenByToken(String token) {
		String sql = "select person_id as personId, app_id as appId, token as token, gen_time as genTime " +
				" from access_token where token=? limit 1";
		AccessToken at = null;
		try {
			Map<String, Object> map = jdbcTemplate.queryForMap(sql, token);
			AccessTokenId ati = new AccessTokenId();
			ati.setAppId((String) map.get("appId"));
			ati.setPersonId(((BigInteger) map.get("personId")).longValue());
			
			at = new AccessToken();
			at.setAccessTokenId(ati);
			at.setToken((String) map.get("token"));
			at.setGenTime((Date) map.get("genTime"));
		}catch(Exception e){
			LOG.error(e);
			return null;
		}
		return  at;
	}

	@Override
	public AccessToken getAccessTokenByPersonId(long personId) {
		String sql = "select person_id as personId, app_id as appId, token as token, gen_time as genTime " +
				" from access_token where person_id=? limit 1";
		
		AccessToken at = null;
		try{
			Map<String, Object> map = jdbcTemplate.queryForMap(sql, personId);
			AccessTokenId ati = new AccessTokenId();
			ati.setAppId((String) map.get("appId"));
			ati.setPersonId(((BigInteger) map.get("personId")).longValue());
			
			at = new AccessToken();
			at.setAccessTokenId(ati);
			at.setToken((String) map.get("token"));
			at.setGenTime((Date) map.get("genTime"));
		}catch(Exception e){
			LOG.error(e);
			return null;
		}
		return  at;
	}

	@Override
	public void save(AccessToken accessToken) {
		//String sql = "insert into access_token(person_id, app_id, token, gen_time) values(:personId, :appId, :token, :genTime) ";
		String sql = "insert ignore into access_token(person_id, app_id, token, gen_time) values(?, ?, ?, ?) ";
		jdbcTemplate.update(sql, accessToken.getAccessTokenId().getPersonId(), accessToken.getAccessTokenId().getAppId(), accessToken.getToken(), accessToken.getGenTime());
	}
	public void update(AccessToken accessToken){
		//String sql = "insert into access_token(person_id, app_id, token, gen_time) values(:personId, :appId, :token, :genTime) ";
				String sql = "update access_token   set token=?,gen_time=? where person_id=? and app_id=?";
				jdbcTemplate.update(sql, accessToken.getToken(),accessToken.getGenTime(),accessToken.getAccessTokenId().getPersonId(), accessToken.getAccessTokenId().getAppId());
	}
	@Override
	public AccessToken getByToken(String kronozToken) {
		String sql = "select person_id as personId, app_id as appId, token as token, gen_time as genTime " +
				" from access_token where token=? limit 1";
		
		AccessToken at = null;
		try{
			Map<String, Object> map = jdbcTemplate.queryForMap(sql, kronozToken);
			AccessTokenId ati = new AccessTokenId();
			ati.setAppId((String) map.get("appId"));
			ati.setPersonId(((BigInteger) map.get("personId")).longValue());
			at = new AccessToken();
			at.setAccessTokenId(ati);
			at.setToken((String) map.get("token"));
			at.setGenTime((Date) map.get("genTime"));
		}catch(Exception e){
			LOG.error(e);
			return null;
		}
		return  at;
	}
}
