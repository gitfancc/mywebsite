package com.appscomm.sport.model;

public interface BaseModel<T> {
	/**
	 * 实体类对应的表名
	 * @return
	 */
	public String getTableName();
	/**
	 * 主键字段名
	 * @return
	 */
	public String getIdFieldName();
	
	/**
	 * 实体类的class
	 * @return
	 */
	public Class<T> getModelType();
}

