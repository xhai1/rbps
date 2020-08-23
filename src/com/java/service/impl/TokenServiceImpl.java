package com.java.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.java.dao.TokenDao;
import com.java.service.TokenService;
@Service("tokenService")
public class TokenServiceImpl<T> implements TokenService<T> {

	private TokenDao<T> dao;
	public void setDao(TokenDao<T> dao) {
		this.dao = dao;
	}
	
	@Override
	public void insertToken(T t) {
		dao.insertToken(t);
	}

	@Override
	public T findOneToken(Map map) {
		return dao.findOneToken(map);
	}
	
}
