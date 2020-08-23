package com.java.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.java.dao.LogDao;
import com.java.service.LogService;
@Service("logService")
public class LogServiceImpl<T> implements LogService<T> {

	private LogDao<T> dao ;
	public void setDao(LogDao<T> dao) {
		this.dao = dao;
	}
	
	@Override
	public void insertLog(T t) throws Exception {
		dao.insertLog(t);
	}

	@Override
	public List<T> findLog(T t) throws Exception {
		return dao.findLog(t);
	}

	@Override
	public int countLog(T t) throws Exception {
		return dao.countLog(t);
	}

	@Override
	public void deleteLog(Integer logId) throws Exception {
		dao.deleteLog(logId);
	}

	@Override
	public void truncateLog() throws Exception {
		dao.truncateLog();
	}

	

}
