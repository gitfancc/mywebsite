package com.appscomm.sport.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appscomm.sport.cache.CacheClient;
import com.appscomm.sport.dao.impl.JdbcTemplate;
import com.appscomm.sport.model.BaseModel;

@Service("baseDao")
public class BaseDao extends JdbcTemplate{
	
	@Autowired
	private CacheClient cacheClient;
	
	public <T> List<T> cList(BaseModel<T> model, String sql4Id, Object... args){
		List<Map<String, Object>> list = queryForList(sql4Id, args);
		List<T> result = new ArrayList<T>();
		for(Map<String, Object> map : list){
			T t = cFind(map.get(model.getIdFieldName()), model);
			result.add(t);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T cFind(Object idValue, BaseModel<T> model){
		String cacheKey = getCacheKey(model.getModelType(), idValue);
		Object result = cacheClient.getCache(cacheKey);
		if(result!=null){
			return (T)result;
		}
		String sql = "select * from "+model.getTableName()+" where "+model.getIdFieldName()+"=?";
		T t = queryForObject(sql, model.getModelType(), idValue);
		if(t!=null){
			cacheClient.setCache(cacheKey, t);
		}
		return t;
	}
	
	public <T> void cDelete(Object idValue, BaseModel<T> model){

		String sql = "delete from "+model.getTableName()+" where "+model.getIdFieldName()+"=?";
		
		delete(sql, idValue);
		deleteCache(idValue, model);
	}
		
	public <T> void deleteCache(Object idValue, BaseModel<T> model){
		String cacheKey = getCacheKey(model.getModelType(), idValue);
		cacheClient.deleteCache(cacheKey);
	}
	
	private <T> String getCacheKey(Class<T> requiredType, Object idValue){
		String cacheKey = requiredType.getName()+"_"+idValue;
		return cacheKey;
	}
}
