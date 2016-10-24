/**
 * Copyright appscomm 2013. All rights reserved.
 *
 * @createDate 2013-8-27
 */
package com.appscomm.sport.dao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 * Jdbc操作辅助类，对Sping jdbctemplate再封装。
 *
 * @author qindf
 *
 */
public class OldJdbcTemplate {
	private ExecutorService executor = Executors.newCachedThreadPool();
	private NamedParameterJdbcTemplate jdbcTemplate;
	private Object lockObject = new Object();
	private int dbType = 0;
	
	public OldJdbcTemplate(DataSource dataSource){
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	/**
	 * 开启新线程批量处理
	 * 
	 * @param sql
	 * @param setter
	 * @return
	 */
	public void batchUpdate(final String sql,
			final BatchPreparedStatementSetter setter) {
		executor.submit(new Callable<int[]>() {
			@Override
			public int[] call() throws Exception {
				synchronized (lockObject) {
					return jdbcTemplate.getJdbcOperations().batchUpdate(sql,
							setter);
				}

			}
		});
	}

	/**
	 * 保存对象
	 * 
	 * @param sql
	 * @param object
	 * @return
	 */
	public int saveObject(String sql, Object object) {
		return jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(
				object));
	}

	public int save(String sql, Map<String, ?> paramMap) {
		return jdbcTemplate.update(sql, paramMap);
	}
	
	/**
	 * 保存对象获取自动生成主键
	 * @param sql
	 * @param object
	 * @return
	 */
	public int saveObjectGetSeq(String sql, Object object){
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(
				object),keyHolder);
		Number generatedKey = keyHolder.getKey();
		if(generatedKey!=null)
			return generatedKey.intValue();
		else
			return 0;
	}

	/**
	 * 更新对象
	 * 
	 * @param sql
	 * @param object
	 * @return
	 */
	public int updateObject(String sql, Object object) {
		return jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(
				object));
	}

	/**
	 * 更新数据
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public int update(String sql, Object... args) {
		return jdbcTemplate.getJdbcOperations().update(sql, args);
	}

	/**
	 * 删除数据
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public int delete(String sql, Object... args) {
		return jdbcTemplate.getJdbcOperations().update(sql, args);
	}

	/**
	 * 查询对象
	 * 
	 * @param <T>
	 * @param sql
	 * @param requiredType
	 *            查询的对象类型
	 * @param args
	 * @return
	 */
	public <T> T queryForObject(String sql, Class<T> requiredType,
			Object... args) {
		try {
			return jdbcTemplate.getJdbcOperations().queryForObject(sql,
					BeanPropertyRowMapper.newInstance(requiredType), args);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	/**
	 * 查询对象
	 * 
	 * @param <T>
	 * @param sql
	 * @param requiredType
	 *            查询的对象类型
	 * @param args
	 * @return
	 */
	public <T> List<T> query(String sql, Class<T> requiredType, Object... args) {
		return jdbcTemplate.getJdbcOperations().query(sql,
				BeanPropertyRowMapper.newInstance(requiredType), args);
	}
	
	public <T> List<T> query(String sql, Class<T> requiredType,  Map<String, ?> paramMap) {
		return jdbcTemplate.query(sql,paramMap,
				BeanPropertyRowMapper.newInstance(requiredType));
	}
	
	/**
	 * 查询数据，返回的键值对Map结构
	 * 
	 * @param sql
	 * @param args
	 * @return key为sql查询返回的列名称，value为对应的值。
	 */
	public Map<String, Object> queryForMap(String sql, Object... args) {
		return jdbcTemplate.getJdbcOperations().queryForMap(sql, args);
	}

	/**
	 * 批量查询数据，返回键值对Map结构
	 * 
	 * @param sql
	 * @param args
	 * @return key为sql查询返回的列名称，value为对应的值。
	 */
	public List<Map<String, Object>> queryForList(String sql, Object... args) {
		return jdbcTemplate.getJdbcOperations().queryForList(sql, args);
	}

	/**
	 * 查询整数
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public int queryForInt(String sql, Object... args) {
		return jdbcTemplate.getJdbcOperations().queryForInt(sql, args);
	}

	/**
	 * 查询长整数
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public long queryForLong(String sql, Object... args) {
		return jdbcTemplate.getJdbcOperations().queryForLong(sql, args);
	}

	/**
	 * 转换数据类型
	 * 
	 * @param object
	 * @return
	 */
	public Long toLong(Object object) {
		if (dbType == 1) {
			return ((BigDecimal) object).longValue();
		} else if (dbType == 2) {
			return (Long) object;
		}
		return null;
	}
}