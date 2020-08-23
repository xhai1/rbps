package com.java.dao;

import java.util.Map;

import org.mybatis.spring.annotation.Mapper;

@Mapper("tokenDao")
public interface TokenDao<T> {

	public abstract void insertToken(T t);
	
	@SuppressWarnings("unchecked")
	public abstract T findOneToken(Map map);
	
}
