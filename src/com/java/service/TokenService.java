package com.java.service;

import java.util.Map;

/**
 *@author: wangq
 *@date: 2015-8-15上午09:03:39
 *@version:
 *@description：
 */
public interface TokenService<T> {

	public abstract void insertToken(T t);
	
	@SuppressWarnings("unchecked")
	public abstract T findOneToken(Map map);
	
}
