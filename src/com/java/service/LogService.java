package com.java.service;

import java.util.List;


/**
 *@author: wangq
 *@date: 2015-8-1下午03:22:25
 *@version:
 *@description：
 */
public interface LogService<T> {

	public abstract void insertLog(T t) throws Exception;
	
	public abstract List<T> findLog(T t) throws Exception;
	
	public abstract int countLog(T t) throws Exception;
	
	public abstract void deleteLog(Integer logId) throws Exception;
	
	public abstract void truncateLog() throws Exception;
}
